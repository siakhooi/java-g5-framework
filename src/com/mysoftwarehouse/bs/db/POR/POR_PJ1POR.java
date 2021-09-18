/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.POR;

import com.mysoftwarehouse.bs.P.BSPJ1POR;
import com.mysoftwarehouse.util.CalendarTool;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class POR_PJ1POR {

    public static void select(BSPJ1POR form, String rsName, String Cmp,
            Calendar FrmDte, Calendar ToDte) throws SQLException {
        boolean hasFrmDte = FrmDte != null;
        boolean hasToDte = ToDte != null;
        Calendar f = null, t = null;
        if (hasFrmDte) {
            f = CalendarTool.copy(FrmDte);
            f = CalendarTool.getDate(f);
        }
        if (hasToDte) {
            t = CalendarTool.copy(ToDte);
            t.add(Calendar.DAY_OF_MONTH, 1);
            t = CalendarTool.getDate(t);
        }

        String sql = "SELECT * FROM ";

        sql += "(SELECT Itm, SUM(TtlAmt) TtlAmt, Sum(Qty) TtlQty FROM " +
                "BSPOR A, BSPORD B " +
                "WHERE A.Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND A.PorNo=B.PorNo ";
        if (hasFrmDte) {
            sql += "AND PorDte>=? ";
        }
        if (hasToDte) {
            sql += "AND PorDte<? ";
        }
        sql += "GROUP BY Itm ) AA, ";
        sql += "BSPI BB ";
        sql += "WHERE BB.Cmp=? " +
                "AND BB.Itm=AA.Itm ";

        sql += "ORDER BY AA.TtlQty DESC ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        if (hasFrmDte) {
            form.cmd().db.setParameter(rsName, i++, f);
        }
        if (hasToDte) {
            form.cmd().db.setParameter(rsName, i++, t);
        }
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
