/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;

/**
 *
 * @author Ng Siak Hooi
 */
public interface GuiComponentInterface {

    public void init(FormControl fc, AbstractEntryField entryField);

    public void init();

    public void refreshLook();
}
