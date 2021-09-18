/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_secret.form;

import com.gqrsoft.g5.developer.publicform.*;
import com.gqrsoft.g5.developer.form.ButtonForm2;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class SelectionFormButtonForm extends ButtonForm2 {

    public final static String BUTTON_OK = "ok";
    public final static String BUTTON_CANCEL = "cancel";
    public final static String BUTTON_SELECTALL = "selectall";
    public final static String BUTTON_SELECTNONE = "selectnone";
    public boolean enabledSelectAll = true;
    public boolean enabledSelectNone = true;

    @Override
    public void build() {
        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        String title = "SelectionFormButtonForm.button.OK.label";
        title = cmd.lang.getSystemString(title);
        super.addI18nApplication(BUTTON_OK, title, "");
        super.setIcon(icon);

        icon = cmd.icon.getCancelIcon(cmd.icon.getDefaultHeight());
        title = "SelectionFormButtonForm.button.Cancel.label";
        title = cmd.lang.getSystemString(title);
        super.addI18nApplication(BUTTON_CANCEL, title, "");
        super.setIcon(icon);

        super.addSeparator();
        
        icon = cmd.icon.getSelectIcon(cmd.icon.getDefaultHeight());
        title = "SelectionFormButtonForm.button.SelectAll.label";
        title = cmd.lang.getSystemString(title);
        super.addI18nApplication(BUTTON_SELECTALL, title, "");
        super.setIcon(icon);
        super.setEnabled(enabledSelectAll);

        icon = cmd.icon.getSelectIcon(cmd.icon.getDefaultHeight());
        title = "SelectionFormButtonForm.button.SelectNone.label";
        title = cmd.lang.getSystemString(title);
        super.addI18nApplication(BUTTON_SELECTNONE, title, "");
        super.setIcon(icon);
        super.setEnabled(enabledSelectNone);

    }
}
