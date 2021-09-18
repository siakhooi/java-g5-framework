/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN.TXNE;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.db.DB.DataEnum;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class Record_PFTXNE {

    public boolean found;
    public BigDecimal AcuAmt;
    public BigDecimal YearAcuAmt;
    public BigDecimal MonthAcuAmt;
    public Calendar TxnDte;
    public int TxnId;
    //
    void readPrevious(UserFormInterface form,
            String id, String Acc, Calendar txnDte, int txnId)
            throws SQLException {
        final String psName = "Record_PFTXNE.1";
        final String sql = "SELECT * " +
                "FROM PFTXNE " +
                "WHERE Id=? " +
                "AND Acc=? " +
                "AND (TxnDte<? OR (TxnDte=? AND TxnId<?)) " +
                "ORDER BY TxnDte, TxnId ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, Acc);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.execQuery(psName, psName);
        found = form.cmd().db.last(psName);
        if (found) {
            AcuAmt = form.cmd().db.getBigDecimal(psName, "AcuAmt");
            YearAcuAmt = form.cmd().db.getBigDecimal(psName, "YearAcuAmt");
            MonthAcuAmt = form.cmd().db.getBigDecimal(psName, "MonthAcuAmt");
            TxnDte = form.cmd().cal.Timestamp2Calendar(
                    form.cmd().db.getTimestamp(psName, "TxnDte"));
            TxnId = form.cmd().db.getInteger(psName, "TxnId");
        } else {
            AcuAmt = BigDecimal.ZERO;
            YearAcuAmt = BigDecimal.ZERO;
            MonthAcuAmt = BigDecimal.ZERO;
            TxnDte = null;
        }
    }

    void findNextStop(UserFormInterface form,
            String id, String acc, Calendar txnDte, int txnId)
            throws SQLException {
        final String psName = "Record_PFTXNE.2";
        final String sql = "SELECT " +
                "PFTXNE.TxnId, PFTXNE.TxnDte " +
                "FROM PFTXNE, PFTXN " +
                "WHERE PFTXN.Id=PFTXNE.Id " +
                "AND PFTXN.TxnId=PFTXNE.TxnId " +
                "AND PFTXNE.Id=? " +
                "AND PFTXNE.Acc=? " +
                "AND (PFTXNE.TxnDte>? " +
                "OR (PFTXNE.TxnDte=? AND PFTXNE.TxnId>? )) " +
                "AND PFTXN.TxnTyp <>? " +
                "AND PFTXN.FrmAcc=? " +
                "ORDER BY TxnDte, TxnId ";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, DataEnum.TxnTyp.N.code);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.execQuery(psName, psName);
        found=form.cmd().db.first(psName);
        if (found) {
            this.TxnId = form.cmd().db.getInteger(psName, "TxnId");
            this.TxnDte = form.cmd().cal.Timestamp2Calendar(
                    form.cmd().db.getTimestamp(psName, "TxnDte"));
        }
    }
}
