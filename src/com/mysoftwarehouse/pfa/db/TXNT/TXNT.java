/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXNT;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.D.PFDP0TXNT;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXNT {

    public static void insert(
            PFDP0TXNT form,
            String id, String[] value) throws SQLException {
        String psName = "TXNT.I";
        String sql = "INSERT INTO PFTXNT (" +
                "Id, Seq, TxnDte, FrmAcc, ToAcc,  " +
                "RefNo, TxnTyp, UsrAmt, Dsc, Remark" +
                ") VALUES(?, NEXT VALUE FOR PFTXNTSEQ, " +
                "?, ?, ?, ?, ?, ?, ?, ?) ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1, j = 0;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, value[j++]);
        form.cmd().db.setParameter(psName, i++, value[j++]);
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
        String sql = "DELETE FROM PFTXNT WHERE Id=?";
        String psName = "TXNT.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(
            UserFormInterface form,
            String id, int seq) throws SQLException {
        String sql = "DELETE FROM PFTXNT WHERE Id=? AND Seq=? ";
        String psName = "TXNT.D2";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.setParameter(psName, 2, seq);
        form.cmd().db.execUpdate(psName);
    }

    public static void select_All(UserFormInterface form,
            String rsName, String id) throws SQLException {
        String sql = "SELECT * FROM PFTXNT WHERE Id=? ORDER BY Seq ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, id);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
