/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.values;

/**
 *
 * @author SHNG
 */
public class G6Boolean {

    public static String getString(boolean value) {
        return value ? "T" : "F";
    }

    public static boolean isTrue(String s) {
        String s1 = s.toUpperCase().trim();
        return s1.equals("Y") ||
                s1.equals("T") ||
                s1.equals("TRUE") ||
                s1.equals("YES") ||
                s1.equals("1");
    }

    public static boolean isFalse(String s) {
        String s1 = s.toUpperCase().trim();
        return s1.equals("N") ||
                s1.equals("F") ||
                s1.equals("FALSE") ||
                s1.equals("NO") ||
                s1.equals("0");
    }

    public static boolean isBoolean(String s) {
        return isTrue(s) || isFalse(s);
    }
}
