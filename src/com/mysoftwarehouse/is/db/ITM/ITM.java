/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.ITM;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.is.A.ISAP0;
import com.mysoftwarehouse.is.B.ISBP2WHS;
import com.mysoftwarehouse.is.C.ISCP0ITM;
import com.mysoftwarehouse.is.C.ISCS0ITM;
import com.mysoftwarehouse.is.D.ISDP0UOM;
import com.mysoftwarehouse.is.G.ISGE_TXN;
import com.mysoftwarehouse.is.H.ISHE0ITM;
import com.mysoftwarehouse.is.I.ISIE0ITM;
import com.mysoftwarehouse.is.I.ISIP0ITM;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ITM {

    public static void delete(ISBP2WHS form, String Whs) throws SQLException {
        String sql = "DELETE FROM ISITM WHERE Whs=? ";
        String psName = "ITM.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(ISCP0ITM form, String Whs, String Itm)
            throws SQLException {
        ITMH.createDelete(form, Whs, Itm);

        String sql = "DELETE FROM ISITM WHERE Whs=? AND Itm=?";
        String psName = "ITM.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.setParameter(psName, 2, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static int getCount(ISAP0 form) {
        try {
            String rsName = "ITM.count";
            String sql = "SELECT COUNT(*) cnt FROM ISITM";
            form.cmd().db.setStatement(rsName, sql);
            form.cmd().db.execQuery(rsName, rsName);
            if (form.cmd().db.next(rsName)) {
                return form.cmd().db.getInteger(rsName, "cnt");
            }
        } catch (SQLException ex) {
        }
        return 0;
    }

    public static void insert(ISCS0ITM form, String Whs, String Itm, String Nme,
            String Uom, BigDecimal SsQty, BigDecimal StdCst, String Remark)
            throws SQLException {
        String sql = "INSERT INTO ISITM ( " +
                "Whs, Itm, Nme, Uom, Qty, SsQty, StdCst, WacCst, Remark) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        String psName = "ITM.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Uom);
        form.cmd().db.setParameter(psName, i++, BigDecimal.ZERO);
        form.cmd().db.setParameter(psName, i++, SsQty);
        form.cmd().db.setParameter(psName, i++, StdCst);
        form.cmd().db.setParameter(psName, i++, BigDecimal.ZERO);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);

        ITMH.createInsert(form, Whs, Itm);
    }

    public static boolean select(UserFormInterface form, String rsName,
            String Whs, String Itm) throws SQLException {
        String sql = "SELECT * FROM ISITM WHERE Whs=? AND Itm=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static boolean hasItm(UserFormInterface form,
            String Whs, String Itm) throws SQLException {
        String rsName = "ITM.has";
        return select(form, rsName, Whs, Itm);
//        String sql = "SELECT * FROM BSSI WHERE Cmp=? AND Itm=?";
//        form.cmd().db.setStatement(rsName, sql);
//        form.cmd().db.setParameter(rsName, 1, Cmp);
//        form.cmd().db.setParameter(rsName, 2, Itm);
//        form.cmd().db.execQuery(rsName, rsName);
//        return form.cmd().db.next(rsName);
    }

    public static boolean hasQty(ISCP0ITM form,
            String Whs, String Itm) throws SQLException {
        String rsName = "ITM.Qty";
        if (select(form, rsName, Whs, Itm)) {
            BigDecimal qty = form.cmd().db.getBigDecimal(rsName, "Qty");
            return qty.compareTo(BigDecimal.ZERO) != 0;
        } else {
            return false;
        }
    }

    public static boolean useUOM(ISDP0UOM form, String Whs, String Uom) throws SQLException {
        String rsName = "ITM.useUom";
        String sql = "SELECT * FROM ISITM WHERE Whs=? AND Uom=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Uom);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(UserFormInterface form, String rsName, String Whs)
            throws SQLException {
        String sql = "SELECT * FROM ISITM WHERE Whs=? ORDER BY Whs, Itm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void copyWacToStd(ISIP0ITM form, String Whs, String Itm) throws SQLException {
        String sql = "UPDATE ISITM SET StdCst=WacCst " +
                "WHERE Whs=? AND Itm=? ";
        String psName = "ITM.CP";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);

        ITMH.createUpdate(form, Whs, Itm, "Copy WacCst to StdCst");
    }

    public static void updateTxn(ISGE_TXN form, String Whs, String Itm,
            BigDecimal NewQty, BigDecimal NewWacCst) throws SQLException {
        String sql = "UPDATE ISITM SET Qty=?, WacCst=? " +
                "WHERE Whs=? AND Itm=? ";
        String psName = "ITM.UTXN";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, NewQty);
        form.cmd().db.setParameter(psName, i++, NewWacCst);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);

        ITMH.createUpdate(form, Whs, Itm, "Add Txn");
    }

    public static void updateStdCst(ISIE0ITM form, String Whs, String Itm,
            BigDecimal StdCst) throws SQLException {
        String sql = "UPDATE ISITM SET StdCst=? " +
                "WHERE Whs=? AND Itm=? ";
        String psName = "ITM.USC";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, StdCst);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);

        ITMH.createUpdate(form, Whs, Itm, "Update StdCst");
    }

    public static void updateSsQty(ISHE0ITM form, String Whs, String Itm,
            BigDecimal SsQty) throws SQLException {
        String sql = "UPDATE ISITM SET SsQty=? " +
                "WHERE Whs=? AND Itm=? ";
        String psName = "ITM.US";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, SsQty);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);

        ITMH.createUpdate(form, Whs, Itm, "Update SsQty");
    }

    public static void update(ISCS0ITM form, String Whs, String Itm, String Nme,
            String Uom, BigDecimal SsQty, BigDecimal StdCst, String Remark)
            throws SQLException {
        String sql = "UPDATE ISITM SET " +
                "Nme=?, Uom=?, SsQty=?, StdCst=?, Remark=? " +
                "WHERE Whs=? AND Itm=? ";
        String psName = "ITM.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Uom);
        form.cmd().db.setParameter(psName, i++, SsQty);
        form.cmd().db.setParameter(psName, i++, StdCst);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);

        ITMH.createUpdate(form, Whs, Itm);
    }

//    public static BigDecimal getPrice(UserFormInterface form, String Cmp, String Itm) throws SQLException {
//        String rsName = "SI.Price";
//        if (select(form, rsName, Cmp, Itm)) {
//            return form.cmd().db.getBigDecimal(rsName, "Price");
//        } else {
//            return BigDecimal.ZERO;
//        }
//    }
//
//    public static void select_AllActive(BSDL1SI form, String rsName, String Cmp)
//            throws SQLException {
//        String sql = "SELECT * FROM BSSI WHERE Cmp=? AND Status=? ORDER BY Itm";
//        form.cmd().db.setStatement(rsName, sql);
//        form.cmd().db.setParameter(rsName, 1, Cmp);
//        form.cmd().db.setParameter(rsName, 2, SiEnum.Status.A.typ);
//        form.cmd().db.execQuery(rsName, rsName);
//    }
//
    }
