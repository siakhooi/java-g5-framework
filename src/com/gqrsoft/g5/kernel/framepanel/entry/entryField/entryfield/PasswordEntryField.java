/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield;

import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldEnum.FieldAction;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f1.DefaultFieldLabel;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f2dataentry.PasswordTextField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f3.DefaultSelectButton;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu.ContextMenuMouseListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu.PasswordFieldContextMenu;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f5functionkey.ColorAcceleratorKeyListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f6keytypedcontrol.TextKeyTypedListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f7valuelistener.PasswordFieldValueListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f8valuecontrol.PasswordValue;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier.PasswordValueVerifier;

/**
 *
 * @author Ng Siak Hooi
 */
public class PasswordEntryField extends AbstractEntryField {

    private PasswordTextField textfield;

    @Override
    public void init() {
        // 1 label
        DefaultFieldLabel f1 = new DefaultFieldLabel();
        super.label = f1;
        super.allGuiComponents.add(f1);

        // 2 dataentry
        textfield = new PasswordTextField();
        super.dataComponent = textfield;
        super.allGuiComponents.add(textfield);

        // 3 select
        DefaultSelectButton f3 = new DefaultSelectButton();
        super.selectButton = f3;
        super.allGuiComponents.add(f3);

        // 4 context menu
        PasswordFieldContextMenu f4 = new PasswordFieldContextMenu();
        super.contextMenu = f4;
        super.allGuiComponents.add(f4);
        textfield.addMouseListener(new ContextMenuMouseListener(f4));

        // 5 function key
        ColorAcceleratorKeyListener f5 =
                new ColorAcceleratorKeyListener();
        super.functionKey = f5;
        super.allGuiComponents.add(f5);
        textfield.addKeyListener(f5);

        // 6 keypress control
        TextKeyTypedListener f6 = new TextKeyTypedListener(textfield);
        super.keyTyped = f6;
        super.allGuiComponents.add(f6);
        textfield.addKeyListener(f6);

        // 7 value listener
        PasswordFieldValueListener f7 = new PasswordFieldValueListener(textfield);
        super.valueListener = f7;
        super.allGuiComponents.add(f7);
        textfield.addFocusListener(f7);
        textfield.addActionListener(f7);

        // 8 value control
        PasswordValue f8 = new PasswordValue();
        f8.init(formControl, this);
        super.value = f8;

        // 9 value verifier
        PasswordValueVerifier f9 = new PasswordValueVerifier();
        f9.init(formControl, this);
        super.valueverifier = f9;

    }

    @Override
    public void setToInitValue() {
        textfield.setText("");
    }

    public String getValue() {
        return new String(textfield.getPassword());
    }

    public void setValue(String value) {
        textfield.setText(value);
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
                textfield.paste();
                break;
            case DELETE:
                textfield.replaceSelection("");
                break;
            case CLEARALL:
                textfield.selectAll();
                textfield.replaceSelection("");
                break;
            case SELECTALL:
                textfield.selectAll();
                break;
            case VERIFYVALUE:
                super.executeAction(t);
                break;
        }
    }
}
