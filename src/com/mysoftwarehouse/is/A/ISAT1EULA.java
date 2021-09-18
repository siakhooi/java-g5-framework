/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.A;

import com.gqrsoft.g5.developer.form.UserFormInterface;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISAT1EULA extends ISAT_EULA {

    @Override
    public String getFormTitle() {
        return "ISAT1EULA.title";
    }

//    @Override
//    protected void initButton() {
//        super.buttons.addButton("Accept", "ISAT1EULA.button.Accept",
//                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), true);
//        super.buttons.addButton("Reject", "ISAT1EULA.button.Reject",
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
    public void onEscapeKeyPressed() {
        cmd.form.closeApplication(true);
    }

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ISAB1EULA.class;
    }
}
