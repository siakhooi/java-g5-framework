/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CMP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBE0CMPS;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.B.BSBS0CMP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CMPS {

    public static void insert(BSBS0CMP form, String Cmp) throws SQLException {
        String sql = "INSERT INTO BSCMPS (" +
                "Cmp, QttSign1, QttSign2, InvSign1, InvSign2, RcpSign1, RcpSign2, " +
                "PorSign1, PorSign2, PivSign1, PivSign2" +
                ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String psName = "CMPS.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        for (int j = 0; j < 10; j++) {
            form.cmd().db.setParameter(psName, i++, "");
        }
        form.cmd().db.execUpdate(psName);
        CMPSH.createInsert(form, Cmp);
    }

    public static boolean select(UserFormInterface form,
            String rsName, String cmp) throws SQLException {
        String sql = "SELECT * FROM BSCMPS WHERE Cmp=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, cmp);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(BSBE0CMPS form, String Cmp,
            String QttSign1, String QttSign2, String InvSign1, String InvSign2,
            String RcpSign1, String RcpSign2, String PorSign1, String PorSign2,
            String PivSign1, String PivSign2)
            throws SQLException {

        String sql = "UPDATE BSCMPS SET " +
                "QttSign1=?, QttSign2=?, InvSign1=?, InvSign2=?, RcpSign1=?, RcpSign2=?, " +
                "PorSign1=?, PorSign2=?, PivSign1=?, PivSign2=? " +
                "WHERE Cmp=? ";
        String psName = "CMPS.U";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, QttSign1);
        form.cmd().db.setParameter(psName, i++, QttSign2);
        form.cmd().db.setParameter(psName, i++, InvSign1);
        form.cmd().db.setParameter(psName, i++, InvSign2);
        form.cmd().db.setParameter(psName, i++, RcpSign1);
        form.cmd().db.setParameter(psName, i++, RcpSign2);
        form.cmd().db.setParameter(psName, i++, PorSign1);
        form.cmd().db.setParameter(psName, i++, PorSign2);
        form.cmd().db.setParameter(psName, i++, PivSign1);
        form.cmd().db.setParameter(psName, i++, PivSign2);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.execUpdate(psName);
        CMPSH.createUpdate(form, Cmp);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        CMPSH.createDelete(form, Cmp);
        String sql = "DELETE FROM BSCMPS WHERE Cmp=? ";
        String psName = "CMPS.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }
}
