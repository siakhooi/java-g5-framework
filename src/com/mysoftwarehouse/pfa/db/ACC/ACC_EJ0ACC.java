/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACC;

import com.mysoftwarehouse.pfa.E.PFEJ0ACC;
import com.mysoftwarehouse.pfa.db.DB.DataEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACC_EJ0ACC {

    public static void select(
            PFEJ0ACC form,
            String rsName, String id, String frmAcc, String toAcc) throws SQLException {
        String sql = "SELECT Acc, Nme, AccTyp, " +
                "BalAmt, BalDte, Remark " +
                "FROM PFACC " +
                "WHERE Id=? AND AccTyp<>? ";
        boolean hasFrmAcc = !form.cmd().data.isNull(frmAcc);
        boolean hasToAcc = !form.cmd().data.isNull(toAcc);
        if (hasFrmAcc) {
            sql += "AND Acc>=? ";
        }
        if (hasToAcc) {
            sql += "AND Acc<=? ";
        }
        sql += "ORDER BY Acc ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, id);
        form.cmd().db.setParameter(rsName, i++, DataEnum.AccTyp.C.code);
        if (hasFrmAcc) {
            form.cmd().db.setParameter(rsName, i++, frmAcc);
        }
        if (hasToAcc) {
            form.cmd().db.setParameter(rsName, i++, toAcc);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
