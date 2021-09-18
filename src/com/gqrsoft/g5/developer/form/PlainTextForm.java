/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractPlainTextForm;

/**
 * to show/edit plain text using <code>JTextArea</code>.
 * 
 * @author Ng Siak Hooi
 */
@Deprecated
public abstract class PlainTextForm extends AbstractPlainTextForm {

    @Override
    protected abstract void init();

    @Override
    protected abstract void save(String newText);
}
