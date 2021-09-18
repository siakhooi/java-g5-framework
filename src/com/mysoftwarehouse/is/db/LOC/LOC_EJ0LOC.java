/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.LOC;

import com.mysoftwarehouse.is.E.ISEJ0LOC;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class LOC_EJ0LOC {
    public static void select(ISEJ0LOC form, String rsName, 
            String Whs, String FrmLoc, String ToLoc) throws SQLException {

        String sql = "SELECT * FROM ISLOC ";
        boolean hasFrmLoc = !form.cmd().data.isNull(FrmLoc);
        boolean hasToLoc = !form.cmd().data.isNull(ToLoc);
        sql += "WHERE Whs=? ";

        if (hasFrmLoc) {
            sql += "AND Loc>=? ";
        }
        if (hasToLoc) {
            sql += "AND Loc<=? ";
        }
        sql += "ORDER BY Whs, Loc ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Whs);
        if (hasFrmLoc) {
            form.cmd().db.setParameter(rsName, i++, FrmLoc);
        }
        if (hasToLoc) {
            form.cmd().db.setParameter(rsName, i++, ToLoc);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }

}
