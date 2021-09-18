/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.gui;

import com.gqrsoft.g5.kernel.control.FormControl;
import java.awt.Component;

/**
 *
 * @author Ng Siak Hooi
 */
public interface ProcessFormAreaInterface {

    public void setFormControl(FormControl fc);

    public void init();

    public Component getComponent();
}
