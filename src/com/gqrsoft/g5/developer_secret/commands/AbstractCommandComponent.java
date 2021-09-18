/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_secret.commands;

import com.gqrsoft.g5.kernel.control.FormControl;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractCommandComponent implements CommandComponentInterface {

    private FormControl formControl;

    @Override
    public void setFormControl(FormControl fc) {
        this.formControl = fc;
    }

    protected FormControl getFormControl() {
        return this.formControl;
    }
}
