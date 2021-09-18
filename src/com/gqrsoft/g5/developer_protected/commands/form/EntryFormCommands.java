/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.form;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum;
import com.gqrsoft.g5.developer.publicobject.TabFormEnum;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.external.VerifyResult;
import com.gqrsoft.g5.kernel.framepanel.entry.entryForm.EntryFieldGroup;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

/**
 *
 * @author Ng Siak Hooi
 */
public class EntryFormCommands extends AbstractCommandComponent {

    private final int ERROR_NO_TAB = -1;

    private JTabbedPane getTab() {
        return getFormControl().entryPanel.dataFieldTabbedPane;
    }

    private int getTabIndex(String tabName) {
        EntryFieldGroup f = getFormControl().entryForm.allTab.get(tabName);
        if (f == null) {
            return ERROR_NO_TAB;
        }
        int i = getTab().indexOfTab(f.tabI18nDescription);
        if (i == -1) {
            return ERROR_NO_TAB;
        }
        return i;
    }

    public void setTabEnabled(String tabName, boolean value) {
        int i = getTabIndex(tabName);
        if (i == -1) {
            return;
        }
        getTab().setEnabledAt(i, value);
    }

    public void showTab(String tabName) {
        int i = getTabIndex(tabName);
        if (i == -1) {
            return;
        }
        getTab().setSelectedIndex(i);
    }

    public void setTabLayout(TabFormEnum.TabLayoutPolicy v) {
        getTab().setTabLayoutPolicy(v.tabLayoutPolicy);
    }

    public void setTabIcon(String tabName, Icon icon) {
        int i = getTabIndex(tabName);
        if (i == -1) {
            return;
        }
        getTab().setIconAt(i, icon);
    }

    public void setTabTooltips(String tabName, String tooltips) {
        int i = getTabIndex(tabName);
        if (i == -1) {
            return;
        }
        getTab().setToolTipTextAt(i, tooltips);
    }

    public void setTabBackgroundAt(String tabName, Color background) {
        int i = getTabIndex(tabName);
        if (i == -1) {
            return;
        }
        getTab().setBackgroundAt(i, background);
    }

    public void setTabForegroundAt(String tabName, Color foreground) {
        int i = getTabIndex(tabName);
        if (i == -1) {
            return;
        }
        getTab().setForegroundAt(i, foreground);
    }

    @Deprecated
    public void setI18nStatus(String i18nMessage) {
        getFormControl().entryPanel.statusBar.setStatus(i18nMessage);
    }

    @Deprecated
    public void setStatus(String message) {
        message = getFormControl().cmd.lang.getString(message);
        setI18nStatus(message);
    }

    @Deprecated
    public void setStatus(String message, String... values) {
        message = getFormControl().cmd.lang.getString(message, values);
        setI18nStatus(message);
    }

    public void setI18nStatus(EntryFormEnum.StatusType type, String i18nMessage) {
        getFormControl().entryPanel.statusBar.setStatus(type, i18nMessage);
    }

    public void setStatus(EntryFormEnum.StatusType type, String message) {
        message = getFormControl().cmd.lang.getString(message);
        setI18nStatus(type, message);
    }

    public void setStatus(EntryFormEnum.StatusType type, String message, String... values) {
        message = getFormControl().cmd.lang.getString(message, values);
        setI18nStatus(type, message);
    }

    public void verify(String fieldName) {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        verifyAfterSetValue(entryField);
    }

    private void verifyAfterSetValue(AbstractEntryField entryField) {
        //entryField.executeAction(FieldAction.VERIFYVALUE);
        if (entryField.display.valid) {
            VerifyResult vr = entryField.externalEvent.verifyFieldValue(entryField.field.fieldName);
            entryField.display.valid = vr.valid;
            entryField.display.i18nErrorMessage = vr.i18nErrorMessage;
//            entryField.display.valid = entryField.externalEvent.verifyFieldValue(entryField.field.fieldName);
//            entryField.display.i18nErrorMessage = entryField.externalEvent.getI18nVerifyErrorMessage();
        }
        entryField.refreshAll();
    }

    public void setValue(String fieldName, String value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

//    public void setValue(String fieldName, String value) throws EntryFieldWrongDataTypeException{        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;        entryField.value.setValue(value);        entryField.executeAction(FieldAction.VERIFYVALUE);    }
    public void setValue(String fieldName, byte[] value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, boolean value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, Boolean value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, java.util.Calendar value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, java.util.Date value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, java.sql.Date value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, java.sql.Timestamp value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, java.sql.Time value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, java.math.BigDecimal value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, java.math.BigInteger value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, Integer value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, Double value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, Float value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, Short value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, Long value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, Byte value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, int value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, double value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, float value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, short value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, long value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, byte value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setValue(String fieldName, java.awt.Color value) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.value.setValue(value);
        verifyAfterSetValue(entryField);
    }

    public void setEditable(String fieldName, boolean value) {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.field.editable = value;
        entryField.display.setEditable(value);
        entryField.refreshAll();
    }

    public void setVisible(String fieldName, boolean value) {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.field.visible = value;
        entryField.display.setVisible(value);
        entryField.refreshAll();
    }

    public void setMandatory(String fieldName, boolean value) {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        entryField.field.mandatory = value;
        entryField.display.setMandatory(value);
        entryField.refreshAll();
    }

    public boolean isNull(String fieldName) {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        return entryField.value.isNull();
    }

    public String getString(String fieldName) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        return entryField.value.getString();
    }

