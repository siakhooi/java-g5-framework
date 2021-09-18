/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SA;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.E.BSEL1SA;
import com.mysoftwarehouse.bs.CG.E.BSES0SA;
import com.mysoftwarehouse.bs.data.SaEnum;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class SA {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSSA WHERE Cmp=? ";
        String psName = "SA.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasSA(UserFormInterface form, String Cmp, String Adj) throws SQLException {
        String rsName = "SA.has";
        return select(form, rsName, Cmp, Adj);
    }

    public static boolean select(UserFormInterface form, String rsName, String Cmp, String Adj) throws SQLException {
        String sql = "SELECT * FROM BSSA WHERE Cmp=? AND Adj=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Adj);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void delete(UserFormInterface form, String Cmp, String Adj) throws SQLException {
        SAH.createDelete(form, Cmp, Adj);

        String sql = "DELETE FROM BSSA WHERE Cmp=? AND Adj=?";
        String psName = "SA.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Adj);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(UserFormInterface form, String Cmp, String Adj, String Nme, String Typ, int Prio, BigDecimal Amt, String Dflt, String Status) throws SQLException {
        String sql = "INSERT INTO BSSA ( " +
                "Cmp, Adj, Nme, Typ, Prio, Amt, Dflt, Status) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
        String psName = "SA.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Adj);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Typ);
        form.cmd().db.setParameter(psName, i++, Prio);
        form.cmd().db.setParameter(psName, i++, Amt);
        form.cmd().db.setParameter(psName, i++, Dflt);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.execUpdate(psName);

        SAH.createInsert(form, Cmp, Adj);
    }

    public static void select_All(UserFormInterface form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSSA WHERE Cmp=? ORDER BY Prio, Adj";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void select_AllActive(BSEL1SA form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSSA WHERE Cmp=? AND Status=? ORDER BY Prio, Adj";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, SaEnum.Status.A.typ);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(BSES0SA form, String Cmp, String Adj, String Nme, String Typ, int Prio, BigDecimal Amt, String Dflt, String Status) throws SQLException {
        String sql = "UPDATE BSSA SET " +
                "Nme=?, Typ=?, Prio=?, Amt=?, Dflt=?, Status=? " +
                "WHERE Cmp=? AND Adj=? ";
        String psName = "SA.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Typ);
        form.cmd().db.setParameter(psName, i++, Prio);
        form.cmd().db.setParameter(psName, i++, Amt);
        form.cmd().db.setParameter(psName, i++, Dflt);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Adj);
        form.cmd().db.execUpdate(psName);

        SAH.createUpdate(form, Cmp, Adj);
    }
}
