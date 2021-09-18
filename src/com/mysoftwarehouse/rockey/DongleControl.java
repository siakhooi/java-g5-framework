/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.rockey;

import com.gqrsoft.g5.developer.form.UserFormInterface;
//-Djava.library.path=K:\40.rockey
//Exception in thread "AWT-EventQueue-0" java.lang.UnsatisfiedLinkError: K:\40.rockey\ry4nd_dll.dll: Can't find dependent libraries
/**
 *
 * @author Ng Siak Hooi
 */
public class DongleControl {

    public static boolean hasValidDongle(UserFormInterface form) {
        RockeyCommands rc = new RockeyCommands();
        boolean i = DongleFinder.findRockey(form, rc,
                DongleConfig.DEFAULT_PASSWORD_3,
                DongleConfig.DEFAULT_PASSWORD_4);
        if (!i) {
            return false;
        }
        i = DongleUserId.compare(form, rc);
        if (!i) {
            return false;
        }
        i = DongleSeed.compare(form, rc);
        if (!i) {
            return false;
        }
        i = DongleAlgo.compare(form, rc);
        if (!i) {
            return false;
        }
        return true;
    }

    static String read(BSZT2 form) {
        RockeyCommands rc = new RockeyCommands();
        form.cmd().linewrite.init();
        boolean i = DongleFinder.findRockey(form, rc,
                DongleConfig.DEFAULT_PASSWORD_3,
                DongleConfig.DEFAULT_PASSWORD_4);
        if (!i) {
            return form.cmd().linewrite.getString();
        }
        i = DongleUserId.read(form, rc);
        if (!i) {
            return form.cmd().linewrite.getString();
        }

        i = DongleSeed.read(form, rc);
        if (!i) {
            return form.cmd().linewrite.getString();
        }
        i = DongleAlgo.read(form, rc);
        if (!i) {
            return form.cmd().linewrite.getString();
        }

        return form.cmd().linewrite.getString();
    }

    static String write(BSZT1 form, short ap1, short ap2) throws Exception {
        int k = 0;
        RockeyCommands rc = new RockeyCommands();
        form.cmd().linewrite.init();
        boolean i = DongleFinder.findRockey(form, rc, ap1, ap2);
        if (!i) {
            return form.cmd().linewrite.getString();
        }
        WriteHistory.add(form, rc.getHardwareID());
        i = DongleUserId.write(form, rc);
        if (!i) {
            return form.cmd().linewrite.getString();
        }
        form.cmd().debug.println("index:" + (k++));
        i = DongleAlgo.write(form, rc);
        if (!i) {
            return form.cmd().linewrite.getString();
        }

        form.cmd().debug.println("index:" + (k++));
        rc.close();
        return form.cmd().linewrite.getString();
    }
}
