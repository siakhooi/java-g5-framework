/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.POR;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.KM.L.BSLP0PI;
import com.mysoftwarehouse.bs.NO.N.BSNL0PORD;
import com.mysoftwarehouse.bs.NO.N.BSNS0POR;
import com.mysoftwarehouse.bs.NO.N.BSNS0PORD;
import com.mysoftwarehouse.bs.db.PI.PIP;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PORD {

    public static void copyFrmPor(BSNS0POR form, String Cmp, String ToPorNo, String FrmPorNo) throws SQLException {
        String sql = "INSERT INTO BSPORD (" +
                "Cmp, PorNo, Itm, Price, Qty, TtlAmt) " +
                "SELECT Cmp, ?, Itm, Price, Qty, TtlAmt " +
                "FROM BSPORD " +
                "WHERE Cmp=? " +
                "AND PorNo=? ";
        String psName = "PORD.cpPor";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToPorNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmPorNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSPORD WHERE Cmp=? ";
        String psName = "PORD.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String PorNo) throws SQLException {
        String sql = "DELETE FROM BSPORD WHERE Cmp=? AND PorNo=?";
        String psName = "PORD.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, PorNo);
        form.cmd().db.execUpdate(psName);
    }


    public static void delete(UserFormInterface form, String Cmp, String PorNo, String Itm) throws SQLException {
        String sql = "DELETE FROM BSPORD WHERE Cmp=? AND PorNo=? AND Itm=? ";
        String psName = "PORD.D";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void insert(BSNS0PORD form, String Cmp, String PorNo,
            String Itm, BigDecimal Qty, String Sup) throws SQLException {
        BigDecimal Price = PIP.getPrice(form, Cmp, Itm, Sup);
        String sql = "INSERT INTO BSPORD (" +
                "Cmp, PorNo, Itm, Price, Qty, TtlAmt" +
                ") VALUES (?, ?, ?, ?, ?, ?) ";
        String psName = "PORD.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Qty);
        form.cmd().db.setParameter(psName, i++, Price.multiply(Qty));
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSNS0PORD form, String rsName, String Cmp, String PorNo, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSPORD WHERE Cmp=? AND PorNo=? AND Itm=? ";
        form.cmd().db.setStatement(rsName, sql);
        int i = 1;
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, PorNo);
        form.cmd().db.setParameter(rsName, i++, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSNL0PORD form, String rsName, String Cmp, String PorNo)
            throws SQLException {
        String sql = "SELECT * FROM BSPORD WHERE Cmp=? AND PorNo=? ORDER BY Itm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, PorNo);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void update(BSNS0PORD form, String Cmp, String PorNo,
            String Itm, BigDecimal Price, BigDecimal Qty) throws SQLException {
        String sql = "UPDATE BSPORD SET " +
                "Price=?, Qty=?, TtlAmt=? " +
                "WHERE Cmp=? AND PorNo=? AND Itm=? ";
        String psName = "PORD.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Qty);
        form.cmd().db.setParameter(psName, i++, Price.multiply(Qty));
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean useITM(BSLP0PI form, String Cmp, String Itm) throws SQLException {
        String rsName = "PORD.usePI";
        String sql = "SELECT * FROM BSPORD WHERE Cmp=? AND Itm=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }
}
