/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.QTT;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.H.BSHS0QTT;
import com.mysoftwarehouse.bs.HJ.H.BSHT0QTTR;
import com.mysoftwarehouse.bs.db.CFG.CFG;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class QTTR {

    public static void copyFrmQtt(BSHS0QTT form, String Cmp, String ToQttNo, String FrmQttNo) throws SQLException {
        String sql = "INSERT INTO BSQTTR (" +
                "Cmp, QttNo, Seq, Text) " +
                "SELECT Cmp, ?, Seq, Text " +
                "FROM BSQTTR " +
                "WHERE Cmp=? " +
                "AND QttNo=? ";
        String psName = "QTTR.cpQtt";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToQttNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmQttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSQTTR WHERE Cmp=? ";
        String psName = "QTTR.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String QttNo) throws SQLException {
        String sql = "DELETE FROM BSQTTR WHERE Cmp=? AND QttNo=?";
        String psName = "QTTR.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void loadDefaultTerm(BSHS0QTT form, String Cmp, String QttNo) throws SQLException {
        String rsName = "QTTR.L";
        CFG.select(form, rsName, Cmp);
        String Trm = form.cmd().db.getString(rsName, "DfltTrmQtt");

        String sql = "INSERT INTO BSQTTR (Cmp, QttNo, Seq, Text) " +
                "SELECT Cmp, ?, Seq, Text FROM BSSTD " +
                "WHERE Cmp=? AND Trm=? ";
        String psName = "QTTR.L";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Trm);
        form.cmd().db.execUpdate(psName);
    }

    public static void selectAll(UserFormInterface form, String rsName, String Cmp, String QttNo) throws SQLException {
        String sql = "SELECT * FROM BSQTTR WHERE Cmp=? AND QttNo=? ORDER BY Seq";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, QttNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void writeAll(BSHT0QTTR form, String Cmp, String QttNo, 
            Vector<String> allText) throws SQLException {
        String sql = "DELETE FROM BSQTTR WHERE Cmp=? AND QttNo=? ";
        String psName = "QTTR.W";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, QttNo);
        form.cmd().db.execUpdate(psName);
        sql = "INSERT INTO BSQTTR (Cmp, QttNo, Seq, Text) VALUES(?, ?, ?, ?)";
        psName = "QTTR.I";
        form.cmd().db.setStatement(psName, sql);
        int Seq = 0;
        for (String t1 : allText) {
            int i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.setParameter(psName, i++, QttNo);
            form.cmd().db.setParameter(psName, i++, Seq++);
            form.cmd().db.setParameter(psName, i++, t1);
            form.cmd().db.execUpdate(psName);
        }
    }
}
