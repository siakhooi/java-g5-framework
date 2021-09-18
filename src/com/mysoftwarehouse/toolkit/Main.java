/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit;

import com.gqrsoft.g5.deploy.Starter;

/**
 *
 * @author Ng Siak Hooi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.init(new ApplicationInformation());
        starter.init(new ApplicationControl());
        starter.init(args);
        starter.run();
    }
}
