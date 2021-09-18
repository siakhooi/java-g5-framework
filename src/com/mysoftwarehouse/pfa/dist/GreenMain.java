/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.dist;

import com.gqrsoft.g5.deploy.Starter;

/**
 *
 * @author Ng Siak Hooi
 */
public class GreenMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int newLength = args.length + 1;
        String[] newArgs = new String[newLength];
        System.arraycopy(args, 0, newArgs, 0, args.length);
        newArgs[args.length] = "-d";
        Starter starter = new Starter();
        starter.init(new ApplicationInformation());
        starter.init(new ApplicationControl());
        starter.init(newArgs);
        starter.run();
    }
}
