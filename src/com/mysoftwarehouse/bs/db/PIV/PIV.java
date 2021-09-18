/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PIV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.K.BSKP0SUP;
import com.mysoftwarehouse.bs.KM.M.BSMP0PY;
import com.mysoftwarehouse.bs.NO.O.BSOL0PIV;
import com.mysoftwarehouse.bs.NO.O.BSOP0PIV;
import com.mysoftwarehouse.bs.NO.O.BSOR0PIV;
import com.mysoftwarehouse.bs.NO.O.BSOS0PIV;
import com.mysoftwarehouse.bs.data.InvEnum;
import com.mysoftwarehouse.util.CalendarTool;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PIV {

    public static void copyFrmPor(BSOP0PIV form, String Cmp, String PivNo, String PorNo) throws SQLException {
        String sql = "INSERT INTO BSPIV (" +
                "Cmp, PivNo, Sup, PivDte, RefNum, Status, TtlAmt, Remark, PayTyp " +
                ") SELECT Cmp, ?, Sup, ?, PorNo, ?, ?, PorNo, ? FROM BSPOR " +
                "WHERE Cmp=? AND PorNo=? ";
        String psName = "PIV.cpPor";
        Calendar PivDte = CalendarTool.getDate(form.cmd().cal.getNow());

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.setParameter(psName, i++, PivDte);
        form.cmd().db.setParameter(psName, i++, InvEnum.Status.D.typ);
        form.cmd().db.setParameter(psName, i++, BigDecimal.ZERO);
        form.cmd().db.setParameter(psName, i++, "");
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPIV WHERE Cmp=? ";
        String psName = "PIV.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String PivNo) throws SQLException {
        String sql = "DELETE FROM BSPIV WHERE Cmp=? AND PivNo=?";
        String psName = "PIV.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PivNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasPiv(BSOR0PIV form, String Cmp, String PivNo) throws SQLException {
        String rsName = "PIV.hasPiv";
        String sql = "SELECT * FROM BSPIV WHERE Cmp=? AND PivNo=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PivNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void insert(BSOS0PIV form, String Cmp, String Sup,
            String PivNo, Calendar PivDte, String RefNum,
            String Status, String Remark,
            String PayTyp, String PayRefNum, String PayInfo) throws SQLException {
        String sql = "INSERT INTO BSPIV (" +
                "Cmp, PivNo, Sup, PivDte, RefNum, Status, Remark, PayTyp, PayRefNum, PayInfo" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        String psName = "PIV.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.setParameter(psName, i++, PivDte);
        form.cmd().db.setParameter(psName, i++, RefNum);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, PayTyp);
        form.cmd().db.setParameter(psName, i++, PayRefNum);
        form.cmd().db.setParameter(psName, i++, PayInfo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSOS0PIV form, String rsName, String Cmp, String PivNo) throws SQLException {
        String sql = "SELECT * FROM BSPIV WHERE Cmp=? AND PivNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PivNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void selectAll(BSOL0PIV form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSPIV WHERE Cmp=? ORDER BY PivNo ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(BSOS0PIV form, String Cmp, String PivNo,
            Calendar PivDte, String RefNum, String Status,
            String Remark, String PayTyp, String PayRefNum, String PayInfo) throws SQLException {
        String sql = "UPDATE BSPIV SET " +
                "PivDte=?, RefNum=?, Status=?, Remark=?, " +
                "PayTyp=?, PayRefNum=?, PayInfo=? " +
                "WHERE Cmp=? AND PivNo=? ";
        String psName = "PIV.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, PivDte);
        form.cmd().db.setParameter(psName, i++, RefNum);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, PayTyp);
        form.cmd().db.setParameter(psName, i++, PayRefNum);
        form.cmd().db.setParameter(psName, i++, PayInfo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean usePY(BSMP0PY form, String Cmp, String PayTyp) throws SQLException {
        String rsName = "PIV.usePY";
        String sql = "SELECT * FROM BSPIV WHERE Cmp=? AND PayTyp=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PayTyp);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static boolean useSUP(BSKP0SUP form, String Cmp, String Sup) throws SQLException {
        String rsName = "PIV.useSUP";
        String sql = "SELECT * FROM BSPIV WHERE Cmp=? AND Sup=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Sup);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }
}
