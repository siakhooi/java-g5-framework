/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.POR;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.K.BSKP0SUP;
import com.mysoftwarehouse.bs.KM.L.BSLP0PI;
import com.mysoftwarehouse.bs.KM.M.BSMP0PY;
import com.mysoftwarehouse.bs.NO.N.BSNL0POR;
import com.mysoftwarehouse.bs.NO.N.BSNS0POR;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class POR {

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPOR WHERE Cmp=? ";
        String psName = "POR.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }


    public static void delete(UserFormInterface form, String Cmp, String PorNo) throws SQLException {
        String sql = "DELETE FROM BSPOR WHERE Cmp=? AND PorNo=?";
        String psName = "POR.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean hasPor(UserFormInterface form, String Cmp, String PorNo) throws SQLException {
        String rsName = "POR.hasPor";
        String sql = "SELECT * FROM BSPOR WHERE Cmp=? AND PorNo=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PorNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void insert(BSNS0POR form, String Cmp, String Sup,
            String PorNo, Calendar PorDte, Calendar ShpDte, String RefNum,
            String Status, String Remark) throws SQLException {
        String sql = "INSERT INTO BSPOR (" +
                "Cmp, PorNo, Sup, PorDte, ShpDte, RefNum, Status, Remark" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
        String psName = "POR.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.setParameter(psName, i++, PorDte);
        form.cmd().db.setParameter(psName, i++, ShpDte);
        form.cmd().db.setParameter(psName, i++, RefNum);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSNS0POR form, String rsName, String Cmp, String PorNo) throws SQLException {
        String sql = "SELECT * FROM BSPOR WHERE Cmp=? AND PorNo=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PorNo);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void selectAll(BSNL0POR form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSPOR WHERE Cmp=? ORDER BY PorNo ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(BSNS0POR form, String Cmp, String PorNo,
            Calendar PorDte, Calendar ShpDte, String RefNum, String Status, String Remark) throws SQLException {
        String sql = "UPDATE BSPOR SET " +
                "PorDte=?, ShpDte=?, RefNum=?, Status=?, Remark=? " +
                "WHERE Cmp=? AND PorNo=? ";
        String psName = "POR.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, PorDte);
        form.cmd().db.setParameter(psName, i++, ShpDte);
        form.cmd().db.setParameter(psName, i++, RefNum);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean useSUP(BSKP0SUP form, String Cmp, String Sup) throws SQLException {
        String rsName = "POR.useSUP";
        String sql = "SELECT * FROM BSPOR WHERE Cmp=? AND Sup=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Sup);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }
}
