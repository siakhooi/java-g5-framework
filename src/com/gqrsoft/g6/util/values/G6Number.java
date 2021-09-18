/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.values;

/**
 *
 * @author SHNG
 */
public class G6Number {

    public static int parseInteger(String n, int defaultValue) {
        try {
            return Integer.parseInt(n);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
