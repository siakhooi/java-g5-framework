/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import com.gqrsoft.g5.developer.form.CommonDialogForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum;

/**
 * usage:
 * new MessageDialogForm()<BR>
 * set cmd.out.title<BR>
 * set cmd.out.message<BR>
 * set cmd.out.dialogMessageType<BR>
 * @author Ng Siak Hooi
 */
public final class MessageDialogForm extends CommonDialogForm {

    @Override
    protected void init() {
        super.setMessage(cmd.in.message);
        super.setMessageType(cmd.in.dialogMessageType);
        super.setOptionType(FormEnum.DialogOptionType.DEFAULT);
    }

    @Override
    protected void userAction(int result) {
        cmd.form.closeForm();
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }

    @Override
    public String getFormI18nTitle() {
        if (cmd.in.i18nTitle == null) {
            String title = "MessageDialogForm.title";
            return cmd.lang.getSystemString(title);
        } else {
            return cmd.in.i18nTitle;
        }
    }

    @Override
    public String getFormTitle() {
        return null;
    }
}
