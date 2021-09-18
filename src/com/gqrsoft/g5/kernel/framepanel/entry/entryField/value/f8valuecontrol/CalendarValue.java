/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f8valuecontrol;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.CalendarEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier.CalendarValueVerifier;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Ng Siak Hooi
 */
public class CalendarValue implements ValueInterface {

    protected FormControl formControl;
    protected AbstractEntryField entryField;

    @Override
    public void init(FormControl fc, AbstractEntryField entryField) {
        this.formControl = fc;
        this.entryField = entryField;
    }

    private CalendarEntryField textField() {
        return (CalendarEntryField) this.entryField;
    }

    private CalendarValueVerifier verifier() {
        return (CalendarValueVerifier) entryField.valueverifier;
    }

    @Override
    public void verifyDataEntry() {
        verifier().verify();
    }

    @Override
    public boolean isNull() {
        return verifier().calendarValue == null;
    }

    @Override
    public Calendar getCalendar() throws EntryFieldWrongDataTypeException {
        return verifier().calendarValue;
    }

    @Override
    public void setValue(Calendar value) throws EntryFieldWrongDataTypeException {
        verifier().setValue(value);
    }

    @Override
    public void setValue(String value) throws EntryFieldWrongDataTypeException {
        textField().setValue(value);
        verifier().verify();
    }

    @Override
    public String getString() throws EntryFieldWrongDataTypeException {
        return entryField.display.editValueText;
    }

    @Override
    public boolean getBoolean() throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public BigDecimal getBigDecimal() throws EntryFieldWrongDataTypeException {
        return new BigDecimal(getLong());
    }

    @Override
    public int getInteger() throws EntryFieldWrongDataTypeException {
        return (int) getLong();
    }

    @Override
    public long getLong() throws EntryFieldWrongDataTypeException {
        return getCalendar().getTimeInMillis();
    }

    @Override
    public Color getColor() throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(byte[] value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(boolean value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Boolean value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Date value) throws EntryFieldWrongDataTypeException {
        setValue(formControl.cmd.cal.Date2Calendar(value));
    }

    @Override
    public void setValue(java.sql.Date value) throws EntryFieldWrongDataTypeException {
        setValue(formControl.cmd.cal.Date2Calendar(value));
    }

    @Override
    public void setValue(Timestamp value) throws EntryFieldWrongDataTypeException {
        setValue(formControl.cmd.cal.Timestamp2Calendar(value));
    }

    @Override
    public void setValue(Time value) throws EntryFieldWrongDataTypeException {
        setValue(formControl.cmd.cal.Time2Calendar(value));
    }

    @Override
    public void setValue(BigDecimal value) throws EntryFieldWrongDataTypeException {
        setValue(value.longValue());
    }

    @Override
    public void setValue(BigInteger value) throws EntryFieldWrongDataTypeException {
        setValue(value.longValue());
    }

    @Override
    public void setValue(Integer value) throws EntryFieldWrongDataTypeException {
        setValue(value.longValue());
    }

    @Override
    public void setValue(Double value) throws EntryFieldWrongDataTypeException {
        setValue(value.longValue());
    }

    @Override
    public void setValue(Float value) throws EntryFieldWrongDataTypeException {
        setValue(value.longValue());
    }

    @Override
    public void setValue(Short value) throws EntryFieldWrongDataTypeException {
        setValue(value.longValue());
    }

    @Override
    public void setValue(Long value) throws EntryFieldWrongDataTypeException {
        setValue(value.longValue());
    }

    @Override
    public void setValue(Byte value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(int value) throws EntryFieldWrongDataTypeException {
        setValue((long) value);
    }

    @Override
    public void setValue(double value) throws EntryFieldWrongDataTypeException {
        setValue((long) value);
    }

    @Override
    public void setValue(float value) throws EntryFieldWrongDataTypeException {
        setValue((long) value);
    }

    @Override
    public void setValue(short value) throws EntryFieldWrongDataTypeException {
        setValue((long) value);
    }

    @Override
    public void setValue(long value) throws EntryFieldWrongDataTypeException {
        setValue(formControl.cmd.cal.long2Calendar(value));
    }

    @Override
    public void setValue(byte value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Color value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }
}
