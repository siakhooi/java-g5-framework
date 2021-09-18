/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PSI;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.Q.BSQS0PSI;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PSI {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPSI WHERE Cmp=? ";
        String psName = "PSI.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasPSI(UserFormInterface form, String Cmp, String SpcInst) throws SQLException {
        String rsName = "PSI.has";
        String sql = "SELECT * FROM BSPSI WHERE Cmp=? AND SpcInst=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, SpcInst);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(UserFormInterface form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSPSI WHERE Cmp=? ORDER BY SpcInst";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static boolean select(UserFormInterface form, String rsName, String Cmp, String SpcInst) throws SQLException {
        String sql = "SELECT * FROM BSPSI WHERE Cmp=? AND SpcInst=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, SpcInst);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void insert(UserFormInterface form, String Cmp, String SpcInst, String Nme, String Status) throws SQLException {
        String sql = "INSERT INTO BSPSI ( " +
                "Cmp, SpcInst, Nme, Status) " +
                "VALUES(?, ?, ?, ?) ";
        String psName = "PSI.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, SpcInst);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.execUpdate(psName);

        PSIH.createInsert(form, Cmp, SpcInst);
    }

    public static void update(BSQS0PSI form, String Cmp, String SpcInst, String Nme, String Status) throws SQLException {
        String sql = "UPDATE BSPSI SET " +
                "Nme=?, Status=? " +
                "WHERE Cmp=? AND SpcInst=? ";
        String psName = "PSI.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, SpcInst);
        form.cmd().db.execUpdate(psName);

        PSIH.createUpdate(form, Cmp, SpcInst);
    }

    public static void delete(UserFormInterface form, String Cmp, String SpcInst) throws SQLException {
        PSIH.createDelete(form, Cmp, SpcInst);

        String sql = "DELETE FROM BSPSI WHERE Cmp=? AND SpcInst=?";
        String psName = "PSI.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, SpcInst);
        form.cmd().db.execUpdate(psName);
    }
}
