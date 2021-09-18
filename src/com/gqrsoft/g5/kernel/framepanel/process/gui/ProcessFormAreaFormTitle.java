/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.gui;

import java.awt.Component;
import javax.swing.JLabel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessFormAreaFormTitle extends AbstractProcessFormArea {

    private JLabel lblProcessFormTitle;

    @Override
    public Component getComponent() {
        return lblProcessFormTitle;
    }

    @Override
    public void init() {
        String t = "";
        t = getFormControl().userForm.getFormI18nTitle();
        lblProcessFormTitle = new JLabel(t);
        lblProcessFormTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

    }
}
