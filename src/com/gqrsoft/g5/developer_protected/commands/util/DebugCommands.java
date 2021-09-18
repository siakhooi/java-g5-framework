/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import com.gqrsoft.g5.kernel.core.util.CONSOLE;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author Ng Siak Hooi
 */
public class DebugCommands {

    public String getStackTraceText(Exception ex) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(baos, true);
        ex.printStackTrace(pw);
        pw.flush();
        return baos.toString();
    }

    public void println(String s) {
        CONSOLE.println(s);
    }

    public void error(String s) {
        CONSOLE.error(s);
    }

    public void error(String s, Exception ex) {
        CONSOLE.error(s, ex);
    }
}
