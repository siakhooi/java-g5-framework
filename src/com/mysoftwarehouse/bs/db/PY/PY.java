/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PY;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.M.BSML0PY;
import com.mysoftwarehouse.bs.KM.M.BSML1PY;
import com.mysoftwarehouse.bs.KM.M.BSMP0PY;
import com.mysoftwarehouse.bs.KM.M.BSMS0PY;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PY {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPY WHERE Cmp=? ";
        String psName = "PY.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSMP0PY form, String Cmp, String PayTyp) throws SQLException {
        PYH.createDelete(form, Cmp, PayTyp);

        String sql = "DELETE FROM BSPY WHERE Cmp=? AND PayTyp=? ";
        String psName = "PY.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PayTyp);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasPY(BSMS0PY form, String Cmp, String PayTyp) throws SQLException {
        String sql = "SELECT * FROM BSPY WHERE Cmp=? AND PayTyp=?";
        String rsName = "PY.has";
        form.cmd().db.setStatement(rsName, sql);
        int i = 1;
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, PayTyp);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static boolean select(BSMS0PY form, String rsName, String Cmp, String PayTyp) throws SQLException {
        String sql = "SELECT * FROM BSPY WHERE Cmp=? AND PayTyp=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PayTyp);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSML0PY form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSPY WHERE Cmp=? ORDER BY PayTyp";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void select_All(BSML1PY form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSPY WHERE Cmp=? ORDER BY PayTyp";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(BSMS0PY form, String Cmp, String PayTyp, String Nme) throws SQLException {
        String sql = "UPDATE BSPY SET " +
                "Nme=? WHERE Cmp=? AND PayTyp=? ";
        String psName = "PY.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PayTyp);
        form.cmd().db.execUpdate(psName);

        PYH.createUpdate(form, Cmp, PayTyp);
    }

    public static void insert(UserFormInterface form, String Cmp, String PayTyp, String Nme) throws SQLException {
        String sql = "INSERT INTO BSPY ( " +
                "Cmp, PayTyp, Nme) VALUES(?, ?, ?) ";
        String psName = "PY.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PayTyp);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.execUpdate(psName);

        PYH.createInsert(form, Cmp, PayTyp);
    }
}
