/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACCT;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.G.PFGP0ACCT;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACCT {

    public static void insert(
            PFGP0ACCT form,
            String id, String[] value) throws SQLException {
        String psName = "ACCT.I";
        String sql = "INSERT INTO PFACCT (" +
                "Id, Seq, Acc, Nme, AccTyp, BalAmt, BalDte, Remark" +
                ") VALUES(?, NEXT VALUE FOR PFACCTSEQ, ?, ?, ?, ?, ?, ?) ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1, j = 0;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, value[j++]);
        form.cmd().db.setParameter(psName, i++, value[j++]);
        form.cmd().db.setParameter(psName, i++, value[j++]);
        form.cmd().db.setParameter(psName, i++, value[j++]);
        form.cmd().db.setParameter(psName, i++, value[j++]);
        form.cmd().db.setParameter(psName, i++, value[j++]);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(
            UserFormInterface form, String id) throws SQLException {
        String sql = "DELETE FROM PFACCT WHERE Id=?";
        String psName = "ACCT.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(
            UserFormInterface form,
            String id, int seq) throws SQLException {
        String sql = "DELETE FROM PFACCT WHERE Id=? AND Seq=? ";
        String psName = "ACCT.D2";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.setParameter(psName, 2, seq);
        form.cmd().db.execUpdate(psName);
    }

    public static void select_All(
            UserFormInterface form,
            String rsName, String id) throws SQLException {
        String sql = "SELECT * FROM PFACCT WHERE Id=? ORDER BY Seq ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, id);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
