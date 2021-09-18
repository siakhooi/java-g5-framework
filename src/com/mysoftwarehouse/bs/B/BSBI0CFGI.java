/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.form.ImageForm2;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBI0CFGI extends ImageForm2 {

//    @Override
//    public boolean showButtons() {
//        return false;
//    }

    @Override
    public Color getBackgroundColor() {
        return Color.WHITE;
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    @Override
    public Image getImage() {
        return cmd.local.imageValue;
    }

    @Override
    public void receiveSignal(int arg0) {
        Image img = cmd.local.imageValue;
        if (img != null) {
            ImageIcon n = new ImageIcon(img);
            int h = (int) (cmd.sys.getScreenHeight() * 0.5);
            if (n.getIconHeight() > h) {
                n = cmd.image.resize(n, h);
                img=n.getImage();
            }
        }
        super.setImage(img);
    }
}
