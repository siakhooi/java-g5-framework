/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util;

import java.awt.Toolkit;

/**
 *
 * @author SHNG
 */
public class G6System {

    public void exit(int n) {
        System.exit(n);
    }

    public void beep() {
        Toolkit.getDefaultToolkit().beep();
    }

    public void sleep(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    public int getScreenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    public int getScreenHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}
