/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.QTT;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.C.BSCP0CUS;
import com.mysoftwarehouse.bs.HJ.H.BSHL0QTT;
import com.mysoftwarehouse.bs.HJ.H.BSHS0QTT;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class QTT {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSQTT WHERE Cmp=? ";
        String psName = "QTT.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String QttNo) throws SQLException {
        String sql = "DELETE FROM BSQTT WHERE Cmp=? AND QttNo=?";
        String psName = "QTT.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasQtt(UserFormInterface form, String Cmp, String QttNo) throws SQLException {
        String rsName = "QTT.hasQtt";
        String sql = "SELECT * FROM BSQTT WHERE Cmp=? AND QttNo=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, QttNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSHS0QTT form, String Cmp, String QttNo,
            Calendar QttDte, Calendar ExpDte, String RefNum, String Status, String Remark) throws SQLException {
        String sql = "UPDATE BSQTT SET " +
                "QttDte=?, ExpDte=?, RefNum=?, Status=?, Remark=? " +
                "WHERE Cmp=? AND QttNo=? ";
        String psName = "QTT.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, QttDte);
        form.cmd().db.setParameter(psName, i++, ExpDte);
        form.cmd().db.setParameter(psName, i++, RefNum);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(BSHS0QTT form, String Cmp, String Cus, String QttNo,
            Calendar QttDte, Calendar ExpDte, String RefNum, String Status, String Remark) throws SQLException {
        String sql = "INSERT INTO BSQTT (" +
                "Cmp, QttNo, Cus, QttDte, ExpDte, RefNum, Status, TtlAmt, Remark" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        String psName = "QTT.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, QttNo);
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.setParameter(psName, i++, QttDte);
        form.cmd().db.setParameter(psName, i++, ExpDte);
        form.cmd().db.setParameter(psName, i++, RefNum);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, BigDecimal.ZERO);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSHS0QTT form, String rsName, String Cmp, String QttNo) throws SQLException {
        String sql = "SELECT * FROM BSQTT WHERE Cmp=? AND QttNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, QttNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static boolean useCUS(BSCP0CUS form, String Cmp, String Cus) throws SQLException {
        String sql = "SELECT * FROM BSQTT WHERE Cmp=? AND Cus=? ";
        String psName = "QTT.useCUS";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.execQuery(psName, psName);
        return form.cmd().db.next(psName);
    }

    public static void select_AllActive(BSHL0QTT form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSQTT WHERE Cmp=? ORDER BY QttNo ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
