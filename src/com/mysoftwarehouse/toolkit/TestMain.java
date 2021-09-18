/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit;

/**
 *
 * @author Ng Siak Hooi
 */
public class TestMain {

    public static void main(String[] args) {
        String[] s = {
            "-f0", "com.mysoftwarehouse.toolkit.demo.m20.blank.HelloWorld",
            "-t0", "Hello Button",
            "-f1", "test",
            "-t1", "test111",
            "-f2", "test",
            "-t2", "test222",
            "-f3", "test",
            "-t3", "test333",
            "-hsql", "jdbc:hsqldb:/mysoftwarehouse/bs/bsdb"

// "-hsql", "jdbc:hsqldb:sadfaf
        };
        Main.main(s);
    }
}
