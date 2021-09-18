/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN.TXNE;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.db.ACCM.ACCM2;
import com.mysoftwarehouse.pfa.db.ACCY.ACCY2;
import com.mysoftwarehouse.pfa.db.DB.DataEnum;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXNE3 {
//inclusive, exclusive update
    static void increase(UserFormInterface form, String id, int txnId)
            throws SQLException {
        adjust(form, id, txnId, true);
    }

    static void decrease(UserFormInterface form, String id, int txnId)
            throws SQLException {
        adjust(form, id, txnId, false);
    }

    private static void adjust(UserFormInterface form,
            String id, int txnId, boolean increase) throws SQLException {
        Record_PFTXN txn = new Record_PFTXN();
        txn.select(form, id, txnId);
        int txnCntAdjustment = 1;
        if (!increase) {
            txnCntAdjustment = -1;
        }
        if (!txn.TxnTyp.equals(DataEnum.TxnTyp.N.code)) {
            txnCntAdjustment = 0;
        }
        int y = form.cmd().cal.getYear(txn.TxnDte);
        int m = form.cmd().cal.getMonth(txn.TxnDte);

        String psName = "TXNE3.D1";
        String sql = "SELECT * FROM PFTXNE WHERE Id=? AND TxnId=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.execQuery(psName, psName);
        while (form.cmd().db.next(psName)) {
            String acc = form.cmd().db.getString(psName, "Acc");
            BigDecimal amt = form.cmd().db.getBigDecimal(psName, "Amt");
            BigDecimal dbt = form.cmd().db.getBigDecimal(psName, "Dbt");
            BigDecimal crt = form.cmd().db.getBigDecimal(psName, "Crt");
            if (!increase) {
                amt = BigDecimal.ZERO.subtract(amt);
                dbt = BigDecimal.ZERO.subtract(dbt);
                crt = BigDecimal.ZERO.subtract(crt);
            }
            ACCY2.adjust(form, id, acc, amt, dbt, crt, txnCntAdjustment, y);
            ACCM2.adjust(form, id, acc, amt, dbt, crt, txnCntAdjustment, y, m);

            TXNE_Adjustor.adjustAfter(form, id, acc, txn.TxnDte, txnId, amt);
        }
    }
}
