/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.ITM;

import com.mysoftwarehouse.is.I.ISIJ0ITM;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ITM_IJ0ITM {
    public static void select(ISIJ0ITM form, String rsName,
            String Whs, String FrmItm, String ToItm) throws SQLException {

        String sql = "SELECT * FROM ISITM ";
        boolean hasFrmItm = !form.cmd().data.isNull(FrmItm);
        boolean hasToItm = !form.cmd().data.isNull(ToItm);
        sql += "WHERE Whs=? ";

        if (hasFrmItm) {
            sql += "AND Itm>=? ";
        }
        if (hasToItm) {
            sql += "AND Itm<=? ";
        }
        sql += "ORDER BY Whs, Itm ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Whs);
        if (hasFrmItm) {
            form.cmd().db.setParameter(rsName, i++, FrmItm);
        }
        if (hasToItm) {
            form.cmd().db.setParameter(rsName, i++, ToItm);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
