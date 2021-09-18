/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.frame;

import java.awt.Toolkit;
import java.awt.Window;

/**
 *
 * @author Ng Siak Hooi
 */
public class WindowPositionSetter {

    public static void setPosition(Window jf, FrameInterface parent) {
        int w = jf.getSize().width;
        int h = jf.getSize().height;
        int sw = Toolkit.getDefaultToolkit().getScreenSize().width;
        int sh = Toolkit.getDefaultToolkit().getScreenSize().height;
        int pw = 0;
        int ph = 0;
        int px = 0;
        int py = 0;
        int x = 0;
        int y = 0;
        if (parent == null) {
            pw = sw;
            ph = sh;
        } else {
//            if (parent.isJDialog()) {
            pw = parent.getWindow().getSize().width;
            ph = parent.getWindow().getSize().height;
            px = parent.getWindow().getLocation().x;
            py = parent.getWindow().getLocation().y;
//            }
//            if (parent.isJFrame()) {
//                pw = parent.getWindow().getSize().width;
//                ph = parent.getWindow().getSize().height;
//                px = parent.getWindow().getLocation().x;
//                py = parent.getWindow().getLocation().y;
//            }
        }
        //System.out.println("w:"+w+",h:"+h+",pw:"+pw+",ph:"+ph);
        if (w > pw && w > sw) {
            x = 0;
        } else {
            x = px + (pw - w) / 2;
        }
        if (h > ph && h > sh) {
            y = 0;
        } else {
            y = py + (ph - h) / 2;
        }

        if (x >= sw) {
            x = px;
        }
        if (y >= sh) {
            y = py;
        }
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        jf.setLocation(x, y);
    }
}
