/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f8valuecontrol;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.BooleanEntryField;
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
public class BooleanValue implements ValueInterface {

    protected FormControl formControl;
    protected AbstractEntryField entryField;
    protected BooleanEntryField booleanEntryField;

    @Override
    public void init(FormControl fc, AbstractEntryField entryField) {
        this.formControl = fc;
        this.entryField = entryField;
        this.booleanEntryField = (BooleanEntryField) entryField;
    }

    @Override
    public void verifyDataEntry() {
        entryField.display.valid = true;
        entryField.display.errorMessage = "";
        entryField.display.i18nErrorMessage = "";
    }
    /**
     * BooleanValue never null (checkbox)
     * @return
     */
    @Override
    public boolean isNull() {
        return false; 
    }

    @Override
    public boolean getBoolean() throws EntryFieldWrongDataTypeException {
        return booleanEntryField.getValue();
    }

    @Override
    public void setValue(boolean value) throws EntryFieldWrongDataTypeException {
        booleanEntryField.setValue(value);
        entryField.display.valid = true;
        entryField.display.errorMessage = "";
        entryField.display.i18nErrorMessage = "";
    }

    @Override
    public String getString() throws EntryFieldWrongDataTypeException {
        return formControl.cmd.data.boolean2String(getBoolean());
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
    public void setValue(String value) throws EntryFieldWrongDataTypeException {
        if (formControl.cmd.data.isBoolean(value)) {
            setValue(formControl.cmd.data.isTrue(value));
        } else {
            throw new EntryFieldWrongDataTypeException();
        }
    }

    @Override
    public void setValue(byte[] value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

    @Override
    public void setValue(Boolean value) throws EntryFieldWrongDataTypeException {
        setValue(value.booleanValue());
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

    @Override
    public void setValue(Color value) throws EntryFieldWrongDataTypeException {
        throw new EntryFieldWrongDataTypeException();
    }

}
