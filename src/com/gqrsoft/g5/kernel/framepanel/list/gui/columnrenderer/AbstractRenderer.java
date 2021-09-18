/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.columnrenderer;

import com.gqrsoft.g5.kernel.config.DefaultStyle;
import com.gqrsoft.g5.kernel.control.FormControl;
import com.gqrsoft.g5.kernel.framepanel.entry.field.Field;
import com.gqrsoft.g5.kernel.framepanel.list.gui.table.DefaultReadOnlyTableModel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractRenderer extends DefaultTableCellRenderer {

    protected FormControl formControl;

    protected abstract String format(AbstractRenderer comp, Field f, Object text);

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (formControl == null) {
            if (table.getModel() instanceof DefaultReadOnlyTableModel) {
                DefaultReadOnlyTableModel tm =
                        (DefaultReadOnlyTableModel) table.getModel();
                this.formControl = tm.formControl;
            }
        }
        row = table.convertRowIndexToModel(row);
        column = table.convertColumnIndexToModel(column);
        Field f = formControl.listForm.allFieldsByIndex.elementAt(column);
        boolean isKey =
                formControl.listForm.allKeyFieldsByName.containsKey(f.fieldName);
        if (!isSelected) {
            if (isKey) {
                this.setBackground(DefaultStyle.getListKeyFieldBackgroundColor());
            } else {
                this.setBackground(DefaultStyle.getListDataFieldBackgroundColor());
            }
        }


        //ListFieldInterface field = getListFieldInterface(column);
        //UserValueInterface userValue = (UserValueInterface) value;

//        ListValueFormater valueFormater = new ListValueFormater();
//        String text = valueFormater.format(field, userValue);

        //formating done at DataManager;
        String text = format(this, f, value);
        this.setText(text);

        this.setHorizontalAlignment(f.alignment.jTextFieldAlignment);
        this.setToolTipText(f.i18nHelpMessage);
        return formControl.listForm.formatCell(this, row, column);
        //return this;
    }
}
