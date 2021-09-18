/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f8valuecontrol;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.ColorEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f9valueverifier.ColorValueVerifier;
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
public class ColorValue implements ValueInterface {

    protected FormControl formControl;
    protected AbstractEntryField entryField;

    @Override
    public void init(FormControl fc, AbstractEntryField entryField) {
        this.formControl = fc;
        this.entryField = entryField;
    }

    private ColorEntryField textField() {
        return (ColorEntryField) this.entryField;
    }

    private ColorValueVerifier verifier() {
        return (ColorValueVerifier) entryField.valueverifier;
    }

    @Override
    public void verifyDataEntry() {
        verifier().verify();
    }

    @Override
    public boolean isNull() {
        return verifier().colorValue == null;
    }

    @Override
    public Color getColor() throws EntryFieldWrongDataTypeException {
        return verifier().colorValue;
    }

    @Override
    public void setValue(Color value) throws EntryFieldWrongDataTypeException {
        verifier().setValue(value);
    }

    @Override
    public String getString() throws EntryFieldWrongDataTypeException {
        return formControl.cmd.data.Color2Hex(getColor());
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
    public void setValue(String value) throws EntryFieldWrongDataTypeException {
        setValue(formControl.cmd.data.Hex2Color(value));
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
    public void setValue(Calendar value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Date value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(java.sql.Date value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Timestamp value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Time value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(BigDecimal value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(BigInteger value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Integer value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Double value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Float value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Short value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Long value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Byte value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(int value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(double value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(float value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(short value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(long value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(byte value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }
}
