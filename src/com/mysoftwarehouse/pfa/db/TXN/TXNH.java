/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXNH {

    static void createDelete(UserFormInterface form, String id, int txnId)
            throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, id, txnId, HisAct, HisRmk);
    }

    static void createInsert(UserFormInterface form, String id, int txnId)
            throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, id, txnId, HisAct, HisRmk);
    }

    public static void createUpdate(UserFormInterface form, String id, int txnId)
            throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, id, txnId, HisAct, HisRmk);
    }

    static void createDelete_AccountDelete(UserFormInterface form, String id,
            String acc) throws SQLException {
        String HisAct = "D";
        String HisRmk = "Account Deletion";
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "TXNH.create";
        String sql = "INSERT INTO PFTXNH (" +
                "Id, HisSeq, HisDte, HisAct, HisRmk, " +
                "TxnId, TxnTyp, TxnDte, FrmAcc, ToAcc, " +
                "UsrAmt, TxnAmt, RefNo, Dsc, Remark) " +
                "SELECT Id, NEXT VALUE FOR PFTXNHSEQ, ?, ?, ?, " +
                "TxnId, TxnTyp, TxnDte, FrmAcc, ToAcc, " +
                "UsrAmt, TxnAmt, RefNo, Dsc, Remark FROM " +
                "PFTXN WHERE Id=? AND TxnId=(" +
                "SELECT BalTxnId FROM PFACC WHERE Id=? AND Acc=? ) ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.execUpdate(psName);
    }

    private static void create(UserFormInterface form,
            String id, int txnId, String HisAct, String HisRmk)
            throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "TXNH.create";
        String sql = "INSERT INTO PFTXNH (" +
                "Id, HisSeq, HisDte, HisAct, HisRmk, " +
                "TxnId, TxnTyp, TxnDte, FrmAcc, ToAcc, " +
                "UsrAmt, TxnAmt, RefNo, Dsc, Remark) " +
                "SELECT Id, NEXT VALUE FOR PFTXNHSEQ, ?, ?, ?, " +
                "TxnId, TxnTyp, TxnDte, FrmAcc, ToAcc, " +
                "UsrAmt, TxnAmt, RefNo, Dsc, Remark FROM " +
                "PFTXN WHERE Id=? AND TxnId=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.execUpdate(psName);
    }
}
