/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PIV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.L.BSLP0PI;
import com.mysoftwarehouse.bs.NO.O.BSOL0PIVD;
import com.mysoftwarehouse.bs.NO.O.BSOP0PIV;
import com.mysoftwarehouse.bs.NO.O.BSOS0PIV;
import com.mysoftwarehouse.bs.NO.O.BSOS0PIVD;
import com.mysoftwarehouse.bs.db.PI.PIP;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PIVD {

    public static void copyFrmPiv(BSOS0PIV form, String Cmp, String ToPivNo, String FrmPivNo) throws SQLException {
        String sql = "INSERT INTO BSPIVD (" +
                "Cmp, PivNo, Itm, Price, Qty, TtlAmt) " +
                "SELECT Cmp, ?, Itm, Price, Qty, TtlAmt " +
                "FROM BSPIVD " +
                "WHERE Cmp=? " +
                "AND PivNo=? ";
        String psName = "PIVD.cpPiv";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToPivNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmPivNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmPor(BSOP0PIV form, String Cmp, String PivNo, String PorNo) throws SQLException {
        String sql = "INSERT INTO BSPIVD (" +
                "Cmp, PivNo, Itm, Price, Qty, TtlAmt " +
                ") SELECT Cmp, ?, Itm, Price, Qty, TtlAmt " +
                "FROM BSPORD WHERE Cmp=? AND PorNo=? ";
        String psName = "PIVD.cpPor";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPIVD WHERE Cmp=? ";
        String psName = "PIVD.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String PivNo) throws SQLException {
        String sql = "DELETE FROM BSPIVD WHERE Cmp=? AND PivNo=?";
        String psName = "PIVD.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PivNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String PivNo, String Itm) throws SQLException {
        String sql = "DELETE FROM BSPIVD WHERE Cmp=? AND PivNo=? AND Itm=? ";
        String psName = "PIVD.D";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(BSOS0PIVD form, String Cmp, String PivNo,
            String Itm, BigDecimal Qty, String Sup) throws SQLException {
        BigDecimal Price = PIP.getPrice(form, Cmp, Itm, Sup);
        String sql = "INSERT INTO BSPIVD (" +
                "Cmp, PivNo, Itm, Price, Qty, TtlAmt" +
                ") VALUES (?, ?, ?, ?, ?, ?) ";
        String psName = "PIVD.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Qty);
        form.cmd().db.setParameter(psName, i++, Price.multiply(Qty));
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSOS0PIVD form, String rsName, String Cmp, String PivNo, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSPIVD WHERE Cmp=? AND PivNo=? AND Itm=? ";
        form.cmd().db.setStatement(rsName, sql);
        int i = 1;
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, PivNo);
        form.cmd().db.setParameter(rsName, i++, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSOL0PIVD form, String rsName, String Cmp, String PivNo)
            throws SQLException {
        String sql = "SELECT * FROM BSPIVD WHERE Cmp=? AND PivNo=? ORDER BY Itm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PivNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(BSOS0PIVD form, String Cmp, String PivNo,
            String Itm, BigDecimal Price, BigDecimal Qty) throws SQLException {
        String sql = "UPDATE BSPIVD SET " +
                "Price=?, Qty=?, TtlAmt=? " +
                "WHERE Cmp=? AND PivNo=? AND Itm=? ";
        String psName = "PIVD.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Qty);
        form.cmd().db.setParameter(psName, i++, Price.multiply(Qty));
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void update(BSOS0PIVD form, String Cmp, String PivNo, int Seq, String Itm, BigDecimal Price, BigDecimal Qty) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static boolean useITM(BSLP0PI form, String Cmp, String Itm) throws SQLException {
        String rsName = "PIVD.usePI";
        String sql = "SELECT * FROM BSPIVD WHERE Cmp=? AND Itm=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }
}
