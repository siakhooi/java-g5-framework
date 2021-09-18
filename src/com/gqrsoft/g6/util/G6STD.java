/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author SHNG
 */
public class G6STD {

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static void println() {
        println("");
    }

    public static void error(String s) {
        System.err.println(s);
    }

    public static void error(Exception ex) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(baos, true);
        ex.printStackTrace(pw);
        pw.flush();
        error(baos.toString());
    }
}
