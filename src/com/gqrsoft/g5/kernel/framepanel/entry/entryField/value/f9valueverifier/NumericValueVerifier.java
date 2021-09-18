/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier;

import com.gqrsoft.g5.kernel.core.util.NULL;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldDisplayControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.NumericEntryField;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author Ng Siak Hooi
 */
public class NumericValueVerifier extends AbstractValueVerifier {

    private String value;
    private String inputMask;
    private String outputMask;
    public BigDecimal numericValue = null;

    public void setValue(BigDecimal value) {
        inputMask = numericField().field.inputMask;
        outputMask = numericField().field.outputMask;

        if (value == null) {
            numericValue = null;
            return;
        }
        display().valid = true;
        display().errorMessage = "";
        display().i18nErrorMessage = "";
        numericValue = value;
//reconvert value
        if (!NULL.isNull(inputMask)) {
            BigDecimal bd = BigDecimal.ZERO.add(numericValue);
            DecimalFormat d = new DecimalFormat(inputMask);
            display().editValueText = d.format(bd.doubleValue());
            numericValue = new BigDecimal(display().editValueText);
        } else {
            display().editValueText = numericValue.toPlainString();
        }

//generate outputmask
        if (!NULL.isNull(outputMask)) {
            BigDecimal bd = BigDecimal.ZERO.add(numericValue);
            DecimalFormat d = new DecimalFormat(outputMask);
            display().displayValueText = d.format(bd.doubleValue());
        } else {
            display().displayValueText = display().editValueText;
        }
    }

    private NumericEntryField numericField() {
        return (NumericEntryField) super.entryField;
    }

    private EntryFieldDisplayControl display() {
        return super.entryField.display;
    }

    @Override
    public void verify() {
        inputMask = numericField().field.inputMask;
        value = numericField().getValue();
        outputMask = numericField().field.outputMask;

        display().editValueText = value;

        if (isNullValue()) {
            numericValue = null;
            return;
        }
//        if (isNullInputMask()) {
//            return;
//        }
        checkValue();
    }

    private void checkValue() {
        BigDecimal tempValue = null;
        try {
            tempValue = new BigDecimal(value);
        } catch (Exception e) {
            display().valid = false;
            display().errorMessage =
                    "NumericValueVerifier.error.InvalidNumericValue";
            display().displayValueText = value;
            display().i18nErrorMessage =
                    formControl.cmd.lang.getSystemString(
                    display().errorMessage);
            return;
        }
        display().valid = true;
        display().errorMessage = "";
        display().i18nErrorMessage = "";

//reconvert value
        if (!NULL.isNull(inputMask)) {
            BigDecimal bd = BigDecimal.ZERO.add(tempValue);
            DecimalFormat d = new DecimalFormat(inputMask);
            display().editValueText = d.format(bd.doubleValue());
            numericValue = new BigDecimal(display().editValueText);
        } else {
            display().editValueText = value;
            numericValue =new BigDecimal(value);
        }

//generate outputmask
        if (!NULL.isNull(outputMask)) {
            BigDecimal bd = BigDecimal.ZERO.add(tempValue);
            DecimalFormat d = new DecimalFormat(outputMask);
            display().displayValueText = d.format(bd.doubleValue());
        } else {
            display().displayValueText = display().editValueText;
        }
    }
//    private boolean isNullInputMask() {
//        if (NULL.isNull(inputMask)) {
//            display().valid = true;
//            display().errorMessage = "";
//            //display().displayValueText = value;
//            display().i18nErrorMessage = "";
//            return true;
//        }
//        return false;
//    }
    private boolean isNullValue() {
        if (NULL.isNull(value)) {
            boolean i = numericField().field.mandatory;
            if (i) {
                display().valid = false;
                display().errorMessage = "NumericValueVerifier.error.ValueIsNull";
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
