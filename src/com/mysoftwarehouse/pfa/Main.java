/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa;

/**
 *
 * @author Ng Siak Hooi
 */
public class Main {

    public static void main(String[] argv) {
        String[] argc = {
            "-f0", "com.mysoftwarehouse.pfa.A.PFAP0",
            "-t0", "Start WangTool",
            "-f1", "com.mysoftwarehouse.pfa.A.PFAP1",
            "-t1", "Stop WangTool",
            "-hsql", "jdbc:hsqldb:/mysoftwarehouse/pfa/pfadb"
        };
        com.mysoftwarehouse.toolkit.Main.main(argc);

//          "-f1", "com.mysoftwarehouse.pfa.A.PFAI0" ,
//          "-t1", "Image",
//          "-f2", "com.mysoftwarehouse.pfa.B.PFBM0",
//          "-t2", "Menu"

    }
}
