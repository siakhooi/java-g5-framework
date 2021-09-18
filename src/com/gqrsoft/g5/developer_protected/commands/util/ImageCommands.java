/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import com.gqrsoft.g5.kernel.core.util.CONSOLE;
import com.gqrsoft.g5.kernel.core.util.IMAGE;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Ng Siak Hooi
 */
public class ImageCommands {

    public Image newImage() {
        return new ImageIcon().getImage();
    }

    public ImageIcon createImageIcon(Class clazz, String jarpath) {
        java.net.URL imgURL = clazz.getResource(jarpath);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            CONSOLE.error("Couldn't find file: " + jarpath);
            return null;
        }
    }

    public ImageIcon createImageIcon(ImageIcon ic1, ImageIcon ic2) {
        return IMAGE.mergeImageIcons(ic1, ic2);
    }

    public ImageIcon resize(ImageIcon imgIcon, int newHeight) {
        return IMAGE.resize(imgIcon, newHeight);
    }
}
