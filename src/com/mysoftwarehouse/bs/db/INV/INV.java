/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.C.BSCP0CUS;
import com.mysoftwarehouse.bs.HJ.I.BSIL0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIP0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIS0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIS0INVD;
import com.mysoftwarehouse.bs.data.InvEnum;
import com.mysoftwarehouse.util.CalendarTool;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class INV {

    public static void copyFrmQtt(BSIP0INV form, String Cmp, String InvNo, String QttNo) throws SQLException {
        String sql = "INSERT INTO BSINV (" +
                "Cmp, InvNo, Cus, InvDte, DueDte, RefNum, Status, TtlAmt, Remark" +
                ") SELECT Cmp, ?, Cus, ?, ?, QttNo, ?, ?, QttNo FROM BSQTT " +
                "WHERE Cmp=? AND QttNo=? ";
        String psName = "INV.cpQtt";
        Calendar InvDte = CalendarTool.getDate(form.cmd().cal.getNow());

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, InvDte);
        form.cmd().db.setParameter(psName, i++, InvDte);
        form.cmd().db.setParameter(psName, i++, InvEnum.Status.D.typ);
        form.cmd().db.setParameter(psName, i++, BigDecimal.ZERO);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSINV WHERE Cmp=? ";
        String psName = "INV.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String InvNo) throws SQLException {
        String sql = "DELETE FROM BSINV WHERE Cmp=? AND InvNo=?";
        String psName = "INV.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, InvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasInv(UserFormInterface form, String Cmp, String InvNo) throws SQLException {
        String rsName = "INV.has";
        String sql = "SELECT * FROM BSINV WHERE Cmp=? AND InvNo=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, InvNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static boolean select(BSIS0INV form, String rsName, String Cmp, String InvNo) throws SQLException {
        String sql = "SELECT * FROM BSINV WHERE Cmp=? AND InvNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, InvNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select(BSIS0INVD form, String rsName, String Cmp, String InvNo, int Seq) throws SQLException {
        String sql = "SELECT * FROM BSINV WHERE Cmp=? AND InvNo=? AND Seq=? ";
        form.cmd().db.setStatement(rsName, sql);
        int i = 1;
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, InvNo);
        form.cmd().db.setParameter(rsName, i++, Seq);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void select_AllActive(BSIL0INV form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSINV WHERE Cmp=? ORDER BY InvNo ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void insert(BSIS0INV form, String Cmp, String Cus,
            String InvNo, Calendar InvDte, Calendar DueDte, String RefNum,
            String Status, String Remark) throws SQLException {
        String sql = "INSERT INTO BSINV (" +
                "Cmp, InvNo, Cus, InvDte, DueDte, RefNum, Status, TtlAmt, Remark" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        String psName = "INV.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.setParameter(psName, i++, InvDte);
        form.cmd().db.setParameter(psName, i++, DueDte);
        form.cmd().db.setParameter(psName, i++, RefNum);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, BigDecimal.ZERO);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);
    }

    public static void update(BSIS0INV form, String Cmp, String InvNo,
            Calendar InvDte, Calendar DueDte, String RefNum, String Status,
            String Remark) throws SQLException {
        String sql = "UPDATE BSINV SET " +
                "InvDte=?, DueDte=?, RefNum=?, Status=?, Remark=? " +
                "WHERE Cmp=? AND InvNo=? ";
        String psName = "INV.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, InvDte);
        form.cmd().db.setParameter(psName, i++, DueDte);
        form.cmd().db.setParameter(psName, i++, RefNum);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean useCUS(BSCP0CUS form, String Cmp, String Cus) throws SQLException {
        String rsName = "INV.useCUS";
        String sql = "SELECT * FROM BSINV WHERE Cmp=? AND Cus=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Cus);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }
}
