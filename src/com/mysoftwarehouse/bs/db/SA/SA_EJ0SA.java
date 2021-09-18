/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SA;

import com.mysoftwarehouse.bs.CG.E.BSEJ0SA;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class SA_EJ0SA {

    public static void select(BSEJ0SA form, String rsName, String Cmp,
            String FrmAdj, String ToAdj, boolean active, boolean inactive)
            throws SQLException {

        String sql = "SELECT * FROM BSSA ";
        boolean hasFrmAdj = !form.cmd().data.isNull(FrmAdj);
        boolean hasToAdj = !form.cmd().data.isNull(ToAdj);
        sql += "WHERE Cmp=? ";

        if (hasFrmAdj) {
            sql += "AND Adj>=? ";
        }
        if (hasToAdj) {
            sql += "AND Adj<=? ";
        }
        boolean checkStatus = false;
        if (active && !inactive) {
            sql += "AND Status=? ";
            checkStatus = true;
        }
        if (!active && inactive) {
            sql += "AND Status<>? ";
            checkStatus = true;
        }
        sql += "ORDER BY Cmp, Adj ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        if (hasFrmAdj) {
            form.cmd().db.setParameter(rsName, i++, FrmAdj);
        }
        if (hasToAdj) {
            form.cmd().db.setParameter(rsName, i++, ToAdj);
        }
        if (checkStatus) {
            form.cmd().db.setParameter(rsName, i++, "A");
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
