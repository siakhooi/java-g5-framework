/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.form;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ListFormCommands extends AbstractCommandComponent {

    public int getSelectedRow() {
        if (getFormControl().listPanel != null) {
            return getFormControl().listPanel.table.getSelectedRow();
        }
        return 0;
    }

    public int[] getSelectedRows() {
        if (getFormControl().listPanel != null) {
            return getFormControl().listPanel.table.getSelectedRows();
        }
        return new int[0];
    }

    public void refreshList() {
        if (getFormControl().listPanel != null) {
            getFormControl().listPanel.commands.doReload1();
        }
    }

    public String getString(int row, String fieldName) {
        return (String) getObject(row, fieldName);
    }

    public Object getObject(int row, String fieldName) {
        if (getFormControl().listPanel == null) {
            return null;
        }
        int col = -1;
        Vector<Field> vf = getFormControl().listForm.allFieldsByIndex;
        for (int i = 0; i < vf.size(); i++) {
            Field f = vf.elementAt(i);
            if (f.fieldName.equals(fieldName)) {
                col = i;
                break;
            }
        }
        if (col == -1) {
            return null;
        }
        return getFormControl().listPanel.dataManager.getValueAt(row, col);
    }

//    public void selectLastRow() {
//        if (getFormControl().listPanel == null) {
//            return;
//        }
//        JTable table = getFormControl().listPanel.table;
//        int r = table.getRowCount();
//        if (r == 0) {
//            return;
//        }
//        int c = table.getColumnCount();
//        table.changeSelection(r - 1, 0, false, false);
//        table.changeSelection(r - 1, c - 1, false, true);
//    }
    public void selectLastRow() {
        selectRow(getFormControl().listPanel.table.getRowCount() - 1);
    }

    public void selectFirstRow() {
        selectRow(0);
    }

    public int getRowCount() {
        if (getFormControl().listPanel == null) {
            return 0;
        }
        JTable table = getFormControl().listPanel.table;
        return table.getRowCount();
    }

    public void clearSelection() {
        if (getFormControl().listPanel == null) {
            return;
        }
        JTable table = getFormControl().listPanel.table;
        table.clearSelection();
    }

    public void selectRow(int n) {
        if (getFormControl().listPanel == null) {
            return;
        }
        JTable table = getFormControl().listPanel.table;
        int r = table.getRowCount();
        if (r == 0 || r <= n) {
            return;
        }
        int c = table.getColumnCount();
        table.changeSelection(n, 0, false, false);
        table.changeSelection(n, c - 1, false, true);
    }

    public void addRowSelection(int r1, int r2) {
        if (getFormControl().listPanel == null) {
            return;
        }
        JTable table = getFormControl().listPanel.table;
        try {
            table.addRowSelectionInterval(r1, r2);
        } catch (IllegalArgumentException e) {
        }
    }

    public TableModel getTableModel() {
        return getFormControl().listPanel.tableModel;
    }
}
