/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.RCP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.J.BSJS0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJS0RCPD;
import com.mysoftwarehouse.bs.HJ.J.BSJT0RCPE;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class RCPE {

    public static void copyFrmRcp(BSJS0RCP form, String Cmp, String ToRcpNo, String FrmRcpNo) throws SQLException {
        String sql = "INSERT INTO BSRCPE (" +
                "Cmp, RcpNo, Itm, Seq, Text) " +
                "SELECT Cmp, ?, Itm, Seq, Text " +
                "FROM BSRCPE " +
                "WHERE Cmp=? " +
                "AND RcpNo=? ";
        String psName = "RCPE.cpRcp";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToRcpNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmRcpNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmSi(BSJS0RCPD form, String Cmp, String RcpNo, String Itm) throws SQLException {
        String sql = "INSERT INTO BSRCPE (" +
                "Cmp, RcpNo, Itm, Seq, Text) " +
                "SELECT Cmp, ?, Itm, Seq, Text " +
                "FROM BSSID " +
                "WHERE Cmp=? " +
                "AND Itm=? ";
        String psName = "RCPE.cpSi";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSRCPE WHERE Cmp=? ";
        String psName = "RCPE.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String RcpNo) throws SQLException {
        String sql = "DELETE FROM BSRCPE WHERE Cmp=? AND RcpNo=?";
        String psName = "RCPE.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, RcpNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String RcpNo, String Itm) throws SQLException {
        String sql = "DELETE FROM BSRCPE WHERE Cmp=? AND RcpNo=? AND Itm=? ";
        String psName = "RCPE.D2";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, RcpNo);
        form.cmd().db.setParameter(psName, 3, Itm);
        form.cmd().db.execUpdate(psName);
    }

    public static void selectAll(UserFormInterface form, String rsName,
            String Cmp, String RcpNo, String Itm) throws SQLException {
        String sql = "SELECT * FROM BSRCPE WHERE Cmp=? AND RcpNo=? AND Itm=? ORDER BY Seq";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, RcpNo);
        form.cmd().db.setParameter(rsName, 3, Itm);
        form.cmd().db.execQuery(rsName, rsName);
    }

    public static void writeAll(BSJT0RCPE form, String Cmp,
            String RcpNo, String Itm, Vector<String> allText) throws SQLException {
        String sql = "DELETE FROM BSRCPE WHERE Cmp=? AND RcpNo=? AND Itm=? ";
        String psName = "RCPE.W";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, RcpNo);
        form.cmd().db.setParameter(psName, 3, Itm);
        form.cmd().db.execUpdate(psName);
        sql = "INSERT INTO BSRCPE (Cmp, RcpNo, Itm, Seq, Text) VALUES(?, ?, ?, ?, ?)";
        psName = "RCPE.I";
        form.cmd().db.setStatement(psName, sql);
        int Seq = 0;
        for (String t1 : allText) {
            int i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.setParameter(psName, i++, RcpNo);
            form.cmd().db.setParameter(psName, i++, Itm);
            form.cmd().db.setParameter(psName, i++, Seq++);
            form.cmd().db.setParameter(psName, i++, t1);
            form.cmd().db.execUpdate(psName);
        }
    }
}
