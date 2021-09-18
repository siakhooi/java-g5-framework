/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SI;

import com.mysoftwarehouse.bs.CG.D.BSDJ0SID;
import com.mysoftwarehouse.bs.data.SiEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class SI_DJ0SID {

    public static void select(BSDJ0SID form, String rsName, String Cmp,
            String FrmItm, String ToItm, boolean active, boolean inactive)
            throws SQLException {

//        String sql = "SELECT * FROM BSSI A, BSSID B ";
        String sql = "SELECT * FROM " +
                "BSSI A LEFT OUTER JOIN BSSID B ON A.Cmp=B.Cmp AND A.Itm=B.Itm ";
        
        boolean hasFrmItm = !form.cmd().data.isNull(FrmItm);
        boolean hasToItm = !form.cmd().data.isNull(ToItm);
        sql += "WHERE A.Cmp=? ";
//        sql += "AND A.Cmp=B.Cmp ";
//        sql += "AND A.Itm=B.Itm ";

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
            form.cmd().db.setParameter(rsName, i++, SiEnum.Status.A.typ);
        }
        form.cmd().db.execQuery(rsName, rsName);

    }
}
