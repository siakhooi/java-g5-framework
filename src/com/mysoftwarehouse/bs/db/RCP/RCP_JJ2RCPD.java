/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.RCP;

import com.mysoftwarehouse.bs.HJ.J.BSJJ2RCPD;
import com.mysoftwarehouse.util.CalendarTool;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class RCP_JJ2RCPD {

    public static void select(BSJJ2RCPD form, String rsName, String Cmp, Calendar FrmDte, Calendar ToDte)
            throws SQLException {

        String sql = "SELECT B.Nme CusNme, A.TtlAmt AllTtlAmt, " +
                "C.TtlAmt ItmTtlAmt, D.Nme ItmNme, * FROM " +
                "BSRCP A LEFT OUTER JOIN BSRCPD C ON A.Cmp=C.Cmp AND A.RcpNo=C.RcpNo " +
                "LEFT OUTER JOIN BSSI D ON C.Cmp=D.Cmp AND C.Itm=D.Itm, BSCUS B ";
        boolean hasFrmDte = FrmDte != null;
        boolean hasToDte = ToDte != null;
        sql += "WHERE Cmp=? ";
        sql += "AND A.Cmp=B.Cmp ";
        sql += "AND A.Cus=B.Cus ";

        if (hasFrmDte) {
            sql += "AND A.RcpDte>=? ";
        }
        if (hasToDte) {
            sql += "AND A.RcpDte<? ";
        }
        sql += "ORDER BY A.Cmp, A.RcpNo, C.Itm ";

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
