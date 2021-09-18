/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.WHS;

import com.mysoftwarehouse.is.B.ISBJ0WHS;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class WHS_BJ0WHS {
    public static void select(ISBJ0WHS form, String rsName, 
            String frmWhs, String toWhs) throws SQLException {

        String sql = "SELECT * FROM ISWHS A ";
        boolean hasFrmWhs = !form.cmd().data.isNull(frmWhs);
        boolean hasToWhs = !form.cmd().data.isNull(toWhs);
        sql += "WHERE 1=1 ";
        if (hasFrmWhs) {
            sql += "AND A.Whs>=? ";
        }
        if (hasToWhs) {
            sql += "AND A.Whs<=? ";
        }
        sql += "ORDER BY A.Whs ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        if (hasFrmWhs) {
            form.cmd().db.setParameter(rsName, i++, frmWhs);
        }
        if (hasToWhs) {
            form.cmd().db.setParameter(rsName, i++, toWhs);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
