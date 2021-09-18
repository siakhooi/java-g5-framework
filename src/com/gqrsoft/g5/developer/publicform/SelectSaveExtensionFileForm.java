/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * usage: 
 * set cmd.out.fileValue
 * set cmd.out.i18nTitle
 * set cmd.out.i18nExtensionDescription
 * set cmd.out.extension
 * return from cmd.out.fileValue
 * @author Ng Siak Hooi
 */
public class SelectSaveExtensionFileForm extends SelectSaveFileForm {

    @Override
    public String getFormI18nTitle() {
        if (cmd.in.i18nTitle == null) {
            String title = "SelectSaveExtensionFileForm.title";
            return cmd.lang.getSystemString(title);
        } else {
            return cmd.in.i18nTitle;
        }
    }

    @Override
    protected void init() {
        super.init();
        FileNameExtensionFilter f =
                new FileNameExtensionFilter(
                cmd.in.i18nExtensionDescription,
                cmd.in.extension);
        addFileFilter(f);
    }
}
