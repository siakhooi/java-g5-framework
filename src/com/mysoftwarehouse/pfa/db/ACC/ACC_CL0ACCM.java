/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACC;

import com.mysoftwarehouse.pfa.C.PFCL0ACCM;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACC_CL0ACCM {

    public static void select(
            PFCL0ACCM form,
            String rsName, String id, int year, int month)
            throws SQLException {

        String sql = "SELECT " +
                "PFACC.Acc, PFACC.Nme, PFACC.AccTyp, " +
                "PFACC.Status, PFACC.ShowInMain, PFACC.SysTyp, " +
                "PFACCM.TxnCnt, PFACCM.TtlDbt, PFACCM.TtlCrt, PFACCM.TtlAmt " +
                "FROM PFACCM, PFACC, PFACT " +
                "WHERE PFACT.AccTyp=PFACC.AccTyp " +
                "AND PFACC.Id=PFACCM.Id " +
                "AND PFACC.Acc=PFACCM.Acc " +
                "AND PFACC.Id=? " +
                "AND PFACCM.Year=? " +
                "AND PFACCM.Month=? " +
                "AND PFACT.AccTyp in ('I', 'E') " +
                "AND PFACC.SysTyp='N' " +
                "AND PFACC.ShowInMain='Y' " +
                "ORDER BY PFACT.OrderFull, PFACC.Acc";

        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, id);
        form.cmd().db.setParameter(rsName, 2, year);
        form.cmd().db.setParameter(rsName, 3, month);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
