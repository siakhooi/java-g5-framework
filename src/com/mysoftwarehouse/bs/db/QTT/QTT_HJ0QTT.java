/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.QTT;

import com.mysoftwarehouse.bs.HJ.H.BSHJ0QTT;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class QTT_HJ0QTT {

    public static void select(BSHJ0QTT form, String rsName, String Cmp, String QttNo) throws SQLException {
        String sql = "SELECT " +
                "C.Nme ItmNme, " +
                "E.Nme CusNme, " +
                "G.Text ItmText, " +
                "B.TtlAmt ItmTtlAmt, " +
                "A.TtlAmt NetTtlAmt, " +
                "* " +
                "FROM BSQTT A, " +
                "BSQTTD B LEFT OUTER JOIN BSQTTE G ON B.Cmp=G.Cmp AND B.QttNo=G.QttNo AND B.Itm=G.Itm, " +
                "BSSI C, " +
                //"BSSI C LEFT OUTER JOIN BSSID G ON C.Cmp=G.Cmp AND C.Itm=G.Itm, " +
                "BSQTTA D, BSCUS E, BSQTTC F " +
                "WHERE Cmp=? AND QttNo=? " +
                "AND A.Cmp=B.Cmp " +
                "AND A.QttNo=B.QttNo " +
                "AND A.Cmp=D.Cmp " +
                "AND A.QttNo=D.QttNo " +
                "AND A.Cmp=F.Cmp " +
                "AND A.QttNo=F.QttNo " +
                "AND A.Cmp=E.Cmp " +
                "AND A.Cus=E.Cus " +
                "AND B.Cmp=C.Cmp " +
                "AND B.Itm=C.Itm " +
                "ORDER BY Cmp, QttNo, Itm, G.Seq ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, QttNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void selectCmp(BSHJ0QTT form, String rsName, String Cmp) throws SQLException {
        String sql = "SELECT * FROM BSCMP A, BSCMPA B, BSCMPC C " +
                "WHERE Cmp=? " +
                "AND A.Cmp=B.Cmp " +
                "AND A.Cmp=C.Cmp ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void selectQttj(BSHJ0QTT form, String rsName, String Cmp, String QttNo) throws SQLException {
        String sql = "SELECT * FROM BSQTTJ WHERE Cmp=? AND QttNo=? ORDER BY Prio, Seq ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, QttNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void selectQttr(BSHJ0QTT form, String rsName, String Cmp, String QttNo) throws SQLException {
        String sql = "SELECT * FROM BSQTTR WHERE Cmp=? AND QttNo=? ORDER BY Seq ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, QttNo);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
