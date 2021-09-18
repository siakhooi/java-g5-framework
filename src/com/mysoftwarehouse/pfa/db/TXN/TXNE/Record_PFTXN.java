/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN.TXNE;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class Record_PFTXN {

    public boolean found;
    public String TxnTyp;
    public String ToAcc;
    public Calendar TxnDte;

    void select(UserFormInterface form,
            String id, int txnId) throws SQLException {
        String psName = "Record_PFTXN";
        String sql = "SELECT * FROM PFTXN " +
                "WHERE Id=? " +
                "AND TxnId=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.execQuery(psName, psName);
        found = form.cmd().db.next(psName);
        if (found) {
            TxnTyp = form.cmd().db.getString(psName, "TxnTyp");
            TxnDte = form.cmd().cal.Timestamp2Calendar(
                    form.cmd().db.getTimestamp(psName, "TxnDte"));
            ToAcc = form.cmd().db.getString(psName, "ToAcc");
        } else {
            TxnTyp = null;
            TxnDte = null;
            ToAcc = null;
        }
    }
}
