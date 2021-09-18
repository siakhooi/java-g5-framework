/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.dist;

/**
 *
 * @author Ng Siak Hooi
 */
public class Main {

    public static void main(String argv[]) {
        argv = new String[]{
            "-f0", "com.mysoftwarehouse.bs.A.BSAP0",
            "-t0", "BizSuite",
            "-f2", "com.mysoftwarehouse.is.A.ISAP0",
            "-t2", "InvSuite",
            "-f3", "com.mysoftwarehouse.as.A.ASAP0",
            "-t3", "AccSuite",
            "-f4", "com.mysoftwarehouse.rockey.BSZE0",
            "-t4", "BizSuite Rockey",
            "-hsql", "jdbc:hsqldb:/mysoftwarehouse/as/asdb"

        };
//-hsql,-- <arg>hsql connection url

        com.mysoftwarehouse.toolkit.Main.main(argv);
    }
}
