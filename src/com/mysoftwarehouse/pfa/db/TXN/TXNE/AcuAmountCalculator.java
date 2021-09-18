/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN.TXNE;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class AcuAmountCalculator {

    public BigDecimal AcuAmt;
    public BigDecimal YearAcuAmt;
    public BigDecimal MonthAcuAmt;

    void calculate(
            BigDecimal acuAmt,
            BigDecimal yearAcuAmt,
            BigDecimal monthAcuAmt,
            Calendar lastTxnDte,
            Calendar newTxnDte,
            BigDecimal newAmt) {
        if (lastTxnDte != null) {
            this.AcuAmt = acuAmt.add(newAmt);
            if (lastTxnDte.get(Calendar.YEAR) == newTxnDte.get(Calendar.YEAR)) {
                YearAcuAmt = yearAcuAmt.add(newAmt);
                if (lastTxnDte.get(Calendar.MONTH) ==
                        newTxnDte.get(Calendar.MONTH)) {
                    MonthAcuAmt = monthAcuAmt.add(newAmt);
                } else {
                    this.MonthAcuAmt = newAmt;
                }
            } else {
                this.YearAcuAmt = newAmt;
                this.MonthAcuAmt = newAmt;
            }
        } else {
            this.AcuAmt = newAmt;
            this.YearAcuAmt = newAmt;
            this.MonthAcuAmt = newAmt;
        }
//        System.out.println("newAmt: "+newAmt.toPlainString());
//        System.out.println("AcuAmt: "+AcuAmt.toPlainString());
//        System.out.println("MonthAcuAmt: "+MonthAcuAmt.toPlainString());
//        System.out.println("YearAcuAmt: "+YearAcuAmt.toPlainString());
    }
}
