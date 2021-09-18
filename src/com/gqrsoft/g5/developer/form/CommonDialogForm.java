/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractCommonDialogForm;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class CommonDialogForm extends AbstractCommonDialogForm {

    @Override
    protected abstract void init();

    @Override
    protected abstract void userAction(int result);
}
