/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu;

/**
 *
 * @author Ng Siak Hooi
 */
public class TextFieldContextMenu extends AbstractContextMenu {

    @Override
    public void init() {
        setEnability(true, false, true, true, true, true, true, true);
    }

    @Override
    public void refreshLook() {
        boolean enable = super.entryField.display.editable;
        boolean hasViewForm = super.entryField.field.viewFormInterface != null;
//                getInputFieldOption().getUserField().detailFormName.length() > 0;
        boolean hasSelectForm = super.entryField.field.selectFormInterface != null;
//                getInputFieldOption().getUserField().lookupFormName.length() > 0;
        if (enable) {
            setEnability(true, hasSelectForm, true, true,
                    true, true, true, true);
        } else {
            setEnability(true, hasViewForm, false, true,
                    false, false, false, true);
        }
    }
}
