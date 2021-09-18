/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.I.BSIS0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIS0INVD;
import com.mysoftwarehouse.bs.HJ.I.BSIT0INVE;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class INVE {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSINVE WHERE Cmp=? ";
        String psName = "INVE.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String InvNo) throws SQLException {
        String sql = "DELETE FROM BSINVE WHERE Cmp=? AND InvNo=?";
        String psName = "INVE.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, InvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String InvNo, String Itm) throws SQLException {
        String sql = "DELETE FROM BSINVE WHERE Cmp=? AND InvNo=? AND Itm=? ";
        String psName = "INVE.D2";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, InvNo);
        form.cmd().db.setParameter(psName, 3, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmSi(BSIS0INVD form, String Cmp, String InvNo, String Itm) throws SQLException {
        String sql = "INSERT INTO BSINVE (" +
                "Cmp, InvNo, Itm, Seq, Text) " +
                "SELECT Cmp, ?, Itm, Seq, Text " +
                "FROM BSSID " +
                "WHERE Cmp=? " +
                "AND Itm=? ";
        String psName = "INVE.cpSi";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmInv(BSIS0INV form, String Cmp, String ToInvNo, String FrmInvNo) throws SQLException {
        String sql = "INSERT INTO BSINVE (" +
                "Cmp, InvNo, Itm, Seq, Text) " +
                "SELECT Cmp, ?, Itm, Seq, Text " +
                "FROM BSINVE " +
                "WHERE Cmp=? " +
                "AND InvNo=? ";
        String psName = "INVE.cpInv";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToInvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmInvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void selectAll(UserFormInterface form, String rsName,
            String Cmp, String InvNo, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSINVE WHERE Cmp=? AND InvNo=? AND Itm=? ORDER BY Seq";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, InvNo);
        form.cmd().db.setParameter(rsName, 3, Itm);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void writeAll(BSIT0INVE form, String Cmp,
            String InvNo, String Itm, Vector<String> allText) throws SQLException {
        String sql = "DELETE FROM BSINVE WHERE Cmp=? AND InvNo=? AND Itm=? ";
        String psName = "INVE.W";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, InvNo);
        form.cmd().db.setParameter(psName, 3, Itm);
        form.cmd().db.execUpdate(psName);
        sql = "INSERT INTO BSINVE (Cmp, InvNo, Itm, Seq, Text) VALUES(?, ?, ?, ?, ?)";
        psName = "INVE.I";
        form.cmd().db.setStatement(psName, sql);
        int Seq = 0;
        for (String t1 : allText) {
            int i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.setParameter(psName, i++, InvNo);
            form.cmd().db.setParameter(psName, i++, Itm);
            form.cmd().db.setParameter(psName, i++, Seq++);
            form.cmd().db.setParameter(psName, i++, t1);
            form.cmd().db.execUpdate(psName);
        }
    }
}
