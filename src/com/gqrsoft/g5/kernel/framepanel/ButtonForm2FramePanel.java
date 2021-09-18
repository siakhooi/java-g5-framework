/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel;

import com.gqrsoft.g5.developer.form.ButtonForm2;
import com.gqrsoft.g5.kernel.framepanel.button2.ButtonForm2PanelGenerator;
import com.gqrsoft.g5.kernel.framepanel.menu.MenuTree;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ButtonForm2FramePanel extends AbstractFramePanel {

    @Override
    public void initPanel() {
    }

    @Override
    public void onInEnter() {
        ButtonForm2 mf = (ButtonForm2) (getFormControl().userForm);
        mf.build();
        MenuTree mt = mf.menuTree;
        
        
        ButtonForm2PanelGenerator buttonPanelGenerator =
                new ButtonForm2PanelGenerator();
        JPanel panel=
                buttonPanelGenerator.generate(mf);

        getPanel().setLayout(new BorderLayout());
        getPanel().add(panel, BorderLayout.CENTER);
    }

    @Override
    public void onEscapeKeyPressed() {
        getFormControl().userForm.onEscapeKeyPressed();
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
