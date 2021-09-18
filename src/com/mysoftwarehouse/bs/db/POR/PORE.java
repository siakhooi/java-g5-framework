/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.POR;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.NO.N.BSNS0POR;
import com.mysoftwarehouse.bs.NO.N.BSNS0PORD;
import com.mysoftwarehouse.bs.NO.N.BSNT0PORE;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class PORE {

    public static void copyFrmPor(BSNS0POR form, String Cmp, String ToPorNo, String FrmPorNo) throws SQLException {
        String sql = "INSERT INTO BSPORE (" +
                "Cmp, PorNo, Itm, Seq, Text) " +
                "SELECT Cmp, ?, Itm, Seq, Text " +
                "FROM BSPORE " +
                "WHERE Cmp=? " +
                "AND PorNo=? ";
        String psName = "PORE.cpPor";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToPorNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmPorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmPi(BSNS0PORD form, String Cmp, String PorNo, String Itm) throws SQLException {
        String sql = "INSERT INTO BSPORE (" +
                "Cmp, PorNo, Itm, Seq, Text) " +
                "SELECT Cmp, ?, Itm, Seq, Text " +
                "FROM BSPID " +
                "WHERE Cmp=? " +
                "AND Itm=? ";
        String psName = "PORE.cpPi";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPORE WHERE Cmp=? ";
        String psName = "PORE.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String PorNo) throws SQLException {
        String sql = "DELETE FROM BSPORE WHERE Cmp=? AND PorNo=?";
        String psName = "PORE.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String PorNo, String Itm) throws SQLException {
        String sql = "DELETE FROM BSPORE WHERE Cmp=? AND PorNo=? AND Itm=? ";
        String psName = "PORE.D2";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PorNo);
        form.cmd().db.setParameter(psName, 3, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void selectAll(UserFormInterface form, String rsName,
            String Cmp, String PorNo, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSPORE WHERE Cmp=? AND PorNo=? AND Itm=? ORDER BY Seq";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PorNo);
        form.cmd().db.setParameter(rsName, 3, Itm);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void writeAll(BSNT0PORE form, String Cmp,
            String PorNo, String Itm, Vector<String> allText) throws SQLException {
        String sql = "DELETE FROM BSPORE WHERE Cmp=? AND PorNo=? AND Itm=? ";
        String psName = "PORE.W";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PorNo);
        form.cmd().db.setParameter(psName, 3, Itm);
        form.cmd().db.execUpdate(psName);
        sql = "INSERT INTO BSPORE (Cmp, PorNo, Itm, Seq, Text) VALUES(?, ?, ?, ?, ?)";
        psName = "PORE.I";
        form.cmd().db.setStatement(psName, sql);
        int Seq = 0;
        for (String t1 : allText) {
            int i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.setParameter(psName, i++, PorNo);
            form.cmd().db.setParameter(psName, i++, Itm);
            form.cmd().db.setParameter(psName, i++, Seq++);
            form.cmd().db.setParameter(psName, i++, t1);
            form.cmd().db.execUpdate(psName);
        }
    }
}
