/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SY;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.F.BSFL0SY;
import com.mysoftwarehouse.bs.CG.F.BSFL1SY;
import com.mysoftwarehouse.bs.CG.F.BSFP0SY;
import com.mysoftwarehouse.bs.CG.F.BSFS0SY;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class SY {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSSY WHERE Cmp=? ";
        String psName = "SY.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSFP0SY form, String Cmp, String PayTyp) throws SQLException {
        SYH.createDelete(form, Cmp, PayTyp);

        String sql = "DELETE FROM BSSY WHERE Cmp=? AND PayTyp=?";
        String psName = "SY.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PayTyp);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasSY(UserFormInterface form, String Cmp, String PayTyp) throws SQLException {
        String sql = "SELECT * FROM BSSY WHERE Cmp=? AND PayTyp=?";
        String rsName = "SY.has";
        form.cmd().db.setStatement(rsName, sql);
        int i = 1;
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, PayTyp);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static boolean select(BSFS0SY form, String rsName, String Cmp, String PayTyp) throws SQLException {
        String sql = "SELECT * FROM BSSY WHERE Cmp=? AND PayTyp=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PayTyp);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSFL0SY form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSSY WHERE Cmp=? ORDER BY PayTyp";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void select_All(BSFL1SY form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSSY WHERE Cmp=? ORDER BY PayTyp";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(BSFS0SY form, String Cmp, String PayTyp, String Nme) throws SQLException {
        String sql = "UPDATE BSSY SET " +
                "Nme=? WHERE Cmp=? AND PayTyp=? ";
        String psName = "SY.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PayTyp);
        form.cmd().db.execUpdate(psName);

        SYH.createUpdate(form, Cmp, PayTyp);
    }

    public static void insert(UserFormInterface form, String Cmp, String PayTyp, String Nme) throws SQLException {
        String sql = "INSERT INTO BSSY ( " +
                "Cmp, PayTyp, Nme) VALUES(?, ?, ?) ";
        String psName = "SY.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PayTyp);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.execUpdate(psName);

        SYH.createInsert(form, Cmp, PayTyp);
    }
}
