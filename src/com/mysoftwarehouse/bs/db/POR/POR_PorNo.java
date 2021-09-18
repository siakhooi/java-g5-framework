/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.POR;

import com.mysoftwarehouse.bs.NO.N.BSNS0POR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class POR_PorNo {

    public static String getNextPorNo(BSNS0POR form, String Cmp) throws SQLException {
        String psName = "POR_PorNo.U1";
        String sql = "UPDATE BSCFG SET LstNumPor=LstNumPor+1 " +
                "WHERE Cmp=? ";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);

        sql = "SELECT NumDgtsPor, PrfxPor, LstNumPor FROM BSCFG WHERE Cmp=? ";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execQuery(psName, psName);
        form.cmd().db.first(psName);
        int n = form.cmd().db.getInteger(psName, "LstNumPor");
        int m = form.cmd().db.getInteger(psName, "NumDgtsPor");
        String s = form.cmd().db.getString(psName, "PrfxPor");
        m = m - s.length();
        m = m - (form.cmd().data.int2String(n).length());
        s = s + form.cmd().data.repeatString("0", m);
        s = s + form.cmd().data.int2String(n);
        return s;
    }
}
