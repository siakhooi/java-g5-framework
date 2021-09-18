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
public class DongleUserId {

    static boolean write(UserFormInterface form, RockeyCommands rc) {
        boolean i = rc.writeUserId(
                DongleConfig.USER_ID);
        if (!i) {
            form.cmd().linewrite.println(
                    rc.getReturnMessage());
        } else {
            form.cmd().linewrite.println(
                    "User Id written: " + DongleConfig.USER_ID);
        }
        return i;
    }

    static boolean read(UserFormInterface form, RockeyCommands rc) {
        boolean i = rc.readUserId();
        if (!i) {
            form.cmd().linewrite.println(rc.getReturnMessage());
        } else {
            form.cmd().linewrite.println("User Id read: " +
                    rc.getUserId());
        }
        return i;
    }

    static boolean compare(UserFormInterface form, RockeyCommands rc) {
        boolean i = rc.readUserId();
        if (!i) {
            form.cmd().linewrite.println(rc.getReturnMessage());
            return i;
        } else {
            int uid = rc.getUserId();
            form.cmd().linewrite.print("User Id read: " + uid);
            if (uid == DongleConfig.USER_ID) {
                form.cmd().linewrite.println(" (matched)!");
                return true;
            } else {
                form.cmd().linewrite.println(" (unmatched)!");
                return false;
            }
        }
    }
}
