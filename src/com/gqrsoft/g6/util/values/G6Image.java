/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util.values;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author SHNG
 */
public class G6Image {

    public static Image newImage() {
        return new ImageIcon().getImage();
    }

    public static ImageIcon merge(ImageIcon ic1, ImageIcon ic2) {
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

    public static ImageIcon create(Class clazz, String jarPath) {
        java.net.URL imgURL = clazz.getResource(jarPath);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            return null;
        }
    }
}
