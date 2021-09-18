/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PIV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PIV_PivNo {

    public static String getNextPivNo(UserFormInterface form, String Cmp) throws SQLException {
        String psName = "PIV_PivNo.U1";
        String sql = "UPDATE BSCFG SET LstNumPiv=LstNumPiv+1 " +
                "WHERE Cmp=? ";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);

        sql = "SELECT NumDgtsPiv, PrfxPiv, LstNumPiv FROM BSCFG WHERE Cmp=? ";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execQuery(psName, psName);
        form.cmd().db.first(psName);
        int n = form.cmd().db.getInteger(psName, "LstNumPiv");
        int m = form.cmd().db.getInteger(psName, "NumDgtsPiv");
        String s = form.cmd().db.getString(psName, "PrfxPiv");
        m = m - s.length();
        m = m - (form.cmd().data.int2String(n).length());
        s = s + form.cmd().data.repeatString("0", m);
        s = s + form.cmd().data.int2String(n);
        return s;
    }
}
