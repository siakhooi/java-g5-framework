/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.frame;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.FramePanelInterface;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JToolBar;

/**
 *
 * @author Ng Siak Hooi
 */
public interface FrameInterface {

    public FormControl getFormControl();

    public void initStep30(boolean modal);

    public void initStep40(
            FramePanelInterface left,
            FramePanelInterface right,
            FramePanelInterface top,
            FramePanelInterface bottom);

    public void initStep41(JToolBar toolbar);

    public void setFormControl(FormControl value);

    public void show();

    public void close();

    public boolean isJFrame();

    public boolean isJDialog();

    public JFrame getJFrame();

    public JDialog getJDialog();

    public Window getWindow();
}
