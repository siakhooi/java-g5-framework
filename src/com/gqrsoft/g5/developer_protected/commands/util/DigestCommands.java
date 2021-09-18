/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import java.util.zip.CRC32;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Ng Siak Hooi
 */
public class DigestCommands {

    public long crc32(byte[] b) {
        CRC32 c = new CRC32();
        c.update(b);
        return c.getValue();
    }

    public byte[] md5(byte[] data) {
        return DigestUtils.md5(data);
    }

    public byte[] md5(String data) {
        return DigestUtils.md5(data);
    }

    public String md5Hex(byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    public String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }

    public byte[] sha(byte[] data) {
        return DigestUtils.sha(data);
    }

    public byte[] sha(String data) {
        return DigestUtils.sha(data);
    }

    public String shaHex(byte[] data) {
        return DigestUtils.shaHex(data);
    }

    public String shaHex(String data) {
        return DigestUtils.shaHex(data);
    }
}
