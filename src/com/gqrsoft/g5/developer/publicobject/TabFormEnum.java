/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicobject;

import javax.swing.JTabbedPane;

/**
 *
 * @author Ng Siak Hooi
 */
public class TabFormEnum {

    public enum TabLayoutPolicy {

        WRAP(JTabbedPane.WRAP_TAB_LAYOUT),
        SCROLL(JTabbedPane.SCROLL_TAB_LAYOUT);
        public int tabLayoutPolicy;

        TabLayoutPolicy(int v) {
            this.tabLayoutPolicy = v;
        }
    }
}
