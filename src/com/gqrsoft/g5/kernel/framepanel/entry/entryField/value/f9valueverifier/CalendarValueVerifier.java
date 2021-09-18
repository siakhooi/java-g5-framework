/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier;

import com.gqrsoft.g5.kernel.core.util.NULL;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldDisplayControl;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.CalendarEntryField;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Ng Siak Hooi
 */
public class CalendarValueVerifier extends AbstractValueVerifier {

    private String value;
    private String inputMask;
    private String outputMask;
    public Calendar calendarValue = null;

    public void setValue(Calendar value) {
        inputMask = calendarField().field.inputMask;
        outputMask = calendarField().field.outputMask;

        if (value == null) {
            calendarValue = null;
            return;
        }
        if (isNullInputMask()) {
            return;
        }

        display().valid = true;
        display().errorMessage = "";
        display().i18nErrorMessage = "";

        calendarValue = value;
        SimpleDateFormat tempInput = new SimpleDateFormat(inputMask);
        display().editValueText = tempInput.format(value.getTime());
        if (NULL.isNull(outputMask)) {
            display().displayValueText = display().editValueText;
        } else {
            SimpleDateFormat tempOutput = new SimpleDateFormat(outputMask);
            display().displayValueText = tempOutput.format(value.getTime());
        }
    }

    private CalendarEntryField calendarField() {
        return (CalendarEntryField) super.entryField;
    }

    private EntryFieldDisplayControl display() {
        return super.entryField.display;
    }

    @Override
    public void verify() {
        inputMask = calendarField().field.inputMask;
        value = calendarField().getValue();
        outputMask = calendarField().field.outputMask;

        display().editValueText = value;

        if (isNullValue()) {
            calendarValue = null;
            return;
        }
        if (isNullInputMask()) {
            return;
        }
        checkInputMask();
    }

    private void checkInputMask() {
        SimpleDateFormat tempInput = new SimpleDateFormat(inputMask);
        Date tempDate = null;
        try {
            tempDate = tempInput.parse(value);
        } catch (java.text.ParseException ex) {
            display().valid = false;
            display().errorMessage = "CalendarValueVerifier.error.InvalidCalendarOrCalendarFormat";
            display().displayValueText = value;
            display().i18nErrorMessage =
                    formControl.cmd.lang.getSystemString(
                    display().errorMessage);
            return;
        }
        display().valid = true;
        display().errorMessage = "";
        display().i18nErrorMessage = "";

        calendarValue = formControl.cmd.cal.Date2Calendar(tempDate);
        display().editValueText = tempInput.format(tempDate);
        if (NULL.isNull(outputMask)) {
            display().displayValueText = display().editValueText;
        } else {
            SimpleDateFormat tempOutput = new SimpleDateFormat(outputMask);
            display().displayValueText = tempOutput.format(tempDate);
        }
    }

    private boolean isNullValue() {
        if (NULL.isNull(value)) {
            boolean i = calendarField().field.mandatory;
            if (i) {
                display().valid = false;
                display().errorMessage = "CalendarValueVerifier.error.ValueIsNull";
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

    private boolean isNullInputMask() {
        if (NULL.isNull(inputMask)) {
            display().valid = false;
            display().errorMessage = "CalendarValueVerifier.error.NoInputMaskForCalendar";
            display().displayValueText = value;
            display().i18nErrorMessage =
                    formControl.cmd.lang.getSystemString(
                    display().errorMessage);
            return true;
        }
        return false;
    }
}
