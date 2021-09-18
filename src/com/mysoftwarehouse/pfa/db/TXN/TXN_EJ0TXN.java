/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN;

import com.mysoftwarehouse.pfa.E.PFEJ0TXN;
import com.mysoftwarehouse.pfa.db.DB.DataEnum.TxnTyp;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXN_EJ0TXN {

    public static void select(PFEJ0TXN form,
            String rsName, String id, String frmAcc, String toAcc,
            Calendar frmDte, Calendar toDte) throws SQLException {
        boolean hasFrmAcc = !form.cmd().data.isNull(frmAcc);
        boolean hasToAcc = !form.cmd().data.isNull(toAcc);
        boolean hasFrmDte = (frmDte != null);
        boolean hasToDte = (frmDte != null);

        String sql = "SELECT " +
                "PFTXN.TxnDte, PFTXN.FrmAcc, PFTXN.ToAcc, " +
                "PFTXN.TxnAmt, PFTXN.RefNo, " +
                "PFTXNE.Dbt Adjustment, PFTXN.Remark " +
                "FROM PFTXN, PFTXNE " +
                "WHERE PFTXN.Id=PFTXNE.Id " +
                "AND PFTXN.TxnId=PFTXNE.TxnId " +
                "AND PFTXN.Id=? " +
                "AND PFTXN.TxnTyp=? " +
                "AND PFTXNE.Dbt>? ";
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
        sql += " ORDER BY PFTXNE.Acc, PFTXNE.TxnDte ";
        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, id);
        form.cmd().db.setParameter(rsName, i++, TxnTyp.R.code);
        form.cmd().db.setParameter(rsName, i++, 0);
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
