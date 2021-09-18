/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXNT2;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.D.PFDP0TXNT2;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXNT2 {
	    public static void delete(
            UserFormInterface form,
            String id, int seq) throws SQLException {
        String sql = "DELETE FROM PFTXNT2 WHERE Id=? AND Seq=? ";
        String psName = "TXNT2.D2";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.setParameter(psName, 2, seq);
        form.cmd().db.execUpdate(psName);
    }
    public static void select_All(UserFormInterface form,
            String rsName, String id) throws SQLException {
        String sql = "SELECT * FROM PFTXNT2 WHERE Id=? ORDER BY Seq ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, id);
        form.cmd().db.execQuery(rsName, rsName);
    }
	// Purge temp table
    public static void delete(
            UserFormInterface form, String id) throws SQLException {
        String sql = "DELETE FROM PFTXNT2 WHERE Id=?";
        String psName = "TXNT2.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(
            PFDP0TXNT2 form,
            String id, String[] value) throws SQLException {
        String psName = "TXNT2.I";
        String sql = "INSERT INTO PFTXNT2 (" +
                "Id, Seq, TxnId " +
                ") VALUES(?, NEXT VALUE FOR PFTXNT2SEQ, " +
                "?) ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1, j = 0;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, value[j++]);
        form.cmd().db.execUpdate(psName);
    }
}
