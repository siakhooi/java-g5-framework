/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SI;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.CG.D.BSDL0SI;
import com.mysoftwarehouse.bs.CG.D.BSDL1SI;
import com.mysoftwarehouse.bs.CG.D.BSDP0SI;
import com.mysoftwarehouse.bs.CG.D.BSDS0SI;
import com.mysoftwarehouse.bs.data.SiEnum;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class SI {

    public static BigDecimal getPrice(UserFormInterface form, String Cmp, String Itm) throws SQLException {
        String rsName = "SI.Price";
        if (select(form, rsName, Cmp, Itm)) {
            return form.cmd().db.getBigDecimal(rsName, "Price");
        } else {
            return BigDecimal.ZERO;
        }
    }

    public static boolean hasItm(UserFormInterface form, String Cmp, String Itm) throws SQLException {
        String rsName = "SI.has";
        String sql = "SELECT * FROM BSSI WHERE Cmp=? AND Itm=?";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSDL0SI form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSSI WHERE Cmp=? ORDER BY Itm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void select_AllActive(BSDL1SI form, String rsName, String Cmp)
            throws SQLException {
        String sql = "SELECT * FROM BSSI WHERE Cmp=? AND Status=? ORDER BY Itm";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, SiEnum.Status.A.typ);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static boolean select(UserFormInterface form, String rsName, String Cmp, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSSI WHERE Cmp=? AND Itm=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, Itm);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSDS0SI form, String Cmp, String Itm, String Nme, BigDecimal Price, String Status) throws SQLException {
        String sql = "UPDATE BSSI SET " +
                "Nme=?, Price=?, Status=? " +
                "WHERE Cmp=? AND Itm=? ";
        String psName = "SI.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);

        SIH.createUpdate(form, Cmp, Itm);
    }

    public static void insert(BSDS0SI form, String Cmp, String Itm, String Nme, BigDecimal Price, String Status) throws SQLException {
        String sql = "INSERT INTO BSSI ( " +
                "Cmp, Itm, Nme, Price, Status) " +
                "VALUES(?, ?, ?, ?, ?) ";
        String psName = "SI.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Price);
        form.cmd().db.setParameter(psName, i++, Status);
        form.cmd().db.execUpdate(psName);

        SIH.createInsert(form, Cmp, Itm);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSSI WHERE Cmp=? ";
        String psName = "SI.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSDP0SI form, String Cmp, String Itm) throws SQLException {
        SIH.createDelete(form, Cmp, Itm);

        String sql = "DELETE FROM BSSI WHERE Cmp=? AND Itm=?";
        String psName = "SI.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, Itm);
        form.cmd().db.execUpdate(psName);
    }
}
