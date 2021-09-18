/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.forms;

import com.gqrsoft.g5.developer.form.TextForm2;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicform.ActionForm_Close;
import com.mysoftwarehouse.toolkit.ToolkitConfiguration;

/**
 *
 * @author Ng Siak Hooi
 */
public class ViewHelp extends TextForm2 {

    @Override
    protected void init() {
        super.setColumns(ToolkitConfiguration.HELP_COLUMNS);
        super.setRows(ToolkitConfiguration.HELP_ROWS);
        super.setEditable(false);
        super.setLineWrap(false);
        super.setText(cmd.in.stringValue);
        super.setWrapStyleWord(false);
    }

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ActionForm_Close.class;
    }

    @Override
    public String getFormTitle() {
        return "ViewHelp.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
