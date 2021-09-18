/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.value.f8valuecontrol;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;

/**
 *
 * @author Ng Siak Hooi
 */
public interface ValueInterface {

    public void init(FormControl fc, AbstractEntryField entryField);

    public boolean isNull();

    public void verifyDataEntry();

    public String getString() throws EntryFieldWrongDataTypeException;

    public boolean getBoolean() throws EntryFieldWrongDataTypeException;

    public java.util.Calendar getCalendar() throws EntryFieldWrongDataTypeException;

    public java.math.BigDecimal getBigDecimal() throws EntryFieldWrongDataTypeException;

    public int getInteger() throws EntryFieldWrongDataTypeException;

    public long getLong() throws EntryFieldWrongDataTypeException;

    public java.awt.Color getColor() throws EntryFieldWrongDataTypeException;

    public void setValue(String value) throws EntryFieldWrongDataTypeException;
    //public void setValue(StringBuffer value);
    public void setValue(byte[] value) throws EntryFieldWrongDataTypeException;
    //public void setValue(Byte[] value);
    //public void setValue(Character value);
    //public void setValue(char value);
    public void setValue(boolean value) throws EntryFieldWrongDataTypeException;

    public void setValue(Boolean value) throws EntryFieldWrongDataTypeException;

    public void setValue(java.util.Calendar value) throws EntryFieldWrongDataTypeException;

    public void setValue(java.util.Date value) throws EntryFieldWrongDataTypeException;

    public void setValue(java.sql.Date value) throws EntryFieldWrongDataTypeException;

    public void setValue(java.sql.Timestamp value) throws EntryFieldWrongDataTypeException;

    public void setValue(java.sql.Time value) throws EntryFieldWrongDataTypeException;

    public void setValue(java.math.BigDecimal value) throws EntryFieldWrongDataTypeException;

    public void setValue(java.math.BigInteger value) throws EntryFieldWrongDataTypeException;

    public void setValue(Integer value) throws EntryFieldWrongDataTypeException;

    public void setValue(Double value) throws EntryFieldWrongDataTypeException;

    public void setValue(Float value) throws EntryFieldWrongDataTypeException;

    public void setValue(Short value) throws EntryFieldWrongDataTypeException;

    public void setValue(Long value) throws EntryFieldWrongDataTypeException;

    public void setValue(Byte value) throws EntryFieldWrongDataTypeException;

    public void setValue(int value) throws EntryFieldWrongDataTypeException;

    public void setValue(double value) throws EntryFieldWrongDataTypeException;

    public void setValue(float value) throws EntryFieldWrongDataTypeException;

    public void setValue(short value) throws EntryFieldWrongDataTypeException;

    public void setValue(long value) throws EntryFieldWrongDataTypeException;

    public void setValue(byte value) throws EntryFieldWrongDataTypeException;

    public void setValue(java.awt.Color value) throws EntryFieldWrongDataTypeException;
}
