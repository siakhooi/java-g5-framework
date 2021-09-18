/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACC;

import com.mysoftwarehouse.pfa.F.PFFJ0ACC;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACC_FJ0ACC {
//profit and loss
    public static void select(
            PFFJ0ACC form,
            String rsName, String id,
            String reportType, Calendar year, Calendar yearmonth) throws SQLException {
        boolean monthType = "M".equals(reportType);
        int y = 0, m = 0;
        if (monthType) {
            y = form.cmd().cal.getYear(yearmonth);
            m = form.cmd().cal.getMonth(yearmonth);
            selectMonth(form, rsName, id, y, m);
        } else {
            y = form.cmd().cal.getYear(year);
            selectYear(form, rsName, id, y);
        }
    }

    private static void selectMonth(
            PFFJ0ACC form,
            String rsName, String id, int y, int m) 
            throws SQLException {
        String sql = "SELECT " +
                "PFACC.Acc, PFACC.Nme, PFACC.AccTyp, " +
                "PFACT.Nme AccTypNme, PFACCM.TtlAmt, " +
                "PFACCM.TtlDbt, PFACCM.TtlCrt " +
                "FROM PFACCM, PFACC, PFACT " +
                "WHERE PFACC.AccTyp=PFACT.AccTyp " +
                "AND PFACC.Id=PFACCM.Id " +
                "AND PFACC.Acc=PFACCM.Acc " +
                "AND PFACC.Id=? " +
                "AND PFACT.AccTyp IN ('I', 'E') " +
                "AND PFACCM.Year=? " +
                "AND PFACCM.Month=? " +
                "ORDER BY PFACT.OrderPL, PFACC.Acc ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, id);
        form.cmd().db.setParameter(rsName, i++, y);
        form.cmd().db.setParameter(rsName, i++, m);
        form.cmd().db.execQuery(rsName, rsName);
    }

    private static void selectYear(
            PFFJ0ACC form, 
            String rsName, String id, int y) throws SQLException {
        String sql = "SELECT " +
                "PFACC.Acc, PFACC.Nme, PFACC.AccTyp, " +
                "PFACT.Nme AccTypNme, PFACCY.TtlAmt, " +
                "PFACCY.TtlDbt, PFACCY.TtlCrt " +
                "FROM PFACCY, PFACC, PFACT " +
                "WHERE PFACC.AccTyp=PFACT.AccTyp " +
                "AND PFACC.Id=PFACCY.Id " +
                "AND PFACC.Acc=PFACCY.Acc " +
                "AND PFACC.Id=? " +
                "AND PFACT.AccTyp IN ('I', 'E') " +
                "AND PFACCY.Year=? " +
                "ORDER BY PFACT.OrderPL, PFACC.Acc ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, id);
        form.cmd().db.setParameter(rsName, i++, y);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
