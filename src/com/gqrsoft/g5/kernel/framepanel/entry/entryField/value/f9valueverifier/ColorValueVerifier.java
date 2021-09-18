/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier;

import com.gqrsoft.g5.kernel.core.util.CONSOLE;
import com.gqrsoft.g5.kernel.core.util.NULL;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldDisplayControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.ColorEntryField;
import java.awt.Color;

/**
 *
 * @author Ng Siak Hooi
 */
public class ColorValueVerifier extends AbstractValueVerifier {

    private String value;
    public Color colorValue = null;

    public void setValue(Color value) {
        if (value == null) {
            colorValue = null;
            return;
        }

        display().valid = true;
        display().errorMessage = "";
        display().i18nErrorMessage = "";
        colorValue = value;
        display().editValueText = formControl.cmd.data.Color2Hex(colorValue);
        display().displayValueText = display().editValueText;
    }

    private ColorEntryField colorField() {
        return (ColorEntryField) super.entryField;
    }

    private EntryFieldDisplayControl display() {
        return super.entryField.display;
    }

    @Override
    public void verify() {
//        inputMask = colorField().field.inputMask;
        value = colorField().getValue();
//        outputMask = colorField().field.outputMask;

        display().editValueText = value;

        if (isNullValue()) {
            colorValue = null;
            return;
        }
        checkValue();
    }

    private void checkValue() {
        long i = 0;
        try {
            i = Long.parseLong(value, 16);
        } catch (Exception e) {
            display().valid = false;
            display().errorMessage =
                    "ColorValueVerifier.error.InvalidColorValue";
            display().displayValueText = value;
            display().i18nErrorMessage =
                    formControl.cmd.lang.getSystemString(
                    display().errorMessage);
            return;
        }
        display().valid = true;
        display().errorMessage = "";
        display().i18nErrorMessage = "";
        colorValue = formControl.cmd.data.long2Color(i);
        display().editValueText = formControl.cmd.data.long2Hex(i, 6);
        display().displayValueText = display().editValueText;

    }

    private boolean isNullValue() {
        if (NULL.isNull(value)) {
            boolean i = colorField().field.mandatory;
            if (i) {
                display().valid = false;
                display().errorMessage = "ColorValueVerifier.error.ValueIsNull";
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
            return true;
        }
        return false;
    }
}
