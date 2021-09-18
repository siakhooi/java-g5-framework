/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f5functionkey;

/**
 *
 * @author Ng Siak Hooi
 */
public class CalendarAcceleratorKeyListener extends AbstractAcceleratorKeyListener {

    @Override
    public void refreshLook() {
        boolean isSelect = super.entryField.display.editable;
        setEnability(true, isSelect);
    }
}
