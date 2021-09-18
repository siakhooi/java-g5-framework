/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.mysoftwarehouse.bs.HJ.I.BSIJ0INV;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class INV_IJ0INV {

    public static void select(BSIJ0INV form, String rsName, String Cmp, String InvNo) throws SQLException {
        String sql = "SELECT " +
                "C.Nme ItmNme, " +
                "E.Nme CusNme, " +
                "G.Text ItmText, " +
                "B.TtlAmt ItmTtlAmt, " +
                "A.TtlAmt NetTtlAmt, " +
                "* " +
                "FROM BSINV A, " +
                "BSINVD B LEFT OUTER JOIN BSINVE G ON B.Cmp=G.Cmp AND B.InvNo=G.InvNo AND B.Itm=G.Itm, " +
                "BSSI C, " +
                //"BSSI C LEFT OUTER JOIN BSSID G ON C.Cmp=G.Cmp AND C.Itm=G.Itm, " +
                "BSINVA D, BSCUS E, BSINVC F " +
                "WHERE Cmp=? AND InvNo=? " +
                "AND A.Cmp=B.Cmp " +
                "AND A.InvNo=B.InvNo " +
                "AND A.Cmp=D.Cmp " +
                "AND A.InvNo=D.InvNo " +
                "AND A.Cmp=F.Cmp " +
                "AND A.InvNo=F.InvNo " +
                "AND A.Cmp=E.Cmp " +
                "AND A.Cus=E.Cus " +
                "AND B.Cmp=C.Cmp " +
                "AND B.Itm=C.Itm " +
                "ORDER BY Cmp, InvNo, Itm, G.Seq ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, InvNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void selectCmp(BSIJ0INV form, String rsName, String Cmp) throws SQLException {
        String sql = "SELECT * FROM BSCMP A, BSCMPA B, BSCMPC C " +
                "WHERE Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND A.Cmp=C.Cmp ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void selectInvj(BSIJ0INV form, String rsName, String Cmp, String InvNo) throws SQLException {
        String sql = "SELECT * FROM BSINVJ WHERE Cmp=? AND InvNo=? ORDER BY Prio, Seq ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, InvNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void selectInvr(BSIJ0INV form, String rsName, String Cmp, String InvNo) throws SQLException {
        String sql = "SELECT * FROM BSINVR WHERE Cmp=? AND InvNo=? ORDER BY Seq ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, InvNo);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
