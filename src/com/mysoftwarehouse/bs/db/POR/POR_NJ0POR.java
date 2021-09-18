/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.POR;

import com.mysoftwarehouse.bs.NO.N.BSNJ0POR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class POR_NJ0POR {

    public static void select(BSNJ0POR form, String rsName, String Cmp, String PorNo)
            throws SQLException {

        String sql = "SELECT " +
                "C.Nme ItmNme, " +
                "E.Nme SupNme, " +
                "B.TtlAmt ItmTtlAmt, " +
                "A.TtlAmt NetTtlAmt, " +
                "G.Text ItmText, " +
                "* " +
                "FROM " +
                "BSPOR A, " +
                //"BSPORD B, " +
                "BSPORD B LEFT OUTER JOIN BSPORE G ON B.Cmp=G.Cmp AND B.PorNo=G.PorNo AND B.Itm=G.Itm, " +
                "BSPI C, " +
                "BSPORA D, " +
                "BSSUP E, " +
                "BSPORC F " +
                "WHERE Cmp=? AND PorNo=? " +
                "AND A.Cmp=B.Cmp " +
                "AND A.PorNo=B.PorNo " +
                "AND A.Cmp=D.Cmp " +
                "AND A.PorNo=D.PorNo " +
                "AND A.Cmp=F.Cmp " +
                "AND A.PorNo=F.PorNo " +
                "AND A.Cmp=E.Cmp " +
                "AND A.Sup=E.Sup " +
                "AND B.Cmp=C.Cmp " +
                "AND B.Itm=C.Itm " +
                "ORDER BY Cmp, PorNo, Itm, G.Seq ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, PorNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void selectCmp(BSNJ0POR form, String rsName, String Cmp) throws SQLException {
        String sql = "SELECT * FROM BSCMP A, BSCMPA B, BSCMPC C " +
                "WHERE Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND A.Cmp=C.Cmp ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void selectPors(BSNJ0POR form, String rsName, String Cmp, String PorNo) throws SQLException {
        String sql = "SELECT * FROM BSPORS WHERE Cmp=? AND PorNo=? ORDER BY Seq ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, PorNo);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
