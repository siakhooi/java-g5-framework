/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.values;

/**
 *
 * @author SHNG
 */
public class G6String {

    public static String ltrim(String source) {
        return source.replaceAll("^\\s+", "");
    }

    /* remove trailing whitespace */
    public static String rtrim(String source) {
        return source.replaceAll("\\s+$", "");
    }

    /* replace multiple whitespaces between words with single blank */
    public static String itrim(String source) {
        return source.replaceAll("\\b\\s{2,}\\b", " ");
    }

    public static String remove0D0A(String source) {
        return source.replaceAll("\n", "").replaceAll("\r", "");
    }

    public static String selectText(int i, String... values) {
        if (values.length > i) {
            return values[i];
        }
        return null;
    }

    public static boolean isNull(String s) {
        if (s == null) {
            return true;
        }
        if (s.length() == 0) {
            return true;
        }
        return false;
    }

    public static String ifNull(String s, String value) {
        return isNull(s) ? value : s;
    }

    public static String unNullString(String s) {
        return (s == null) ? "" : s;
    }

    public static String getString(int n) {
        return "" + n;
    }

    public static String getString(long n) {
        return "" + n;
    }

    public static String repeat(String s, int n) {
        StringBuffer sb = new StringBuffer();
        while (n-- > 0) {
            sb.append(s);
        }
        return sb.toString();
    }
    public static String Array2String(String s[], String separator) {
        if (s == null) {
            return "";
        }
        String j = "";
        for (int i = 0; i < s.length; i++) {
            if (i > 0) {
                j = j + separator;
            }
            j = j + s[i];
        }
        return j;
    }

    public static String[] String2Array(String s, String separator) {
        return s.split(separator);
    }
}
