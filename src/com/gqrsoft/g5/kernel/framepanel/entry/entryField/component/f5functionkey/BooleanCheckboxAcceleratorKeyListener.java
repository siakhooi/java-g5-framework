/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f5functionkey;

/**
 *
 * @author Ng Siak Hooi
 */
public class BooleanCheckboxAcceleratorKeyListener extends AbstractAcceleratorKeyListener {

    @Override
    public void init() {
        setEnability(true, false);
    }

    @Override
    public void refreshLook() {
        setEnability(true, false);
    }
}
