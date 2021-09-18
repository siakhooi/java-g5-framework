/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PI;

import com.mysoftwarehouse.bs.KM.L.BSLJ0PI;
import com.mysoftwarehouse.bs.data.PiEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PI_LJ0PI {

    public static void select(BSLJ0PI form, String rsName, String Cmp,
            String FrmItm, String ToItm, boolean active, boolean inactive)
            throws SQLException {
        String sql = "SELECT * FROM BSPI ";
        boolean hasFrmItm = !form.cmd().data.isNull(FrmItm);
        boolean hasToItm = !form.cmd().data.isNull(ToItm);
        sql += "WHERE Cmp=? ";

        if (hasFrmItm) {
            sql += "AND Itm>=? ";
        }
        if (hasToItm) {
            sql += "AND Itm<=? ";
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
        sql += "ORDER BY Cmp, Itm ";

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
