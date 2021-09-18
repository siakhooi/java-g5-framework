/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACC;

import com.mysoftwarehouse.pfa.C.PFCL0ACC;
import com.mysoftwarehouse.pfa.data.PFACC;
import com.mysoftwarehouse.pfa.db.DB.DataEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACC_CL0ACC {

    public static void select(
            PFCL0ACC form, 
            String rsName, String id) throws SQLException {
        String sql = "SELECT * " +
                "FROM PFACC, PFACT " +
                "WHERE PFACC.AccTyp=PFACT.AccTyp " +
                "AND PFACC.Id=? " +
                "AND PFACT.AccTyp <> ? " +
                "AND PFACC.Status=? " +
                "ORDER BY PFACT.OrderFull, PFACC.Acc ";
        String psName = "ACC.CL0ACC";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.setParameter(psName, 2, DataEnum.AccTyp.C.code);
        form.cmd().db.setParameter(psName, 3, PFACC.Status.A.code);
        form.cmd().db.execQuery(psName, rsName);
    }
}
