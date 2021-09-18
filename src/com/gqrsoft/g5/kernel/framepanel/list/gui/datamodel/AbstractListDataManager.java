/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list.gui.datamodel;

import com.gqrsoft.g5.kernel.control.FormControl;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractListDataManager {

    protected FormControl formControl;

    public abstract int getRowCount();

    public abstract Object getValueAt(int rowIndex, int columnIndex);

    public abstract void init();

    public void init(FormControl formControl) {
        this.formControl = formControl;
        init();
    }
}
