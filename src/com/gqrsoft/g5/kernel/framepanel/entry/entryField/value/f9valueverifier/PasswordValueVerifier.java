/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.core.util.NULL;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldDisplayControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.PasswordEntryField;

/**
 *
 * @author Ng Siak Hooi
 */
public class PasswordValueVerifier extends AbstractValueVerifier {

    private String value;
//    private String inputMask;
//    private String outputMask;
    public String textValue = "";

    private PasswordEntryField textField() {
        return (PasswordEntryField) super.entryField;
    }

    private EntryFieldDisplayControl display() {
        return super.entryField.display;
    }

    @Override
    public void verify() {
//        inputMask = textField().field.inputMask;
        value = textField().getValue();
//        outputMask = textField().field.outputMask;

        textValue = value;
        display().editValueText = value;

        if (isNullValue()) {
            return;
        }
        display().valid = true;
        display().errorMessage = "";
        display().i18nErrorMessage = "";
        display().editValueText = value;

        display().displayValueText =
                EngineConfiguration.Entry.DEFAULT_TEXT_REPRESENT_PASSWORD;
    }

    private boolean isNullValue() {
        if (NULL.isNull(value)) {
            boolean i = textField().field.mandatory;
            if (i) {
                display().valid = false;
                display().errorMessage = "PasswordValueVerifier.error.ValueIsNull";
                display().editValueText = "";
                display().displayValueText = "";
                display().i18nErrorMessage =
                        formControl.cmd.lang.getSystemString(
                        display().errorMessage);
            } else {
                display().valid = true;
                display().errorMessage = "";
                display().editValueText = "";
                display().displayValueText = "";
                display().i18nErrorMessage = "";
            }
            return true; //no continue
        }
        return false;
    }
}
