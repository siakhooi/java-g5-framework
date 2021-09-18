/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.TCD;

import com.mysoftwarehouse.is.F.ISFJ0TCD;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class TCD_FJ0TCD {

    public static void select(ISFJ0TCD form, String rsName,
            String Whs, String FrmTcd, String ToTcd) throws SQLException {

        String sql = "SELECT * FROM ISTCD ";
        boolean hasFrmTcd = !form.cmd().data.isNull(FrmTcd);
        boolean hasToTcd = !form.cmd().data.isNull(ToTcd);
        sql += "WHERE Whs=? ";

        if (hasFrmTcd) {
            sql += "AND Tcd>=? ";
        }
        if (hasToTcd) {
            sql += "AND Tcd<=? ";
        }
        sql += "ORDER BY Whs, Tcd ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Whs);
        if (hasFrmTcd) {
            form.cmd().db.setParameter(rsName, i++, FrmTcd);
        }
        if (hasToTcd) {
            form.cmd().db.setParameter(rsName, i++, ToTcd);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
