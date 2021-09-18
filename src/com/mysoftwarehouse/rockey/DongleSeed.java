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
public class DongleSeed {

    static boolean read(UserFormInterface form, RockeyCommands rc) {
        for (DongleSeedValue sv : DongleConfig.allSeeds) {
            boolean i = rc.seed(sv.seed);
            if (!i) {
                form.cmd().linewrite.println(
                        rc.getReturnMessage());
                return false;
            }

            short s1 = rc.getSeedValue1();
            short s2 = rc.getSeedValue2();
            short s3 = rc.getSeedValue3();
            short s4 = rc.getSeedValue4();
            form.cmd().linewrite.print("Seed [" + form.cmd().data.long2Hex(sv.seed, 8) + "] - ");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(s1, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(s2, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(s3, 4) + ",");
            form.cmd().linewrite.println(form.cmd().data.long2Hex(s4, 4) + "");
        }
        return true;
    }

    static boolean compare(UserFormInterface form, RockeyCommands rc) {
        for (DongleSeedValue sv : DongleConfig.allSeeds) {
            boolean i = rc.seed(sv.seed);
            if (!i) {
                form.cmd().linewrite.println(
                        rc.getReturnMessage());
                return false;
            }

            short s1 = rc.getSeedValue1();
            short s2 = rc.getSeedValue2();
            short s3 = rc.getSeedValue3();
            short s4 = rc.getSeedValue4();
            form.cmd().linewrite.print("Seed [" + form.cmd().data.long2Hex(sv.seed, 8) + "] - ");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(s1, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(s2, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(s3, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(s4, 4) + "");
            i = sv.compare(s1, s2, s3, s4);
            form.cmd().linewrite.println(i ? " (matched)" : " (unmatched)");
            if (!i) {
                return false;
            }
        }
        form.cmd().linewrite.println("Seed Test done!");
        return true;
    }

}
