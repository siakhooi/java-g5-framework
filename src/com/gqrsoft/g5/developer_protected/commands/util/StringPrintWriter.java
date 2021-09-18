/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author Ng Siak Hooi
 */
public class StringPrintWriter {

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private PrintWriter pw;

    public StringPrintWriter() {
        baos = new ByteArrayOutputStream();
        pw = new PrintWriter(baos, true);
    }

    public void init() {
        baos = new ByteArrayOutputStream();
        pw = new PrintWriter(baos, true);
    }

    public String getString() {
        return baos.toString();
    }

    public void print(String s) {
        pw.print(s);
        pw.flush();
    }

    public void println(String s) {
        pw.println(s);
        pw.flush();
    }

    public void print(char c) {
        pw.print(c);
        pw.flush();
    }

    public void println(char c) {
        pw.println(c);
        pw.flush();
    }
}
