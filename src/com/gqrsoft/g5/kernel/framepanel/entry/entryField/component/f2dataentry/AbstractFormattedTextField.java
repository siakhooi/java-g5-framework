/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f2dataentry;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.config.EntryFieldStyle;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.GuiComponentInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractFormattedTextField
        extends JFormattedTextField
        implements GuiComponentInterface, TextDataEntryInterface {

    protected FormControl formControl;
    protected AbstractEntryField entryField;

    @Override
    public void init(FormControl fc, AbstractEntryField entryField) {
        this.formControl = fc;
        this.entryField = entryField;
    }

    @Override
    public int getCurrentValueLength() {
        return getText().length();
    }

    @Override
    public int getSelectedValueLength() {
        String s = getSelectedText();
        if (s == null) {
            return 0;
        }
        return s.length();
    }

    @Override
    public void init() {
        int fieldCol = entryField.field.textColumns;
        if (fieldCol == 0) {
            fieldCol = EngineConfiguration.Entry.DEFAULT_TEXT_COLUMNS;
        } // if not, will resize automatically.
        super.setColumns(fieldCol);

        super.setFont(EntryFieldStyle.getFieldFont());
        super.setHorizontalAlignment(entryField.field.alignment.jTextFieldAlignment);

//weird, already have in *EntryField
//        TextFieldListener tfl = new TextFieldListener(this);
//        tfl.init(formControl, entryField);
//        entryField.valueListener = tfl;
//        addFocusListener(tfl);
//        addActionListener(tfl);

    }

    @Override
    public void refreshLook() {
//        CONSOLE.println("edit: "+entryField.display.editValueText);
//        CONSOLE.println("display: "+entryField.display.displayValueText);
        super.setEditable(entryField.display.editable);
        super.setVisible(entryField.display.visible);
        //super.setFocusable(true);
        super.setBackground(
                EntryFieldStyle.getFieldBackgroundColor(
                entryField.display.valid));

        if (entryField.display.valid) {
            super.setOpaque(entryField.display.editable);
            if (isFocusOwner()) {
                setText(entryField.display.editValueText);
            } else {
                setText(entryField.display.displayValueText);
            }
        } else {
            super.setOpaque(true);
            setText(entryField.display.editValueText);
        }
    }
}
