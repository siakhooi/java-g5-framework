/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACC;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.AU.PFAS0USR;
import com.mysoftwarehouse.pfa.conf.DBVALUE;
import com.mysoftwarehouse.pfa.db.CFG.CFG;
import com.mysoftwarehouse.pfa.db.DB.DataEnum.TxnTyp;
import com.mysoftwarehouse.pfa.db.TXN.TXN2;
import com.mysoftwarehouse.pfa.db.CFG.TxnId;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACC1 {

    public static void insert(UserFormInterface form,
            String id,
            String acc, String Nme, String AccTyp, String SysTyp,
            BigDecimal BalAmt,
            Calendar BalDte, String Remark, String status, boolean showInMain) throws SQLException {
        //insert ACC
        int txnId = TxnId.nextTxnId(form, id);
        String psName = "ACC.I1";
        String sql = "INSERT INTO PFACC (" +
                "Id, Acc, Nme, AccTyp, AccGrp, SysTyp, BalAmt, " +
                "BalDte, BalTxnId, Remark, Status, ShowInMain " +
                ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, AccTyp);
        form.cmd().db.setParameter(psName, i++, AccTyp);
        form.cmd().db.setParameter(psName, i++, SysTyp);
        form.cmd().db.setParameter(psName, i++, BalAmt);
        form.cmd().db.setParameter(psName, i++, BalDte);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, status);
        form.cmd().db.setParameter(psName, i++,
                form.cmd().data.boolean2String(showInMain));
        form.cmd().db.execUpdate(psName);

        //create TXN
        updateACC_createBalTxn(form, id, txnId, acc, BalDte, BalAmt);

        // create ACCH
        ACCH.createInsert(form, id, acc);
    }

    public static void insertSystemACC(
            PFAS0USR form,
            String id, String acc,
            String Nme, String AccTyp, String SysTyp,
            Calendar BalDte, String Remark, String status, boolean showInMain) throws SQLException {
        //insert ACC
        int txnId = DBVALUE.NO_TXNID;
        BigDecimal BalAmt = BigDecimal.ZERO;
        String psName = "ACC.I1";
        String sql = "INSERT INTO PFACC (" +
                "Id, Acc, Nme, AccTyp, AccGrp, SysTyp, BalAmt, " +
                "BalDte, BalTxnId, Remark, Status, ShowInMain " +
                ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, AccTyp);
        form.cmd().db.setParameter(psName, i++, AccTyp);
        form.cmd().db.setParameter(psName, i++, SysTyp);
        form.cmd().db.setParameter(psName, i++, BalAmt);
        form.cmd().db.setParameter(psName, i++, BalDte);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, status);
        form.cmd().db.setParameter(psName, i++,
                form.cmd().data.boolean2String(showInMain));
        form.cmd().db.execUpdate(psName);

        // create ACCH
        ACCH.createInsert(form, id, acc);
    }

    public static void updateACC(UserFormInterface form, String id,
            String acc, String Nme, BigDecimal BalAmt,
            Calendar BalDte, String Remark, String status, boolean showInMain) throws SQLException {
        String rsName = "ACC.S4";
        ACC.select(form, rsName, id, acc);
        BigDecimal bd = form.cmd().db.getBigDecimal(rsName, "BalAmt");
        Calendar ca = form.cmd().cal.Timestamp2Calendar(
                form.cmd().db.getTimestamp(rsName, "BalDte"));
        int txnId = form.cmd().db.getInteger(rsName, "BalTxnId");
        boolean dateChanged = !ca.equals(BalDte);
        boolean amtChanged = (bd.compareTo(BalAmt) != 0);

        String psName1 = "ACC.U5";
        String sql1 = "UPDATE PFACC " +
                "SET Nme=?, Remark=?, Status=?, ShowInMain=? " +
                "WHERE Id=? AND Acc=? ";
        form.cmd().db.setStatement(psName1, sql1);
        int i = 1;
        form.cmd().db.setParameter(psName1, i++, Nme);
        form.cmd().db.setParameter(psName1, i++, Remark);
        form.cmd().db.setParameter(psName1, i++, status);
        form.cmd().db.setParameter(psName1, i++, form.cmd().data.boolean2String(showInMain));
        form.cmd().db.setParameter(psName1, i++, id);
        form.cmd().db.setParameter(psName1, i++, acc);
        form.cmd().db.execUpdate(psName1);

        if (dateChanged || amtChanged) {
            TXN2.delete(form, id, txnId);
            updateACC_createBalTxn(form, id, txnId, acc, BalDte, BalAmt);
            update(form, id, acc, BalDte, BalAmt);
        }
        ACCH.createUpdate(form, id, acc);
    }

    private static void update(UserFormInterface form,
            String id, String acc, Calendar BalDte, BigDecimal BalAmt)
            throws SQLException {
        String psName1 = "ACC.U6";
        String sql1 = "UPDATE PFACC " +
                "SET BalAmt=?, BalDte=? " +
                "WHERE Id=? AND Acc=? ";
        form.cmd().db.setStatement(psName1, sql1);
        int i = 1;
        form.cmd().db.setParameter(psName1, i++, BalAmt);
        form.cmd().db.setParameter(psName1, i++, BalDte);
        form.cmd().db.setParameter(psName1, i++, id);
        form.cmd().db.setParameter(psName1, i++, acc);
        form.cmd().db.execUpdate(psName1);
    }

    private static void updateACC_createBalTxn(
            UserFormInterface form,
            String id, int txnId, String acc,
            Calendar BalDte, BigDecimal BalAmt) throws SQLException {
        String capAcc = form.cmd().global.texts.get(
                CFG.PFCFG_CAPITAL_ACCOUNT);
        String txnDesc = DBVALUE.ACCOUNT_BAL_TXN_DESC;
        String txnRefNo = "";
        String txnRemark = "";

        TXN2.insert(form, id, txnId, TxnTyp.B.code,
                BalDte, acc, BalAmt,
                capAcc, txnRefNo,
                txnDesc, txnRemark);
    }
}
