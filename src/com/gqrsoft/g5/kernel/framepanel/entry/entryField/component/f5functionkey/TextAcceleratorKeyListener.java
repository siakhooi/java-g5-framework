/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f5functionkey;

/**
 *
 * @author Ng Siak Hooi
 */
public class TextAcceleratorKeyListener extends AbstractAcceleratorKeyListener {

    @Override
    public void refreshLook() {
        boolean isEditable = entryField.display.editable;
        boolean hasViewForm = entryField.field.viewFormInterface != null;
        boolean hasSelectForm = entryField.field.selectFormInterface != null;

        boolean isSelect = isEditable ? hasSelectForm : hasViewForm;
        setEnability(true, isSelect);
    }
}
