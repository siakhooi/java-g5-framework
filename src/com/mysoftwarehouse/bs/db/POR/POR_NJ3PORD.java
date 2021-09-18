/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.POR;

import com.mysoftwarehouse.bs.NO.N.BSNJ3PORD;
import com.mysoftwarehouse.bs.data.SupEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class POR_NJ3PORD {

    public static void select(BSNJ3PORD form, String rsName, String Cmp,
            String FrmSup, String ToSup, boolean active, boolean inactive)
            throws SQLException {
        boolean hasFrmSup = !form.cmd().data.isNull(FrmSup);
        boolean hasToSup = !form.cmd().data.isNull(ToSup);

        String sql = "SELECT B.Nme SupNme, A.TtlAmt AllTtlAmt, " +
                "C.TtlAmt ItmTtlAmt, D.Nme ItmNme, * FROM " +
                "BSPOR A " +
                "LEFT OUTER JOIN BSPORD C ON A.Cmp=C.Cmp AND A.PorNo=C.PorNo " +
                "LEFT OUTER JOIN BSPI D ON C.Cmp=D.Cmp AND C.Itm=D.Itm, " +
                "BSSUP B ";

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

        sql += "ORDER BY A.Cmp, B.Sup, A.PorNo, C.Itm ";

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
