/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.util;

import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.ColorEntryField;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.kernel.control.FormControl;

/**
 *
 * @author Ng Siak Hooi
 */
public class SelectColorFactory {

    public static void selectColor(
            FormControl formControl, ColorEntryField entryField) {
        try {
            UserFormInterface f = formControl.cmd.form.create(entryField.field.selectFormInterface);

            formControl.out.colorValue = entryField.value.getColor();
            formControl.out.fieldName = entryField.field.fieldName;
            formControl.cmd.form.execute(f);
            entryField.value.setValue(formControl.out.colorValue);

        } catch (EntryFieldWrongDataTypeException ex) {
        //should not happened.
        }
    }
}
