/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PY;

import com.mysoftwarehouse.bs.KM.M.BSMJ0PY;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PY_MJ0PY {

    public static void select(BSMJ0PY form, String rsName, String Cmp,
            String FrmPayTyp, String ToPayTyp) throws SQLException {
        String sql = "SELECT * FROM BSPY ";
        boolean hasFrmPayTyp = !form.cmd().data.isNull(FrmPayTyp);
        boolean hasToPayTyp = !form.cmd().data.isNull(ToPayTyp);
        sql += "WHERE Cmp=? ";

        if (hasFrmPayTyp) {
            sql += "AND PayTyp>=? ";
        }
        if (hasToPayTyp) {
            sql += "AND PayTyp<=? ";
        }
        sql += "ORDER BY Cmp, PayTyp ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        if (hasFrmPayTyp) {
            form.cmd().db.setParameter(rsName, i++, FrmPayTyp);
        }
        if (hasToPayTyp) {
            form.cmd().db.setParameter(rsName, i++, ToPayTyp);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
