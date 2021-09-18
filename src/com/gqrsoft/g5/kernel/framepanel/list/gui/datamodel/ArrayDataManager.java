/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.datamodel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ArrayDataManager extends AbstractListDataManager {

    private Object[][] getArray() {
        return formControl.listForm.array;
    }

    @Override
    public int getRowCount() {
        Object[][] array = getArray();
        if (array == null) {
            return 0;
        }
        return array.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[][] array = getArray();
        if (array == null) {
            return null;
        }
        if (array.length > rowIndex) {
            Object[] obj = array[rowIndex];
            if (obj.length > columnIndex) {
                return obj[columnIndex];
            }
        }
        return null;
    }

    @Override
    public void init() {
    }
}
