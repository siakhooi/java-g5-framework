/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield;

import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldEnum.FieldAction;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f1.DefaultFieldLabel;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f2dataentry.TextComboBox;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu.ContextMenuMouseListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu.TextComboBoxContextMenu;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f5functionkey.TextComboBoxAcceleratorKeyListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f7valuelistener.ComboBoxValueListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.util.ShowHelpFactory;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f8valuecontrol.ComboBoxValue;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier.BlankValueVerifier;

/**
 *
 * @author Ng Siak Hooi
 */
public class ComboBoxEntryField extends AbstractEntryField {

    private TextComboBox combobox;

    @Override
    public void init() {
        // 1 label
        DefaultFieldLabel f1 = new DefaultFieldLabel();
        super.label = f1;
        super.allGuiComponents.add(f1);

        // 2 dataentry
        combobox = new TextComboBox();
        super.dataComponent = combobox;
        super.allGuiComponents.add(combobox);

        // 3 select
        // no select button

        // 4 context menu
        TextComboBoxContextMenu f4 = new TextComboBoxContextMenu();
        super.contextMenu = f4;
        super.allGuiComponents.add(f4);
        combobox.addMouseListener(new ContextMenuMouseListener(f4));

        // 5 function key
        TextComboBoxAcceleratorKeyListener f5 =
                new TextComboBoxAcceleratorKeyListener();
        super.functionKey = f5;
        super.allGuiComponents.add(f5);
        combobox.addKeyListener(f5);

        // 6 keypress control
        // no keytyped control

        // 7 value listener
        ComboBoxValueListener f7 = new ComboBoxValueListener(combobox);
        super.valueListener = f7;
        super.allGuiComponents.add(f7);
        combobox.addFocusListener(f7);

        // 8 value control
        ComboBoxValue f8 = new ComboBoxValue();
        f8.init(formControl, this);
        super.value = f8;

        // 9 value verifier
        BlankValueVerifier f9 = new BlankValueVerifier();
        f9.init(formControl, this);
        super.valueverifier = f9;
    }

    @Override
    public void executeAction(FieldAction t) {
        switch (t) {
            case SHOWHELP:
                ShowHelpFactory.showHelp(formControl, this);
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

    public boolean isNull() {
        return combobox.isNull();
    }
    @Override
    public void setToInitValue() {
        combobox.setValue(0);
    }

    public void setValue(int value) {
        combobox.setValue(value);
    }

    public void setValue(String value) {
        combobox.setValue(value);
    }

    public String getValue() {
        return combobox.getValue();
    }

    public int getValueIndex() {
        return combobox.getValueIndex();
    }
}
