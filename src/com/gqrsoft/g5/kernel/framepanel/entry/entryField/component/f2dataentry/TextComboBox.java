/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f2dataentry;

import com.gqrsoft.g5.kernel.config.EntryFieldStyle;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.GuiComponentInterface;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Ng Siak Hooi
 */
public class TextComboBox extends JComboBox implements GuiComponentInterface {

    protected FormControl formControl;
    protected AbstractEntryField entryField;
    private Vector<String> allOptions;

    public String getValue() {
        int i = super.getSelectedIndex();
        return entryField.field.optionValues.elementAt(i);
    }

    public int getValueIndex() {
        return super.getSelectedIndex();
    }
    // never null?
    public boolean isNull() {
        return false;
    }

    @Override
    public void init(FormControl fc, AbstractEntryField entryField) {
        this.formControl = fc;
        this.entryField = entryField;
    }

    @Override
    public void init() {
        allOptions = new Vector<String>();
        int optionCount = entryField.field.optionI18nLabels.size();
        int m = entryField.field.optionValues.size();
        if (m < optionCount) {
            optionCount = m;
        } //use smallest

        for (int i = 0; i < optionCount; i++) {
            String i18nLabel = entryField.field.optionI18nLabels.elementAt(i);
            //i18nLabel = formControl.cmd.lang.getString(i18nLabel);
            allOptions.add(i18nLabel);
        }
        super.setModel(new DefaultComboBoxModel(allOptions));
    }

    @Override
    public void refreshLook() {
        super.setEnabled(entryField.display.editable);
        super.setVisible(entryField.display.visible);
        super.setBackground(
                EntryFieldStyle.getFieldBackgroundColor(
                entryField.display.valid));

    }

    public void setValue(int value) {
        super.setSelectedIndex(value);
    }

    public void setValue(String value) {
        for (int i = 0; i < allOptions.size(); i++) {
            String a = entryField.field.optionValues.elementAt(i);

            if (a.equals(value)) {
                setValue(i);
                return;
            }
        }

    }
}
