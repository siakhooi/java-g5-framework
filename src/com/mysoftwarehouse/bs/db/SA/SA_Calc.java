/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SA;

import com.mysoftwarehouse.bs.data.SaEnum;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Ng Siak Hooi
 */
public class SA_Calc {

    public static BigDecimal calculate(BigDecimal grossAmt, String Type, BigDecimal Amt) {
        if (Type.equals(SaEnum.Type.FIX.typ)) {
            return Amt;
        } else if (Type.equals(SaEnum.Type.FCT.typ)) {
            return grossAmt.multiply(Amt);
        } else if (Type.equals(SaEnum.Type.NEAR.typ)) {
            if (Amt.compareTo(BigDecimal.ZERO) <= 0) {
                return BigDecimal.ZERO;
            }
            BigDecimal m = grossAmt.divide(Amt);
            BigDecimal m1 = m.setScale(0, RoundingMode.HALF_UP);
            BigDecimal m2 = m1.multiply(Amt);
            return m2.subtract(grossAmt);
        } else if (Type.equals(SaEnum.Type.LOWR.typ)) {
            if (Amt.compareTo(BigDecimal.ZERO) <= 0) {
                return BigDecimal.ZERO;
            }
            BigDecimal m = grossAmt.divide(Amt);
            BigDecimal m1 = m.setScale(0, RoundingMode.FLOOR);
            BigDecimal m2 = m1.multiply(Amt);
            return m2.subtract(grossAmt);
        } else if (Type.equals(SaEnum.Type.UPPR.typ)) {
            if (Amt.compareTo(BigDecimal.ZERO) <= 0) {
                return BigDecimal.ZERO;
            }
            BigDecimal m = grossAmt.divide(Amt);
            BigDecimal m1 = m.setScale(0, RoundingMode.CEILING);
            BigDecimal m2 = m1.multiply(Amt);
            return m2.subtract(grossAmt);
        }
        return BigDecimal.ZERO;
    }
}
