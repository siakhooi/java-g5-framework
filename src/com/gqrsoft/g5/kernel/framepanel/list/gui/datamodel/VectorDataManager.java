/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.datamodel;

import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class VectorDataManager extends AbstractListDataManager {

    private Vector<Vector<Object>> getVector() {
        return formControl.listForm.vector;
    }

    @Override
    public int getRowCount() {
        Vector<Vector<Object>> vector = getVector();
        if (vector == null) {
            return 0;
        }
        return vector.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vector<Vector<Object>> vector = getVector();
        if (vector == null) {
            return null;
        }
        if (vector.size() > rowIndex) {
            Vector<Object> objs = vector.elementAt(rowIndex);
            if (objs != null) {
                if (objs.size() > columnIndex) {
                    return objs.elementAt(columnIndex);
                }
            }
        }
        return null;
    }

    @Override
    public void init() {
    }
}
