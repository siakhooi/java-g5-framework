/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.QTT;

import com.mysoftwarehouse.bs.HJ.H.BSHJ3QTTD;
import com.mysoftwarehouse.bs.data.CusEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class QTT_HJ3QTTD {

    public static void select(BSHJ3QTTD form, String rsName, String Cmp, 
            String FrmCus, String ToCus, boolean active, boolean inactive)
            throws SQLException {
        boolean hasFrmCus = !form.cmd().data.isNull(FrmCus);
        boolean hasToCus = !form.cmd().data.isNull(ToCus);
        
        String sql = "SELECT B.Nme CusNme, A.TtlAmt AllTtlAmt, " +
                "C.TtlAmt ItmTtlAmt, D.Nme ItmNme, * FROM " +
                "BSQTT A " +
                "LEFT OUTER JOIN BSQTTD C ON A.Cmp=C.Cmp AND A.QttNo=C.QttNo " +
                "LEFT OUTER JOIN BSSI D ON C.Cmp=D.Cmp AND C.Itm=D.Itm, " +
                "BSCUS B ";

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

        sql += "ORDER BY A.Cmp, B.Cus, A.QttNo, C.Itm ";

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
