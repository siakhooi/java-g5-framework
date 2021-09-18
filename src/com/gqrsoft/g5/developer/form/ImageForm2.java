/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer_protected.abstractform.AbstractImageForm2;
import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class ImageForm2 extends AbstractImageForm2 {

    @Override
    public Color getBackgroundColor() {
        return null;
    }

    @Override
    public abstract Image getImage();

    @Override
    protected boolean hasAction() {
        return false;
    }

    @Override
    protected void userAction() {
    }
}
