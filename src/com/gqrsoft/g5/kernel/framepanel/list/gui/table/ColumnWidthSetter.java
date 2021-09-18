/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.table;

import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Ng Siak Hooi
 */
public class ColumnWidthSetter {

    public static void init(FormControl formControl) {
        JTable table = formControl.listPanel.table;
        Vector<Field> v = formControl.listForm.allFieldsByIndex;

        for (int i = 0; i < v.size(); i++) {
            Field f = v.elementAt(i);
            int columnWidth = f.listColumnWidth;
            if (columnWidth > 0) {
                TableColumn column = table.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidth);
            }
            if (!f.visible) {
                TableColumn column = table.getColumnModel().getColumn(i);
                table.getColumnModel().removeColumn(column);
            }
        }
    }
}
