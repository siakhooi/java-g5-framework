/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.button;

import com.gqrsoft.g5.developer_protected.tools.ButtonsBuilder;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ButtonPanelLayoutFactory {

    public static void execute(
            JPanel parent,
            JPanel buttonPanel,
            ButtonsBuilder buttons) {
        parent.add(buttonPanel, buttons.verticalLocation.pageLayoutValue);
    }
}
