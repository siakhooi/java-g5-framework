/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util;

import java.util.Random;

/**
 *
 * @author shng
 */
public class G6Random {

    private Random rnd = new Random();
    private final String DOMAIN_ALPHANUMERIC =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public String getAlphaNumericString(int i) {
        final String domain = DOMAIN_ALPHANUMERIC;
        return getString(domain, i);
    }

    public String getString(String domain, int i) {
        String d = "";
        for (int j = 0; j < i; j++) {
            d = d + domain.charAt(rnd.nextInt(domain.length()));
        }
        return d;
    }

    public void getBytes(byte[] bytes) {
        rnd.nextBytes(bytes);
    }

    public int getInt() {
        return rnd.nextInt();
    }

    public int getInt(int from, int to) {
        int i = (int) (rnd.nextDouble() * (to - from + 1));
        i = Math.abs(i);
        return i + from;
    }

    public short getShort() {
        return (short) (getInt() & 0xffff);
    }
}
