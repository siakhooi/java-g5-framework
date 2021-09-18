/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.BooleanEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.CalendarEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.ColorEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.ComboBoxEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.NumericEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.PasswordEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.RadioBoxEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.TextEntryField;

/**
 *
 * @author Ng Siak Hooi
 */
public class EntryFieldFactory {

    public static AbstractEntryField createEntryField(FormControl formControl, Field userField) {
        AbstractEntryField entryField = null;
        switch (userField.fieldType) {
            case BOOLEAN:
                entryField = new BooleanEntryField();
                break;
            case CALENDAR:
                entryField = new CalendarEntryField();
                break;
            case COLOR:
                entryField = new ColorEntryField();
                break;
            case NUMERIC:
                entryField = new NumericEntryField();
                break;
            case PASSWORD:
                entryField = new PasswordEntryField();
                break;
            case TEXT:
                entryField = new TextEntryField();
                break;
            case RADIO:
                entryField = new RadioBoxEntryField();
                break;
            case COMBO:
                entryField = new ComboBoxEntryField();
                break;
        }
        if (entryField != null) {
            entryField.field = userField;
            entryField.formControl = formControl;
            entryField.init(userField, formControl);
            entryField.init();
            entryField.initAll(formControl, entryField);

            userField.entryField = entryField;
        }
        return entryField;

//        if(inputField!=null){
//            InputFieldOption option=new InputFieldOption(inputOption);
//            option.setInputField(inputField);
//            option.setUserField(userField);
//            userField.setInputFieldOption(option);
//            inputField.setInputFieldOption(option);
//            inputField.getDisplayControl().setInputFieldOption(option);
//            inputField.init();
//            inputField.getDisplayControl().setMandatory(!userField.allowBlank);
//            inputField.getDisplayControl().setEditable(userField.editable);
//            inputField.getDisplayControl().setVisible(userField.visible);
//            
//            inputField.refreshLook();
//        }
    }
}
