/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class INV_InvNo {

    public static String getNextInvNo(UserFormInterface form, String Cmp) throws SQLException {
        String psName = "INV_InvNo.U";
        String sql = "UPDATE BSCFG SET LstNumInv=LstNumInv+1 " +
                "WHERE Cmp=? ";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);

        sql = "SELECT NumDgtsInv, PrfxInv, LstNumInv FROM BSCFG WHERE Cmp=? ";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execQuery(psName, psName);
        form.cmd().db.first(psName);
        int n = form.cmd().db.getInteger(psName, "LstNumInv");
        int m = form.cmd().db.getInteger(psName, "NumDgtsInv");
        String s = form.cmd().db.getString(psName, "PrfxInv");
        m = m - s.length();
        m = m - (form.cmd().data.int2String(n).length());
        s = s + form.cmd().data.repeatString("0", m);
        s = s + form.cmd().data.int2String(n);
        return s;


    }
}
