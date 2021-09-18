/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PI;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.L.BSLP0PI;
import com.mysoftwarehouse.bs.KM.L.BSLS0PI;
import com.mysoftwarehouse.bs.KM.L.BSLT0PID;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class PID {

    public static void select_All(UserFormInterface form, String rsName, String Cmp, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSPID WHERE Cmp=? AND Itm=? ORDER BY Seq";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void writeAll(BSLT0PID form, String Cmp, String Itm, Vector<String> allText) throws SQLException {
        String sql = "DELETE FROM BSPID WHERE Cmp=? AND Itm=? ";
        String psName = "PID.W";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Itm);
        form.cmd().db.execUpdate(psName);
        sql = "INSERT INTO BSPID (Cmp, Itm, Seq, Text) VALUES(?, ?, ?, ?)";
        psName = "PID.I";
        form.cmd().db.setStatement(psName, sql);
        int Seq = 0;
        for (String t1 : allText) {
            int i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.setParameter(psName, i++, Itm);
            form.cmd().db.setParameter(psName, i++, Seq++);
            form.cmd().db.setParameter(psName, i++, t1);
            form.cmd().db.execUpdate(psName);
        }
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPID WHERE Cmp=? ";
        String psName = "PID.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmPi(BSLS0PI form, String Cmp, String FromItm, String ToItm) throws SQLException {
        String sql = "INSERT INTO BSPID (Cmp, Itm, Seq, Text) " +
                "SELECT Cmp, ?, Seq, Text FROM BSPID WHERE Cmp=? AND Itm=? ";
        String psName = "PID.cpPi";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToItm);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FromItm);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSLP0PI form, String Cmp, String Itm) throws SQLException {
        String sql = "DELETE FROM BSPID WHERE Cmp=? AND Itm=?";
        String psName = "PID.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Itm);
        form.cmd().db.execUpdate(psName);
    }
}
