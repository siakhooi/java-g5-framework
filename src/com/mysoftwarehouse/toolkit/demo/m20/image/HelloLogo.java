/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m20.image;

import com.gqrsoft.g5.developer.form.ImageForm2;
import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloLogo extends ImageForm2 {

    @Override
    public Color getBackgroundColor() {
        return Color.YELLOW;
    }

    @Override
    public String getFormTitle() {
        return "HelloLogo.title";
    }

    @Override
    public Image getImage() {
        return cmd.appinfo.getApplicationLogo().getImage();
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
    
}
