/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CFG;

import com.mysoftwarehouse.bs.B.BSBJ0CFG;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CFG_BJ0CFG {

    public static void select(BSBJ0CFG form, String rsName, String frmCmp, String toCmp) throws SQLException {

        String sql = "SELECT * FROM BSCFG ";
        boolean hasFrmCmp = !form.cmd().data.isNull(frmCmp);
        boolean hasToCmp = !form.cmd().data.isNull(toCmp);
        if (hasFrmCmp || hasToCmp) {
            sql += "WHERE ";
            if (hasFrmCmp && hasToCmp) {
                sql += "Cmp>=? ";
                sql += "AND Cmp<=? ";
            } else {
                if (hasToCmp) {
                    sql += "Cmp>=? ";
                } else {
                    sql += "Cmp<=? ";
                }
            }
        }
        sql += "ORDER BY Cmp ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        if (hasFrmCmp) {
            form.cmd().db.setParameter(rsName, i++, frmCmp);
        }
        if (hasToCmp) {
            form.cmd().db.setParameter(rsName, i++, toCmp);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
