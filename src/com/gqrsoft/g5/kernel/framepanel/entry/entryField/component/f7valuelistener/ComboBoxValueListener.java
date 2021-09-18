/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f7valuelistener;

import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldEnum.FieldAction;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.component.f2dataentry.TextComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;

/**
 *
 * @author Ng Siak Hooi
 */
public class ComboBoxValueListener extends AbstractValueListener {

    private TextComboBox dataField;
    //private boolean tempLost = false;
    public ComboBoxValueListener(TextComboBox dataField) {
        this.dataField = dataField;
    }

    @Override
    public void focusGained(FocusEvent e) {
//        if (tempLost) {
//            tempLost = false;
//            return;
//        }
        if (!entryField.display.editable) {
            return;
        }
        if (!entryField.display.valid) {
            String msg = entryField.display.i18nErrorMessage;
            entryField.externalStatusOutput.setStatus(StatusType.ERROR, msg);
        }
        entryField.externalEvent.onFocusGain(entryField.field.fieldName);
        entryField.refreshAll();
    }

    @Override
    public void focusLost(FocusEvent e) {
//        if (e.isTemporary()) {
//            tempLost = true;
//            return;
//        }
        if (!entryField.display.editable) {
            return;
        }
        entryField.externalEvent.onFocusLost(entryField.field.fieldName);

        entryField.executeAction(FieldAction.VERIFYVALUE);
        entryField.refreshAll();
        entryField.externalStatusOutput.setStatus(StatusType.INFO, "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!entryField.display.editable) {
            return;
        }
        entryField.executeAction(FieldAction.VERIFYVALUE);
        entryField.refreshAll();
        if (!entryField.display.valid) {
            String msg = entryField.display.i18nErrorMessage;
            entryField.externalStatusOutput.setStatus(StatusType.ERROR, msg);
        }
    }
}
