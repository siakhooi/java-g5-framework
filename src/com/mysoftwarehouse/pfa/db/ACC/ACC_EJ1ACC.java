/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACC;

import com.mysoftwarehouse.pfa.E.PFEJ1ACC;
import com.mysoftwarehouse.pfa.db.DB.DataEnum;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACC_EJ1ACC {

    public static void select(
            PFEJ1ACC form,
            String rsName, String id,
            String frmAcc, String toAcc,
            String reportType, Calendar year, Calendar yearmonth)
            throws SQLException {
        boolean monthType = "M".equals(reportType);
        int y = 0, m = 0;
        if (monthType) {
            y = form.cmd().cal.getYear(yearmonth);
            m = form.cmd().cal.getMonth(yearmonth);
            selectMonth(form, rsName, id, frmAcc, toAcc, y, m);
        } else {
            y = form.cmd().cal.getYear(year);
            selectYear(form, rsName, id, frmAcc, toAcc, y);
        }
    }

    private static void selectMonth(
            PFEJ1ACC form,
            String rsName, String id,
            String frmAcc, String toAcc, int y, int m)
            throws SQLException {
        boolean hasFrmAcc = !form.cmd().data.isNull(frmAcc);
        boolean hasToAcc = !form.cmd().data.isNull(toAcc);
        String sql = "SELECT " +
                "PFACC.Acc, PFACC.Nme, PFACC.AccTyp, " +
                "PFACCM.TtlDbt, PFACCM.TtlCrt " +
                "FROM PFACCM, PFACC, PFACT " +
                "WHERE PFACC.AccTyp=PFACT.AccTyp " +
                "AND PFACC.Id=PFACCM.Id " +
                "AND PFACC.Acc=PFACCM.Acc " +
                "AND PFACC.Id=? " +
                "AND PFACT.AccTyp<>? " +
                "AND PFACCM.Year=? " +
                "AND PFACCM.Month=? ";
        if (hasFrmAcc) {
            sql += " AND PFACC.Acc>=? ";
        }
        if (hasToAcc) {
            sql += " AND PFACC.Acc<=? ";
        }
        sql += " ORDER BY PFACT.OrderFull, PFACC.Acc ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, id);
        form.cmd().db.setParameter(rsName, i++, DataEnum.AccTyp.C.code);
        form.cmd().db.setParameter(rsName, i++, y);
        form.cmd().db.setParameter(rsName, i++, m);
        if (hasFrmAcc) {
            form.cmd().db.setParameter(rsName, i++, frmAcc);
        }
        if (hasToAcc) {
            form.cmd().db.setParameter(rsName, i++, toAcc);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }

    private static void selectYear(
            PFEJ1ACC form,
            String rsName, String id,
            String frmAcc, String toAcc, int y)
            throws SQLException {
        boolean hasFrmAcc = !form.cmd().data.isNull(frmAcc);
        boolean hasToAcc = !form.cmd().data.isNull(toAcc);
        String sql = "SELECT " +
                "PFACC.Acc, PFACC.Nme, PFACC.AccTyp, " +
                "PFACCY.TtlDbt, PFACCY.TtlCrt " +
                "FROM PFACCY, PFACC, PFACT " +
                "WHERE PFACC.AccTyp=PFACT.AccTyp " +
                "AND PFACC.Id=PFACCY.Id " +
                "AND PFACC.Acc=PFACCY.Acc " +
                "AND PFACC.Id=? " +
                "AND PFACT.AccTyp<>? " +
                "AND PFACCY.Year=? ";
        if (hasFrmAcc) {
            sql += " AND PFACC.Acc>=? ";
        }
        if (hasToAcc) {
            sql += " AND PFACC.Acc<=? ";
        }
        sql += " ORDER BY PFACT.OrderFull, PFACC.Acc ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, id);
        form.cmd().db.setParameter(rsName, i++, DataEnum.AccTyp.C.code);
        form.cmd().db.setParameter(rsName, i++, y);
        if (hasFrmAcc) {
            form.cmd().db.setParameter(rsName, i++, frmAcc);
        }
        if (hasToAcc) {
            form.cmd().db.setParameter(rsName, i++, toAcc);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
