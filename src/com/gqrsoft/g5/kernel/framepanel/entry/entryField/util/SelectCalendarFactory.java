/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.util;

import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.CalendarEntryField;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.kernel.control.FormControl;

/**
 *
 * @author Ng Siak Hooi
 */
public class SelectCalendarFactory {

    public static void selectCalendar(
            FormControl formControl, CalendarEntryField entryField) {
        try {
            UserFormInterface f = formControl.cmd.form.create(entryField.field.selectFormInterface);

            formControl.out.calendarValue = entryField.value.getCalendar();
            formControl.out.fieldName = entryField.field.fieldName;
            formControl.cmd.form.execute(f);
            entryField.value.setValue(formControl.out.calendarValue);


        } catch (EntryFieldWrongDataTypeException ex) {
        //should not happened.
        }
    }
}
