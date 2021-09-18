/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.POR;

import com.mysoftwarehouse.bs.NO.N.BSNJ3POR;
import com.mysoftwarehouse.bs.data.SupEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class POR_NJ3POR {

    public static void select(BSNJ3POR form, String rsName, String Cmp, 
            String FrmSup, String ToSup, boolean active, boolean inactive)
            throws SQLException {

        String sql = "SELECT B.Nme SupNme, * FROM BSPOR A, BSSUP B ";
        boolean hasFrmSup = !form.cmd().data.isNull(FrmSup);
        boolean hasToSup = !form.cmd().data.isNull(ToSup);
        sql += "WHERE Cmp=? ";
        sql += "AND A.Cmp=B.Cmp ";
        sql += "AND A.Sup=B.Sup ";
        if (hasFrmSup) {
            sql += "AND A.Sup>=? ";
        }
        if (hasToSup) {
            sql += "AND A.Sup<=? ";
        }
        boolean checkStatus = false;
        if (active && !inactive) {
            sql += "AND B.Status=? ";
            checkStatus = true;
        }
        if (!active && inactive) {
            sql += "AND B.Status<>? ";
            checkStatus = true;
        }
        sql += "ORDER BY A.Cmp, A.Sup, A.PorNo ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        if (hasFrmSup) {
            form.cmd().db.setParameter(rsName, i++, FrmSup);
        }
        if (hasToSup) {
            form.cmd().db.setParameter(rsName, i++, ToSup);
        }
        if (checkStatus) {
            form.cmd().db.setParameter(rsName, i++, SupEnum.SupStatus.A.typ);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
