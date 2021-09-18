/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.TCD;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.is.A.ISAP0;
import com.mysoftwarehouse.is.B.ISBP2WHS;
import com.mysoftwarehouse.is.F.ISFL_TCD;
import com.mysoftwarehouse.is.F.ISFP0TCD;
import com.mysoftwarehouse.is.F.ISFS0TCD;
import com.mysoftwarehouse.is.G.ISGE_TXN;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class TCD {

    public static void delete(ISBP2WHS form, String Whs) throws SQLException {
        String sql = "DELETE FROM ISTCD WHERE Whs=? ";
        String psName = "TCD.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(ISFP0TCD form, String Whs, String Tcd) throws SQLException {
        TCDH.createDelete(form, Whs, Tcd);

        String sql = "DELETE FROM ISTCD WHERE Whs=? AND Tcd=?";
        String psName = "TCD.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.setParameter(psName, 2, Tcd);
        form.cmd().db.execUpdate(psName);
    }

    public static int getCount(ISAP0 form) {
        try {
            String rsName = "TCD.count";
            String sql = "SELECT COUNT(*) cnt FROM ISTCD";
            form.cmd().db.setStatement(rsName, sql);
            form.cmd().db.execQuery(rsName, rsName);
            if (form.cmd().db.next(rsName)) {
                return form.cmd().db.getInteger(rsName, "cnt");
            }
        } catch (SQLException ex) {
        }
        return 0;
    }

    public static boolean hasTcd(ISFS0TCD form, String Whs, String Tcd) throws SQLException {
        String rsName = "TCD.has";
        return select(form, rsName, Whs, Tcd);
    }

    public static boolean hasTcd(ISGE_TXN form, String Whs, String Tcd, String Typ) throws SQLException {
        String rsName = "TCD.has2";
        String sql = "SELECT * FROM ISTCD WHERE Whs=? AND Tcd=? AND Typ=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Tcd);
        form.cmd().db.setParameter(rsName, 3, Typ);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void insert(ISFS0TCD form, String Whs,
            String Tcd, String Typ, String Nme, String Remark) throws SQLException {
        String sql = "INSERT INTO ISTCD ( " +
                "Whs, Tcd, Typ, Nme, Remark) " +
                "VALUES(?, ?, ?, ?, ?) ";
        String psName = "TCD.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Tcd);
        form.cmd().db.setParameter(psName, i++, Typ);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);

        TCDH.createInsert(form, Whs, Tcd);
    }

    public static boolean select(ISFS0TCD form, String rsName,
            String Whs, String Tcd) throws SQLException {
        String sql = "SELECT * FROM ISTCD WHERE Whs=? AND Tcd=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Tcd);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(UserFormInterface form, String rsName,
            String Whs) throws SQLException {
        String sql = "SELECT * FROM ISTCD WHERE Whs=? ORDER BY Whs, Tcd";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void select_All(ISFL_TCD form, String rsName,
            String Whs, String Typ) throws SQLException {
        String sql = "SELECT * FROM ISTCD WHERE Whs=? AND Typ=? ORDER BY Whs, Tcd";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Typ);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(ISFS0TCD form, String Whs,
            String Tcd, String Typ, String Nme, String Remark) throws SQLException {
        String sql = "UPDATE ISTCD SET " +
                "Typ=?, Nme=?, Remark=? " +
                "WHERE Whs=? AND Tcd=? ";
        String psName = "TCD.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Typ);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Tcd);
        form.cmd().db.execUpdate(psName);

        TCDH.createUpdate(form, Whs, Tcd);
    }
////    public static boolean hasQty(ISCP0ITM form,
////            String Whs, String Itm) throws SQLException {
////        String rsName = "ITM.Qty";
////        if (select(form, rsName, Whs, Itm)) {
////            BigDecimal qty = form.cmd().db.getBigDecimal(rsName, "Qty");
////            return qty.compareTo(BigDecimal.ZERO) != 0;
////        } else {
////            return false;
////        }
////    }
////
////
//////    public static BigDecimal getPrice(UserFormInterface form, String Cmp, String Itm) throws SQLException {
//////        String rsName = "SI.Price";
//////        if (select(form, rsName, Cmp, Itm)) {
//////            return form.cmd().db.getBigDecimal(rsName, "Price");
//////        } else {
//////            return BigDecimal.ZERO;
//////        }
//////    }
//////
//////    public static void select_AllActive(BSDL1SI form, String rsName, String Cmp)
//////            throws SQLException {
//////        String sql = "SELECT * FROM BSSI WHERE Cmp=? AND Status=? ORDER BY Itm";
//////        form.cmd().db.setStatement(rsName, sql);
//////        form.cmd().db.setParameter(rsName, 1, Cmp);
//////        form.cmd().db.setParameter(rsName, 2, SiEnum.Status.A.typ);
//////        form.cmd().db.execQuery(rsName, rsName);
//////    }
//////
    }
