/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SUP;

import com.mysoftwarehouse.bs.KM.K.BSKJ2SUP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class SUP_KJ2SUP {

    public static void select(BSKJ2SUP form, String rsName, String Cmp,
            String FrmSup, String ToSup, boolean active, boolean inactive)
            throws SQLException {
        String sql = "SELECT * FROM BSSUP A, BSSUPC B ";
        boolean hasFrmSup = !form.cmd().data.isNull(FrmSup);
        boolean hasToSup = !form.cmd().data.isNull(ToSup);
        sql += "WHERE A.Cmp=? ";

        sql += "AND A.Cmp=B.Cmp ";
        sql += "AND A.Sup=B.Sup ";
        sql += "AND A.PriCnt=B.Seq ";

        if (hasFrmSup) {
            sql += "AND A.Sup>=? ";
        }
        if (hasToSup) {
            sql += "AND A.Sup<=? ";
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
        sql += "ORDER BY A.Cmp, A.Sup ";

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
            form.cmd().db.setParameter(rsName, i++, "A");
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
