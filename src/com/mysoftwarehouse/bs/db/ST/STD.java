/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.ST;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.G.BSGS0ST;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class STD {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSSTD WHERE Cmp=? ";
        String psName = "STD.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String Trm) throws SQLException {
        String sql = "DELETE FROM BSSTD WHERE Cmp=? AND Trm=? ";
        String psName = "STD.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Trm);
        form.cmd().db.execUpdate(psName);
    }

    public static void selectAll(UserFormInterface form, String rsName, String Cmp, String Trm) throws SQLException {
        String sql = "SELECT * FROM BSSTD WHERE Cmp=? AND Trm=? ORDER BY Seq";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Trm);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void copyFrmSt(BSGS0ST form, String Cmp, String ToTrm,String FromTrm) throws SQLException {
        String sql = "INSERT INTO BSSTD (Cmp, Trm, Seq, Text) " +
                "SELECT Cmp, ?, Seq, Text FROM BSSTD WHERE Cmp=? AND Trm=? ";
        String psName = "STD.cpSt";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToTrm);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FromTrm);
        form.cmd().db.execUpdate(psName);
    }

    public static void writeAll(UserFormInterface form, String Cmp, String Trm, Vector<String> allText) throws SQLException {
        String sql = "DELETE FROM BSSTD WHERE Cmp=? AND Trm=? ";
        String psName = "STD.W";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Trm);
        form.cmd().db.execUpdate(psName);
        sql = "INSERT INTO BSSTD (Cmp, Trm, Seq, Text) VALUES(?, ?, ?, ?)";
        psName = "STD.I";
        form.cmd().db.setStatement(psName, sql);
        int Seq = 0;
        for (String t1 : allText) {
            int i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.setParameter(psName, i++, Trm);
            form.cmd().db.setParameter(psName, i++, Seq++);
            form.cmd().db.setParameter(psName, i++, t1);
            form.cmd().db.execUpdate(psName);
        }

    }
}
