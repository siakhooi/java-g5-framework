/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.RCP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.HJ.J.BSJL0RCPJ;
import com.mysoftwarehouse.bs.HJ.J.BSJP0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJS0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJS0RCPJ;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class RCPJ {

    public static void copyFrmInv(BSJP0RCP form, String Cmp, String RcpNo, String InvNo) throws SQLException {
        String sql = "INSERT INTO BSRCPJ (" +
                "Cmp, RcpNo, Seq, Adj, Nme, Typ, Prio, Amt" +
                ") SELECT Cmp, ?, Seq, Adj, Nme, Typ, Prio, Amt " +
                "FROM BSINVJ WHERE Cmp=? AND InvNo=? ";
        String psName = "RCPJ.cpInv";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, InvNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void copyFrmRcp(BSJS0RCP form, String Cmp, String ToRcpNo, String FrmRcpNo) throws SQLException {
        String sql = "INSERT INTO BSRCPJ (" +
                "Cmp, RcpNo, Seq, Adj, Nme, Typ, Prio, Amt) " +
                "SELECT Cmp, ?, Seq, Adj, Nme, Typ, Prio, Amt " +
                "FROM BSRCPJ " +
                "WHERE Cmp=? " +
                "AND RcpNo=? ";
        String psName = "RCPJ.cpRcp";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToRcpNo);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, FrmRcpNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(BSBP2CMP form, String Cmp) throws SQLException {
        String sql = "DELETE FROM BSRCPJ WHERE Cmp=? ";
        String psName = "RCPJ.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String RcpNo) throws SQLException {
        String sql = "DELETE FROM BSRCPJ WHERE Cmp=? AND RcpNo=?";
        String psName = "RCPJ.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, RcpNo);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(UserFormInterface form, String Cmp, String RcpNo, int Seq) throws SQLException {
        String sql = "DELETE FROM BSRCPJ WHERE Cmp=? AND RcpNo=? AND Seq=? ";
        String psName = "RCPJ.D2";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Cmp);
        form.cmd().db.setParameter(psName, 2, RcpNo);
        form.cmd().db.setParameter(psName, 3, Seq);
        form.cmd().db.execUpdate(psName);
    }

    public static void update(BSJS0RCPJ form, String Cmp, String RcpNo, int Seq,
            String Adj, String Nme, String Typ, int Prio, BigDecimal Amt) throws SQLException {
        String sql = "UPDATE BSRCPJ SET " +
                "Adj=?, Nme=?, Typ=?, Prio=?, Amt=? " +
                "WHERE Cmp=? AND RcpNo=? AND Seq=? ";
        String psName = "RCPJ.U";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Adj);
        form.cmd().db.setParameter(psName, i++, Nme);
        form.cmd().db.setParameter(psName, i++, Typ);
        form.cmd().db.setParameter(psName, i++, Prio);
        form.cmd().db.setParameter(psName, i++, Amt);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Seq);
        form.cmd().db.execUpdate(psName);
    }

    private static int _getNextSeq(BSJS0RCPJ form, String Cmp, String RcpNo) throws SQLException {
        String sql = "SELECT MAX(Seq) MS FROM BSRCPJ WHERE Cmp=? AND RcpNo=? ";
        String psName = "RCPJ.NS";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.execQuery(psName, psName);
        if (form.cmd().db.next(psName)) {
            try {
                i = form.cmd().db.getInteger(psName, "MS");
                return i + 1;
            } catch (SQLException ex) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static void insert(BSJS0RCPJ form, String Cmp, String RcpNo, String Adj) throws SQLException {
        int Seq = _getNextSeq(form, Cmp, RcpNo);
        String sql = "INSERT INTO BSRCPJ (" +
                "Cmp, RcpNo, Seq, Adj, Nme, Typ, Prio, Amt" +
                ") SELECT ?, ?, ?, Adj, Nme, Typ, Prio, Amt FROM BSSA " +
                "WHERE Cmp=? AND Adj=? ";
        String psName = "RCPJ.I";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Seq);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Adj);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(BSJS0RCPJ form, String rsName, String Cmp, String RcpNo, int Seq) throws SQLException {
        String sql = "SELECT * FROM BSRCPJ WHERE Cmp=? AND RcpNo=? AND Seq=? ";
        form.cmd().db.setStatement(rsName, sql);
        int i = 1;
        form.cmd().db.setParameter(rsName, i++, Cmp);
        form.cmd().db.setParameter(rsName, i++, RcpNo);
        form.cmd().db.setParameter(rsName, i++, Seq);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(BSJL0RCPJ form, String rsName, String Cmp, String RcpNo)
            throws SQLException {
        String sql = "SELECT * FROM BSRCPJ WHERE Cmp=? AND RcpNo=? ORDER BY Prio, Seq ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Cmp);
        form.cmd().db.setParameter(rsName, 2, RcpNo);
        form.cmd().db.execQuery(rsName, rsName);
    }
}
