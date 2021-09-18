/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.gui;

import com.gqrsoft.g5.kernel.control.FormControl;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractProcessFormArea implements ProcessFormAreaInterface {

    private FormControl fc;

    public void setFormControl(FormControl fc) {
        this.fc = fc;
    }

    protected FormControl getFormControl() {
        return fc;
    }
}
