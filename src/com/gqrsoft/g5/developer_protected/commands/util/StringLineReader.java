/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.StringReader;

/**
 *
 * @author Ng Siak Hooi
 */
public class StringLineReader {

    private LineNumberReader line;

    public void init(String s) {
        line = new LineNumberReader(new StringReader(s));
    }

    public void init(InputStream is) {
        line = new LineNumberReader(new InputStreamReader(is));
    }

    public String readLine() throws IOException {
        return line.readLine();
    }
}
