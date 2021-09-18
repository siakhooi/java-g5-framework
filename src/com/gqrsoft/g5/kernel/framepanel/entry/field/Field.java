/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.field;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FieldEnum;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class Field {

    public FieldEnum.FieldType fieldType;
    public String fieldName;

    public Field(FieldEnum.FieldType type, String name) {
        this.fieldType = type;
        this.fieldName = name;
    //sqlDataType=type.getDefaultSqlDataType();
    }
//    
    public String label = "";
    public String helpMessage = "";
    public String i18nLabel = "";
    public String i18nHelpMessage = "";
//
    public boolean editable = true;
    public boolean visible = true;
    //public boolean allowBlank = true;
    public boolean mandatory = false;
//
    public Class<? extends UserFormInterface> selectFormInterface = null;//select
    public Class<? extends UserFormInterface> viewFormInterface = null; //on view
    public FieldEnum.AlignmentType alignment=FieldEnum.AlignmentType.LEFT;
    public FieldEnum.TextCaseType textCaseType = FieldEnum.TextCaseType.MIX;
//
    public int listColumnWidth = 0;
    public int entryFormColumnSpan = 0;
    public int textColumns = 0;
    public int maximumLength = 0;
//    
    public String inputMask = "";
    public String outputMask = "";
//  
    public String allowCharacters = "";
//
//    public String defaultValueLocalNameSpace=""; //localName
//    public String dataValueLocalNameSpace="";    //localName
//    public String listDataLocalNameSpace="";  //localName
//
//    public UserFieldSQLDataType sqlDataType=UserFieldSQLDataType.STRING;
//// database data type (for retriving purpose??)
//    // for setParapurpose??? how?
//    
    public Vector<String> optionValues;
    public Vector<String> optionI18nLabels;
    public AbstractEntryField entryField;
}
