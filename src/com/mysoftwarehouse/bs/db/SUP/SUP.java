/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SUP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.K.BSKL0SUP;
import com.mysoftwarehouse.bs.KM.K.BSKP0SUP;
import com.mysoftwarehouse.bs.KM.K.BSKS0SUP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class SUP {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSSUP WHERE Cmp=? ";
        String psName = "SUP.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSKP0SUP form, String Cmp, String Sup) throws SQLException {
        SUPH.createDelete(form, Cmp, Sup);

        String sql = "DELETE FROM BSSUP WHERE Cmp=? AND Sup=? ";
        String psName = "SUP.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Sup);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasSup(UserFormInterface form, String Cmp, String Sup) throws SQLException {
        String rsName = "SUP.has";
        String sql = "SELECT * FROM BSSUP WHERE Cmp=? AND Sup=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Sup);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void insert(BSKS0SUP form, String Cmp, String Sup,
            String Nme, String Typ, String RegNo, int PriAdd, int PriCnt,
            String WebSte, String Status, String Remark)
            throws SQLException {
        String sql = "INSERT INTO BSSUP (" +
                "Cmp, Sup, Nme, Typ, RegNo, PriAdd, PriCnt, WebSte, Status, Remark" +
                ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String psName = "SUP.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Typ);
        form.cmd().db.setParameter(psName, i++, RegNo);
        form.cmd().db.setParameter(psName, i++, PriAdd);
        form.cmd().db.setParameter(psName, i++, PriCnt);
        form.cmd().db.setParameter(psName, i++, WebSte);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);

        SUPH.createInsert(form, Cmp, Sup);
    }

    public static boolean select(BSKS0SUP form, String rsName, String Cmp, String Sup) throws SQLException {
        String sql = "SELECT * FROM BSSUP WHERE Cmp=? AND Sup=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Sup);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSKL0SUP form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSSUP WHERE Cmp=? ORDER BY Sup";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(BSKS0SUP form, String Cmp, String Sup,
            String Nme, String Typ, String RegNo, int priAdd, int priCnt,
            String WebSte, String Status, String Remark) throws SQLException {
        String sql = "UPDATE BSSUP SET " +
                "Nme=?, Typ=?, RegNo=?, PriAdd=?, PriCnt=?, WebSte=?, Status=?, Remark=? " +
                "WHERE Cmp=? AND Sup=? ";
        String psName = "SUP.U";

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
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.execUpdate(psName);

        SUPH.createUpdate(form, Cmp, Sup);
    }
}
