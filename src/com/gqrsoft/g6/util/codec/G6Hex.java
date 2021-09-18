/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.codec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author SHNG
 */
public class G6Hex {

    public static char[] hexEncode(byte[] data) {
        return Hex.encodeHex(data);
    }

    public static byte[] hexDecode(char[] data) throws DecoderException {
        return Hex.decodeHex(data);
    }
}
