/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.ITM;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.is.B.ISBP2WHS;
import com.mysoftwarehouse.is.C.ISCL0ITMB;
import com.mysoftwarehouse.is.C.ISCP0ITM;
import com.mysoftwarehouse.is.E.ISEL0ITMB;
import com.mysoftwarehouse.is.E.ISEP0LOC;
import com.mysoftwarehouse.is.G.ISGE_TXN;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ITMB {

    public static void check(ISGE_TXN form, String Whs, String Itm, String Loc) throws SQLException {
        if (has(form, Whs, Itm, Loc)) {
            return;
        }
        String sql = "INSERT INTO ISITMB ( Whs, Itm, Loc, Qty) " +
                "VALUES(?, ?, ?, ?) ";
        String psName = "ITMB.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Loc);
        form.cmd().db.setParameter(psName, i++, BigDecimal.ZERO);
        form.cmd().db.execUpdate(psName);
    }

    public static void updateIn(ISGE_TXN form, String Whs,
            String Itm, String Loc, BigDecimal TxnQty) throws SQLException {
        String sql = "UPDATE ISITMB SET Qty=Qty+? " +
                "WHERE Whs=? AND Itm=? AND Loc=? ";
        String psName = "ITMB.UI";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, TxnQty);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Loc);
        form.cmd().db.execUpdate(psName);
    }
    public static void updateOut(ISGE_TXN form, String Whs,
            String Itm, String Loc, BigDecimal TxnQty) throws SQLException {
        String sql = "UPDATE ISITMB SET Qty=Qty-? " +
                "WHERE Whs=? AND Itm=? AND Loc=? ";
        String psName = "ITMB.UI";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, TxnQty);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Loc);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(ISBP2WHS form, String Whs) throws SQLException {
        String sql = "DELETE FROM ISITMB WHERE Whs=? ";
        String psName = "ITMB.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(ISCP0ITM form, String Whs, String Itm) throws SQLException {
        String sql = "DELETE FROM ISITMB WHERE Whs=? AND Itm=?";
        String psName = "ITMB.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.setParameter(psName, 2, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasQty(ISCP0ITM form, String Whs, String Itm) throws SQLException {
        String rsName = "ITMB.hasQty";
        String sql = "SELECT * FROM ISITMB WHERE Whs=? AND Itm=? AND Qty<>? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.setParameter(rsName, 3, 0);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    static boolean has(UserFormInterface form,
            String Whs, String Itm, String Loc) throws SQLException {
        String rsName = "ITMB.has";
        String sql = "SELECT * FROM ISITMB WHERE Whs=? AND Itm=? " +
                "AND Loc=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.setParameter(rsName, 3, Loc);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_AllbyItm(ISCL0ITMB form, String rsName,
            String Whs, String Itm) throws SQLException {
        String sql = "SELECT * FROM ISITMB A, ISLOC B " +
                "WHERE A.Whs=? AND A.Itm=? " +
                "AND A.Whs=B.Whs " +
                "AND A.Loc=B.Loc " +
                "ORDER BY A.Whs, A.Itm, A.Loc";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void select_AllbyLoc(ISEL0ITMB form, String rsName,
            String Whs, String Loc) throws SQLException {
        String sql = "SELECT * FROM ISITMB A, ISITM B " +
                "WHERE A.Whs=? AND A.Loc=? " +
                "AND A.Whs=B.Whs " +
                "AND A.Itm=B.Itm " +
                "ORDER BY A.Whs, A.Loc, A.Itm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Loc);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static boolean useLoc(ISEP0LOC form, String Whs,
            String Loc) throws SQLException {
        String rsName = "ITMB.useLoc";
        String sql = "SELECT * FROM ISITMB WHERE Whs=? AND Loc=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Loc);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }
}
