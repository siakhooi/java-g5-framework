/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier;

import com.gqrsoft.g5.kernel.core.util.NULL;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.TextEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldDisplayControl;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ng Siak Hooi
 */
public class TextValueVerifier extends AbstractValueVerifier {

    private String value;
    private String inputMask;
    private String outputMask;
    private Pattern p;
    private Matcher m;
    public String textValue = "";

    private TextEntryField textField() {
        return (TextEntryField) super.entryField;
    }

    private EntryFieldDisplayControl display() {
        return super.entryField.display;
    }

    @Override
    public void verify() {
        inputMask = textField().field.inputMask;
        value = textField().getValue();
        outputMask = textField().field.outputMask;

        textValue = value;
        display().editValueText = value;

        if (isNullValue()) {
            return;
        }
        if (isNullInputMask()) {
            return;
        }
        checkInputMask();
    }

    private boolean isNullValue() {
        if (NULL.isNull(value)) {
            boolean i = textField().field.mandatory;
            if (i) {
                display().valid = false;
                display().errorMessage = "TextValueVerifier.error.ValueIsNull";
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

    private boolean isNullInputMask() {
        if (NULL.isNull(inputMask)) {
            display().valid = true;
            display().errorMessage = "";
            display().displayValueText = value;
            display().i18nErrorMessage = "";
            return true;
        }
        return false;
    }

    private void checkInputMask() {
        p = Pattern.compile(inputMask);
        m = p.matcher(value);
        boolean isMatch = m.matches();
        if (isMatch) {
            display().valid = true;
            display().errorMessage = "";
            display().i18nErrorMessage = "";
            generateText();
        } else {
            display().valid = false;
            display().errorMessage = "TextValueVerifier.error.UnmatchedInputMask";
            display().displayValueText = value;
            display().i18nErrorMessage =
                    formControl.cmd.lang.getSystemString(
                    display().errorMessage);
        }
    }

    private void generateText() {
        if (NULL.isNull(outputMask)) {
            display().displayValueText = value;
            return;
        }

        MessageFormat mf = new MessageFormat(outputMask);
        String[] s = new String[m.groupCount()];
        for (int i = 0; i < m.groupCount(); i++) {
            s[i] = m.group(i + 1);
        }
        display().displayValueText = mf.format(s);
    }
}
