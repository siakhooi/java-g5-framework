/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu;

/**
 *
 * @author Ng Siak Hooi
 */
public class PasswordFieldContextMenu extends AbstractContextMenu {

    @Override
    public void init() {
        setEnability(true, false, false, false,
                true, true, true, true);
    }

    @Override
    public void refreshLook() {
        boolean enable = super.entryField.display.editable;
        if (enable) {
            setEnability(true, false, false, false,
                    true, true, true, true);
        } else {
            setEnability(true, false, false, false,
                    false, false, false, true);
        }
    }
}
