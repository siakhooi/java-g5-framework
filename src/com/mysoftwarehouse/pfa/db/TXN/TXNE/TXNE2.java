/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN.TXNE;

import com.mysoftwarehouse.pfa.db.DB.DataEnum;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXNE2 {

    public static void deleteTXNE(
            UserFormInterface form, String id, int txnId) throws SQLException {
        TXNE3.decrease(form, id, txnId);

        //delete TXNE
        String psName = "TXNE2.D2";
        String sql = "DELETE FROM PFTXNE WHERE Id=? AND TxnId=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.execUpdate(psName);
    }

    private static void updateTXNTxnAmt(UserFormInterface form,
            String id, int txnId, BigDecimal TxnAmt) throws SQLException {
        String psI2 = "TXNE2.I2";
        String sql = "UPDATE PFTXN SET TxnAmt=? WHERE Id=? AND TxnId=? ";
        form.cmd().db.setStatement(psI2, sql);
        int i = 1;
        form.cmd().db.setParameter(psI2, i++, TxnAmt);
        form.cmd().db.setParameter(psI2, i++, id);
        form.cmd().db.setParameter(psI2, i++, txnId);
        form.cmd().db.execUpdate(psI2);
    }

    public static void insertTXNE(UserFormInterface form,
            String id, int txnId, String TxnTyp, Calendar TxnDte,
            String FrmAcc, String ToAcc, BigDecimal UsrAmt) throws SQLException {
        Record_PFTXNE recPrevTXNE = new Record_PFTXNE();
        recPrevTXNE.readPrevious(form, id, FrmAcc, TxnDte, txnId);

        BigDecimal TxnAmt = UsrAmt;
        if (!TxnTyp.equals(DataEnum.TxnTyp.N.code)) {
            TxnAmt = UsrAmt.subtract(recPrevTXNE.AcuAmt);
        }
        updateTXNTxnAmt(form, id, txnId, TxnAmt);

        //insert TXNE
//FROM ACCOUNT
        Record_PFACC recAcc = new Record_PFACC();
        recAcc.select(form, id, FrmAcc);

        AmountCalculator newAmt = new AmountCalculator();
        newAmt.calculateFromAccount(recAcc.isDebitType(), TxnAmt);
        AcuAmountCalculator newAcuAmt = new AcuAmountCalculator();
        newAcuAmt.calculate(
                recPrevTXNE.AcuAmt,
                recPrevTXNE.YearAcuAmt,
                recPrevTXNE.MonthAcuAmt,
                recPrevTXNE.TxnDte,
                TxnDte,
                newAmt.Amt);

        String psI4 = "TXNE.I4";
        String sql = "INSERT INTO PFTXNE (Id, TxnId, Acc, TxnDte, " +
                "Dbt, Crt, Amt, AcuAmt, YearAcuAmt, MonthAcuAmt " +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        form.cmd().db.setStatement(psI4, sql);
        int i = 1;
        form.cmd().db.setParameter(psI4, i++, id);
        form.cmd().db.setParameter(psI4, i++, txnId);
        form.cmd().db.setParameter(psI4, i++, FrmAcc);
        form.cmd().db.setParameter(psI4, i++, TxnDte);
        form.cmd().db.setParameter(psI4, i++, newAmt.Dbt);
        form.cmd().db.setParameter(psI4, i++, newAmt.Crt);
        form.cmd().db.setParameter(psI4, i++, newAmt.Amt);
        form.cmd().db.setParameter(psI4, i++, newAcuAmt.AcuAmt);
        form.cmd().db.setParameter(psI4, i++, newAcuAmt.YearAcuAmt);
        form.cmd().db.setParameter(psI4, i++, newAcuAmt.MonthAcuAmt);
        form.cmd().db.execUpdate(psI4);

//TO ACCOUNT
        recPrevTXNE.readPrevious(form, id, ToAcc, TxnDte, txnId);
        recAcc.select(form, id, ToAcc);
        newAmt.calculateToAccount(recAcc.isDebitType(), newAmt.Crt, newAmt.Dbt);
        newAcuAmt.calculate(
                recPrevTXNE.AcuAmt,
                recPrevTXNE.YearAcuAmt,
                recPrevTXNE.MonthAcuAmt,
                recPrevTXNE.TxnDte,
                TxnDte,
                newAmt.Amt);

        i = 1;
        form.cmd().db.setParameter(psI4, i++, id);
        form.cmd().db.setParameter(psI4, i++, txnId);
        form.cmd().db.setParameter(psI4, i++, ToAcc);
        form.cmd().db.setParameter(psI4, i++, TxnDte);
        form.cmd().db.setParameter(psI4, i++, newAmt.Dbt);
        form.cmd().db.setParameter(psI4, i++, newAmt.Crt);
        form.cmd().db.setParameter(psI4, i++, newAmt.Amt);
        form.cmd().db.setParameter(psI4, i++, newAcuAmt.AcuAmt);
        form.cmd().db.setParameter(psI4, i++, newAcuAmt.YearAcuAmt);
        form.cmd().db.setParameter(psI4, i++, newAcuAmt.MonthAcuAmt);
        form.cmd().db.execUpdate(psI4);

        //adjust after
        TXNE3.increase(form, id, txnId);
    }
}

