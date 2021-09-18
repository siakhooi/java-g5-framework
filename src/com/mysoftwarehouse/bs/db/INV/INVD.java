/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.D.BSDP0SI;
import com.mysoftwarehouse.bs.HJ.I.BSIL0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIL0INVD;
import com.mysoftwarehouse.bs.HJ.I.BSIP0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIS0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIS0INVD;
import com.mysoftwarehouse.bs.db.SI.SI;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class INVD {

    public static void copyFrmInv(BSIS0INV form, String Cmp, String ToInvNo, String FrmInvNo) throws SQLException {
        String sql = "INSERT INTO BSINVD (" +
                "Cmp, InvNo, Itm, Price, Qty, TtlAmt) " +
                "SELECT Cmp, ?, Itm, Price, Qty, TtlAmt " +
                "FROM BSINVD " +
                "WHERE Cmp=? " +
                "AND InvNo=? ";
        String psName = "INVD.cpInv";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToInvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmInvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmQtt(BSIP0INV form, String Cmp, String InvNo, String QttNo) throws SQLException {
        String sql = "INSERT INTO BSINVD (" +
                "Cmp, InvNo, Itm, Price, Qty, TtlAmt " +
                ") SELECT Cmp, ?, Itm, Price, Qty, TtlAmt " +
                "FROM BSQTTD WHERE Cmp=? AND QttNo=? ";
        String psName = "INVD.cpQtt";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSINVD WHERE Cmp=? ";
        String psName = "INVD.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String InvNo) throws SQLException {
        String sql = "DELETE FROM BSINVD WHERE Cmp=? AND InvNo=?";
        String psName = "INVD.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, InvNo);
        form.cmd().db.execUpdate(psName);

    }

    public static void delete(UserFormInterface form, String Cmp, String InvNo, String Itm) throws SQLException {
        String sql = "DELETE FROM BSINVD WHERE Cmp=? AND InvNo=? AND Itm=? ";
        String psName = "INVD.D";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(BSIS0INVD form, String Cmp, String InvNo, String Itm, BigDecimal Qty) throws SQLException {
        BigDecimal Price = SI.getPrice(form, Cmp, Itm);
        String sql = "INSERT INTO BSINVD (" +
                "Cmp, InvNo, Itm, Price, Qty, TtlAmt" +
                ") VALUES (?, ?, ?, ?, ?, ?) ";
        String psName = "INVD.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Qty);
        form.cmd().db.setParameter(psName, i++, Price.multiply(Qty));
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSIS0INVD form, String rsName, String Cmp, String InvNo, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSINVD WHERE Cmp=? AND InvNo=? AND Itm=? ";
        form.cmd().db.setStatement(rsName, sql);
        int i = 1;
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, InvNo);
        form.cmd().db.setParameter(rsName, i++, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSIL0INVD form, String rsName, String Cmp,
            String InvNo) throws SQLException {
        String sql = "SELECT * FROM BSINVD WHERE Cmp=? AND InvNo=? ORDER BY Itm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, InvNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(BSIS0INVD form, String Cmp, String InvNo, String Itm, BigDecimal Price, BigDecimal Qty) throws SQLException {
        String sql = "UPDATE BSINVD SET " +
                "Price=?, Qty=?, TtlAmt=? " +
                "WHERE Cmp=? AND InvNo=? AND Itm=? ";
        String psName = "INVD.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Qty);
        form.cmd().db.setParameter(psName, i++, Price.multiply(Qty));
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean useITM(BSDP0SI form, String Cmp, String Itm) throws SQLException {
        String rsName = "INVD.useSI";
        String sql = "SELECT * FROM BSINVD WHERE Cmp=? AND Itm=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }
}
