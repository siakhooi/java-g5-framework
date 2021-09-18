/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CMP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBL0CMP;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.B.BSBS0CMP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CMP {

    public static void delete(BSBP2CMP form, String cmp) throws SQLException {
        CMPH.createDelete(form, cmp);

        String sql = "DELETE FROM BSCMP WHERE Cmp=? ";
        String psName = "CMP.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasCMP(BSBS0CMP form, String Cmp) throws SQLException {
        String rsName = "CMP.has";
        String sql = "SELECT * FROM BSCMP WHERE Cmp=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void insert(BSBS0CMP form, String Cmp, String Nme,
            String RegNo, int priAdd, int priCnt, String WebSte, String CurCde) throws SQLException {

        String sql = "INSERT INTO BSCMP (" +
                "Cmp, Nme, RegNo, PriAdd, PriCnt, WebSte, CurCde" +
                ") VALUES(?, ?, ?, ?, ?, ?, ?)";
        String psName = "CMP.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, RegNo);
        form.cmd().db.setParameter(psName, i++, priAdd);
        form.cmd().db.setParameter(psName, i++, priCnt);
        form.cmd().db.setParameter(psName, i++, WebSte);
        form.cmd().db.setParameter(psName, i++, CurCde);
        form.cmd().db.execUpdate(psName);

        CMPH.createInsert(form, Cmp);
    }

    public static void update(BSBS0CMP form, String Cmp, String Nme,
            String RegNo, int PriAdd, int PriCnt, String WebSte, String CurCde) throws SQLException {

        String sql = "UPDATE BSCMP SET " +
                "Nme=?, RegNo=?, PriAdd=?, PriCnt=?, WebSte=?, CurCde=? " +
                "WHERE Cmp=? ";
        String psName = "CMP.U";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, RegNo);
        form.cmd().db.setParameter(psName, i++, PriAdd);
        form.cmd().db.setParameter(psName, i++, PriCnt);
        form.cmd().db.setParameter(psName, i++, WebSte);
        form.cmd().db.setParameter(psName, i++, CurCde);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.execUpdate(psName);
        CMPH.createUpdate(form, Cmp);
    }

    public static boolean select(UserFormInterface form, String rsName, String Cmp) throws SQLException {
        String sql = "SELECT * FROM BSCMP WHERE Cmp=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSBL0CMP form, String rsName) throws SQLException {
        String sql = "SELECT * FROM BSCMP ORDER BY Cmp";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
