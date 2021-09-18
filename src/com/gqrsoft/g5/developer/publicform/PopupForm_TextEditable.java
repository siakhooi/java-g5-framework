/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import com.gqrsoft.g5.developer.form.MenuForm;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class PopupForm_TextEditable extends MenuForm {

    protected boolean getEditable() {
        return true;
    }
    
    public final static String MENU_CUT = "cut";
    public final static String MENU_COPY = "copy";
    public final static String MENU_PASTE = "paste";
    public final static String MENU_DELETE = "delete";
    public final static String MENU_CLEARALL = "clearall";
    public final static String MENU_SELECTALL = "selectall";


    @Override
    public void build() {

//    private JMenuItem mnuHelp;

        Icon icon = cmd.icon.getContextMenuCutIcon(cmd.icon.getDefaultMenuIconHeight());
        String title = "MenuForm_Text.cut.label";
        title = cmd.lang.getSystemString(title);
        super.addI18nApplication(MENU_CUT, title, "");
        super.setIcon(icon);
        super.setEnabled(getEditable());

        icon = cmd.icon.getContextMenuCopyIcon(cmd.icon.getDefaultMenuIconHeight());
        title = "MenuForm_Text.copy.label";
        title = cmd.lang.getSystemString(title);
        super.addI18nApplication(MENU_COPY, title, "");
        super.setIcon(icon);

        icon = cmd.icon.getContextMenuPasteIcon(cmd.icon.getDefaultMenuIconHeight());
        title = "MenuForm_Text.paste.label";
        title = cmd.lang.getSystemString(title);
        super.addI18nApplication(MENU_PASTE, title, "");
        super.setIcon(icon);
        super.setEnabled(getEditable());

        super.addSeparator();

        icon = cmd.icon.getContextMenuDeleteIcon(cmd.icon.getDefaultMenuIconHeight());
        title = "MenuForm_Text.delete.label";
        title = cmd.lang.getSystemString(title);
        super.addI18nApplication(MENU_DELETE, title, "");
        super.setIcon(icon);
        super.setEnabled(getEditable());

        super.addSeparator();

        icon = cmd.icon.getContextMenuClearAllIcon(cmd.icon.getDefaultMenuIconHeight());
        title = "MenuForm_Text.clearall.label";
        title = cmd.lang.getSystemString(title);
        super.addI18nApplication(MENU_CLEARALL, title, "");
        super.setIcon(icon);
        super.setEnabled(getEditable());

        icon = cmd.icon.getContextMenuSelectAllIcon(cmd.icon.getDefaultMenuIconHeight());
        title = "MenuForm_Text.selectall.label";
        title = cmd.lang.getSystemString(title);
        super.addI18nApplication(MENU_SELECTALL, title, "");
        super.setIcon(icon);
    }
}
