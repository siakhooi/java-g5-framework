/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.values;

import java.awt.Color;

/**
 *
 * @author SHNG
 */
public class G6Color {

    public static String Color2Hex(Color n) {
        return long2Hex(Color2long(n), 6);
    }

    public static String long2Hex(long i, int size) {
        String s = Long.toHexString(i).toUpperCase();
        if (s.length() > size) {
            return s.substring(s.length() - size);
        }
        while (s.length() < size) {
            s = "0" + s;
        }
        return s;
    }

    public static long Color2long(Color n) {
        long r = n.getRed();
        long g = n.getGreen();
        long b = n.getBlue();
        r = r << 16;
        g = g << 8;
        long v = r | g | b;
        v = v & 0xffffff;
        return v;
    }

    public static Color Hex2Color(String n) {
        return long2Color(Hex2long(n));
    }

    public static Color long2Color(long n) {
        int b = (int) (n & 0xff);
        n = n >> 8;
        int g = (int) (n & 0xff);
        n = n >> 8;
        int r = (int) (n & 0xff);
        return new Color(r, g, b);
    }

    public static long Hex2long(String s) {
        return Long.parseLong(s, 16);
    }
}
