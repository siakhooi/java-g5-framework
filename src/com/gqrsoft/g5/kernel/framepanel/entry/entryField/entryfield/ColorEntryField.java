/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield;

import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldEnum.FieldAction;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f1.DefaultFieldLabel;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f2dataentry.ColorFormattedTextField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f3.DefaultSelectButton;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu.ColorTextFieldContextMenu;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f4contextmenu.ContextMenuMouseListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f5functionkey.ColorAcceleratorKeyListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f6keytypedcontrol.TextKeyTypedListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f7valuelistener.TextFieldListener;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.util.SelectColorFactory;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f8valuecontrol.ColorValue;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier.ColorValueVerifier;

/**
 *
 * @author Ng Siak Hooi
 */
public class ColorEntryField extends AbstractEntryField {

    private ColorFormattedTextField textfield;

    @Override
    public void init() {
        // 1 label
        DefaultFieldLabel f1 = new DefaultFieldLabel();
        super.label = f1;
        super.allGuiComponents.add(f1);

        // 2 dataentry
        textfield = new ColorFormattedTextField();
        super.dataComponent = textfield;
        super.allGuiComponents.add(textfield);

        // 3 select
        DefaultSelectButton f3 = new DefaultSelectButton();
        super.selectButton = f3;
        super.allGuiComponents.add(f3);

        // 4 context menu
        ColorTextFieldContextMenu f4 = new ColorTextFieldContextMenu();
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
        TextFieldListener f7 = new TextFieldListener(textfield);
        super.valueListener = f7;
        super.allGuiComponents.add(f7);
        textfield.addFocusListener(f7);
        textfield.addActionListener(f7);

        // 8 value control
        ColorValue f8 = new ColorValue();
        f8.init(formControl, this);
        super.value = f8;

        // 9 value verifier
        ColorValueVerifier f9 = new ColorValueVerifier();
        f9.init(formControl, this);
        super.valueverifier = f9;

    }

    @Override
    public void setToInitValue() {
        textfield.setText("");
    }

    public String getValue() {
        return textfield.getText();
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
                this.externalEvent.onFieldOutExit(this.field.fieldName);
                SelectColorFactory.selectColor(formControl, this);
                executeAction(FieldAction.VERIFYVALUE);
//                if (display.valid) {
//                    display.valid = externalEvent.verifyFieldValue(this.field.fieldName);
//                    display.i18nErrorMessage = externalEvent.getI18nVerifyErrorMessage();
//                }
//                refreshAll();
                this.externalEvent.onFieldOutEnter(this.field.fieldName);
                break;
            case CUT:
                textfield.cut();
                break;
            case COPY:
                textfield.copy();
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
