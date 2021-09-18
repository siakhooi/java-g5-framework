/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.codec;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author SHNG
 */
public class G6SHA {

    public static byte[] sha(byte[] data) {
        return DigestUtils.sha(data);
    }

    public static byte[] sha(String data) {
        return DigestUtils.sha(data);
    }

    public static String shaHex(byte[] data) {
        return DigestUtils.shaHex(data);
    }

    public static String shaHex(String data) {
        return DigestUtils.shaHex(data);
    }
}
