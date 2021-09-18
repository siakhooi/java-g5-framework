/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.codec;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author SHNG
 */
public class G6Base64 {

    public static byte[] base64Decode(byte[] base64Data) {
        return Base64.decodeBase64(base64Data);
    }

    public static byte[] base64Encode(byte[] binaryData, boolean isChunked) {
        return Base64.encodeBase64(binaryData, isChunked);
    }
}
