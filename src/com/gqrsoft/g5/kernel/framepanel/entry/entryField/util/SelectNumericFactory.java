/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.util;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.EnterType;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.NumericEntryField;

/**
 *
 * @author Ng Siak Hooi
 */
public class SelectNumericFactory {

    public static void selectNumeric(
            FormControl formControl, NumericEntryField entryField) {

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
//        if (isEditable && hasLookupForm) {
//            getInputFieldOption().form().onFieldSelectExit();
//        }
//        if (!isEditable && hasDetailForm) {
//            getInputFieldOption().form().onFieldViewExit();
//        }
        try {
            UserFormInterface f = formControl.cmd.form.create(formToExecute);

            formControl.out.bigDecimalValue = entryField.value.getBigDecimal();
            formControl.out.fieldName = entryField.field.fieldName;
            formControl.cmd.form.execute(f);
            entryField.value.setValue(formControl.out.bigDecimalValue);
        } catch (EntryFieldWrongDataTypeException e) {

        }
//        getValueControl().verify(o.getSelectedValue());
//        refreshLook();
//        getInputFieldOption().panel().statusInputArea.clearStatus();
//
//        if (isEditable && hasLookupForm) {
//            getInputFieldOption().form().onFieldSelectReturn();
//        }
//        if (!isEditable && hasDetailForm) {
//            getInputFieldOption().form().onFieldViewReturn();
//        }

    }
}
