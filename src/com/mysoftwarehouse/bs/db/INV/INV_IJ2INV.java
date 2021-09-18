/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.mysoftwarehouse.bs.HJ.I.BSIJ2INV;
import com.mysoftwarehouse.util.CalendarTool;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class INV_IJ2INV {

    public static void select(BSIJ2INV form, String rsName, String Cmp,
            Calendar FrmDte, Calendar ToDte) throws SQLException {

        String sql = "SELECT B.Nme CusNme, * FROM BSINV A, BSCUS B ";
        boolean hasFrmDte = FrmDte != null;
        boolean hasToDte = ToDte != null;
        sql += "WHERE Cmp=? ";
        sql += "AND A.Cmp=B.Cmp ";
        sql += "AND A.Cus=B.Cus ";
        if (hasFrmDte) {
            sql += "AND InvDte>=? ";
        }
        if (hasToDte) {
            sql += "AND InvDte<? ";
        }
        sql += "ORDER BY Cmp, InvDte ";

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
