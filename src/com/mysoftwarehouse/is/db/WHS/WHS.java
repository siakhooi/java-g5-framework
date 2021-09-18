/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.WHS;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.is.A.ISAP0;
import com.mysoftwarehouse.is.B.ISBL0WHS;
import com.mysoftwarehouse.is.B.ISBP2WHS;
import com.mysoftwarehouse.is.B.ISBS0WHS;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ng Siak Hooi
 */
public class WHS {

    public static void delete(ISBP2WHS form, String Whs) throws SQLException {
        WHSH.createDelete(form, Whs);

        String sql = "DELETE FROM ISWHS WHERE Whs=? ";
        String psName = "WHS.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.execUpdate(psName);
    }

    public static int getCount(ISAP0 form) {
        try {
            String rsName = "WHS.count";
            String sql = "SELECT COUNT(*) cnt FROM ISWHS";
            form.cmd().db.setStatement(rsName, sql);
            form.cmd().db.execQuery(rsName, rsName);
            if (form.cmd().db.next(rsName)) {
                return form.cmd().db.getInteger(rsName, "cnt");
            }
        } catch (SQLException ex) {
        }
        return 0;
    }

    public static boolean hasWHS(ISBS0WHS form, String Whs) throws SQLException {
        String rsName = "WHS.has";
        return select(form, rsName, Whs);
//        String sql = "SELECT * FROM ISWHS WHERE Whs=? ";
//        form.cmd().db.setStatement(rsName, sql);
//        form.cmd().db.setParameter(rsName, 1, Whs);
//        form.cmd().db.execQuery(rsName, rsName);
//        return form.cmd().db.next(rsName);
    }

    public static void select_All(ISBL0WHS form, String rsName) throws SQLException {
        String sql = "SELECT * FROM ISWHS ORDER BY Whs";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static boolean select(UserFormInterface form, String rsName,
            String Whs) throws SQLException {
        String sql = "SELECT * FROM ISWHS WHERE Whs=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void insert(ISBS0WHS form, String Whs, String Nme,
            String CstTyp, String Remark) throws SQLException {
        String sql = "INSERT INTO ISWHS (" +
                "Whs, Nme, CstTyp, Remark" +
                ") VALUES(?, ?, ?, ?)";
        String psName = "WHS.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, CstTyp);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);
        WHSH.createInsert(form, Whs);
    }

    public static void update(ISBS0WHS form, String Whs, String Nme,
            String CstTyp, String Remark) throws SQLException {
        String sql = "UPDATE ISWHS SET " +
                "Nme=?, CstTyp=?, Remark=? " +
                "WHERE Whs=? ";
        String psName = "WHS.U";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, CstTyp);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.execUpdate(psName);
        WHSH.createUpdate(form, Whs);
    }
}
