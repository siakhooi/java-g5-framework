/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel;

import com.gqrsoft.g5.kernel.control.FormControl;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractFramePanel implements FramePanelInterface {

    private JPanel panel = new JPanel();
    private FormControl formControl;

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void setFormControl(FormControl fc) {
        this.formControl = fc;
    }

    protected FormControl getFormControl() {
        return this.formControl;
    }

    @Override
    public void eventAfterVisible() {
    }

    @Override
    public void eventBeforeVisible() {
    }

    @Override
    public void initPanel0() {
    }

    @Override
    public void initPanel30() {
    }
}
