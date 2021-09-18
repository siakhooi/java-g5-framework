/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PI;

import com.mysoftwarehouse.bs.KM.L.BSLJ2PI;
import com.mysoftwarehouse.bs.data.PiEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PI_LJ2PI {

    public static void select(BSLJ2PI form, String rsName, String Cmp,
            String FrmItm, String ToItm, boolean active, boolean inactive)
            throws SQLException {

        String sql = "SELECT A.Nme ItmNme, C.Nme SupNme, B.Status PriceStatus, " +
                "* FROM BSPI A, BSPIP B, BSSUP C ";
        boolean hasFrmItm = !form.cmd().data.isNull(FrmItm);
        boolean hasToItm = !form.cmd().data.isNull(ToItm);
        sql += "WHERE A.Cmp=? ";
        sql += "AND A.Cmp=B.Cmp ";
        sql += "AND A.Itm=B.Itm ";
        sql += "AND A.Cmp=C.Cmp ";
        sql += "AND B.Sup=C.Sup ";

        if (hasFrmItm) {
            sql += "AND A.Itm>=? ";
        }
        if (hasToItm) {
            sql += "AND A.Itm<=? ";
        }
        boolean checkStatus = false;
        if (active && !inactive) {
            sql += "AND A.Status=? ";
            checkStatus = true;
        }
        if (!active && inactive) {
            sql += "AND A.Status<>? ";
            checkStatus = true;
        }
        sql += "ORDER BY A.Cmp, A.Itm, B.Sup ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        if (hasFrmItm) {
            form.cmd().db.setParameter(rsName, i++, FrmItm);
        }
        if (hasToItm) {
            form.cmd().db.setParameter(rsName, i++, ToItm);
        }
        if (checkStatus) {
            form.cmd().db.setParameter(rsName, i++, PiEnum.Status.A.typ);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
