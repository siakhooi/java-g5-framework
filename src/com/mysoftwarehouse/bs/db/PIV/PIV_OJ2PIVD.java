/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PIV;

import com.mysoftwarehouse.bs.NO.O.BSOJ2PIVD;
import com.mysoftwarehouse.util.CalendarTool;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PIV_OJ2PIVD {

    public static void select(BSOJ2PIVD form, String rsName, String Cmp,
            Calendar FrmDte, Calendar ToDte) throws SQLException {

        String sql = "SELECT B.Nme SupNme, A.TtlAmt AllTtlAmt, " +
                "C.TtlAmt ItmTtlAmt, D.Nme ItmNme, * FROM " +
                "BSPIV A " +
                "LEFT OUTER JOIN BSPIVD C ON A.Cmp=C.Cmp AND A.PivNo=C.PivNo " +
                "LEFT OUTER JOIN BSPI D ON C.Cmp=D.Cmp AND C.Itm=D.Itm, " +
                "BSSUP B ";
        boolean hasFrmDte = FrmDte != null;
        boolean hasToDte = ToDte != null;
        sql += "WHERE Cmp=? ";
        sql += "AND A.Cmp=B.Cmp ";
        sql += "AND A.Sup=B.Sup ";

        if (hasFrmDte) {
            sql += "AND A.PivDte>=? ";
        }
        if (hasToDte) {
            sql += "AND A.PivDte<? ";
        }
        sql += "ORDER BY A.Cmp, A.PivNo, C.Itm ";

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
