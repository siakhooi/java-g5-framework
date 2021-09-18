/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN.TXNE;

import com.mysoftwarehouse.pfa.db.TXN.TXNH;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.db.ACCM.ACCM2;
import com.mysoftwarehouse.pfa.db.ACCY.ACCY2;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXNE_NextStopUpdater {

    static void updateNextStop(UserFormInterface form, String id, String acc, Calendar TxnDte, int TxnId, BigDecimal amt) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static void updateNextStop(UserFormInterface form,
            String id, String acc, int original_y, int original_m,
            Calendar nextTxnDate, int nextTxnId, BigDecimal amt)
            throws SQLException {

        //update TXN.TxnAmt
        String psName = "NSU.1";
        String sql = "UPDATE PFTXN SET " +
                "TxnAmt=TxnAmt+? " +
                "WHERE Id=? " +
                "AND TxnId=? ";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, nextTxnId);
        form.cmd().db.execUpdate(psName);

        TXNH.createUpdate(form, id, nextTxnId);

        //update TXNE FrmAcc
        Record_PFACC recAcc = new Record_PFACC();
        recAcc.select(form, id, acc);
        AmountCalculator newAmt = new AmountCalculator();
        newAmt.calculateFromAccount(recAcc.isDebitType(), amt);

        updateReconSideTXNE(form, id, acc, original_y, original_m,
                nextTxnDate, nextTxnId, newAmt);

        //update TXNE ToAcc
        Record_PFTXN recTxn = new Record_PFTXN();
        recTxn.select(form, id, nextTxnId);
        recAcc.select(form, id, recTxn.ToAcc);

        newAmt.calculateToAccount(recAcc.isDebitType(), newAmt.Crt, newAmt.Dbt);

        updateTheOtherTXNE(form, id, recTxn.ToAcc,
                nextTxnDate, nextTxnId, newAmt);

        TXNE_Adjustor.adjustAfter(form, id, recTxn.ToAcc,
                nextTxnDate, nextTxnId, newAmt.Amt);
    }

    private static void updateTheOtherTXNE(UserFormInterface form,
            String id, String acc, Calendar txnDte, int txnId,
            AmountCalculator ca) throws SQLException {

        TXNE_NetAdjustmentCalculator cna = new TXNE_NetAdjustmentCalculator();
        cna.loadCurrent(form, id, acc, txnId);
        cna.calculateNet(ca.Dbt, ca.Crt);

        int y = form.cmd().cal.getYear(txnDte);
        int m = form.cmd().cal.getMonth(txnDte);
        ACCM2.adjust(form, id, acc, ca.Amt,
                cna.netAdjustDbt, cna.netAdjustCrt, 0, y, m);
        ACCY2.adjust(form, id, acc, ca.Amt,
                cna.netAdjustDbt, cna.netAdjustCrt, 0, y);

        //update TXNE.Dbt, Crt, Amt
        String psName = "NSU.2";
        String sql = "UPDATE PFTXNE SET " +
                "Dbt=Dbt+?, " +
                "Crt=Crt+?, " +
                "Amt=Amt+?, " +
                "AcuAmt=AcuAmt+?, " +
                "YearAcuAmt=YearAcuAmt+?, " +
                "MonthAcuAmt=MonthAcuAmt+? " +
                "WHERE Id=? " +
                "AND TxnId=? " +
                "AND Acc=? ";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, cna.netAdjustDbt);
        form.cmd().db.setParameter(psName, i++, cna.netAdjustCrt);
        form.cmd().db.setParameter(psName, i++, ca.Amt);
        form.cmd().db.setParameter(psName, i++, ca.Amt);
        form.cmd().db.setParameter(psName, i++, ca.Amt);
        form.cmd().db.setParameter(psName, i++, ca.Amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.execUpdate(psName);
    }

    private static void updateReconSideTXNE(UserFormInterface form,
            String id, String acc, int original_y, int original_m,
            Calendar txnDte, int txnId, AmountCalculator ca)
            throws SQLException {

        TXNE_NetAdjustmentCalculator cna = new TXNE_NetAdjustmentCalculator();
        cna.loadCurrent(form, id, acc, txnId);
        cna.calculateNet(ca.Dbt, ca.Crt);

        int y = form.cmd().cal.getYear(txnDte);
        int m = form.cmd().cal.getMonth(txnDte);
        ACCM2.adjust(form, id, acc, ca.Amt,
                cna.netAdjustDbt, cna.netAdjustCrt, 0, y, m);
        ACCY2.adjust(form, id, acc, ca.Amt,
                cna.netAdjustDbt, cna.netAdjustCrt, 0, y);

        //update TXNE.Dbt, Crt, Amt
        String psName = "NSU.3";
        String sql = "UPDATE PFTXNE SET " +
                "Dbt=Dbt+?, " +
                "Crt=Crt+?, " +
                "Amt=Amt+? " +
                "WHERE Id=? " +
                "AND TxnId=? " +
                "AND Acc=? ";

        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, cna.netAdjustDbt);
        form.cmd().db.setParameter(psName, i++, cna.netAdjustCrt);
        form.cmd().db.setParameter(psName, i++, ca.Amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.execUpdate(psName);

        if (original_y != y) {
            updateYearAcu(form, id, acc, txnDte, txnId, y, ca.Amt);
            updateMonthAcu(form, id, acc, txnDte, txnId, y, m, ca.Amt);
        } else if (original_m != m) {
            updateMonthAcu(form, id, acc, txnDte, txnId, y, m, ca.Amt);
        }
    }

    private static void updateYearAcu(UserFormInterface form,
            String id, String acc, Calendar txnDte, int txnId, int y,
            BigDecimal Amt) throws SQLException {
        String psName = "NSU.Y";
        String sql = "UPDATE PFTXNE SET " +
                "YearAcuAmt=YearAcuAmt+? " +
                "WHERE Id=? " +
                "AND Acc=? " +
                "AND (TxnDte>? OR (TxnDte=? AND TxnId>=?)) " +
                "AND Year(TxnDte)=? ";

        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, Amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.execUpdate(psName);
    }

    private static void updateMonthAcu(UserFormInterface form,
            String id, String acc, Calendar txnDte, int txnId,
            int y, int m, BigDecimal Amt) throws SQLException {
        String psName = "NSU.M";
        String sql = "UPDATE PFTXNE SET " +
                "MonthAcuAmt=MonthAcuAmt+? " +
                "WHERE Id=? " +
                "AND Acc=? " +
                "AND (TxnDte>? OR (TxnDte=? AND TxnId>=?)) " +
                "AND Year(TxnDte)=? " +
                "AND Month(TxnDte)=? ";

        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, Amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, m);
        form.cmd().db.execUpdate(psName);
    }
}
