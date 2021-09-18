/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel;

import com.gqrsoft.g5.kernel.framepanel.button.ButtonPanelGenerator;
import com.gqrsoft.g5.kernel.framepanel.button.ButtonPanelLayoutFactory;
import com.gqrsoft.g5.developer.form.ButtonForm;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 * @deprecated 
 */
@Deprecated
public class ButtonFormFramePanel extends AbstractFramePanel {

    private JPanel main = new JPanel();
    private JPanel buttonPanel;

    @Override
    public void initPanel() {
        bf = (ButtonForm) (getFormControl().userForm);
        bf.buildButtonForm(main);
    }
    private ButtonForm bf;

    @Override
    public void onEscapeKeyPressed() {
        getFormControl().userForm.onEscapeKeyPressed();
    }

    @Override
    public void onInEnter() {
        ButtonPanelGenerator bpg = new ButtonPanelGenerator();
        buttonPanel = bpg.generate(
                getFormControl(),
                bf.buttons,
                bf,
                null, null);
//        ButtonPanelLayoutFactory.execute(getPanel(), buttonPanel,
//                bf.buttons);
        getPanel().setLayout(new BorderLayout());
        getPanel().add(main, BorderLayout.CENTER);
        if (bf.buttons.allComponents.size() > 0 && 
                bf.showButtons()) {
            ButtonPanelLayoutFactory.execute(
                    getPanel(), buttonPanel, bf.buttons);
        }
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
