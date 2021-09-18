/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractButtonForm;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 * @deprecated 
 */
@Deprecated
public abstract class ButtonForm extends AbstractButtonForm {

    @Override
    public abstract void buildButtonForm(JPanel parent);

    @Override
    public abstract void buttonClick(String name);

    @Override
    public boolean showButtons() {
        return true;
    }
}
