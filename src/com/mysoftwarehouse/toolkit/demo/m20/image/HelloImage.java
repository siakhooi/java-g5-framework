/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m20.image;

import com.gqrsoft.g5.developer.form.ImageForm2;
import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicform.ActionForm_Close;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import java.awt.Image;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloImage extends ImageForm2 {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ActionForm_Close.class;
    }

    @Override
    public Class<? extends MenuForm> getMenuFormForToolbar() {
        return ActionForm_Close.class;
    }

    @Override
    public boolean hasAction() {
        return true;
    }

    @Override
    public void userAction() {
        cmd.common.showI18nMessage(DialogMessageType.INFORMATION,
                "Hello", "You click me!");
    }

    @Override
    public String getFormTitle() {
        return "HelloImage.title";
    }

    @Override
    public Image getImage() {
        return cmd.image.createImageIcon(
                HelloImage.class,
                "/com/mysoftwarehouse/toolkit/demo/resources/images/logo.jpg").getImage();
    }
}
