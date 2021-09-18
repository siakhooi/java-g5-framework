/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.demo.builtin;

import com.gqrsoft.g5.developer.form.ButtonForm;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class SystemUtilitiesTester extends ButtonForm {

    @Override
    public void buildButtonForm(JPanel parent) {
        super.buttons.addI18nButton("process", "Process now");
    }

    @Override
    public void buttonClick(String name) {
        try {
            cmd.debug.println("doing: beep(), getScreenHeight(), sleep(2000), getScreenWidth(), exit(-1)");
            cmd.sys.beep();
            cmd.debug.println("getScreenHeight(): " + cmd.sys.getScreenHeight());
            cmd.sys.sleep(2000);
            cmd.debug.println("getScreenWidth(): " + cmd.sys.getScreenWidth());
            cmd.sys.exit(-1);
        } catch (InterruptedException ex) {

        }
    }

    @Override
    public String getFormTitle() {
        return "HelloSystemUtilities.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
