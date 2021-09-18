/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * set cmd.out.fileValue
 * set cmd.out.i18nTitle
 * return from cmd.out.fileValue
 * @author Ng Siak Hooi
 */
public class SelectOpenImageFileForm extends SelectOpenFileForm {

    @Override
    public String getFormI18nTitle() {
        if (cmd.in.i18nTitle == null) {
            String title = "SelectOpenImageFileForm.title";
            return cmd.lang.getSystemString(title);
        } else {
            return cmd.in.i18nTitle;
        }
    }

    @Override
    protected void init() {
        super.init();
        addFileFilter(new FileNameExtensionFilter(
                "All Picture Files", "jpg", "jpeg", "jpe", "jfif", "gif", "png"));
        addFileFilter(new FileNameExtensionFilter(
                "JPEG (*.jpg;*.jpeg;*.jpe;*.jfif)", "jpg", "jpeg", "jpe", "jfif"));
        addFileFilter(new FileNameExtensionFilter(
                "GIF (*.gif)", "gif"));
        addFileFilter(new FileNameExtensionFilter(
                "PNG (*.png)", "png"));
    }
}
