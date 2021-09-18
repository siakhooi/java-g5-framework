/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACC;

import com.mysoftwarehouse.pfa.F.PFFJ0ACCM;
import com.mysoftwarehouse.pfa.db.ACCM.ACCM1;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACC_FJ0ACCM {
//balance sheet
    public static void select(
            PFFJ0ACCM form,
            String rsName, String id, Calendar period)
            throws SQLException {
        int y = form.cmd().cal.getYear(period);
        int m = form.cmd().cal.getMonth(period);
        
        ACCM1.prepareIncomeAccountValue(form, id, y, m);
        ACCM1.prepareRetainedIncomeAccountValue(form, id, y, m);
        
        String sql = "SELECT " +
                "PFACC.Acc, PFACC.Nme, PFACC.AccTyp, " +
                "PFACT.Nme AccTypNme, PFACCM.TtlAmt " +
                "FROM PFACT, PFACC, PFACCM " +
                "WHERE PFACC.AccTyp=PFACT.AccTyp " +
                "AND PFACC.Id=PFACCM.Id " +
                "AND PFACC.Acc=PFACCM.Acc " +
                "AND PFACC.Id=? " +
                "AND PFACT.AccTyp IN ('A', 'L', 'C') " +
                "AND (PFACCM.Year<? " +
                "OR (PFACCM.Year=? AND PFACCM.Month<=? )) " +
                "ORDER BY PFACT.OrderBS, PFACC.Acc ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, id);
        form.cmd().db.setParameter(rsName, i++, y);
        form.cmd().db.setParameter(rsName, i++, y);
        form.cmd().db.setParameter(rsName, i++, m);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
