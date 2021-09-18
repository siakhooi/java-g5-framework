/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f8valuecontrol;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.TextEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier.TextValueVerifier;
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
public class TextValue implements ValueInterface {

    protected FormControl formControl;
    protected AbstractEntryField entryField;

    @Override
    public void init(FormControl fc, AbstractEntryField entryField) {
        this.formControl = fc;
        this.entryField = entryField;
    }

    private TextEntryField textField() {
        return (TextEntryField) this.entryField;
    }

    private TextValueVerifier verifier() {
        return (TextValueVerifier) entryField.valueverifier;
    }

    @Override
    public void verifyDataEntry() {
        verifier().verify();
    }

    @Override
    public boolean isNull() {
        return verifier().textValue == null;
    }

    @Override
    public String getString() throws EntryFieldWrongDataTypeException {
        return verifier().textValue;
    }

    @Override
    public void setValue(String value) throws EntryFieldWrongDataTypeException {
        textField().setValue(value);
        verifyDataEntry();
    }

    @Override
    public boolean getBoolean() throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public Calendar getCalendar() throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public BigDecimal getBigDecimal() throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public int getInteger() throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public long getLong() throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public Color getColor() throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(byte[] value) throws EntryFieldWrongDataTypeException {
        setValue(new String(value));
    }

    @Override
    public void setValue(boolean value) throws EntryFieldWrongDataTypeException {
        setValue(formControl.cmd.data.boolean2String(value));
    }

    @Override
    public void setValue(Boolean value) throws EntryFieldWrongDataTypeException {
        setValue(value.booleanValue());
    }

    @Override
    public void setValue(Calendar value) throws EntryFieldWrongDataTypeException {
        String i = EngineConfiguration.Entry.DEFAULT_CALENDAR_INPUT_FORMAT;
        setValue(formControl.cmd.lang.formatCalendar(value, i));
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
        setValue(value.toPlainString());
    }

    @Override
    public void setValue(BigInteger value) throws EntryFieldWrongDataTypeException {
        setValue(value.toString());
    }

    @Override
    public void setValue(Integer value) throws EntryFieldWrongDataTypeException {
        setValue(value.toString());
    }

    @Override
    public void setValue(Double value) throws EntryFieldWrongDataTypeException {
        setValue(value.toString());
    }

    @Override
    public void setValue(Float value) throws EntryFieldWrongDataTypeException {
        setValue(value.toString());
    }

    @Override
    public void setValue(Short value) throws EntryFieldWrongDataTypeException {
        setValue(value.toString());
    }

    @Override
    public void setValue(Long value) throws EntryFieldWrongDataTypeException {
        setValue(value.toString());
    }

    @Override
    public void setValue(Byte value) throws EntryFieldWrongDataTypeException {
        setValue(value.toString());
    }

    @Override
    public void setValue(int value) throws EntryFieldWrongDataTypeException {
        setValue(new Integer(value));
    }

    @Override
    public void setValue(double value) throws EntryFieldWrongDataTypeException {
        setValue(new Double(value));
    }

    @Override
    public void setValue(float value) throws EntryFieldWrongDataTypeException {
        setValue(new Float(value));
    }

    @Override
    public void setValue(short value) throws EntryFieldWrongDataTypeException {
        setValue(new Short(value));
    }

    @Override
    public void setValue(long value) throws EntryFieldWrongDataTypeException {
        setValue(new Long(value));
    }

    @Override
    public void setValue(byte value) throws EntryFieldWrongDataTypeException {
        setValue(new Byte(value));
    }

    @Override
    public void setValue(Color value) throws EntryFieldWrongDataTypeException {
        setValue(formControl.cmd.data.Color2Hex(value));
    }
}
