/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN;

import com.mysoftwarehouse.pfa.E.PFEJ0TXNE;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXN_EJ0TXNE {

    public static void select(PFEJ0TXNE form,
            String rsName, String id, String frmAcc, String toAcc,
            Calendar frmDte, Calendar toDte) throws SQLException {
        boolean hasFrmAcc = !form.cmd().data.isNull(frmAcc);
        boolean hasToAcc = !form.cmd().data.isNull(toAcc);
        boolean hasFrmDte = (frmDte != null);
        boolean hasToDte = (frmDte != null);

        String sql = "SELECT " +
                "PFACC.Acc, PFACC.Nme, PFACC.AccTyp, " +
                "PFTXN.TxnTyp, PFTXN.TxnDte, PFTXN.RefNo, PFTXN.Dsc, " +
                "PFTXNE.Dbt, PFTXNE.Crt " +
                "FROM PFTXN, PFTXNE, PFACC, PFACT " +
                "WHERE PFACC.Id=PFTXNE.Id " +
                "AND PFACC.Id=PFTXN.Id " +
                "AND PFACC.Acc=PFTXNE.Acc " +
                "AND PFACT.AccTyp=PFACC.AccTyp " +
                "AND PFTXN.TxnId=PFTXNE.TxnId " +
                "AND PFACT.AccTyp<>'C' " +
                "AND PFACC.Id=? ";

        if (hasFrmAcc) {
            sql += " AND PFTXNE.Acc>=? ";
        }
        if (hasToAcc) {
            sql += " AND PFTXNE.Acc<=? ";
        }
        if (hasFrmDte) {
            sql += " AND PFTXNE.TxnDte>=? ";
        }
        if (hasToDte) {
            sql += " AND PFTXNE.TxnDte<=? ";
        }
        sql += " ORDER BY PFACT.OrderFull, PFTXNE.Acc, PFTXN.TxnDte, PFTXN.TxnId ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, id);
        if (hasFrmAcc) {
            form.cmd().db.setParameter(rsName, i++, frmAcc);
        }
        if (hasToAcc) {
            form.cmd().db.setParameter(rsName, i++, toAcc);
        }
        if (hasFrmDte) {
            form.cmd().db.setParameter(rsName, i++, frmDte);
        }
        if (hasToDte) {
            form.cmd().db.setParameter(rsName, i++, toDte);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
