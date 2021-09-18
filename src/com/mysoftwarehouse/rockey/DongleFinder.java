/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.rockey;

import com.gqrsoft.g5.developer.form.UserFormInterface;

/**
 *
 * @author Ng Siak Hooi
 */
public class DongleFinder {

    static boolean findRockey(UserFormInterface form, RockeyCommands rc, short p3, short p4) {
        rc.init();
        short p1=DongleConfig.BASIC_PASSWORD_1;
        short p2=DongleConfig.BASIC_PASSWORD_2;
        
        if (!rc.find(p1, p2, p3, p4)) {
            form.cmd().linewrite.print("No DONGLE Found: ");
            form.cmd().linewrite.println(rc.getReturnMessage());
            return false;
        }
        do {
            if (rc.open()) {
                int hid = rc.getHardwareID();
                form.cmd().linewrite.println("Hardware ID read: " + hid);
                return true;
            }
        } while (rc.findNext());
        form.cmd().linewrite.print("No DONGLE Found: ");
        form.cmd().linewrite.println(rc.getReturnMessage());
        return false;
    }

}
