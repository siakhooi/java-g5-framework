/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.cmd.form;

import com.gqrsoft.g5.developer.form.ButtonForm2;
import com.mysoftwarehouse.toolkit.demo.DemoMainMenu;

/**
 *
 * @author Ng Siak Hooi
 */
public class FormCommandsTester extends ButtonForm2 {

    private String CLOSEALLBUTME = "closeAllButMe";
    private String CLOSEME = "closeMe";
    private String MENU = "Menu";

    @Override
    public void build() {
        super.addI18nApplication(CLOSEALLBUTME, "Close All But Me", "");
        super.addI18nApplication(CLOSEME, "Close Me", "");
        super.addI18nApplication(MENU, "Menu", "");
    }

    @Override
    public void commandActivated(String name, String title, String description) {
        if (CLOSEALLBUTME.equals(name)) {
            cmd.form.closeAllWindowsButMe();
        } else if (CLOSEME.equals(name)) {
            cmd.form.closeForm();
        } else if (MENU.equals(name)) {
            cmd.form.executeInNewThread(new DemoMainMenu());
        }
    }
}