    public boolean getBoolean(String fieldName) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        return entryField.value.getBoolean();
    }

    public java.util.Calendar getCalendar(String fieldName) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        return entryField.value.getCalendar();
    }

    public java.math.BigDecimal getBigDecimal(String fieldName) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        return entryField.value.getBigDecimal();
    }

    public int getInteger(String fieldName) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        return entryField.value.getInteger();
    }

    public long getLong(String fieldName) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        return entryField.value.getLong();
    }

    public java.awt.Color getColor(String fieldName) throws EntryFieldWrongDataTypeException {
        AbstractEntryField entryField = getFormControl().entryForm.allFieldsByName.get(fieldName).entryField;
        return entryField.value.getColor();
    }
//    public boolean isEnteringAddMode(){
//        return panel().mode().enteringMode.getModeType().isAdd();
//    }
//    public boolean isEnteringEditMode(){
//        return panel().mode().enteringMode.getModeType().isEdit();
//    }
//    public boolean isEnteringViewMode(){
//        return panel().mode().enteringMode.getModeType().isView();
//    }
//    public boolean isEnteringSearchMode(){
//        return panel().mode().enteringMode.getModeType().isSearch();
//    }
//    public boolean isEnteringReportMode(){
//        return panel().mode().enteringMode.getModeType().isReport();
//    }
//    public boolean isEnteringCustomMode(){
//        return panel().mode().enteringMode.getModeType().isCustom();
//    }
//    public boolean isEnteringFromFormInit(){
//        return panel().mode().currentMode==null;
//    }
//    public boolean isEnteringFromAddMode(){
//        return panel().mode().currentMode.getModeType().isAdd();
//    }
//    public boolean isEnteringFromEditMode(){
//        return panel().mode().currentMode.getModeType().isEdit();
//    }
//    public boolean isEnteringFromViewMode(){
//        return panel().mode().currentMode.getModeType().isView();
//    }
//    public boolean isEnteringFromSearchMode(){
//        return panel().mode().currentMode.getModeType().isSearch();
//    }
//    public boolean isEnteringFromReportMode(){
//        return panel().mode().currentMode.getModeType().isReport();
//    }
//    public boolean isEnteringFromCustomMode(){
//        return panel().mode().currentMode.getModeType().isCustom();
//    }
//    public void goAddMode   (){ panel().mode().switchMode(ModeType.ADD   ); }
//    public void goViewMode  (){ panel().mode().switchMode(ModeType.VIEW  ); }
//    public void goEditMode  (){ panel().mode().switchMode(ModeType.EDIT  ); }
//    public void goSearchMode(){ panel().mode().switchMode(ModeType.SEARCH); }
//    public void goReportMode(){ panel().mode().switchMode(ModeType.REPORT); }
//    public void goCustomMode(){ panel().mode().switchMode(ModeType.CUSTOM); }
//    
//    public boolean saveAdd(){  return panel().mode().saveAdd();  }
//    public boolean saveEdit(){  return panel().mode().saveEdit();  }
//    public boolean saveDelete(){  return panel().mode().saveDelete();  }
//    public boolean prepareData(){  return panel().mode().prepareData();  }
//    public boolean prepareDefaultData(){  return panel().mode().prepareDefaultData();  }
//
////    public String getListStringValue(String fieldName){
////        int r=panel().listTableInputArea.getTable().getSelectedRow();
////        if(r==-1) return "";
////        int c=panel().getListFieldIndex(fieldName);
////        if(c==-1) return "";
////        return (String)panel().listTableInputArea.getTableModel().getValueAt(r, c);
////    }
////    public int getListIntegerValue(String fieldName, int defaultValue){
////        String v=getListStringValue(fieldName);
////        if(v.length()==0) return defaultValue;
////        return Integer.parseInt(v);
////    }
////    public BigDecimal getListBigDecimalValue(String fieldName){
////        String v=getListStringValue(fieldName);
////        if(v.length()==0) return null;
////        return new BigDecimal(v);
////        
////    }
//
//    private String errorMessage="";
//    public void error(String s){ this.errorMessage=s; }
//    public String getErrorMessage(){ return this.errorMessage; }
//    public void resetErrorMessage() { this.errorMessage=""; }
//    public void setStatus(String s){
//        panel().statusInputArea.setTranslatedStatus(s);
//    }
//    public void setMultiLanguageStatus(String s){
//        panel().statusInputArea.setMultiLanguageStatus(s);
//    }
//    public void refreshBottomInfo() {
//        panel().bottomInfoInputArea.refresh();
//    }
//    public void refreshTopInfo() {
//        panel().topInfoInputArea.refresh();
//    }
}
