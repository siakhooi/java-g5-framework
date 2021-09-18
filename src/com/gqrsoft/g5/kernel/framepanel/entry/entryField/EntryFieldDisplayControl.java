/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField;

import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;

/**
 *
 * @author Ng Siak Hooi
 */
public class EntryFieldDisplayControl {

    public boolean visible = true;
    public boolean editable = true;
    public boolean mandatory = false;
    public boolean valid = true;
    public String errorMessage = "";
    public String i18nErrorMessage = "";
    private AbstractEntryField entryField;

    //for textField
    public String editValueText = "";
    public String displayValueText = "";
//    public String getInvalidValueText();
    public EntryFieldDisplayControl(AbstractEntryField entryField) {
        this.entryField = entryField;
    }

    public void setEditable(boolean value) {
        boolean userFieldEditable = entryField.field.editable;
        this.editable = userFieldEditable ? value : false;
    }

    public void setVisible(boolean value) {
        boolean userFieldVisible = entryField.field.visible;
        this.visible = userFieldVisible ? value : false;
    }

    public void setMandatory(boolean value) {
        boolean userFieldMandatory = entryField.field.mandatory;
        this.mandatory = userFieldMandatory ? value : false;
    }
}
