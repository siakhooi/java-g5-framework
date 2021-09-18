/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.TXN;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.is.A.ISAP0;
import com.mysoftwarehouse.is.B.ISBP2WHS;
import com.mysoftwarehouse.is.F.ISFP0TCD;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXN {

    public static int getCount(ISAP0 form) {
        try {
            String rsName = "TXN.count";
            String sql = "SELECT COUNT(*) cnt FROM ISTXN";
            form.cmd().db.setStatement(rsName, sql);
            form.cmd().db.execQuery(rsName, rsName);
            if (form.cmd().db.next(rsName)) {
                return form.cmd().db.getInteger(rsName, "cnt");
            }
        } catch (SQLException ex) {
        }
        return 0;
    }

    public static void insert(UserFormInterface form, String Whs, int Seq,
            Calendar TxnDte, String Tcd, String Itm, String FrmLoc, String ToLoc,
            BigDecimal TxnQty, BigDecimal TxnCst, BigDecimal OpnBal,
            BigDecimal NewBal, BigDecimal NewCst, String Remark) throws SQLException {

        String sql = "INSERT INTO ISTXN ( " +
                "Whs, Seq, TxnDte, TxnTme, Tcd, Itm, FrmLoc, ToLoc, TxnQty, TxnCst," +
                "OpnBal, NewBal, NewCst, Remark) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        String psName = "TXN.I";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Seq);
        form.cmd().db.setParameter(psName, i++, TxnDte);
        form.cmd().db.setParameter(psName, i++, TxnDte);
        form.cmd().db.setParameter(psName, i++, Tcd);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, FrmLoc);
        form.cmd().db.setParameter(psName, i++, ToLoc);
        form.cmd().db.setParameter(psName, i++, TxnQty);
        form.cmd().db.setParameter(psName, i++, TxnCst);
        form.cmd().db.setParameter(psName, i++, OpnBal);
        form.cmd().db.setParameter(psName, i++, NewBal);
        form.cmd().db.setParameter(psName, i++, NewCst);
        form.cmd().db.setParameter(psName, i++, Remark);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(ISBP2WHS form, String Whs) throws SQLException {
        String sql = "DELETE FROM ISTXN WHERE Whs=? ";
        String psName = "TXN.DA";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, Whs);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean useTcd(ISFP0TCD form, String Whs, String Tcd) throws SQLException {
        String rsName = "TXN.useTcd";
        String sql = "SELECT * FROM ISTXN WHERE Whs=? AND Tcd=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, Whs);
        form.cmd().db.setParameter(rsName, 2, Tcd);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }
}
