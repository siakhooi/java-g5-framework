/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import com.gqrsoft.g5.developer.form.ButtonForm2;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class ActionForm_Close extends ButtonForm2 {

    public final static String BUTTON_CLOSE = "close";

    @Override
    public void build() {
        Icon icon = cmd.icon.getCloseIcon(cmd.icon.getDefaultHeight());
        String title = "ActionForm_Close.button.Close.label";
        title = cmd.lang.getSystemString(title);
        super.addApplication(BUTTON_CLOSE, title, "");
        super.setIcon(icon);
    }

    @Override
    public void commandActivated(String name, String title, String description) {
        if (name.equals(BUTTON_CLOSE)) {
            cmd.form.closeForm();
        }
    }
}
