/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSAT0EULA extends BSAT_EULA {

    @Override
    public String getFormTitle() {
        return "BSAT0EULA.title";
    }

    @Override
    protected void initButton() {
        super.buttons.addButton("OK", "BSAT0EULA.button.OK",
                cmd.icon.getOKIcon(cmd.icon.getDefaultHeight()), false);
    }

    @Override
    public void buttonClick(String name) {
        cmd.form.closeForm();
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
