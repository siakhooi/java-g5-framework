/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.ST;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.G.BSGL1ST;
import com.mysoftwarehouse.bs.CG.G.BSGL2ST;
import com.mysoftwarehouse.bs.CG.G.BSGL3ST;
import com.mysoftwarehouse.bs.CG.G.BSGS0ST;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ST {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSST WHERE Cmp=? ";
        String psName = "ST.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasST(UserFormInterface form, String cmp, String trm) throws SQLException {
        String rsName = "ST.has";
        String sql = "SELECT * FROM BSST WHERE Cmp=? AND Trm=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, cmp);
        form.cmd().db.setParameter(rsName, 2, trm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(UserFormInterface form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSST WHERE Cmp=? ORDER BY Trm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void select_AllInv(BSGL2ST form, String rsName, String Cmp) throws SQLException {
        String sql = "SELECT * FROM BSST WHERE Cmp=? AND ForInv=? ORDER BY Trm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2,
                form.cmd().data.boolean2String(true));
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void select_AllQtt(BSGL1ST form, String rsName, String Cmp) throws SQLException {
        String sql = "SELECT * FROM BSST WHERE Cmp=? AND ForQtt=? ORDER BY Trm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2,
                form.cmd().data.boolean2String(true));
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void select_AllRcp(BSGL3ST form, String rsName, String Cmp) throws SQLException {
        String sql = "SELECT * FROM BSST WHERE Cmp=? AND ForRcp=? ORDER BY Trm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2,
                form.cmd().data.boolean2String(true));
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static boolean select(UserFormInterface form, String rsName, String Cmp, String Trm) throws SQLException {
        String sql = "SELECT * FROM BSST WHERE Cmp=? AND Trm=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Trm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void insert(UserFormInterface form, String Cmp, String Trm, String Nme, String ForQtt, String ForInv, String ForRcp, String Status) throws SQLException {
        String sql = "INSERT INTO BSST ( " +
                "Cmp, Trm, Nme, ForQtt, ForInv, ForRcp, Status) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?) ";
        String psName = "ST.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Trm);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, ForQtt);
        form.cmd().db.setParameter(psName, i++, ForInv);
        form.cmd().db.setParameter(psName, i++, ForRcp);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.execUpdate(psName);

        STH.createInsert(form, Cmp, Trm);
    }

    public static void update(BSGS0ST form, String Cmp, String Trm, String Nme, String ForQtt, String ForInv, String ForRcp, String Status) throws SQLException {
        String sql = "UPDATE BSST SET " +
                "Nme=?, ForQtt=?, ForInv=?, ForRcp=?, Status=? " +
                "WHERE Cmp=? AND Trm=? ";
        String psName = "ST.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, ForQtt);
        form.cmd().db.setParameter(psName, i++, ForInv);
        form.cmd().db.setParameter(psName, i++, ForRcp);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Trm);
        form.cmd().db.execUpdate(psName);

        STH.createUpdate(form, Cmp, Trm);
    }

    public static void delete(UserFormInterface form, String Cmp, String Trm) throws SQLException {
        STH.createDelete(form, Cmp, Trm);

        String sql = "DELETE FROM BSST WHERE Cmp=? AND Trm=?";
        String psName = "ST.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Trm);
        form.cmd().db.execUpdate(psName);
    }
}
