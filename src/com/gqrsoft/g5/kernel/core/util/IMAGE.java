/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Ng Siak Hooi
 */
public class IMAGE {

    public static ImageIcon createImageIcon(String jarPath) {
        java.net.URL imgURL =
                IMAGE.class.getResource(jarPath);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            CONSOLE.error("Couldn't find file: " + jarPath);
            return null;
        }
    }

    public static ImageIcon mergeImageIcons(ImageIcon ic1, ImageIcon ic2) {
        Image a1 = ic1.getImage();
        Image a2 = ic2.getImage();

        int w = ic1.getIconWidth();
        int h = ic1.getIconHeight();
        BufferedImage total = new BufferedImage(w * 2, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = total.createGraphics();

        g.drawImage(a1, 0, 0, null);
        g.drawImage(a2, w, 0, null);
        g.dispose();
        return new ImageIcon(total);
    }

    public static ImageIcon resize(ImageIcon imgIcon, int newHeight) {
        int h = imgIcon.getIconHeight();
        int w = imgIcon.getIconWidth();
        int newWidth = (int) ((double) w * newHeight / h);
        Image newIcon = imgIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(newIcon);
    }
}
