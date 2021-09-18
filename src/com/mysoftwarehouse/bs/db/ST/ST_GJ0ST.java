/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.ST;

import com.mysoftwarehouse.bs.CG.G.BSGJ0ST;
import com.mysoftwarehouse.bs.data.StEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ST_GJ0ST {

    public static void select(BSGJ0ST form, String rsName, String Cmp,
            String FrmTrm, String ToTrm, boolean active, boolean inactive,
            boolean forQtt, boolean forInv, boolean forRcp)
            throws SQLException {
        String sql = "SELECT * FROM BSST ";
        boolean hasFrmTrm = !form.cmd().data.isNull(FrmTrm);
        boolean hasToTrm = !form.cmd().data.isNull(ToTrm);
        sql += "WHERE Cmp=? ";

        if (hasFrmTrm) {
            sql += "AND Trm>=? ";
        }
        if (hasToTrm) {
            sql += "AND Trm<=? ";
        }
        if (forQtt || forInv || forRcp) {
            sql += " AND (";
            sql += "1=0 ";
            if (forQtt) {
                sql += "OR ForQtt=? ";
            }
            if (forInv) {
                sql += "OR ForInv=? ";
            }
            if (forRcp) {
                sql += "OR ForRcp=? ";
            }

            sql += ") ";
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
        sql += "ORDER BY Cmp, Trm ";

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
            form.cmd().db.setParameter(rsName, i++, StEnum.Status.A.typ);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
