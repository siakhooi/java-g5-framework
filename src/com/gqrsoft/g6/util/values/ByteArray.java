/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.values;

/**
 *
 * @author SHNG
 */
public class ByteArray {

    public static String getString(byte[] data) {
        return getString(data, ",");
    }

    public static String getString(byte[] data, String separator) {
        String s = "";
        for (int i = 0; i < data.length; i++) {
            s += data[i] + (i == data.length - 1 ? "" : separator);
        }
        return s;
    }
}
