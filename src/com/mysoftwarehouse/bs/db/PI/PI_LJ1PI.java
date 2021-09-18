/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PI;

import com.mysoftwarehouse.bs.KM.L.BSLJ1PI;
import com.mysoftwarehouse.bs.data.SupEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PI_LJ1PI {

    public static void select(BSLJ1PI form, String rsName, String Cmp,
            String FrmSup, String ToSup, boolean active, boolean inactive)
            throws SQLException {

        String sql = "SELECT B.Nme ItmNme, C.Nme SupNme, A.Status PriceStatus, " +
                "* FROM BSSUP C, BSPI B, BSPIP A ";
        boolean hasFrmSup = !form.cmd().data.isNull(FrmSup);
        boolean hasToSup = !form.cmd().data.isNull(ToSup);
        sql += "WHERE A.Cmp=? ";

        sql += "AND A.Cmp=B.Cmp ";
        sql += "AND A.Cmp=C.Cmp ";
        sql += "AND A.Sup=C.Sup ";
        sql += "AND A.Itm=B.Itm ";

        if (hasFrmSup) {
            sql += "AND C.Sup>=? ";
        }
        if (hasToSup) {
            sql += "AND C.Sup<=? ";
        }
        boolean checkStatus = false;
        if (active && !inactive) {
            sql += "AND C.Status=? ";
            checkStatus = true;
        }
        if (!active && inactive) {
            sql += "AND C.Status<>? ";
            checkStatus = true;
        }
        sql += "ORDER BY C.Cmp, C.Sup, A.Itm ";

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
