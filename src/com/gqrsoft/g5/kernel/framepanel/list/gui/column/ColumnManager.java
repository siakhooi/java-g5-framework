/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.column;

import com.gqrsoft.g5.kernel.control.FormControl;

/**
 *
 * @author Ng Siak Hooi
 */
public class ColumnManager {

    private FormControl formControl;

    public int getColumnCount() {
        return formControl.listForm.allFieldsByIndex.size();
    }

    public String getColumnName(int column) {
        return formControl.listForm.allFieldsByIndex.get(column).i18nLabel;
    }

    public void init(FormControl formControl) {
        this.formControl = formControl;
    }

    public Class<?> getColumnClass(int columnIndex) {
        return formControl.listForm.allFieldsByIndex.get(columnIndex).fieldType.listColumnClass;
    }
}
