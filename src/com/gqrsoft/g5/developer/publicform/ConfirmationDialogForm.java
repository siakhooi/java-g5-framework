/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import com.gqrsoft.g5.developer.form.CommonDialogForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum;
import javax.swing.JOptionPane;

/**
 * usage:
 * new ConfirmationDialogForm()<BR>
 * set cmd.out.i18nTitle<BR>
 * set cmd.out.message<BR>
 * set cmd.out.dialogMessageType<BR>
 * return from cmd.out.booleanValue
 * @author Ng Siak Hooi
 */
public final class ConfirmationDialogForm extends CommonDialogForm {

    @Override
    protected final void init() {
        super.setMessage(cmd.in.message);
        super.setMessageType(cmd.in.dialogMessageType);
        super.setOptionType(FormEnum.DialogOptionType.YES_NO);
    }

    @Override
    protected final void userAction(int result) {
        boolean r = (result == JOptionPane.YES_OPTION);
        cmd.in.booleanValue = r;
        cmd.form.closeForm();
    }

    @Override
    public final void onEscapeKeyPressed() {
        cmd.in.booleanValue = false;
        cmd.form.closeForm();
    }

    @Override
    public final String getFormI18nTitle() {
        if (cmd.in.i18nTitle == null) {
            String title = "ConfirmationDialogForm.title";
            return cmd.lang.getSystemString(title);
        } else {
            return cmd.in.i18nTitle;
        }
    }

    @Override
    public final String getFormTitle() {
        return null;
    }
}
