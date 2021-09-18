/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.QTT;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.D.BSDP0SI;
import com.mysoftwarehouse.bs.HJ.H.BSHL0QTTD;
import com.mysoftwarehouse.bs.HJ.H.BSHS0QTT;
import com.mysoftwarehouse.bs.HJ.H.BSHS0QTTD;
import com.mysoftwarehouse.bs.db.SI.SI;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class QTTD {

    public static void copyFrmQtt(BSHS0QTT form, String Cmp, String ToQttNo, String FrmQttNo) throws SQLException {
        String sql = "INSERT INTO BSQTTD (" +
                "Cmp, QttNo, Itm, Price, Qty, TtlAmt) " +
                "SELECT Cmp, ?, Itm, Price, Qty, TtlAmt " +
                "FROM BSQTTD " +
                "WHERE Cmp=? " +
                "AND QttNo=? ";
        String psName = "QTTD.cpQtt";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToQttNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmQttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSQTTD WHERE Cmp=? ";
        String psName = "QTTD.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String QttNo) throws SQLException {
        String sql = "DELETE FROM BSQTTD WHERE Cmp=? AND QttNo=?";
        String psName = "QTTD.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String QttNo, String Itm) throws SQLException {
        String sql = "DELETE FROM BSQTTD WHERE Cmp=? AND QttNo=? AND Itm=? ";
        String psName = "QTTD.D";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSHS0QTTD form, String rsName, String Cmp, 
            String QttNo, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSQTTD WHERE Cmp=? AND QttNo=? AND Itm=? ";
        form.cmd().db.setStatement(rsName, sql);
        int i = 1;
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, QttNo);
        form.cmd().db.setParameter(rsName, i++, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSHL0QTTD form, String rsName, String Cmp, String QttNo)
            throws SQLException {
        String sql = "SELECT * FROM BSQTTD WHERE Cmp=? AND QttNo=? ORDER BY Itm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, QttNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static boolean useITM(BSDP0SI form, String Cmp, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSQTTD WHERE Cmp=? AND Itm=? ";
        String psName = "QTTD.useSI";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execQuery(psName, psName);
        return form.cmd().db.next(psName);
    }

    public static void insert(BSHS0QTTD form, String Cmp, String QttNo,
            String Itm, BigDecimal Qty) throws SQLException {
        BigDecimal Price = SI.getPrice(form, Cmp, Itm);
        String sql = "INSERT INTO BSQTTD (" +
                "Cmp, QttNo, Itm, Price, Qty, TtlAmt" +
                ") VALUES (?, ?, ?, ?, ?, ?) ";
        String psName = "QTTD.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Qty);
        form.cmd().db.setParameter(psName, i++, Price.multiply(Qty));
        form.cmd().db.execUpdate(psName);
    }

    public static void update(BSHS0QTTD form, String Cmp, String QttNo,
            String Itm, BigDecimal Price, BigDecimal Qty) throws SQLException {
        String sql = "UPDATE BSQTTD SET " +
                "Price=?, Qty=?, TtlAmt=? " +
                "WHERE Cmp=? AND QttNo=? AND Itm=? ";
        String psName = "QTTD.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Qty);
        form.cmd().db.setParameter(psName, i++, Price.multiply(Qty));
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }
}
