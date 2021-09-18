/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractStyledTextForm;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class StyledTextForm extends AbstractStyledTextForm {

    @Override
    public abstract String getInitialText();

    @Override
    public abstract void hyperlinkClick(String href);
}
