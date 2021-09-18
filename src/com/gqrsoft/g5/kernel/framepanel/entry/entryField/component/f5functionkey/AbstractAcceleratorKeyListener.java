/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f5functionkey;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldEnum.FieldAction;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.GuiComponentInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractAcceleratorKeyListener implements KeyListener, GuiComponentInterface {

    protected FormControl formControl;
    protected AbstractEntryField entryField;
    private boolean isHelp = true;
    private boolean isSelect = true;

    protected void setEnability(boolean isHelp, boolean isSelect) {
        this.isHelp = isHelp;
        this.isSelect = isSelect;
    }

    @Override
    public void init(FormControl fc, AbstractEntryField entryField) {
        this.formControl = fc;
        this.entryField = entryField;
    }

    @Override
    public void init() {
        setEnability(false, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_F1) {
            if (isHelp) {
                entryField.executeAction(FieldAction.SHOWHELP);
            }
        } else if (keyCode == KeyEvent.VK_F2) {
            if (isSelect) {
                entryField.executeAction(FieldAction.SHOWSELECT);
            }
        }
    }
}
