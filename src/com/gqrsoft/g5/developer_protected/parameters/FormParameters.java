/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.parameters;

import com.gqrsoft.g5.developer.publicobject.FormEnum;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.swing.table.TableModel;

/**
 *
 * @author Ng Siak Hooi
 */
public class FormParameters {

    public GlobalParameters map = new GlobalParameters();
    //
    public FormEnum.EnterType formEnterType =
            FormEnum.EnterType.INIT;
    public Calendar calendarValue = null;
    public Color colorValue = null;
    public Image imageValue = null;
    public boolean booleanValue = false;
    public boolean[] booleanValues;
    public int intValue = 0;
    public int[] intValues;
//====    
    public Integer integerValue = null;
    public Integer[] integerValues = null;
    public String stringValue = null;
    public String[] stringValues = null;
    public File fileValue = null;
    public File[] fileValues = null;
    public BigDecimal bigDecimalValue = null;
    public BigDecimal[] bigDecimalValues = null;
    //===
    public String i18nTitle = null;
    public String message = null;
    public String fieldName = null;
    //===
    public String extension = null;
    public String i18nExtensionDescription = null;
    public FormEnum.DialogMessageType dialogMessageType =
            FormEnum.DialogMessageType.INFORMATION;
    //==
    public TableModel tableModel;
    public Object objectValue;
    public Object[] objectValues;
    public Object[][] object2DValues;

//    public void reset() {
//        calendarValue = null;
//        colorValue = null;
//        imageValue = null;
//        integerValue = null;
//        fileValue = null;
//        fileValues = null;
//        stringValue = null;
//        stringValues = null;
//        message = null;
//        fieldName = null;
//    }
}
