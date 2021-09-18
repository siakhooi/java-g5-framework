/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import java.awt.Toolkit;

/**
 *
 * @author Ng Siak Hooi
 */
public class SystemUtilities extends AbstractCommandComponent {

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
