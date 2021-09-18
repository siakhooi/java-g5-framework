/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer_protected.tools.ButtonsBuilder;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum;
import com.gqrsoft.g5.developer_secret.abstractform.PrivateAbstractListForm;
import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractListForm extends PrivateAbstractListForm {

    public final ButtonsBuilder buttons = new ButtonsBuilder(this);
    //
    private Vector<Field> currentVector = null;
    private HashMap<String, Field> currentHashMap = null;

    /**
     * 
     */
    protected abstract void buildKeyList();

    /**
     * 
     */
    protected abstract void buildDataList();

    /**
     * 
     */
    protected abstract void buildGeneral();

    protected boolean isSelectMode() {
        return getActionEnable(ListFormEnum.ListActionType.SELECT);
    }

    protected final boolean getActionEnable(
            ListFormEnum.ListActionType t) {
        switch (t) {
            case ADD:
                return model.add;
            case COPY:
                return model.copy;
            case DELETE:
                return model.delete;
            case EDIT:
                return model.edit;
            case RELOAD:
                return model.reload;
            case SAVE_ALL_TO_CSV:
                return model.saveAllCSV;
            case SAVE_SELECTED_TO_CSV:
                return model.saveSelectedCSV;
            case SELECT:
                return model.select;
            case VIEW:
                return model.view;
            case CLOSE:
                return model.close;
            case RECORD_CHECK_ON_DELETE:
                return model.recordCheckOnDelete;
            case RECORD_CHECK_ON_COPY:
                return model.recordCheckOnCopy;
            case RECORD_CHECK_ON_EDIT:
                return model.recordCheckOnEdit;
            case RECORD_CHECK_ON_VIEW:
                return model.recordCheckOnView;
        }
        return false;
    }

    /**
     * 
     * @param t
     * @param value
     */
    protected final void setActionEnable(
            ListFormEnum.ListActionType t, boolean value) {
        switch (t) {
            case ADD:
                model.add = value;
                break;
            case COPY:
                model.copy = value;
                break;
            case DELETE:
                model.delete = value;
                break;
            case EDIT:
                model.edit = value;
                break;
            case RELOAD:
                model.reload = value;
                break;
            case SAVE_ALL_TO_CSV:
                model.saveAllCSV = value;
                break;
            case SAVE_SELECTED_TO_CSV:
                model.saveSelectedCSV = value;
                break;
            case SELECT:
                model.select = value;
                break;
            case VIEW:
                model.view = value;
                break;
            case CLOSE:
                model.close = value;
                break;
            case RECORD_CHECK_ON_DELETE:
                model.recordCheckOnDelete = value;
                break;
            case RECORD_CHECK_ON_COPY:
                model.recordCheckOnCopy = value;
                break;
            case RECORD_CHECK_ON_EDIT:
                model.recordCheckOnEdit = value;
                break;
            case RECORD_CHECK_ON_VIEW:
                model.recordCheckOnView = value;
                break;
        }
    }

    /**
     * 
     * @param rs
     */
    protected final void useSQL(ResultSet rs) {
        this.resultset = rs;
    //this.array = null;
    }

    /**
     * 
     * @param rsName
     */
    protected final void useSQL(String rsName) {
        useSQL(cmd.db.getResultSet(rsName));
    }

    /**
     * 
     * @param obj
     */
    protected final void useArray(Object[][] obj) {
        this.array = obj;
    }

    /**
     * 
     * @param vector
     */
    protected final void useVector(Vector<Vector<Object>> vector) {
        this.vector = vector;
    }

    /**
     * 
     * @return
     */
    public abstract ListFormEnum.DataType getDataType();

    /**
     * 
     * @return
     */
    public abstract ListFormEnum.ColumnAutoResizeMode getColumnAutoResizeMode();

    /**
     * 
     * @return
     */
    public abstract ListFormEnum.SelectionType getSelectionType();

    @Override
    protected final void addNewField(Field field) {
        currentVector.add(field);
        currentHashMap.put(field.fieldName, field);
        allFieldsByIndex.add(field);
        allFieldsByName.put(field.fieldName, field);
    }

    @Override
    public final void initForm() {
        currentVector = allKeyFieldsByIndex;
        currentHashMap = allKeyFieldsByName;
        buildKeyList();

        currentVector = allDataFieldsByIndex;
        currentHashMap = allDataFieldsByName;
        buildDataList();

        buildGeneral();
    }

    /**
     * 
     * @param form
     */
    protected final void setCopyForm(
            Class<? extends UserFormInterface> form) {
        model.CopyFormInterface = form;
    }

    protected final void setDeleteForm(
            Class<? extends UserFormInterface> form) {
        model.DeleteFormInterface = form;
    }
    /**
     * 
     * @param form
     */
    protected final void setAddForm(
            Class<? extends UserFormInterface> form) {
        model.AddFormInterface = form;
    }

    /**
     * 
     * @param form
     */
    protected final void setEditForm(
            Class<? extends UserFormInterface> form) {
        model.EditFormInterface = form;
    }

    /**
     * 
     * @param form
     */
    protected final void setViewForm(
            Class<? extends UserFormInterface> form) {
        model.ViewFormInterface = form;
    }

    /**
     * not in use.
     */
    @Override
    public final void onEscapeKeyPressed() {
    }

    /**
     * Force reload of the list
     */
    protected final void reload() {
        cmd.list.refreshList();
    }

    public abstract void buttonClick(String name, int selectedRow, int[] selectedRows);

    @Override
    public final void buttonClick(String name) {
        buttonClick(name, cmd.list.getSelectedRow(),
                cmd.list.getSelectedRows());
    }

    protected final int getDefaultButtonIconHeight() {
        return EngineConfiguration.List.DEFAULT_ICON_HEIGHT;
    }
}
