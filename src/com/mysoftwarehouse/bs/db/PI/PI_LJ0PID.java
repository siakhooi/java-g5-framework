/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PI;

import com.mysoftwarehouse.bs.KM.L.BSLJ0PID;
import com.mysoftwarehouse.bs.data.PiEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PI_LJ0PID {

    public static void select(BSLJ0PID form, String rsName, String Cmp,
            String FrmItm, String ToItm, boolean active, boolean inactive)
            throws SQLException {

        String sql = "SELECT * FROM " +
                "BSPI A LEFT OUTER JOIN BSPID B ON A.Cmp=B.Cmp AND A.Itm=B.Itm ";
        
        boolean hasFrmItm = !form.cmd().data.isNull(FrmItm);
        boolean hasToItm = !form.cmd().data.isNull(ToItm);
        sql += "WHERE A.Cmp=? ";

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
        sql += "ORDER BY A.Cmp, A.Itm, B.Seq ";

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
