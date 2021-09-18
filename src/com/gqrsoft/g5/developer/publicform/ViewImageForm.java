/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import com.gqrsoft.g5.developer.form.ImageForm2;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.awt.Image;

/**
 * usage: 
 * set cmd.out.i18nTitle
 * set cmd.out.imageValue
 * @author Ng Siak Hooi
 */
public class ViewImageForm extends ImageForm2 {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ActionForm_Close.class;
    }

    @Override
    public final String getFormI18nTitle() {
        if (cmd.in.i18nTitle == null) {
            String title = "ViewImageForm.title";
            return cmd.lang.getSystemString(title);
        } else {
            return cmd.in.i18nTitle;
        }
    }

    @Override
    public final String getFormTitle() {
        return null;
    }

    public final Image getImage() {
        return cmd.in.imageValue;
    }
}
