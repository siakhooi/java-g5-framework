/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import com.gqrsoft.g5.developer.form.StyledTextForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum;

/**
 * Implemented class to view HTML documents.
 * Usage:
 * set cmd.out.message
 * set cmd.out.i18nTitle
 * execute Form
 * @author Ng Siak Hooi
 */
public class ViewHtmlForm extends StyledTextForm {

    @Override
    public String getInitialText() {
        return cmd.in.message;
    }

    @Override
    public void hyperlinkClick(String href) {
    }

    @Override
    public String getFormI18nTitle() {
        if (cmd.in.i18nTitle == null) {
            String title = "ViewHtmlForm.title";
            return cmd.lang.getSystemString(title);
        } else {
            return cmd.in.i18nTitle;
        }
    }

    @Override
    public String getFormTitle() {
        return null;
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }

    @Override
    public FormEnum.StyledTextType getType() {
        return FormEnum.StyledTextType.HTML;
    }
}
