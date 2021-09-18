/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.config;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Ng Siak Hooi
 */
public class EntryFieldStyle {

    public static Font getLabelFont(boolean mandatory) {
        return mandatory
                ? new Font("SansSerif", Font.BOLD, 12)
                : new Font("SansSerif", Font.PLAIN, 12);
    }

    public static Color getLabelBackgroundColor(boolean mandatory) {
        return mandatory
                ? Color.RED
                : Color.WHITE;
    }

    public static Color getFieldBackgroundColor(boolean valid) {
        return valid
                ? Color.WHITE
                : Color.RED; //new Color(0xff, 0xcf, 0xcf);
    }

    public static Font getFieldFont() {
        return new Font("Monospaced", Font.PLAIN, 12);
    }
}
