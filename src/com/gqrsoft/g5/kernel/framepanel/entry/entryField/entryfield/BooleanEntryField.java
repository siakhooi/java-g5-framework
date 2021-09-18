/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield;

import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldEnum.FieldAction;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu.BooleanCheckBoxContextMenu;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu.ContextMenuMouseListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f2dataentry.BooleanCheckBox;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f5functionkey.BooleanCheckboxAcceleratorKeyListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f7valuelistener.CheckBoxValueListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f8valuecontrol.BooleanValue;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier.BlankValueVerifier;

/**
 *
 * @author Ng Siak Hooi
 */
public class BooleanEntryField extends AbstractEntryField {

    private BooleanCheckBox checkbox;

    @Override
    public void setToInitValue() {
        checkbox.setSelected(false);
    }

    public boolean getValue() {
        return checkbox.isSelected();
    }

    public void setValue(boolean aTrue) {
        checkbox.setSelected(aTrue);
    }

    @Override
    public void init() {
        // 1 label
        // don't have

        // 2 dataentry
        checkbox = new BooleanCheckBox();
        super.dataComponent = checkbox;
        super.allGuiComponents.add(checkbox);

        // 3 select
        // don't have

        // 4 context menu
        BooleanCheckBoxContextMenu f4 = new BooleanCheckBoxContextMenu();
        super.contextMenu = f4;
        super.allGuiComponents.add(f4);
        checkbox.addMouseListener(new ContextMenuMouseListener(f4));

        // 5 function key
        BooleanCheckboxAcceleratorKeyListener f5 = new BooleanCheckboxAcceleratorKeyListener();
        super.functionKey = f5;
        super.allGuiComponents.add(f5);
        checkbox.addKeyListener(f5);

        // 6 keypress control
        // don't have

        // 7 value listener
        CheckBoxValueListener f7 = new CheckBoxValueListener(checkbox);
        super.valueListener = f7;
        super.allGuiComponents.add(f7);
        checkbox.addFocusListener(f7);
        checkbox.addActionListener(f7);

        // 8 value control
        BooleanValue f8 = new BooleanValue();
        f8.init(formControl, this);
        super.value = f8;

        // 9 value verifier
        // don't have
        BlankValueVerifier f9 = new BlankValueVerifier();
        f9.init(formControl, this);
        super.valueverifier = f9;

    }

    @Override
    public void executeAction(FieldAction t) {
        switch (t) {
            case SHOWHELP:
                super.executeAction(t);
                break;
            case SHOWSELECT:
                break;
            case CUT:
                break;
            case COPY:
                break;
            case PASTE:
                break;
            case DELETE:
                break;
            case CLEARALL:
                break;
            case SELECTALL:
                break;
            case VERIFYVALUE:
                super.executeAction(t);
                break;
        }
    }
}
