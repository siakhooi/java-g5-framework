/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CUS;

import com.mysoftwarehouse.bs.CG.C.BSCJ1CUS;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CUS_CJ1CUS {

    public static void select(BSCJ1CUS form, String rsName, String Cmp,
            String FrmCus, String ToCus, boolean active, boolean inactive)
            throws SQLException {
        String sql = "SELECT * FROM BSCUS A, BSCUSA B ";
        boolean hasFrmCus = !form.cmd().data.isNull(FrmCus);
        boolean hasToCus = !form.cmd().data.isNull(ToCus);
        sql += "WHERE A.Cmp=? ";

        sql += "AND A.Cmp=B.Cmp ";
        sql += "AND A.Cus=B.Cus ";
        sql += "AND A.PriAdd=B.Seq ";

        if (hasFrmCus) {
            sql += "AND A.Cus>=? ";
        }
        if (hasToCus) {
            sql += "AND A.Cus<=? ";
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
        sql += "ORDER BY A.Cmp, A.Cus ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        if (hasFrmCus) {
            form.cmd().db.setParameter(rsName, i++, FrmCus);
        }
        if (hasToCus) {
            form.cmd().db.setParameter(rsName, i++, ToCus);
        }
        if (checkStatus) {
            form.cmd().db.setParameter(rsName, i++, "A");
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
