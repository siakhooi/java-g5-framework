/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.I.BSIP0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIS0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIT0INVR;
import com.mysoftwarehouse.bs.db.CFG.CFG;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class INVR {

    public static void copyFrmInv(BSIS0INV form, String Cmp, String ToInvNo, String FrmInvNo) throws SQLException {
        String sql = "INSERT INTO BSINVR (" +
                "Cmp, InvNo, Seq, Text) " +
                "SELECT Cmp, ?, Seq, Text " +
                "FROM BSINVR " +
                "WHERE Cmp=? " +
                "AND InvNo=? ";
        String psName = "INVR.cpInv";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToInvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmInvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmQtt(BSIP0INV form, String Cmp, String InvNo, String QttNo) throws SQLException {
        String sql = "INSERT INTO BSINVR (Cmp, InvNo, Seq, Text)" +
                "SELECT Cmp, ?, Seq, Text " +
                "FROM BSQTTR WHERE Cmp=? AND QttNo=? ";
        String psName = "INVR.cpQtt";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSINVR WHERE Cmp=? ";
        String psName = "INVR.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String InvNo) throws SQLException {
        String sql = "DELETE FROM BSINVR WHERE Cmp=? AND InvNo=?";
        String psName = "INVR.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, InvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void loadDefaultTerm(BSIS0INV form, String Cmp, String InvNo) throws SQLException {
        String rsName = "INVR.L";
        CFG.select(form, rsName, Cmp);
        String Trm = form.cmd().db.getString(rsName, "DfltTrmInv");

        String sql = "INSERT INTO BSINVR (Cmp, InvNo, Seq, Text) " +
                "SELECT Cmp, ?, Seq, Text FROM BSSTD " +
                "WHERE Cmp=? AND Trm=? ";
        String psName = "INVR.L";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Trm);
        form.cmd().db.execUpdate(psName);
    }

    public static void select_All(UserFormInterface form, String rsName, String Cmp, String InvNo)
            throws SQLException {
        String sql = "SELECT * FROM BSINVR WHERE Cmp=? AND InvNo=? ORDER BY Seq";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, InvNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void writeAll(BSIT0INVR form, String Cmp, String InvNo, Vector<String> allText) throws SQLException {
        String sql = "DELETE FROM BSINVR WHERE Cmp=? AND InvNo=? ";
        String psName = "BSINVR.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, InvNo);
        form.cmd().db.execUpdate(psName);
        sql = "INSERT INTO BSINVR (Cmp, InvNo, Seq, Text) VALUES(?, ?, ?, ?)";
        psName = "BSINVR.I";
        form.cmd().db.setStatement(psName, sql);
        int Seq = 0;
        for (String t1 : allText) {
            int i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.setParameter(psName, i++, InvNo);
            form.cmd().db.setParameter(psName, i++, Seq++);
            form.cmd().db.setParameter(psName, i++, t1);
            form.cmd().db.execUpdate(psName);
        }
    }
}
