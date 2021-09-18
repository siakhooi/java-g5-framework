/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicobject;

import java.awt.Color;

/**
 *
 * @author Ng Siak Hooi
 */
public class EntryFormEnum {

    public enum TabType {

        FIELD,
        FORM;
    }

    public enum StatusType {

        INFO(Color.BLACK, Color.WHITE),
        WARN(Color.BLACK, Color.YELLOW),
        ERROR(Color.BLACK, Color.RED);
        public Color fg,  bg;

        StatusType(Color fg, Color bg) {
            this.fg = fg;
            this.bg = bg;
        }
    }
}
