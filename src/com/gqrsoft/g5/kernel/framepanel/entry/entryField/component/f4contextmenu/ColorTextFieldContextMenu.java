/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu;

/**
 *
 * @author Ng Siak Hooi
 */
public class ColorTextFieldContextMenu extends AbstractContextMenu {

    @Override
    public void init() {
        super.setEnability(true, true, true, true,
                true, true, true, true);
    }

    @Override
    public void refreshLook() {
        boolean enable = super.entryField.display.editable;
        if (enable) {
            setEnability(true, true, true, true, true, true, true, true);
        } else {
            setEnability(true, false, false, true, false, false, false, true);
        }
    }
}
