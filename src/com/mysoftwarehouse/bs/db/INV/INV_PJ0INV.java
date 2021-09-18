/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.mysoftwarehouse.bs.P.BSPJ0INV;
import com.mysoftwarehouse.util.CalendarTool;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class INV_PJ0INV {

    public static void select(BSPJ0INV form, String rsName, String Cmp,
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
                "BSINV A, BSINVD B " +
                "WHERE A.Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND A.InvNo=B.InvNo ";
        if (hasFrmDte) {
            sql += "AND InvDte>=? ";
        }
        if (hasToDte) {
            sql += "AND InvDte<? ";
        }
        sql += "GROUP BY Itm ) AA, ";
        sql += "BSSI BB ";
        sql += "WHERE BB.Cmp=? " +
                "AND BB.Itm=AA.Itm ";

        sql += "ORDER BY AA.TtlAmt DESC ";

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
