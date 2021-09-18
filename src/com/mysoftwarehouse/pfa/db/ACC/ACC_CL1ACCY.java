/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACC;

import com.mysoftwarehouse.pfa.C.PFCL1ACCY;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACC_CL1ACCY {

    public static void select(
            PFCL1ACCY form,
            String rsName, String id, int year)
            throws SQLException {
        String sql = "SELECT " +
                "PFACC.Acc, PFACC.Nme, PFACC.AccTyp, " +
                "PFACC.Status, PFACC.ShowInMain, PFACC.SysTyp, " +
                "PFACCY.TxnCnt, PFACCY.TtlDbt, PFACCY.TtlCrt, PFACCY.TtlAmt " +
                "FROM PFACCY, PFACC, PFACT " +
                "WHERE PFACC.AccTyp=PFACT.AccTyp " +
                "AND PFACC.Id=PFACCY.Id " +
                "AND PFACC.Acc=PFACCY.Acc " +
                "AND PFACC.Id=? " +
                "AND PFACCY.Year=? " +
                "AND PFACT.AccTyp IN ('I', 'E') " +
                "AND PFACC.SysTyp='N' " +
                "AND PFACC.ShowInMain='Y' " +
                "ORDER BY PFACT.OrderFull, PFACC.Acc";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, id);
        form.cmd().db.setParameter(rsName, 2, year);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
