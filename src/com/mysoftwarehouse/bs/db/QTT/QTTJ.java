/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.QTT;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.H.BSHL0QTTJ;
import com.mysoftwarehouse.bs.HJ.H.BSHS0QTT;
import com.mysoftwarehouse.bs.HJ.H.BSHS0QTTJ;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class QTTJ {

    public static void copyFrmQtt(BSHS0QTT form, String Cmp, String ToQttNo, String FrmQttNo) throws SQLException {
        String sql = "INSERT INTO BSQTTJ (" +
                "Cmp, QttNo, Seq, Adj, Nme, Typ, Prio, Amt) " +
                "SELECT Cmp, ?, Seq, Adj, Nme, Typ, Prio, Amt " +
                "FROM BSQTTJ " +
                "WHERE Cmp=? " +
                "AND QttNo=? ";
        String psName = "QTTJ.cpQtt";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToQttNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmQttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSQTTJ WHERE Cmp=? ";
        String psName = "QTTJ.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String QttNo) throws SQLException {
        String sql = "DELETE FROM BSQTTJ WHERE Cmp=? AND QttNo=?";
        String psName = "QTTJ.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String QttNo, int Seq) throws SQLException {
        String sql = "DELETE FROM BSQTTJ WHERE Cmp=? AND QttNo=? AND Seq=? ";
        String psName = "QTTJ.D2";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, QttNo);
        form.cmd().db.setParameter(psName, 3, Seq);
        form.cmd().db.execUpdate(psName);
    }

    public static void select_All(BSHL0QTTJ form, String rsName, String Cmp, String QttNo)
            throws SQLException {
        String sql = "SELECT * FROM BSQTTJ WHERE Cmp=? AND QttNo=? ORDER BY Prio, Seq ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, QttNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static boolean select(BSHS0QTTJ form, String rsName, String Cmp, String QttNo, int Seq) throws SQLException {
        String sql = "SELECT * FROM BSQTTJ WHERE Cmp=? AND QttNo=? AND Seq=? ";
        form.cmd().db.setStatement(rsName, sql);
        int i = 1;
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, QttNo);
        form.cmd().db.setParameter(rsName, i++, Seq);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    private static int _getNextSeq(BSHS0QTTJ form, String Cmp, String QttNo) throws SQLException {
        String sql = "SELECT MAX(Seq) MS FROM BSQTTJ WHERE Cmp=? AND QttNo=? ";
        String psName = "QTTJ.NS";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
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

    public static void insert(BSHS0QTTJ form, String Cmp,
            String QttNo, String Adj) throws SQLException {
        int Seq = _getNextSeq(form, Cmp, QttNo);
        String sql = "INSERT INTO BSQTTJ (" +
                "Cmp, QttNo, Seq, Adj, Nme, Typ, Prio, Amt" +
                ") SELECT ?, ?, ?, Adj, Nme, Typ, Prio, Amt FROM BSSA " +
                "WHERE Cmp=? AND Adj=? ";
        String psName = "QTTJ.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.setParameter(psName, i++, Seq);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Adj);
        form.cmd().db.execUpdate(psName);
    }


    public static void update(BSHS0QTTJ form, String Cmp, String QttNo, int Seq,
            String Nme, String Typ, int Prio, BigDecimal Amt) throws SQLException {
        String sql = "UPDATE BSQTTJ SET " +
                "Nme=?, Typ=?, Prio=?, Amt=? " +
                "WHERE Cmp=? AND QttNo=? AND Seq=? ";
        String psName = "QTTJ.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Typ);
        form.cmd().db.setParameter(psName, i++, Prio);
        form.cmd().db.setParameter(psName, i++, Amt);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.setParameter(psName, i++, Seq);
        form.cmd().db.execUpdate(psName);
    }
}
