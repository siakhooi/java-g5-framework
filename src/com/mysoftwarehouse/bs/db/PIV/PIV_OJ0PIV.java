/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.mysoftwarehouse.bs.db.PIV;

import com.mysoftwarehouse.bs.NO.O.BSOJ0PIV;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PIV_OJ0PIV {

    public static void select(BSOJ0PIV form, String rsName, String Cmp, String PivNo) throws SQLException {
            
        String sql = "SELECT " +
                "C.Nme ItmNme, " +
                "E.Nme SupNme, " +
                "B.TtlAmt ItmTtlAmt, " +
                "A.TtlAmt NetTtlAmt, " +
                "G.Text ItmText, " +
                "* " +
                "FROM " +
                "BSPIV A, " +
                //"BSPIVD B, " +
                "BSPIVD B LEFT OUTER JOIN BSPIVE G ON B.Cmp=G.Cmp AND B.PivNo=G.PivNo AND B.Itm=G.Itm, " +
                "BSPI C, " +
                "BSPIVA D, " +
                "BSSUP E, " +
                "BSPIVC F " +
                "WHERE Cmp=? AND PivNo=? " +
                "AND A.Cmp=B.Cmp " +
                "AND A.PivNo=B.PivNo " +
                "AND A.Cmp=D.Cmp " +
                "AND A.PivNo=D.PivNo " +
                "AND A.Cmp=F.Cmp " +
                "AND A.PivNo=F.PivNo " +
                "AND A.Cmp=E.Cmp " +
                "AND A.Sup=E.Sup " +
                "AND B.Cmp=C.Cmp " +
                "AND B.Itm=C.Itm " +
                "ORDER BY Cmp, PivNo, Itm, G.Seq ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, PivNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void selectCmp(BSOJ0PIV form, String rsName, String Cmp) throws SQLException {
        String sql = "SELECT * FROM BSCMP A, BSCMPA B, BSCMPC C " +
                "WHERE Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND A.Cmp=C.Cmp ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

}
