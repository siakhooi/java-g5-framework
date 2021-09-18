/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PI;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.L.BSLL0PIP;
import com.mysoftwarehouse.bs.KM.L.BSLL1PIP;
import com.mysoftwarehouse.bs.KM.L.BSLP0PI;
import com.mysoftwarehouse.bs.KM.L.BSLS0PIP;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PIP {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPIP WHERE Cmp=? ";
        String psName = "PIP.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSLP0PI form, String Cmp, String Itm) throws SQLException {
        //PIPH.createDelete(form, Cmp, Itm);

        String sql = "DELETE FROM BSPIP WHERE Cmp=? AND Itm=?";
        String psName = "PIP.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static BigDecimal getPrice(UserFormInterface form, String Cmp, String Itm, String Sup) throws SQLException {
        String rsName = "PIP.Price";
        if (select(form, rsName, Cmp, Itm, Sup)) {
            return form.cmd().db.getBigDecimal(rsName, "Price");
        } else {
            return BigDecimal.ZERO;
        }
    }

    public static boolean hasItm(UserFormInterface form, String Cmp, String Itm, String Sup) throws SQLException {
        String rsName = "PIP.has";
        String sql = "SELECT * FROM BSPIP WHERE Cmp=? AND Sup=? AND Itm=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Sup);
        form.cmd().db.setParameter(rsName, 3, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void selectAllBySupplier(BSLL1PIP form, String rsName, String Cmp, String Sup) throws SQLException {
        String sql = "SELECT * FROM BSPIP WHERE Cmp=? AND Sup=? ORDER BY Itm ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Sup);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void delete(UserFormInterface form, String Cmp, String Itm, String Sup) throws SQLException {
        PIPH.createDelete(form, Cmp, Itm, Sup);

        String sql = "DELETE FROM BSPIP " +
                "WHERE Cmp=? AND Itm=? AND Sup=? ";
        String psName = "PIP.D2";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(UserFormInterface form, String rsName, String Cmp, String Itm, String Sup) throws SQLException {
        String sql = "SELECT * FROM BSPIP WHERE Cmp=? AND Itm=? AND Sup=? ";
        form.cmd().db.setStatement(rsName, sql);
        int i = 1;
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, Itm);
        form.cmd().db.setParameter(rsName, i++, Sup);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_AllActive(BSLL0PIP form, String rsName, String Cmp, String Itm)
            throws SQLException {
        String sql = "SELECT * FROM BSPIP WHERE Cmp=? AND Itm=? ORDER BY Sup";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void insert(BSLS0PIP form, String Cmp, String Itm, String Sup, BigDecimal Price, String Status, String Remark) throws SQLException {
        String sql = "INSERT INTO BSPIP ( " +
                "Cmp, Itm, Sup, Price, Status, Remark) " +
                "VALUES(?, ?, ?, ?, ?, ?) ";
        String psName = "PIP.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);

        PIPH.createInsert(form, Cmp, Itm, Sup);
    }

    public static void update(BSLS0PIP form, String Cmp, String Itm, String Sup, BigDecimal Price, String Status, String Remark) throws SQLException {
        String sql = "UPDATE BSPIP SET " +
                "Price=?, Status=?, Remark=? " +
                "WHERE Cmp=? AND Itm=? AND Sup=? ";
        String psName = "PIP.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.execUpdate(psName);

        PIPH.createUpdate(form, Cmp, Itm, Sup);
    }
}
