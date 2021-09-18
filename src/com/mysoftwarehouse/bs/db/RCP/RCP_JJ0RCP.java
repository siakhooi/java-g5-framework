/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.RCP;

import com.mysoftwarehouse.bs.HJ.J.BSJJ0RCP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class RCP_JJ0RCP {

    public static void select(BSJJ0RCP form, String rsName, String Cmp, String RcpNo) throws SQLException {
        String sql = "SELECT " +
                "C.Nme ItmNme, " +
                "E.Nme CusNme, " +
                "G.Text ItmText, " +
                "B.TtlAmt ItmTtlAmt, " +
                "A.TtlAmt NetTtlAmt, " +
                "* " +
                "FROM BSRCP A, " +
                "BSRCPD B LEFT OUTER JOIN BSRCPE G ON B.Cmp=G.Cmp AND B.RcpNo=G.RcpNo AND B.Itm=G.Itm, " +
                "BSSI C, " +
                //"BSSI C LEFT OUTER JOIN BSSID G ON C.Cmp=G.Cmp AND C.Itm=G.Itm, " +
                "BSRCPA D, BSCUS E, BSRCPC F " +
                "WHERE Cmp=? AND RcpNo=? " +
                "AND A.Cmp=B.Cmp " +
                "AND A.RcpNo=B.RcpNo " +
                "AND A.Cmp=D.Cmp " +
                "AND A.RcpNo=D.RcpNo " +
                "AND A.Cmp=F.Cmp " +
                "AND A.RcpNo=F.RcpNo " +
                "AND A.Cmp=E.Cmp " +
                "AND A.Cus=E.Cus " +
                "AND B.Cmp=C.Cmp " +
                "AND B.Itm=C.Itm " +
                "ORDER BY Cmp, RcpNo, Itm, G.Seq ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, RcpNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void selectCmp(BSJJ0RCP form, String rsName, String Cmp) throws SQLException {
        String sql = "SELECT * FROM BSCMP A, BSCMPA B, BSCMPC C " +
                "WHERE Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND A.Cmp=C.Cmp ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void selectRcpj(BSJJ0RCP form, String rsName, String Cmp, String RcpNo) throws SQLException {
        String sql = "SELECT * FROM BSRCPJ WHERE Cmp=? AND RcpNo=? ORDER BY Prio, Seq ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, RcpNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void selectRcpr(BSJJ0RCP form, String rsName, String Cmp, String RcpNo) throws SQLException {
        String sql = "SELECT * FROM BSRCPR WHERE Cmp=? AND RcpNo=? ORDER BY Seq ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, RcpNo);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
