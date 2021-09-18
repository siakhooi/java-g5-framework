/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.RCP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.J.BSJP0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJS0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJT0RCPR;
import com.mysoftwarehouse.bs.db.CFG.CFG;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class RCPR {

    public static void copyFrmInv(BSJP0RCP form, String Cmp, String RcpNo, String InvNo) throws SQLException {
        String sql = "INSERT INTO BSRCPR (Cmp, RcpNo, Seq, Text)" +
                "SELECT Cmp, ?, Seq, Text " +
                "FROM BSINVR WHERE Cmp=? AND InvNo=? ";
        String psName = "RCPR.cpInv";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmRcp(BSJS0RCP form, String Cmp, String ToRcpNo, String FrmRcpNo) throws SQLException {
        String sql = "INSERT INTO BSRCPR (" +
                "Cmp, RcpNo, Seq, Text) " +
                "SELECT Cmp, ?, Seq, Text " +
                "FROM BSRCPR " +
                "WHERE Cmp=? " +
                "AND RcpNo=? ";
        String psName = "RCPR.cpRcp";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToRcpNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmRcpNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSRCPR WHERE Cmp=? ";
        String psName = "RCPR.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String RcpNo) throws SQLException {
        String sql = "DELETE FROM BSRCPR WHERE Cmp=? AND RcpNo=?";
        String psName = "RCPR.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, RcpNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void loadDefaultTerm(BSJS0RCP form, String Cmp, String RcpNo) throws SQLException {
        String rsName = "RCPR.L";
        CFG.select(form, rsName, Cmp);
        String Trm = form.cmd().db.getString(rsName, "DfltTrmRcp");

        String sql = "INSERT INTO BSRCPR (Cmp, RcpNo, Seq, Text) " +
                "SELECT Cmp, ?, Seq, Text FROM BSSTD " +
                "WHERE Cmp=? AND Trm=? ";
        String psName = "RCPR.L";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Trm);
        form.cmd().db.execUpdate(psName);
    }

    public static void select_All(UserFormInterface form, String rsName, String Cmp, String RcpNo) throws SQLException {
        String sql = "SELECT * FROM BSRCPR WHERE Cmp=? AND RcpNo=? ORDER BY Seq";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, RcpNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void writeAll(BSJT0RCPR form, String Cmp, String RcpNo, Vector<String> allText) throws SQLException {
        String sql = "DELETE FROM BSRCPR WHERE Cmp=? AND RcpNo=? ";
        String psName = "RCPR.W";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, RcpNo);
        form.cmd().db.execUpdate(psName);
        sql = "INSERT INTO BSRCPR (Cmp, RcpNo, Seq, Text) VALUES(?, ?, ?, ?)";
        psName = "RCPR.I";
        form.cmd().db.setStatement(psName, sql);
        int Seq = 0;
        for (String t1 : allText) {
            int i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.setParameter(psName, i++, RcpNo);
            form.cmd().db.setParameter(psName, i++, Seq++);
            form.cmd().db.setParameter(psName, i++, t1);
            form.cmd().db.execUpdate(psName);
        }
    }
}
