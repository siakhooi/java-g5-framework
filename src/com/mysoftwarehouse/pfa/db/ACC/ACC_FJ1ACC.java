/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACC;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.db.DB.DataEnum.AccTyp;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACC_FJ1ACC {

    public static void select(
            UserFormInterface form,
            String rsName, String id,
            String accType, String reportType,
            Calendar year, Calendar yearmonth)
            throws SQLException {
        boolean monthType = "M".equals(reportType);
        boolean bsType = AccTyp.isBSType(accType);
        int y = 0, m = 0;
        if (monthType) {
            y = form.cmd().cal.getYear(yearmonth);
            m = form.cmd().cal.getMonth(yearmonth);
            if (bsType) {
                selectMonthBS(form, rsName, id, accType, y, m);
            } else {
                selectMonthPL(form, rsName, id, accType, y, m);
            }
        } else {
            y = form.cmd().cal.getYear(year);
            if (bsType) {
                selectYearBS(form, rsName, id, accType, y);
            } else {
                selectYearPL(form, rsName, id, accType, y);
            }
        }
    }

    private static void selectMonthBS(
            UserFormInterface form,
            String rsName, String id,
            String accType, int y, int m) throws SQLException {
        String sql = "SELECT " +
                "PFACC.Acc, PFACC.Nme, SUM(PFACCM.TtlAmt) AS Amt " +
                "FROM PFACC, PFACCM " +
                "WHERE PFACC.Id=PFACCM.Id " +
                "AND PFACC.Acc=PFACCM.Acc " +
                "AND PFACC.Id=? " +
                "AND PFACC.AccTyp=? " +
                "AND (PFACCM.Year<? " +
                "OR ( PFACCM.Year=? AND PFACCM.Month<=?)) " +
                "GROUP BY PFACC.Acc, PFACC.Nme " +
                "ORDER BY PFACC.Acc ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, id);
        form.cmd().db.setParameter(rsName, i++, accType);
        form.cmd().db.setParameter(rsName, i++, y);
        form.cmd().db.setParameter(rsName, i++, y);
        form.cmd().db.setParameter(rsName, i++, m);
        form.cmd().db.execQuery(rsName, rsName);
    }

    private static void selectYearBS(
            UserFormInterface form,
            String rsName, String id, String accType, int y)
            throws SQLException {
        String sql = "SELECT " +
                "PFACC.Acc, PFACC.Nme, SUM(PFACCY.TtlAmt) AS Amt " +
                "FROM PFACC, PFACCY " +
                "WHERE PFACC.Id=PFACCY.Id " +
                "AND PFACC.Acc=PFACCY.Acc " +
                "AND PFACC.Id=? " +
                "AND PFACC.AccTyp=? " +
                "AND PFACCY.Year<=? " +
                "GROUP BY PFACC.Acc, PFACC.Nme " +
                "ORDER BY PFACC.Acc ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, id);
        form.cmd().db.setParameter(rsName, i++, accType);
        form.cmd().db.setParameter(rsName, i++, y);
        form.cmd().db.execQuery(rsName, rsName);
    }

    private static void selectMonthPL(
            UserFormInterface form,
            String rsName, String id, String accType, int y, int m)
            throws SQLException {
        String sql = "SELECT " +
                "PFACC.Acc, PFACC.Nme, PFACCM.TtlAmt AS Amt " +
                "FROM PFACC, PFACCM " +
                "WHERE PFACC.Id=PFACCM.Id " +
                "AND PFACC.Acc=PFACCM.Acc " +
                "AND PFACC.Id=? " +
                "AND PFACC.AccTyp=? " +
                "AND PFACCM.Year=? " +
                "AND PFACCM.Month=? " +
                "ORDER BY PFACC.Acc ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, id);
        form.cmd().db.setParameter(rsName, i++, accType);
        form.cmd().db.setParameter(rsName, i++, y);
        form.cmd().db.setParameter(rsName, i++, m);
        form.cmd().db.execQuery(rsName, rsName);
    }

    private static void selectYearPL(
            UserFormInterface form,
            String rsName, String id, String accType, int y)
            throws SQLException {
        String sql = "SELECT " +
                "PFACC.Acc, PFACC.Nme, PFACCY.TtlAmt AS Amt " +
                "FROM PFACC, PFACCY " +
                "WHERE PFACC.Id=PFACCY.Id " +
                "AND PFACC.Acc=PFACCY.Acc " +
                "AND PFACC.Id=? " +
                "AND PFACC.AccTyp=? " +
                "AND PFACCY.Year=? " +
                "ORDER BY PFACC.Acc ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, id);
        form.cmd().db.setParameter(rsName, i++, accType);
        form.cmd().db.setParameter(rsName, i++, y);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
