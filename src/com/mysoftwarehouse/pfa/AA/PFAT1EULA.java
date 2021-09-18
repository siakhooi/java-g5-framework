/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.AA;

import com.gqrsoft.g5.developer.form.UserFormInterface;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFAT1EULA extends PFAT_EULA {

    @Override
    public String getFormTitle() {
        return "PFAT1EULA.title";
    }

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return PFAB1EULA.class;
    }
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

    public void onEscapeKeyPressed() {
        cmd.form.closeApplication(true);
    }
}
