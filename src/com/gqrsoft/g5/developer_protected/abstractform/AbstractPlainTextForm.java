/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.TextForm;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
@Deprecated
public abstract class AbstractPlainTextForm extends TextForm {

    /**
     * method to save to edited text.
     * only will be called if <code>setEditable(true)</code>, and
     * user clicked <code>OK</code>.
     * @param newText edited text to be saved.
     */
    protected abstract void save(String newText);

    @Override
    public final void buttonClick(String name) {
        if (name.equals("OK")) {
            if (textArea.isEditable()) {
                save(textArea.getText());
            }
        }
        cmd.form.closeForm();
    }

    @Override
    protected void initButton() {
        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        String name = "OK";
        String label = "PlainTextForm.button.OK.label";
        label = cmd.lang.getSystemString(label);
        super.buttons.addI18nButton(name, label, icon, true);
        if (textArea.isEditable()) {
            name = "Cancel";
            label = "PlainTextForm.button.Cancel.label";
            label = cmd.lang.getSystemString(label);
            icon = cmd.icon.getCancelIcon(cmd.icon.getDefaultHeight());
            super.buttons.addI18nButton(name, label, icon, false);
        }
    }
    @Override
    public final void onEscapeKeyPressed() {
        buttonClick("Cancel");
    }
}
