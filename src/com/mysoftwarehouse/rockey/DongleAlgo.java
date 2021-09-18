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
public class DongleAlgo {

    static boolean compare(UserFormInterface form, RockeyCommands rc) {
        for (DongleAlgoValue sv : DongleConfig.allAlgos) {
            boolean i = rc.calculate2(
                    sv.start, sv.seed, sv.p1, sv.p2, sv.p3, sv.p4);
            if (!i) {
                form.cmd().linewrite.println(
                        rc.getReturnMessage());
                return false;
            }

            short r1 = rc.getCalculateValue1();
            short r2 = rc.getCalculateValue2();
            short r3 = rc.getCalculateValue3();
            short r4 = rc.getCalculateValue4();
            form.cmd().linewrite.print("Algo [");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(sv.seed, 8) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(sv.p1, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(sv.p2, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(sv.p3, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(sv.p4, 4) + "");
            form.cmd().linewrite.print("] - ");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(r1, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(r2, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(r3, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(r4, 4) + "");
            i = sv.compare(r1, r2, r3, r4);
            form.cmd().linewrite.println(i ? " (matched)" : " (unmatched)");
            if (!i) {
                return false;
            }
        }
        form.cmd().linewrite.println("Algo Test done!");
        return true;

    }

    static boolean read(UserFormInterface form, RockeyCommands rc) {
        for (DongleAlgoValue sv : DongleConfig.allAlgos) {
            boolean i = rc.calculate2(
                    sv.start, sv.seed, sv.p1, sv.p2, sv.p3, sv.p4);
            if (!i) {
                form.cmd().linewrite.println(
                        rc.getReturnMessage());
                return false;
            }

            short r1 = rc.getCalculateValue1();
            short r2 = rc.getCalculateValue2();
            short r3 = rc.getCalculateValue3();
            short r4 = rc.getCalculateValue4();
            form.cmd().linewrite.print("Algo [");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(sv.seed, 8) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(sv.p1, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(sv.p2, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(sv.p3, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(sv.p4, 4) + "");
            form.cmd().linewrite.print("] - ");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(r1, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(r2, 4) + ",");
            form.cmd().linewrite.print(form.cmd().data.long2Hex(r3, 4) + ",");
            form.cmd().linewrite.println(form.cmd().data.long2Hex(r4, 4) + "");
        }
        return true;
    }

    static boolean write(UserFormInterface form, RockeyCommands rc) {
        DongleAlgoFormula da = new DongleAlgoFormula();
        byte[] algo = da.getAlgo();
        boolean i = rc.writeArithmetic((short) 0, algo);
        if (!i) {
            form.cmd().linewrite.println(rc.getReturnMessage());
        } else {
            form.cmd().linewrite.println("Algo written [byte]: " + algo.length);
        }
        return i;
    }
}
