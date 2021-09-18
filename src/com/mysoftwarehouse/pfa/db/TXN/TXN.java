/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN;

import com.mysoftwarehouse.pfa.AU.PFAP1USR;
import com.mysoftwarehouse.pfa.D.PFDS0TXN;
import com.mysoftwarehouse.pfa.D.PFDP1TXNT2;
import com.mysoftwarehouse.pfa.G.PFGP0ACC;
import com.mysoftwarehouse.pfa.G.PFGP1ACC;
import com.mysoftwarehouse.pfa.db.DB.DataEnum.TxnTyp;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXN {

    public static String PFTXN_TXNID = "PFTXN_TXNID";
    //
    public static void update_AccountRename(
            PFGP0ACC form, String id, String FrmAcc, String ToAcc)
            throws SQLException {
        String psName = "TXN.U1";
        String sql = "UPDATE PFTXN SET FrmAcc=? WHERE Id=? AND FrmAcc=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToAcc);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, FrmAcc);
        form.cmd().db.execUpdate(psName);

        sql = "UPDATE PFTXN SET ToAcc=? WHERE Id=? AND ToAcc=? ";
        form.cmd().db.setStatement(psName, sql);
        i = 1;
        form.cmd().db.setParameter(psName, i++, ToAcc);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, FrmAcc);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(PFAP1USR form, String id) throws SQLException {
        String sql = "DELETE FROM PFTXN WHERE Id=? ";
        String psName = "TXN.D1";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(PFDS0TXN form,
            String rsName, String id, int txnId) throws SQLException {
        String sql = "SELECT * FROM PFTXN WHERE Id=? AND TxnId=? ";
        String psName = "TXN.S2";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.execQuery(psName, rsName);
        return form.cmd().db.next(rsName);
    }
    public static boolean selectRN(PFDP1TXNT2 form,
            String rsName, String id, int txnId) throws SQLException {
        String sql = "SELECT * FROM PFTXN WHERE Id=? AND TxnId=? and TxnTyp in ('R', 'N') ";
        String psName = "TXN.S3";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.execQuery(psName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static boolean useACC(PFGP1ACC form,
            String id, String acc) throws SQLException {
        //need to ignore the balance transaction
        String sql = "SELECT * FROM PFTXN WHERE Id=? AND TxnTyp<>? " +
                "AND (FrmAcc=? OR ToAcc=?) ";
        String psName = "TXN.S1";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, TxnTyp.B.code);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.execQuery(psName, psName);
        return form.cmd().db.next(psName);
    }
}
