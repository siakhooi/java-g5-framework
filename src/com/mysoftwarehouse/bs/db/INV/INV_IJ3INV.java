/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.mysoftwarehouse.bs.HJ.I.BSIJ3INV;
import com.mysoftwarehouse.bs.data.CusEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class INV_IJ3INV {

    public static void select(BSIJ3INV form, String rsName, String Cmp,
            String FrmCus, String ToCus, boolean active, boolean inactive)
            throws SQLException {

        String sql = "SELECT B.Nme CusNme, * FROM BSINV A, BSCUS B ";
        boolean hasFrmCus = !form.cmd().data.isNull(FrmCus);
        boolean hasToCus = !form.cmd().data.isNull(ToCus);
        sql += "WHERE Cmp=? ";
        sql += "AND A.Cmp=B.Cmp ";
        sql += "AND A.Cus=B.Cus ";
        if (hasFrmCus) {
            sql += "AND A.Cus>=? ";
        }
        if (hasToCus) {
            sql += "AND A.Cus<=? ";
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
        sql += "ORDER BY A.Cmp, A.Cus, A.InvNo ";

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
            form.cmd().db.setParameter(rsName, i++, CusEnum.CusStatus.A.typ);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
