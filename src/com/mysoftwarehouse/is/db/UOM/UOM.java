/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.UOM;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.is.A.ISAP0;
import com.mysoftwarehouse.is.B.ISBP2WHS;
import com.mysoftwarehouse.is.D.ISDP0UOM;
import com.mysoftwarehouse.is.D.ISDS0UOM;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class UOM {

    public static void delete(ISBP2WHS form, String Whs) throws SQLException {
        String sql = "DELETE FROM ISUOM WHERE Whs=? ";
        String psName = "UOM.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.execUpdate(psName);
    }

    public static int getCount(ISAP0 form) {
        try {
            String rsName = "UOM.count";
            String sql = "SELECT COUNT(*) cnt FROM ISUOM";
            form.cmd().db.setStatement(rsName, sql);
            form.cmd().db.execQuery(rsName, rsName);
            if (form.cmd().db.next(rsName)) {
                return form.cmd().db.getInteger(rsName, "cnt");
            }
        } catch (SQLException ex) {
        }
        return 0;
    }


    public static void insert(ISDS0UOM form, String Whs,
            String Uom, String Nme, String Remark) throws SQLException {
        String sql = "INSERT INTO ISUOM ( " +
                "Whs, Uom, Nme, Remark) " +
                "VALUES(?, ?, ?, ?) ";
        String psName = "UOM.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Uom);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);

        UOMH.createInsert(form, Whs, Uom);
    }

    public static void update(ISDS0UOM form, String Whs,
            String Uom, String Nme, String Remark) throws SQLException {
        String sql = "UPDATE ISUOM SET " +
                "Nme=?, Remark=? " +
                "WHERE Whs=? AND Uom=? ";
        String psName = "UOM.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Uom);
        form.cmd().db.execUpdate(psName);

        UOMH.createUpdate(form, Whs, Uom);
    }

    public static void select_All(UserFormInterface form, String rsName,
            String Whs) throws SQLException {
        String sql = "SELECT * FROM ISUOM WHERE Whs=? ORDER BY Whs, Uom";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static boolean select(UserFormInterface form, String rsName,
            String Whs, String Uom) throws SQLException {
        String sql = "SELECT * FROM ISUOM WHERE Whs=? AND Uom=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Uom);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static boolean hasUom(UserFormInterface form, String Whs, String Uom) throws SQLException {
        String rsName = "UOM.has";
        return select(form, rsName, Whs, Uom);
    }

    public static void delete(ISDP0UOM form, String Whs, String Uom) throws SQLException {
        UOMH.createDelete(form, Whs, Uom);

        String sql = "DELETE FROM ISUOM WHERE Whs=? AND Uom=?";
        String psName = "UOM.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.setParameter(psName, 2, Uom);
        form.cmd().db.execUpdate(psName);
    }
//
//
//    public static boolean hasQty(ISCP0ITM form,
//            String Whs, String Itm) throws SQLException {
//        String rsName = "ITM.Qty";
//        if (select(form, rsName, Whs, Itm)) {
//            BigDecimal qty = form.cmd().db.getBigDecimal(rsName, "Qty");
//            return qty.compareTo(BigDecimal.ZERO) != 0;
//        } else {
//            return false;
//        }
//    }
//
//
////    public static BigDecimal getPrice(UserFormInterface form, String Cmp, String Itm) throws SQLException {
////        String rsName = "SI.Price";
////        if (select(form, rsName, Cmp, Itm)) {
////            return form.cmd().db.getBigDecimal(rsName, "Price");
////        } else {
////            return BigDecimal.ZERO;
////        }
////    }
////
////    public static void select_AllActive(BSDL1SI form, String rsName, String Cmp)
////            throws SQLException {
////        String sql = "SELECT * FROM BSSI WHERE Cmp=? AND Status=? ORDER BY Itm";
////        form.cmd().db.setStatement(rsName, sql);
////        form.cmd().db.setParameter(rsName, 1, Cmp);
////        form.cmd().db.setParameter(rsName, 2, SiEnum.Status.A.typ);
////        form.cmd().db.execQuery(rsName, rsName);
////    }
////
    }
