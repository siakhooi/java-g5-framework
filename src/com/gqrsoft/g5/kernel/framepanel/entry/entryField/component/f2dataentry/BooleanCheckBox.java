/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f2dataentry;

import com.gqrsoft.g5.kernel.config.EntryFieldStyle;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.GuiComponentInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 *
 * @author Ng Siak Hooi
 */
public class BooleanCheckBox extends JCheckBox implements GuiComponentInterface {

    protected FormControl formControl;
    protected AbstractEntryField entryField;

    @Override
    public void init(FormControl fc, AbstractEntryField entryField) {
        this.formControl = fc;
        this.entryField = entryField;
    }

    @Override
    public void init() {
        String label = entryField.field.i18nLabel;
        super.setText(label);
        super.setHorizontalAlignment(JLabel.LEADING);

        //weird, already have in BooleanEntryField
//        CheckBoxValueListener tfl = new CheckBoxValueListener(this);
//        tfl.init(formControl, entryField);
//        entryField.valueListener = tfl;
//        addFocusListener(tfl);
//        addActionListener(tfl);

        setFont(EntryFieldStyle.getLabelFont(entryField.display.mandatory));
    }

    @Override
    public void refreshLook() {
        super.setEnabled(entryField.display.editable);
        super.setVisible(entryField.display.visible);
//        super.setBackground(
//                EntryFieldStyle.getLabelBackgroundColor(
//                entryField.display.mandatory));

//        super.setBackground(
//                EntryFieldStyle.getFieldBackgroundColor(
//                entryField.display.valid));
//        super.setOpaque(entryField.display.editable);
    }
}
