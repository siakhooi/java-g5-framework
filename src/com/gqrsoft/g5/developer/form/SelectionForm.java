/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractSelectionForm;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class SelectionForm extends AbstractSelectionForm {

    @Override
    protected abstract void buildSelection();

    @Override
    protected abstract void userCancelled();

    @Override
    protected abstract void userSelected(Vector<String> selectedValues);

    @Override
    protected void userClick(String name) {
    }

    @Override
    protected boolean isMultiSelect() {
        return false;
    }

    @Override
    protected int getColumn() {
        return 2;
    }
}
