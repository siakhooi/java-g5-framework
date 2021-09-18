/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author Ng Siak Hooi
 */
public class BigDecimalTool {

    public static String format(BigDecimal n, String format, String nullValue) {
        if (isNull(n)) {
            return nullValue;
        }
        DecimalFormat df = new DecimalFormat(format);
        return df.format(n);
    }

    public static boolean isNull(BigDecimal n) {
        return n == null;
    }
}
