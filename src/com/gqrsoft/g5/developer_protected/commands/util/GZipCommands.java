/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public class GZipCommands {

    private final static int GZIP_BUFFER_SIZE = 8192;

    public byte[] gzip(byte[] data) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        GZIPOutputStream zipout = new GZIPOutputStream(b);
        zipout.write(data, 0, data.length);
        zipout.flush();
        zipout.finish();
        return b.toByteArray();
    }

    public byte[] gunzip(byte[] data) throws IOException {
        ByteArrayInputStream b = new ByteArrayInputStream(data);
        GZIPInputStream zipin = new GZIPInputStream(b);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int length;
        byte[] buffer = new byte[GZIP_BUFFER_SIZE];
        while ((length = zipin.read(buffer, 0, GZIP_BUFFER_SIZE)) != -1) {
            out.write(buffer, 0, length);
        }
        out.flush();
        return out.toByteArray();
    }
}
