/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author SHNG
 */
public class G6ZIP {

    private ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
    private ZipOutputStream zip = new ZipOutputStream(byteArray);

    public void init() {
        byteArray = new ByteArrayOutputStream();
        zip = new ZipOutputStream(byteArray);
    }

    public void add(String name, byte[] data) throws IOException {
        add(name, null, data);
    }

    public void add(String name, String comment, byte[] data) throws IOException {
        ZipEntry ze = new ZipEntry(name);
        if (comment != null) {
            ze.setComment(comment);
        }
        zip.putNextEntry(ze);
        zip.write(data, 0, data.length);
        zip.flush();
        zip.closeEntry();
    }

    public byte[] finish() throws IOException {
        zip.finish();
        return byteArray.toByteArray();
    }
}
