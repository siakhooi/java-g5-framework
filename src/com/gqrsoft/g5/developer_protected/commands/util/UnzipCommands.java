/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author Ng Siak Hooi
 */
public class UnzipCommands {

    private final static int ZIP_BUFFER_SIZE = 8192;
    private ByteArrayInputStream byteArray;
    private ZipInputStream zip;
    private ZipEntry currentZipEntry;

    public void init(byte[] data) {
        byteArray = new ByteArrayInputStream(data);
        zip = new ZipInputStream(byteArray);

    }

    public byte[] getEntry() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        currentZipEntry = zip.getNextEntry();
        int length;
        byte[] buffer = new byte[ZIP_BUFFER_SIZE];
        while ((length = zip.read(buffer, 0, ZIP_BUFFER_SIZE)) != -1) {
            out.write(buffer, 0, length);
        }
        out.flush();
        return out.toByteArray();
    }

    public String getEntryName() {
        return currentZipEntry.getName();
    }

    public String getEntryComment() {
        return currentZipEntry.getComment();
    }
}
