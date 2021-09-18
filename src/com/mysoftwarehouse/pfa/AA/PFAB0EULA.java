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
public class PFAB0EULA extends ButtonForm2 {

    @Override
    public void build() {
        super.addApplication("OK",
                "PFAB0EULA.button.OK", "PFAB0EULA.button.OK");
        super.setIcon(cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()));
//    @Override
//    protected void initButton() {
//        super.buttons.addButton("OK", "PFAT0EULA.button.OK",
//                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), false);
//    }
//
//    @Override
//    public void buttonClick(String name) {
//        cmd.form.closeForm();
//    }

    }

    @Override
    public void commandActivated(String name, String arg1, String arg2) {
        if ("OK".equals(name)) {
            cmd.form.closeForm();
        }
    }
}
