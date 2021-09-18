/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.gui;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.config.ImageIconResource;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessFormAreaInformationIcon extends AbstractProcessFormArea {

    private JLabel lblInfoIcon;

    @Override
    public void init() {
        ImageIcon imgInfo = ImageIconResource.getProcessFormInformationIcon();
        imgInfo = getFormControl().cmd.image.resize(imgInfo,
                EngineConfiguration.Process.DEFAULT_ICON_HEIGHT);
        lblInfoIcon = new JLabel(imgInfo);
    }

    @Override
    public Component getComponent() {
        return lblInfoIcon;
    }
}
