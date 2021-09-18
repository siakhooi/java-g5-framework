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
public class G6MD5 {

    public static byte[] md5(byte[] data) {
        return DigestUtils.md5(data);
    }

    public static byte[] md5(String data) {
        return DigestUtils.md5(data);
    }

    public static String md5Hex(byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }
}
