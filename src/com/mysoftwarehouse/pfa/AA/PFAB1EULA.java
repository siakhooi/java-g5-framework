/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.AA;

import com.gqrsoft.g5.developer.form.ButtonForm2;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFAB1EULA extends ButtonForm2 {

    @Override
    public void build() {
        super.addApplication("Accept",
                "PFAB1EULA.button.Accept", "PFAB1EULA.button.Accept");
        super.setIcon(cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()));
        super.addApplication("Reject",
                "PFAB1EULA.button.Reject", "PFAB1EULA.button.Reject");
        super.setIcon(cmd.icon.getCancelIcon(cmd.icon.getDefaultHeight()));
//    @Override
//    protected void initButton() {
//        super.buttons.addButton("Accept", "PFAT1EULA.button.Accept",
//                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), true);
//        super.buttons.addButton("Reject", "PFAT1EULA.button.Reject",
//                cmd.icon.getCancelIcon(cmd.icon.getDefaultHeight()), false);
//    }
//
//    @Override
//    public void buttonClick(String name) {
//        if ("Accept".equals(name)) {
//            cmd.in.booleanValue = true;
//            cmd.form.closeForm();
//        } else {
//            cmd.form.closeApplication(true);
//        }
//    }

    }

    @Override
    public void commandActivated(String name, String arg1, String arg2) {
        if ("Accept".equals(name)) {
            cmd.in.booleanValue = true;
            cmd.form.closeForm();
        } else if ("Reject".equals(name)) {
            cmd.form.closeApplication(true);
        }
    }
}
