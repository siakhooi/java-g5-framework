/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ng Siak Hooi
 */
public class CONSOLE {

    public static void println(String s) {
        System.out.println(s);
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).
                log(Level.ALL, s);
    }

    public static void error(String s) {
        System.err.println(s);
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).
                log(Level.ALL, s);
    }

    public static void error(String s, Exception ex) {
        System.err.println(s);
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).
                log(Level.ALL, s, ex);
    }
}
