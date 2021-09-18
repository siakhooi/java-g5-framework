/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.RCP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.C.BSCP0CUS;
import com.mysoftwarehouse.bs.CG.F.BSFP0SY;
import com.mysoftwarehouse.bs.HJ.J.BSJL0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJP0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJR0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJS0RCP;
import com.mysoftwarehouse.bs.data.RcpEnum;
import com.mysoftwarehouse.util.CalendarTool;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class RCP {

    public static void copyFrmInv(BSJP0RCP form, String Cmp, String RcpNo, String InvNo) throws SQLException {
        String sql = "INSERT INTO BSRCP (" +
                "Cmp, RcpNo, Cus, RcpDte, RefNum, Status, TtlAmt, Remark, PayTyp" +
                ") SELECT Cmp, ?, Cus, ?, InvNo, ?, ?, InvNo, ? FROM BSINV " +
                "WHERE Cmp=? AND InvNo=? ";
        String psName = "RCP.cpInv";
        Calendar RcpDte = CalendarTool.getDate(form.cmd().cal.getNow());

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, RcpDte);
        form.cmd().db.setParameter(psName, i++, RcpEnum.Status.D.typ);
        form.cmd().db.setParameter(psName, i++, BigDecimal.ZERO);
        form.cmd().db.setParameter(psName, i++, "");
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSRCP WHERE Cmp=? ";
        String psName = "RCP.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String RcpNo) throws SQLException {
        String sql = "DELETE FROM BSRCP WHERE Cmp=? AND RcpNo=?";
        String psName = "RCP.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, RcpNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasRcp(BSJR0RCP form, String Cmp, String RcpNo) throws SQLException {
        String rsName = "RCP.hasRcp";
        String sql = "SELECT * FROM BSRCP WHERE Cmp=? AND RcpNo=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, RcpNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void insert(BSJS0RCP form, String Cmp, String Cus, String RcpNo,
            Calendar RcpDte, String RefNum, String Status, String Remark, 
            String PayTyp, String PayRefNum, String PayInfo) throws SQLException {
        String sql = "INSERT INTO BSRCP (" +
                "Cmp, RcpNo, Cus, RcpDte, RefNum, Status, TtlAmt, Remark, PayTyp, PayRefNum, PayInfo" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        String psName = "RCP.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.setParameter(psName, i++, RcpDte);
        form.cmd().db.setParameter(psName, i++, RefNum);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, BigDecimal.ZERO);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, PayTyp);
        form.cmd().db.setParameter(psName, i++, PayRefNum);
        form.cmd().db.setParameter(psName, i++, PayInfo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSJS0RCP form, String rsName, String Cmp, String RcpNo) throws SQLException {
        String sql = "SELECT * FROM BSRCP WHERE Cmp=? AND RcpNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, RcpNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_AllActive(BSJL0RCP form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSRCP WHERE Cmp=? ORDER BY RcpNo ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(BSJS0RCP form, String Cmp, String RcpNo,
            Calendar RcpDte, String RefNum, String Status, String Remark, 
            String PayTyp, String PayRefNum, String PayInfo) throws SQLException {
        String sql = "UPDATE BSRCP SET " +
                "RcpDte=?, RefNum=?, Status=?, Remark=?, " +
                "PayTyp=?, PayRefNum=?, PayInfo=? " +
                "WHERE Cmp=? AND RcpNo=? ";
        String psName = "RCP.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, RcpDte);
        form.cmd().db.setParameter(psName, i++, RefNum);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, PayTyp);
        form.cmd().db.setParameter(psName, i++, PayRefNum);
        form.cmd().db.setParameter(psName, i++, PayInfo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean useCUS(BSCP0CUS form, String Cmp, String Cus) throws SQLException {
        String rsName = "RCP.useCUS";
        String sql = "SELECT * FROM BSRCP WHERE Cmp=? AND Cus=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Cus);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static boolean useSY(BSFP0SY form, String Cmp, String PayTyp) throws SQLException {
        String rsName = "RCP.useSY";
        String sql = "SELECT * FROM BSRCP WHERE Cmp=? AND PayTyp=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PayTyp);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }
}
