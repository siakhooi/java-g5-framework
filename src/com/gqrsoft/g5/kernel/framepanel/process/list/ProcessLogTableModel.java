/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.process.list;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ProcessLogTableModel extends AbstractTableModel {

    private Vector<String> colTitle;
    private Vector<String> rowDate;
    private Vector<String> rowLog;

    public ProcessLogTableModel(String date, String message) {
        colTitle = new Vector<String>();
        colTitle.add(date);
        colTitle.add(message);

        rowDate = new Vector<String>();
        rowLog = new Vector<String>();
    }

    public void addRow(String date, String message) {
        rowDate.add(date);
        rowLog.add(message);
        int n = rowDate.size() - 1;
        fireTableRowsInserted(n, n);
    }

    public int getRowCount() {
        return rowDate.size();
    }

    public int getColumnCount() {
        return colTitle.size();
    }

    @Override
    public String getColumnName(int column) {
        return colTitle.elementAt(column);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return rowDate.elementAt(rowIndex);
        } else {
            return rowLog.elementAt(rowIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
