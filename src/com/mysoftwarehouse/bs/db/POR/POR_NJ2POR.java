/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.POR;

import com.mysoftwarehouse.bs.NO.N.BSNJ2POR;
import com.mysoftwarehouse.util.CalendarTool;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class POR_NJ2POR {

    public static void select(BSNJ2POR form, String rsName, String Cmp, Calendar FrmDte, Calendar ToDte)
            throws SQLException {

        String sql = "SELECT B.Nme SupNme, * FROM BSPOR A, BSSUP B ";
        boolean hasFrmDte = FrmDte != null;
        boolean hasToDte = ToDte != null;
        sql += "WHERE Cmp=? ";
        sql += "AND A.Cmp=B.Cmp ";
        sql += "AND A.Sup=B.Sup ";
        if (hasFrmDte) {
            sql += "AND PorDte>=? ";
        }
        if (hasToDte) {
            sql += "AND PorDte<? ";
        }
        sql += "ORDER BY Cmp, PorDte, PorNo ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        if (hasFrmDte) {
            Calendar f = CalendarTool.copy(FrmDte);
            f = CalendarTool.getDate(f);
            form.cmd().db.setParameter(rsName, i++, f);
        }
        if (hasToDte) {
            Calendar t = CalendarTool.copy(ToDte);
            t.add(Calendar.DAY_OF_MONTH, 1);
            t = CalendarTool.getDate(t);
            form.cmd().db.setParameter(rsName, i++, t);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
