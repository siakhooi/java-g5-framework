/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN.TXNE;

import com.mysoftwarehouse.pfa.db.DB.DataEnum;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class Record_PFACC {

    public boolean found;

    public boolean isDebitType() {
        return DataEnum.AccTyp.isDebitType(accTyp);
    }
    public String accTyp;
    private String psName = "TXNE.AT.1";
    private String sql = "SELECT * FROM PFACC WHERE Id=? AND Acc=? ";

    void select(UserFormInterface form, String id, String Acc)
            throws SQLException {
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, Acc);
        form.cmd().db.execQuery(psName, psName);
        found = form.cmd().db.first(psName);
        if (found) {
            accTyp = form.cmd().db.getString(psName, "AccTyp");
        } else {
            accTyp = null;
        }
    }
}
