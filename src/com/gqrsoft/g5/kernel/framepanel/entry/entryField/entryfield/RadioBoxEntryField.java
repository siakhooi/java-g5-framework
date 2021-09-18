/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield;

import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldEnum.FieldAction;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f1.DefaultFieldLabel;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f2dataentry.TextRadioBox;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu.TextRadioBoxContextMenu;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f5functionkey.TextRadioBoxAcceleratorKeyListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f7valuelistener.RadioBoxValueListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.util.ShowHelpFactory;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f8valuecontrol.RadioBoxValue;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier.BlankValueVerifier;

/**
 *
 * @author Ng Siak Hooi
 */
public class RadioBoxEntryField extends AbstractEntryField {

    private TextRadioBox radiobox;

    @Override
    public void init() {
        // 1 label
        DefaultFieldLabel f1 = new DefaultFieldLabel();
        super.label = f1;
        super.allGuiComponents.add(f1);

        // 2 dataentry
        radiobox = new TextRadioBox();
        super.dataComponent = radiobox;
        super.allGuiComponents.add(radiobox);

        // 3 select
        // no select button

        // 4 context menu
        TextRadioBoxContextMenu f4 = new TextRadioBoxContextMenu();
        super.contextMenu = f4;
        super.allGuiComponents.add(f4);
        //radiobox.addMouseListener(new ContextMenuMouseListener(f4));

        // 5 function key
        TextRadioBoxAcceleratorKeyListener f5 =
                new TextRadioBoxAcceleratorKeyListener();
        super.functionKey = f5;
        super.allGuiComponents.add(f5);
        //radiobox.addKeyListener(f5);

        // 6 keypress control
        // no keytyped control

        // 7 value listener
        RadioBoxValueListener f7 = new RadioBoxValueListener(radiobox);
        super.valueListener = f7;
        super.allGuiComponents.add(f7);
        // addListener() by TextRadioBox.init()

        // 8 value control
        RadioBoxValue f8 = new RadioBoxValue();
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

    @Override
    public void setToInitValue() {
        radiobox.setValue(0);
    }

    public boolean isNull() {
        return radiobox.isNull();
    }

    public void setValue(int value) {
        radiobox.setValue(value);
    }

    public void setValue(String value) {
        radiobox.setValue(value);
    }

    public String getValue() {
        return radiobox.getValue();
    }

    public int getValueIndex() {
        return radiobox.getValueIndex();
    }
}
