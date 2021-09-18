/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.RCP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.D.BSDP0SI;
import com.mysoftwarehouse.bs.HJ.J.BSJL0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJL0RCPD;
import com.mysoftwarehouse.bs.HJ.J.BSJP0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJS0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJS0RCPD;
import com.mysoftwarehouse.bs.db.SI.SI;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class RCPD {

    public static void copyFrmInv(BSJP0RCP form, String Cmp, String RcpNo, String InvNo) throws SQLException {
        String sql = "INSERT INTO BSRCPD (" +
                "Cmp, RcpNo, Itm, Price, Qty, TtlAmt " +
                ") SELECT Cmp, ?, Itm, Price, Qty, TtlAmt " +
                "FROM BSINVD WHERE Cmp=? AND InvNo=? ";
        String psName = "RCPD.cpInv";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmRcp(BSJS0RCP form, String Cmp, String ToRcpNo, String FrmRcpNo) throws SQLException {
        String sql = "INSERT INTO BSRCPD (" +
                "Cmp, RcpNo, Itm, Price, Qty, TtlAmt) " +
                "SELECT Cmp, ?, Itm, Price, Qty, TtlAmt " +
                "FROM BSRCPD " +
                "WHERE Cmp=? " +
                "AND RcpNo=? ";
        String psName = "RCPD.cpRcp";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToRcpNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmRcpNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSRCPD WHERE Cmp=? ";
        String psName = "RCPD.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String RcpNo) throws SQLException {
        String sql = "DELETE FROM BSRCPD WHERE Cmp=? AND RcpNo=?";
        String psName = "RCPD.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, RcpNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String RcpNo, String Itm) throws SQLException {
        String sql = "DELETE FROM BSRCPD WHERE Cmp=? AND RcpNo=? AND Itm=? ";
        String psName = "RCPD.D";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(BSJS0RCPD form, String Cmp, String RcpNo, String Itm, BigDecimal Qty) throws SQLException {
        BigDecimal Price = SI.getPrice(form, Cmp, Itm);
        String sql = "INSERT INTO BSRCPD (" +
                "Cmp, RcpNo, Itm, Price, Qty, TtlAmt" +
                ") VALUES (?, ?, ?, ?, ?, ?) ";
        String psName = "RCPD.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Qty);
        form.cmd().db.setParameter(psName, i++, Price.multiply(Qty));
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSJS0RCPD form, String rsName, String Cmp, String RcpNo, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSRCPD WHERE Cmp=? AND RcpNo=? AND Itm=? ";
        form.cmd().db.setStatement(rsName, sql);
        int i = 1;
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, RcpNo);
        form.cmd().db.setParameter(rsName, i++, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSJL0RCPD form, String rsName, String Cmp, String RcpNo)
            throws SQLException {
        String sql = "SELECT * FROM BSRCPD WHERE Cmp=? AND RcpNo=? ORDER BY Itm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, RcpNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(BSJS0RCPD form, String Cmp, String RcpNo,
            String Itm, BigDecimal Price, BigDecimal Qty) throws SQLException {
        String sql = "UPDATE BSRCPD SET " +
                "Price=?, Qty=?, TtlAmt=? " +
                "WHERE Cmp=? AND RcpNo=? AND Itm=? ";
        String psName = "RCPD.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Qty);
        form.cmd().db.setParameter(psName, i++, Price.multiply(Qty));
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean useITM(BSDP0SI form, String Cmp, String Itm) throws SQLException {
        String rsName = "RCPD.useSI";
        String sql = "SELECT * FROM BSRCPD WHERE Cmp=? AND Itm=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }
}
