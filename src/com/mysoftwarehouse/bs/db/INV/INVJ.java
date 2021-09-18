/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.I.BSIL0INVJ;
import com.mysoftwarehouse.bs.HJ.I.BSIP0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIS0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIS0INVJ;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class INVJ {

    public static void copyFrmInv(BSIS0INV form, String Cmp, String ToInvNo, String FrmInvNo) throws SQLException {
        String sql = "INSERT INTO BSINVJ (" +
                "Cmp, InvNo, Seq, Adj, Nme, Typ, Prio, Amt) " +
                "SELECT Cmp, ?, Seq, Adj, Nme, Typ, Prio, Amt " +
                "FROM BSINVJ " +
                "WHERE Cmp=? " +
                "AND InvNo=? ";
        String psName = "INVJ.cpInv";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToInvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmInvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmQtt(BSIP0INV form, String Cmp, String InvNo, String QttNo) throws SQLException {
        String sql = "INSERT INTO BSINVJ (" +
                "Cmp, InvNo, Seq, Adj, Nme, Typ, Prio, Amt" +
                ") SELECT Cmp, ?, Seq, Adj, Nme, Typ, Prio, Amt " +
                "FROM BSQTTJ WHERE Cmp=? AND QttNo=? ";
        String psName = "INVJ.cpQtt";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSINVJ WHERE Cmp=? ";
        String psName = "INVJ.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String InvNo) throws SQLException {
        String sql = "DELETE FROM BSINVJ WHERE Cmp=? AND InvNo=?";
        String psName = "INVJ.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, InvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String InvNo, int Seq) throws SQLException {
        String sql = "DELETE FROM BSINVJ WHERE Cmp=? AND InvNo=? AND Seq=? ";
        String psName = "INVJ.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, InvNo);
        form.cmd().db.setParameter(psName, 3, Seq);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(BSIS0INVJ form, String Cmp, String InvNo, String Adj) throws SQLException {
        int Seq = _getNextSeq(form, Cmp, InvNo);
        String sql = "INSERT INTO BSINVJ (" +
                "Cmp, InvNo, Seq, Adj, Nme, Typ, Prio, Amt" +
                ") SELECT ?, ?, ?, Adj, Nme, Typ, Prio, Amt FROM BSSA " +
                "WHERE Cmp=? AND Adj=? ";
        String psName = "INVJ.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Seq);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Adj);
        form.cmd().db.execUpdate(psName);
    }

    public static void update(BSIS0INVJ form, String Cmp, String InvNo,
            int Seq, String Adj, String Nme, String Typ, int Prio, BigDecimal Amt) throws SQLException {
        String sql = "UPDATE BSINVJ SET " +
                "Adj=?, Nme=?, Typ=?, Prio=?, Amt=? " +
                "WHERE Cmp=? AND InvNo=? AND Seq=? ";
        String psName = "INVJ.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Adj);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Typ);
        form.cmd().db.setParameter(psName, i++, Prio);
        form.cmd().db.setParameter(psName, i++, Amt);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Seq);
        form.cmd().db.execUpdate(psName);
    }

    private static int _getNextSeq(BSIS0INVJ form, String Cmp, String InvNo) throws SQLException {
        String sql = "SELECT MAX(Seq) MS FROM BSINVJ WHERE Cmp=? AND InvNo=? ";
        String psName = "INVJ.NS";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.execQuery(psName, psName);
        if (form.cmd().db.next(psName)) {
            try {
                i = form.cmd().db.getInteger(psName, "MS");
                return i + 1;
            } catch (SQLException ex) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static boolean select(BSIS0INVJ form, String rsName, String Cmp, String InvNo, int Seq) throws SQLException {
        String sql = "SELECT * FROM BSINVJ WHERE Cmp=? AND InvNo=? AND Seq=? ";
        form.cmd().db.setStatement(rsName, sql);
        int i = 1;
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, InvNo);
        form.cmd().db.setParameter(rsName, i++, Seq);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSIL0INVJ form, String rsName, String Cmp, String InvNo)
            throws SQLException {
        String sql = "SELECT * FROM BSINVJ WHERE Cmp=? AND InvNo=? ORDER BY Prio, Seq ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, InvNo);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
