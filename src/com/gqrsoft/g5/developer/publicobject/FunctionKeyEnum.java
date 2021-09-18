/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicobject;

import java.awt.event.KeyEvent;

/**
 *
 * @author Ng Siak Hooi
 */
public class FunctionKeyEnum {

    public enum FunctionKey {

        NONE(0, 0),
        //list
        LIST_ADD(KeyEvent.VK_F3, 0),
        LIST_COPY(KeyEvent.VK_F4, 0),
        LIST_EDIT(KeyEvent.VK_F7, 0),
        LIST_DELETE(KeyEvent.VK_F9, 0),
        LIST_VIEW(KeyEvent.VK_F6, 0),
        LIST_SELECT(KeyEvent.VK_F8, 0),
        LIST_CLOSE(KeyEvent.VK_ESCAPE, 0),
        LIST_RELOAD(KeyEvent.VK_F5, 0),
        LIST_SAVEALLTOCSV(0, 0),
        LIST_SAVESELECTEDTOCSV(0, 0),
        //mode entry form
        MODE_SEARCH(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK),
        MODE_ADD(KeyEvent.VK_F3, 0),
        MODE_COPY(KeyEvent.VK_F4, 0),
        MODE_EDIT(KeyEvent.VK_F7, 0),
        MODE_DELETE(KeyEvent.VK_F9, 0),
        MODE_VIEW(KeyEvent.VK_F6, 0),
        MODE_OK(KeyEvent.VK_F8, 0),
        MODE_CANCEL(KeyEvent.VK_ESCAPE, 0),
        MODE_CLOSE(KeyEvent.VK_ESCAPE, 0),
        MODE_RELOAD(KeyEvent.VK_F5, 0),
        MODE_RESET(KeyEvent.VK_F5, 0),
        MODE_BACK(KeyEvent.VK_LEFT, KeyEvent.ALT_DOWN_MASK),
        //report entry form
        REPORT_VIEWONSCREEN(KeyEvent.VK_F8, 0),
        REPORT_QUICKPRINT(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK),
        REPORT_PRINT(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK),
        REPORT_SAVEASPDF(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK),
        REPORT_RESET(KeyEvent.VK_F5, 0),
        REPORT_CLOSE(KeyEvent.VK_ESCAPE, 0),
        // field
        HELP(KeyEvent.VK_F1, 0),
        SELECTVALUE(KeyEvent.VK_F2, 0),
        CUT(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK),
        COPY(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK),
        PASTE(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK),
        DELETE(KeyEvent.VK_DELETE, 0),
        SELECT_ALL(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK);
        public int keyCode;
        public int modifiers;

        FunctionKey(int keyCode, int modifiers) {
            this.keyCode = keyCode;
            this.modifiers = modifiers;
        }
    }
}
