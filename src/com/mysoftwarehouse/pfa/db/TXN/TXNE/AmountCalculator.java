/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN.TXNE;

import java.math.BigDecimal;

/**
 *
 * @author Ng Siak Hooi
 */
public class AmountCalculator {

    BigDecimal Dbt;
    BigDecimal Crt;
    BigDecimal Amt;

    void calculateFromAccount(boolean debitType, BigDecimal TxnAmt) {
        Amt = TxnAmt;
        Dbt = TxnAmt;
        if (!debitType) {
            Dbt = BigDecimal.ZERO.subtract(TxnAmt);
        }
        Crt = BigDecimal.ZERO;
        if (Dbt.compareTo(BigDecimal.ZERO) == -1) {
            Crt = Crt.subtract(Dbt);
            Dbt = BigDecimal.ZERO;
        }
    }

    void calculateToAccount(boolean debitType, BigDecimal dbt, BigDecimal crt) {
        Dbt = dbt;
        Crt = crt;
        if (debitType) {
            Amt = dbt.subtract(crt);
        } else {
            Amt = crt.subtract(dbt);
        }
    }

}
