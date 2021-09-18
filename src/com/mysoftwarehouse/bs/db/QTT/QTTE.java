/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.QTT;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.H.BSHS0QTT;
import com.mysoftwarehouse.bs.HJ.H.BSHS0QTTD;
import com.mysoftwarehouse.bs.HJ.H.BSHT0QTTE;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class QTTE {

    public static void copyFrmQtt(BSHS0QTT form, String Cmp, String ToQttNo, String FrmQttNo) throws SQLException {
        String sql = "INSERT INTO BSQTTE (" +
                "Cmp, QttNo, Itm, Seq, Text) " +
                "SELECT Cmp, ?, Itm, Seq, Text " +
                "FROM BSQTTE " +
                "WHERE Cmp=? " +
                "AND QttNo=? ";
        String psName = "QTTE.cpQtt";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToQttNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmQttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmSi(BSHS0QTTD form, String Cmp, String QttNo, String Itm) throws SQLException {
        String sql = "INSERT INTO BSQTTE (" +
                "Cmp, QttNo, Itm, Seq, Text) " +
                "SELECT Cmp, ?, Itm, Seq, Text " +
                "FROM BSSID " +
                "WHERE Cmp=? " +
                "AND Itm=? ";
        String psName = "QTTE.cpSi";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSQTTE WHERE Cmp=? ";
        String psName = "QTTE.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String QttNo) throws SQLException {
        String sql = "DELETE FROM BSQTTE WHERE Cmp=? AND QttNo=?";
        String psName = "QTTE.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String QttNo, String Itm) throws SQLException {
        String sql = "DELETE FROM BSQTTE WHERE Cmp=? AND QttNo=? AND Itm=? ";
        String psName = "QTTE.D2";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, QttNo);
        form.cmd().db.setParameter(psName, 3, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void selectAll(UserFormInterface form, String rsName,
            String Cmp, String QttNo, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSQTTE WHERE Cmp=? AND QttNo=? AND Itm=? ORDER BY Seq";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, QttNo);
        form.cmd().db.setParameter(rsName, 3, Itm);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void writeAll(BSHT0QTTE form, String Cmp,
            String QttNo, String Itm, Vector<String> allText) throws SQLException {
        String sql = "DELETE FROM BSQTTE WHERE Cmp=? AND QttNo=? AND Itm=? ";
        String psName = "QTTE.W";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, QttNo);
        form.cmd().db.setParameter(psName, 3, Itm);
        form.cmd().db.execUpdate(psName);
        sql = "INSERT INTO BSQTTE (Cmp, QttNo, Itm, Seq, Text) VALUES(?, ?, ?, ?, ?)";
        psName = "QTTE.I";
        form.cmd().db.setStatement(psName, sql);
        int Seq = 0;
        for (String t1 : allText) {
            int i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.setParameter(psName, i++, QttNo);
            form.cmd().db.setParameter(psName, i++, Itm);
            form.cmd().db.setParameter(psName, i++, Seq++);
            form.cmd().db.setParameter(psName, i++, t1);
            form.cmd().db.execUpdate(psName);
        }
    }
}
