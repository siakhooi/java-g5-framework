/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN;

import com.mysoftwarehouse.pfa.db.TXN.TXNE.TXNE2;
import com.mysoftwarehouse.pfa.db.DB.DataEnum;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.D.PFDS0TXN;
import com.mysoftwarehouse.pfa.G.PFGP1ACC;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXN2 {

    public static void delete_AccountBalance(
            PFGP1ACC form, String id, String acc) throws SQLException {
        String psName = "TXN2.D2";
        String sql = "SELECT BalTxnId FROM PFACC WHERE Id=? AND Acc=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.execQuery(psName, psName);
        form.cmd().db.first(psName);
        int txnId = form.cmd().db.getInteger(psName, "BalTxnId");
        delete(form, id, txnId);
    }

    public static void delete(UserFormInterface form,
            String id, int txnId) throws SQLException {
        TXNH.createDelete(form, id, txnId);

        TXNE2.deleteTXNE(form, id, txnId);

        String sql = "DELETE FROM PFTXN WHERE Id=? AND TxnId=? ";
        String psName = "TXN2.D";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(UserFormInterface form,
            String id, int txnId, String TxnTyp,
            Calendar TxnDte, String FrmAcc, BigDecimal UsrAmt,
            String ToAcc, String txnRefNo,
            String txnDesc, String txnRemark) throws SQLException {
        String psName = "TXN2.I";
        String sql = "INSERT INTO PFTXN (" +
                "Id, TxnId, TxnTyp, TxnDte, FrmAcc, ToAcc, " +
                "UsrAmt, TxnAmt, RefNo, Dsc, Remark) VALUES (" +
                "?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?) ";
        form.cmd().db.setStatement(psName, sql);
        BigDecimal txnAmt = BigDecimal.ZERO;
        if (TxnTyp.equals(DataEnum.TxnTyp.N)) {
            txnAmt = UsrAmt;
        }
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, TxnTyp);
        form.cmd().db.setParameter(psName, i++, TxnDte);
        form.cmd().db.setParameter(psName, i++, FrmAcc);
        form.cmd().db.setParameter(psName, i++, ToAcc);
        form.cmd().db.setParameter(psName, i++, UsrAmt);
        form.cmd().db.setParameter(psName, i++, txnAmt);
        form.cmd().db.setParameter(psName, i++, txnRefNo);
        form.cmd().db.setParameter(psName, i++, txnDesc);
        form.cmd().db.setParameter(psName, i++, txnRemark);
        form.cmd().db.execUpdate(psName);
        form.cmd().db.getPreparedStatement(psName).close();

        TXNE2.insertTXNE(form, id, txnId, TxnTyp, TxnDte, FrmAcc, ToAcc, UsrAmt);

        TXNH.createInsert(form, id, txnId);
    }

    public static void update(PFDS0TXN form,
            String id, int txnId, String TxnTyp,
            Calendar TxnDte, String FrmAcc, BigDecimal UsrAmt,
            String ToAcc, String txnRefNo,
            String txnDesc, String txnRemark) throws SQLException {
        String psName = "TXN2.U";
        String sql = "SELECT * FROM PFTXN " +
                "WHERE Id=? " +
                "AND TxnId=? " +
                "AND TxnTyp=? " +
                "AND TxnDte=? " +
                "AND FrmAcc=? " +
                "AND ToAcc=? " +
                "AND UsrAmt=?  ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, TxnTyp);
        form.cmd().db.setParameter(psName, i++, TxnDte);
        form.cmd().db.setParameter(psName, i++, FrmAcc);
        form.cmd().db.setParameter(psName, i++, ToAcc);
        form.cmd().db.setParameter(psName, i++, UsrAmt);
        form.cmd().db.execQuery(psName, psName);
        if (!form.cmd().db.next(psName)) {
//            //got changes
            delete(form, id, txnId);
            insert(form, id, txnId, TxnTyp, TxnDte, FrmAcc, UsrAmt,
                    ToAcc, txnRefNo, txnDesc, txnRemark);
        } else {
            sql = "UPDATE PFTXN SET " +
                    "RefNo=?, " +
                    "Dsc=?, " +
                    "Remark=? " +
                    "WHERE Id=? " +
                    "AND TxnId=? ";
            form.cmd().db.setStatement(psName, sql);
            i = 1;
            form.cmd().db.setParameter(psName, i++, txnRefNo);
            form.cmd().db.setParameter(psName, i++, txnDesc);
            form.cmd().db.setParameter(psName, i++, txnRemark);
            form.cmd().db.setParameter(psName, i++, id);
            form.cmd().db.setParameter(psName, i++, txnId);
            form.cmd().db.execUpdate(psName);
        }
        TXNH.createUpdate(form, id, txnId);
    }
}
