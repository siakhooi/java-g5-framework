/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.QTT;

import com.mysoftwarehouse.bs.HJ.H.BSHS0QTT;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class QTT_QttNo {

    public static String getNextQttNo(BSHS0QTT form, String Cmp) throws SQLException {
        String psName = "QTT_QttNo.U1";
        String sql = "UPDATE BSCFG SET LstNumQtt=LstNumQtt+1 " +
                "WHERE Cmp=? ";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);

        sql = "SELECT NumDgtsQtt, PrfxQtt, LstNumQtt FROM BSCFG WHERE Cmp=? ";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execQuery(psName, psName);
        form.cmd().db.first(psName);
        int n = form.cmd().db.getInteger(psName, "LstNumQtt");
        int m = form.cmd().db.getInteger(psName, "NumDgtsQtt");
        String s = form.cmd().db.getString(psName, "PrfxQtt");
        m = m - s.length();
        m = m - (form.cmd().data.int2String(n).length());
        s = s + form.cmd().data.repeatString("0", m);
        s = s + form.cmd().data.int2String(n);
        return s;
    }
}
