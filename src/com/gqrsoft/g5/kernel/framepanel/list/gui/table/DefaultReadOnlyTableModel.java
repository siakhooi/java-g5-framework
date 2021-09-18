/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.table;

import com.gqrsoft.g5.kernel.control.FormControl;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ng Siak Hooi
 */
public class DefaultReadOnlyTableModel extends AbstractTableModel {

    public FormControl formControl;

    public void init(FormControl formControl) {
        this.formControl = formControl;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getColumnCount() {
        return formControl.listPanel.columnManager.getColumnCount();
    }

    @Override
    public String getColumnName(int column) {
        return formControl.listPanel.columnManager.getColumnName(column);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return formControl.listPanel.columnManager.getColumnClass(columnIndex);
    }

    @Override
    public int getRowCount() {
        return formControl.listPanel.dataManager.getRowCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return formControl.listPanel.dataManager.getValueAt(rowIndex, columnIndex);
    }

    public void reload() {
        super.fireTableDataChanged();
    }

    public void reload(int row1, int row2) {
        super.fireTableRowsUpdated(row1, row2);
    }
}
