/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN.TXNE;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXNE_Adjustor {
    /*
     * adjust after, exclusive current, and next stop
     * adjust only AcuAmt, YearAcuAmt, MonthAcuAmt
     */

    static void adjustAfter(UserFormInterface form, String id,
            String acc, Calendar txnDte, int txnId, BigDecimal amt)
            throws SQLException {

        Record_PFTXNE nextStop = new Record_PFTXNE();
        nextStop.findNextStop(form, id, acc, txnDte, txnId);
        int y = form.cmd().cal.getYear(txnDte);
        int m = form.cmd().cal.getMonth(txnDte);

        if (!nextStop.found) {
            updateAll(form, id, acc, txnDte, txnId, amt);
        } else {
            updateUntilNextStop(form, id, acc, txnDte, txnId, amt,
                    nextStop.TxnDte, nextStop.TxnId);
            amt = BigDecimal.ZERO.subtract(amt);
            TXNE_NextStopUpdater.updateNextStop(
                    form, id, acc, y, m, nextStop.TxnDte, nextStop.TxnId, amt);
        }
    }

    private static void updateAll(UserFormInterface form,
            String id, String acc, Calendar txnDte, int txnId, BigDecimal amt)
            throws SQLException {
        String psName = "", sql = "";
        int i;
        psName = "TXNE3.AJ.1";
        sql = "UPDATE PFTXNE SET " +
                "AcuAmt=AcuAmt+? " +
                "WHERE Id=? " +
                "AND Acc=? " +
                "AND (TxnDte>? OR (TxnDte=? AND TxnId>?)) ";
        i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.execUpdate(psName);

        int y = form.cmd().cal.getYear(txnDte);
        psName = "TXNE3.AJ.2";
        sql = "UPDATE PFTXNE SET " +
                "YearAcuAmt=YearAcuAmt+? " +
                "WHERE Id=? AND Acc=? " +
                "AND (TxnDte>? OR (TxnDte=? AND TxnId>?)) " +
                "AND Year(TxnDte)=? ";
        i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.execUpdate(psName);

        int m = form.cmd().cal.getMonth(txnDte);
        psName = "TXNE3.AJ.3";
        sql = "UPDATE PFTXNE SET " +
                "MonthAcuAmt=MonthAcuAmt+? " +
                "WHERE Id=? AND Acc=? " +
                "AND (TxnDte>? OR (TxnDte=? AND TxnId>?)) " +
                "AND Year(TxnDte)=? " +
                "AND Month(TxnDte)=? ";
        i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, m);
        form.cmd().db.execUpdate(psName);
    }

    private static void updateUntilNextStop(UserFormInterface form,
            String id, String acc, Calendar txnDte, int txnId,
            BigDecimal amt, Calendar nextTxnDte, int nextTxnId)
            throws SQLException {
        String psName = "", sql = "";
        int i;
        psName = "TXNE3.AN.1";
        sql = "UPDATE PFTXNE SET " +
                "AcuAmt=AcuAmt+? " +
                "WHERE Id=? " +
                "AND Acc=? " +
                "AND (TxnDte>? OR (TxnDte=? AND TxnId>?)) " +
                "AND (TxnDte<? OR (TxnDte=? AND TxnId<?)) ";
        i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, nextTxnDte);
        form.cmd().db.setParameter(psName, i++, nextTxnDte);
        form.cmd().db.setParameter(psName, i++, nextTxnId);
        form.cmd().db.execUpdate(psName);

        int y = form.cmd().cal.getYear(txnDte);

        psName = "TXNE3.AN.2";
        sql = "UPDATE PFTXNE SET " +
                "YearAcuAmt=YearAcuAmt+? " +
                "WHERE Id=? " +
                "AND Acc=? " +
                "AND (TxnDte>? OR (TxnDte=? AND TxnId>?)) " +
                "AND (TxnDte<? OR (TxnDte=? AND TxnId<?)) " +
                "AND Year(TxnDte)=? ";
        i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, nextTxnDte);
        form.cmd().db.setParameter(psName, i++, nextTxnDte);
        form.cmd().db.setParameter(psName, i++, nextTxnId);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.execUpdate(psName);

        int m = form.cmd().cal.getMonth(txnDte);
        psName = "TXNE3.AN.3";
        sql = "UPDATE PFTXNE SET " +
                "MonthAcuAmt=MonthAcuAmt+? " +
                "WHERE Id=? " +
                "AND Acc=? " +
                "AND (TxnDte>? OR (TxnDte=? AND TxnId>?)) " +
                "AND (TxnDte<? OR (TxnDte=? AND TxnId<?)) " +
                "AND Year(TxnDte)=? " +
                "AND Month(TxnDte)=? ";
        i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnDte);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, nextTxnDte);
        form.cmd().db.setParameter(psName, i++, nextTxnDte);
        form.cmd().db.setParameter(psName, i++, nextTxnId);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, m);
        form.cmd().db.execUpdate(psName);
    }
}
