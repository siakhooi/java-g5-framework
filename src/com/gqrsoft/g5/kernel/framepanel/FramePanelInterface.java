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
public interface FramePanelInterface {

    public void eventAfterVisible();

    public void eventBeforeVisible();

    public JPanel getPanel();

    public void initPanel0();

    public void initPanel30();

    public void setFormControl(FormControl fc);

    public void initPanel();

    public abstract void onEscapeKeyPressed();

    public void onInEnter();

    public void onOutExit();

    public void onInExit();

    public void onOutEnter();
}
