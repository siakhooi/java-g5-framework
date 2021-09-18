/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.ST;

import com.mysoftwarehouse.bs.CG.G.BSGJ0STD;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ST_GJ0STD {

    public static void select(BSGJ0STD form, String rsName, String Cmp,
            String FrmTrm, String ToTrm, boolean active, boolean inactive,
            boolean forQtt, boolean forInv, boolean forRcp)
            throws SQLException {
        String sql = "SELECT * FROM BSST A, BSSTD B ";
        boolean hasFrmTrm = !form.cmd().data.isNull(FrmTrm);
        boolean hasToTrm = !form.cmd().data.isNull(ToTrm);
        sql += "WHERE A.Cmp=? ";
        sql += "AND A.Cmp=B.Cmp ";
        sql += "AND A.Trm=B.Trm ";

        if (hasFrmTrm) {
            sql += "AND A.Trm>=? ";
        }
        if (hasToTrm) {
            sql += "AND A.Trm<=? ";
        }
        if (forQtt || forInv || forRcp) {
            sql += " AND (";
            sql += "1=0 ";
            if (forQtt) {
                sql += "OR A.ForQtt=? ";
            }
            if (forInv) {
                sql += "OR A.ForInv=? ";
            }
            if (forRcp) {
                sql += "OR A.ForRcp=? ";
            }

            sql += ") ";
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
        sql += "ORDER BY A.Cmp, A.Trm, B.Seq ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        if (hasFrmTrm) {
            form.cmd().db.setParameter(rsName, i++, FrmTrm);
        }
        if (hasToTrm) {
            form.cmd().db.setParameter(rsName, i++, ToTrm);
        }
        if (forQtt) {
            form.cmd().db.setParameter(rsName, i++, "Y");
        }
        if (forInv) {
            form.cmd().db.setParameter(rsName, i++, "Y");
        }
        if (forRcp) {
            form.cmd().db.setParameter(rsName, i++, "Y");
        }
        if (checkStatus) {
            form.cmd().db.setParameter(rsName, i++, "A");
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
