/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PIV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.NO.O.BSOS0PIV;
import com.mysoftwarehouse.bs.NO.O.BSOS0PIVD;
import com.mysoftwarehouse.bs.NO.O.BSOT0PIVE;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class PIVE {

    public static void copyFrmPiv(BSOS0PIV form, String Cmp, String ToPivNo, String FrmPivNo) throws SQLException {
        String sql = "INSERT INTO BSPIVE (" +
                "Cmp, PivNo, Itm, Seq, Text) " +
                "SELECT Cmp, ?, Itm, Seq, Text " +
                "FROM BSPIVE " +
                "WHERE Cmp=? " +
                "AND PivNo=? ";
        String psName = "PIVE.cpPiv";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToPivNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmPivNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmPi(BSOS0PIVD form, String Cmp, String PivNo, String Itm) throws SQLException {
        String sql = "INSERT INTO BSPIVE (" +
                "Cmp, PivNo, Itm, Seq, Text) " +
                "SELECT Cmp, ?, Itm, Seq, Text " +
                "FROM BSPID " +
                "WHERE Cmp=? " +
                "AND Itm=? ";
        String psName = "PIVE.cpPi";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPIVE WHERE Cmp=? ";
        String psName = "PIVE.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String PivNo) throws SQLException {
        String sql = "DELETE FROM BSPIVE WHERE Cmp=? AND PivNo=?";
        String psName = "PIVE.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PivNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String PivNo, String Itm) throws SQLException {
        String sql = "DELETE FROM BSPIVE WHERE Cmp=? AND PivNo=? AND Itm=? ";
        String psName = "PIVE.D2";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PivNo);
        form.cmd().db.setParameter(psName, 3, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void selectAll(UserFormInterface form, String rsName,
            String Cmp, String PivNo, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSPIVE WHERE Cmp=? AND PivNo=? AND Itm=? ORDER BY Seq";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PivNo);
        form.cmd().db.setParameter(rsName, 3, Itm);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void writeAll(BSOT0PIVE form, String Cmp,
            String PivNo, String Itm, Vector<String> allText) throws SQLException {
        String sql = "DELETE FROM BSPIVE WHERE Cmp=? AND PivNo=? AND Itm=? ";
        String psName = "PIVE.W";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PivNo);
        form.cmd().db.setParameter(psName, 3, Itm);
        form.cmd().db.execUpdate(psName);
        sql = "INSERT INTO BSPIVE (Cmp, PivNo, Itm, Seq, Text) VALUES(?, ?, ?, ?, ?)";
        psName = "PIVE.I";
        form.cmd().db.setStatement(psName, sql);
        int Seq = 0;
        for (String t1 : allText) {
            int i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.setParameter(psName, i++, PivNo);
            form.cmd().db.setParameter(psName, i++, Itm);
            form.cmd().db.setParameter(psName, i++, Seq++);
            form.cmd().db.setParameter(psName, i++, t1);
            form.cmd().db.execUpdate(psName);
        }
    }
}
