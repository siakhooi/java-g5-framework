/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer.publicobject.ListFormEnum;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ColumnAutoResizeMode;
import com.gqrsoft.g5.developer_protected.abstractform.AbstractListForm;
import java.awt.Component;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class ListForm extends AbstractListForm {

    public void afterReload() {
        cmd.list.selectFirstRow();
    }

    public Component formatCell(Component cellComp, int row, int column) {
        return cellComp;
    }

    public ListFormEnum.ListActionType getDoubleClickAction() {
        if (super.isSelectMode()) {
            return ListFormEnum.ListActionType.SELECT;
        } else {
            return ListFormEnum.ListActionType.VIEW;
        }
    }

    @Override
    protected abstract void buildKeyList();

    @Override
    protected abstract void buildDataList();

    @Override
    protected abstract void buildGeneral();

    public abstract void executeReload(int selectedRow, int[] selectedRows);

    public void verifySaveSelectedToCSV(int selectedRow, int[] selectedRows) throws Exception {
    }

    public void verifySaveAllToCSV(int selectedRow, int[] selectedRows) throws Exception {
    }

    public void verifyDelete(int selectedRow, int[] selectedRows) throws Exception {
    }

    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
    }

    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
    }

    public void executeAdd(int selectedRow, int[] selectedRows) throws Exception {
    }

    public void executeCopy(int selectedRow, int[] selectedRows) throws Exception {
    }

    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
    }

    public void executeSaveAllToCSV(int selectedRow, int[] selectedRows) throws Exception {
    }

    public void executeSaveSelectedToCSV(int selectedRow, int[] selectedRows) throws Exception {
    }

    public void executeView(int selectedRow, int[] selectedRows) throws Exception {
    }

    @Override
    public void buttonClick(String name, int selectedRow, int[] selectedRows) {
    }

    public boolean showButtons() {
        return true;
    }

    @Override
    public ListFormEnum.ColumnAutoResizeMode getColumnAutoResizeMode() {
        return ColumnAutoResizeMode.LAST_COLUMN;
    }

    @Override
    public ListFormEnum.DataType getDataType() {
        return ListFormEnum.DataType.RESULTSET;
    }

    @Override
    public ListFormEnum.SelectionType getSelectionType() {
        return ListFormEnum.SelectionType.MULTIPLE_INTERVAL;
    }
}
