/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSAT1EULA extends BSAT_EULA {

    @Override
    public String getFormTitle() {
        return "BSAT1EULA.title";
    }

    @Override
    protected void initButton() {
        super.buttons.addButton("Accept", "BSAT1EULA.button.Accept",
                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), true);
        super.buttons.addButton("Reject", "BSAT1EULA.button.Reject",
                cmd.icon.getCancelIcon(cmd.icon.getDefaultHeight()), false);
    }

    @Override
    public void buttonClick(String name) {
        if ("Accept".equals(name)) {
            cmd.in.booleanValue = true;
            cmd.form.closeForm();
        } else {
            cmd.form.closeApplication(true);
        }
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeApplication(true);
    }
}
