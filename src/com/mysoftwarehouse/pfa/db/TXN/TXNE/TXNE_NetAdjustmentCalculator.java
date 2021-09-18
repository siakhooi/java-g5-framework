/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN.TXNE;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXNE_NetAdjustmentCalculator {

    private BigDecimal currentDbt;
    private BigDecimal currentCrt;
    public BigDecimal netAdjustDbt;
    public BigDecimal netAdjustCrt;

    void calculateNet(BigDecimal adjustDbt, BigDecimal adjustCrt) {
        BigDecimal newDbt = currentDbt.add(adjustDbt);
        BigDecimal newCrt = currentCrt.add(adjustCrt);
        newDbt = newDbt.subtract(newCrt);
        newCrt = BigDecimal.ZERO;
        if (newDbt.compareTo(newCrt) == -1) {
            newCrt = newCrt.subtract(newDbt);
            newDbt = BigDecimal.ZERO;
        }
        netAdjustDbt = newDbt.subtract(currentDbt);
        netAdjustCrt = newCrt.subtract(currentCrt);
    }

    void loadCurrent(UserFormInterface form,
            String id, String acc, int txnId) throws SQLException {
        String psName = "TXNE3.CNA.1";
        String sql = "SELECT * FROM PFTXNE WHERE Id=? AND TxnId=? AND Acc=? ";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, txnId);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.execQuery(psName, psName);
        form.cmd().db.first(psName);
        currentDbt = form.cmd().db.getBigDecimal(psName, "Dbt");
        currentCrt = form.cmd().db.getBigDecimal(psName, "Crt");
    }
}
