/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.codec;

import java.util.zip.CRC32;

/**
 *
 * @author SHNG
 */
public class G6CRC32 {

    public long digest(byte[] b) {
        CRC32 c = new CRC32();
        c.update(b);
        return c.getValue();
    }
}
