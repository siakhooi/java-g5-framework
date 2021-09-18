/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PI;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.L.BSLL0PI;
import com.mysoftwarehouse.bs.KM.L.BSLL1PI;
import com.mysoftwarehouse.bs.KM.L.BSLP0PI;
import com.mysoftwarehouse.bs.KM.L.BSLS0PI;
import com.mysoftwarehouse.bs.data.PiEnum;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PI {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPI WHERE Cmp=? ";
        String psName = "PI.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSLP0PI form, String Cmp, String Itm) throws SQLException {
        PIH.createDelete(form, Cmp, Itm);

        String sql = "DELETE FROM BSPI WHERE Cmp=? AND Itm=?";
        String psName = "PI.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasItm(BSLS0PI form, String Cmp, String Itm) throws SQLException {
        String rsName = "PI.has";
        String sql = "SELECT * FROM BSPI WHERE Cmp=? AND Itm=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSLL0PI form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSPI WHERE Cmp=? ORDER BY Itm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static boolean select(UserFormInterface form, String rsName, String Cmp, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSPI WHERE Cmp=? AND Itm=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_AllActive(BSLL1PI form, String rsName, String Cmp) throws SQLException {
        String sql = "SELECT * FROM BSPI WHERE Cmp=? AND Status=? ORDER BY Itm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PiEnum.Status.A.typ);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void insert(BSLS0PI form, String Cmp, String Itm, String Nme, String Status) throws SQLException {
        String sql = "INSERT INTO BSPI ( " +
                "Cmp, Itm, Nme, Status) " +
                "VALUES(?, ?, ?, ?) ";
        String psName = "PI.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.execUpdate(psName);

        PIH.createInsert(form, Cmp, Itm);
    }

    public static void update(BSLS0PI form, String Cmp, String Itm, String Nme, String Status) throws SQLException {
        String sql = "UPDATE BSPI SET " +
                "Nme=?, Status=? " +
                "WHERE Cmp=? AND Itm=? ";
        String psName = "PI.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);

        PIH.createUpdate(form, Cmp, Itm);
    }
}
