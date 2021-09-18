/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.LOC;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.is.A.ISAP0;
import com.mysoftwarehouse.is.B.ISBP2WHS;
import com.mysoftwarehouse.is.E.ISEP0LOC;
import com.mysoftwarehouse.is.E.ISES0LOC;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class LOC {

    public static void delete(ISBP2WHS form, String Whs) throws SQLException {
        String sql = "DELETE FROM ISLOC WHERE Whs=? ";
        String psName = "LOC.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.execUpdate(psName);
    }

    public static int getCount(ISAP0 form) {
        try {
            String rsName = "LOC.count";
            String sql = "SELECT COUNT(*) cnt FROM ISLOC";
            form.cmd().db.setStatement(rsName, sql);
            form.cmd().db.execQuery(rsName, rsName);
            if (form.cmd().db.next(rsName)) {
                return form.cmd().db.getInteger(rsName, "cnt");
            }
        } catch (SQLException ex) {
        }
        return 0;
    }

    public static void insert(ISES0LOC form, String Whs,
            String Loc, String Nme, String Remark) throws SQLException {
        String sql = "INSERT INTO ISLOC ( " +
                "Whs, Loc, Nme, Remark) " +
                "VALUES(?, ?, ?, ?) ";
        String psName = "LOC.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Loc);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);

        LOCH.createInsert(form, Whs, Loc);
    }

    public static void update(ISES0LOC form, String Whs,
            String Loc, String Nme, String Remark) throws SQLException {
        String sql = "UPDATE ISLOC SET " +
                "Nme=?, Remark=? " +
                "WHERE Whs=? AND Loc=? ";
        String psName = "LOC.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Loc);
        form.cmd().db.execUpdate(psName);

        LOCH.createUpdate(form, Whs, Loc);
    }

    public static void select_All(UserFormInterface form, String rsName,
            String Whs) throws SQLException {
        String sql = "SELECT * FROM ISLOC WHERE Whs=? ORDER BY Whs, Loc";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static boolean select(UserFormInterface form, String rsName,
            String Whs, String Loc) throws SQLException {
        String sql = "SELECT * FROM ISLOC WHERE Whs=? AND Loc=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Loc);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static boolean hasLoc(UserFormInterface form, String Whs, String Loc) throws SQLException {
        String rsName = "LOC.has";
        return select(form, rsName, Whs, Loc);
    }

    public static void delete(ISEP0LOC form, String Whs, String Loc) throws SQLException {
        LOCH.createDelete(form, Whs, Loc);

        String sql = "DELETE FROM ISLOC WHERE Whs=? AND Loc=?";
        String psName = "LOC.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.setParameter(psName, 2, Loc);
        form.cmd().db.execUpdate(psName);
    }
}
