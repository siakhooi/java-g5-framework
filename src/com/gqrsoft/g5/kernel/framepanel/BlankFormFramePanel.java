/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel;

import com.gqrsoft.g5.developer.form.BlankForm;

/**
 *
 * @author Ng Siak Hooi
 */
public class BlankFormFramePanel extends AbstractFramePanel {

    @Override
    public void initPanel() {
    }

    @Override
    public void onEscapeKeyPressed() {
        getFormControl().userForm.onEscapeKeyPressed();
    }

    @Override
    public void onInEnter() {
        BlankForm bf = (BlankForm) (getFormControl().userForm);
        bf.buildBlankForm(getPanel());
    }

    @Override
    public void onOutExit() {
    }

    @Override
    public void onInExit() {
    }

    @Override
    public void onOutEnter() {
    }
}
