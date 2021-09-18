/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SI;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.D.BSDP0SI;
import com.mysoftwarehouse.bs.CG.D.BSDS0SI;
import com.mysoftwarehouse.bs.CG.D.BSDT0SID;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class SID {

    public static void copyFrmSi(BSDS0SI form, String Cmp, String FromItm, String ToItm) throws SQLException {
        String sql = "INSERT INTO BSSID (Cmp, Itm, Seq, Text) " +
                "SELECT Cmp, ?, Seq, Text FROM BSSID WHERE Cmp=? AND Itm=? ";
        String psName = "SID.cpSi";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToItm);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FromItm);
        form.cmd().db.execUpdate(psName);
    }

    public static void select_All(UserFormInterface form, String rsName, String Cmp, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSSID WHERE Cmp=? AND Itm=? ORDER BY Seq";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void writeAll(BSDT0SID form, String Cmp, String Itm, Vector<String> allText) throws SQLException {
        String sql = "DELETE FROM BSSID WHERE Cmp=? AND Itm=? ";
        String psName = "SID.W";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Itm);
        form.cmd().db.execUpdate(psName);
        sql = "INSERT INTO BSSID (Cmp, Itm, Seq, Text) VALUES(?, ?, ?, ?)";
        psName = "SID.I";
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
        String sql = "DELETE FROM BSSID WHERE Cmp=? ";
        String psName = "SID.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSDP0SI form, String Cmp, String Itm) throws SQLException {
        String sql = "DELETE FROM BSSID WHERE Cmp=? AND Itm=?";
        String psName = "SID.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Itm);
        form.cmd().db.execUpdate(psName);
    }
}
