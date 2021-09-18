/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractFileForm;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class FileForm extends AbstractFileForm {

    /**
     * 
     */
    @Override
    protected abstract void init();

    /**
     * 
     * @return
     */
    @Override
    protected boolean allowAllFileFilter() {
        return true;
    }

    /**
     * 
     * @return
     */
    @Override
    protected boolean hideHiddenFile() {
        return false;
    }
}
