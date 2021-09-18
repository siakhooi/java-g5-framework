/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.util;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.EnterType;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.TextEntryField;

/**
 *
 * @author Ng Siak Hooi
 */
public class SelectTextFactory {

    public static void selectText(FormControl formControl, TextEntryField entryField) {

        boolean isEditable = entryField.display.editable;
        boolean hasViewForm = (entryField.field.viewFormInterface != null);
        boolean hasSelectForm = (entryField.field.selectFormInterface != null);
        Class<? extends UserFormInterface> formToExecute = null;

        if (isEditable) {
            if (hasSelectForm) {
                formToExecute = entryField.field.selectFormInterface;
                formControl.out.formEnterType = EnterType.FIELDSELECT;
                formControl.out.fieldName = entryField.field.fieldName;

            }
        } else {
            if (hasViewForm) {
                formToExecute = entryField.field.viewFormInterface;
                formControl.out.formEnterType = EnterType.FIELDVIEW;
                formControl.out.fieldName = entryField.field.fieldName;

            }
        }
        if (formToExecute == null) {
            return;
        }
        try {
            UserFormInterface f = formControl.cmd.form.create(formToExecute);

            formControl.out.stringValue= entryField.value.getString();
            formControl.out.fieldName = entryField.field.fieldName;
            formControl.cmd.form.execute(f);
            entryField.value.setValue(formControl.out.stringValue);
        } catch (EntryFieldWrongDataTypeException e) {

        }

//        getValueControl().verify(o.getSelectedValue());
//        refreshLook();
//        getInputFieldOption().panel().statusInputArea.clearStatus();
//
    }
}
