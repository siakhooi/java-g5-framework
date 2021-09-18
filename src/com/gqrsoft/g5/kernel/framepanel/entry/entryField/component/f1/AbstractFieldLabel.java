/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f1;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.GuiComponentInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import javax.swing.JLabel;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractFieldLabel
        extends JLabel implements GuiComponentInterface {

    protected FormControl formControl;
    protected AbstractEntryField entryField;

    @Override
    public void init(FormControl fc, AbstractEntryField entryField) {
        this.formControl = fc;
        this.entryField = entryField;
    }
}
