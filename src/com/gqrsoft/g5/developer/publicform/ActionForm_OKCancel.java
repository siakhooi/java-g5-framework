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
public class ActionForm_OKCancel extends ButtonForm2 {

    public final static String BUTTON_OK = "ok";
    public final static String BUTTON_CANCEL= "cancel";

    @Override
    public void build() {
        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        String title = "ActionForm_OKCancel.button.OK.label";
        title = cmd.lang.getSystemString(title);
        super.addI18nApplication(BUTTON_OK, title, "");
        super.setIcon(icon);
        
        icon = cmd.icon.getCancelIcon(cmd.icon.getDefaultHeight());
        title = "ActionForm_OKCancel.button.Cancel.label";
        title = cmd.lang.getSystemString(title);
        super.addI18nApplication(BUTTON_CANCEL, title, "");
        super.setIcon(icon);
    }
}
