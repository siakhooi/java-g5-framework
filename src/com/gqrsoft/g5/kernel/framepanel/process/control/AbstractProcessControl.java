/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.control;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.ProcessFormFramePanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class AbstractProcessControl {

    private FormControl fc;

    public void setFormControl(FormControl fc) {
        this.fc = fc;
    }

    protected FormControl getFormControl() {
        return fc;
    }

    protected ProcessForm form() {
        return fc.processForm;
    }

    protected ProcessFormFramePanel panel() {
        return fc.processPanel;
    }
}
