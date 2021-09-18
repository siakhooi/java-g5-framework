/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import com.gqrsoft.g5.developer.form.FileForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum;

/**
 * usage: 
 * set cmd.out.fileValue
 * set cmd.out.i18nTitle
 * return from cmd.out.fileValue
 * @author Ng Siak Hooi
 */
public class SelectOpenFileForm extends FileForm {

    @Override
    public String getFormI18nTitle() {
        if (cmd.in.i18nTitle == null) {
            String title = "SelectOpenFileForm.title";
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
    protected void init() {
        setDialogOpenType(true);
        setSelectionMode(FormEnum.FileSelectType.FILES_ONLY);
    }
}
