/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CUS;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.C.BSCL0CUS;
import com.mysoftwarehouse.bs.CG.C.BSCP0CUS;
import com.mysoftwarehouse.bs.CG.C.BSCS0CUS;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CUS {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSCUS WHERE Cmp=? ";
        String psName = "CUS.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSCP0CUS form, String Cmp, String Cus) throws SQLException {
        CUSH.createDelete(form, Cmp, Cus);

        String sql = "DELETE FROM BSCUS WHERE Cmp=? AND Cus=? ";
        String psName = "CUS.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Cus);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasCUS(UserFormInterface form, String Cmp, String Cus) throws SQLException {
        String rsName = "CUS.has";
        String sql = "SELECT * FROM BSCUS WHERE Cmp=? AND Cus=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Cus);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void insert(BSCS0CUS form, String Cmp, String Cus,
            String Nme, String Typ, String RegNo, int priAdd, int priCnt,
            String WebSte, String Status, String Remark) throws SQLException {
        String sql = "INSERT INTO BSCUS (" +
                "Cmp, Cus, Nme, Typ, RegNo, PriAdd, PriCnt, WebSte, Status, Remark" +
                ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String psName = "CUS.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Typ);
        form.cmd().db.setParameter(psName, i++, RegNo);
        form.cmd().db.setParameter(psName, i++, priAdd);
        form.cmd().db.setParameter(psName, i++, priCnt);
        form.cmd().db.setParameter(psName, i++, WebSte);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);

        CUSH.createInsert(form, Cmp, Cus);
    }

    public static boolean select(BSCS0CUS form, String rsName, String Cmp, String Cus) throws SQLException {
        String sql = "SELECT * FROM BSCUS WHERE Cmp=? AND Cus=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Cus);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSCL0CUS form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSCUS WHERE Cmp=? ORDER BY Cus";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(BSCS0CUS form, String Cmp, String Cus, String Nme,
            String Typ, String RegNo, int priAdd, int priCnt, String WebSte, String Status, String Remark) throws SQLException {
        String sql = "UPDATE BSCUS SET " +
                "Nme=?, Typ=?, RegNo=?, PriAdd=?, PriCnt=?, WebSte=?, Status=?, Remark=? " +
                "WHERE Cmp=? AND Cus=? ";
        String psName = "CUS.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Typ);
        form.cmd().db.setParameter(psName, i++, RegNo);
        form.cmd().db.setParameter(psName, i++, priAdd);
        form.cmd().db.setParameter(psName, i++, priCnt);
        form.cmd().db.setParameter(psName, i++, WebSte);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.execUpdate(psName);

        CUSH.createUpdate(form, Cmp, Cus);
    }
}
