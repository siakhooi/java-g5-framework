/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut;

/**
 *
 * @author Ng Siak Hooi
 */
public class Main {

    public static void main(String argv[]) {
        argv = new String[]{
            "-f0", "com.mysoftwarehouse.ut.A.UTAP0",
            "-t0", "Useful Tool"
        };
//-hsql,-- <arg>hsql connection url

        com.mysoftwarehouse.toolkit.Main.main(argv);
    }
}
