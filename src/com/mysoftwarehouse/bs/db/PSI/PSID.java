/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PSI;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.Q.BSQS0PSI;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class PSID {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPSID WHERE Cmp=? ";
        String psName = "PSID.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String SpcInst) throws SQLException {
        String sql = "DELETE FROM BSPSID WHERE Cmp=? AND SpcInst=? ";
        String psName = "PSID.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, SpcInst);
        form.cmd().db.execUpdate(psName);
    }

    public static void selectAll(UserFormInterface form, String rsName, String Cmp, String SpcInst) throws SQLException {
        String sql = "SELECT * FROM BSPSID WHERE Cmp=? AND SpcInst=? ORDER BY Seq";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, SpcInst);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void copyFrmPsi(BSQS0PSI form, String Cmp, String FromSpcInst, String ToSpcInst) throws SQLException {
        String sql = "INSERT INTO BSPSID (Cmp, SpcInst, Seq, Text) " +
                "SELECT Cmp, ?, Seq, Text FROM BSPSID WHERE Cmp=? AND SpcInst=? ";
        String psName = "PSID.cpPsi";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToSpcInst);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FromSpcInst);
        form.cmd().db.execUpdate(psName);
    }

    public static void writeAll(UserFormInterface form, String Cmp, String SpcInst, Vector<String> allText) throws SQLException {
        String sql = "DELETE FROM BSPSID WHERE Cmp=? AND SpcInst=? ";
        String psName = "PSID.W";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, SpcInst);
        form.cmd().db.execUpdate(psName);
        sql = "INSERT INTO BSPSID (Cmp, SpcInst, Seq, Text) VALUES(?, ?, ?, ?)";
        psName = "PSID.I";
        form.cmd().db.setStatement(psName, sql);
        int Seq = 0;
        for (String t1 : allText) {
            int i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.setParameter(psName, i++, SpcInst);
            form.cmd().db.setParameter(psName, i++, Seq++);
            form.cmd().db.setParameter(psName, i++, t1);
            form.cmd().db.execUpdate(psName);
        }

    }
}
