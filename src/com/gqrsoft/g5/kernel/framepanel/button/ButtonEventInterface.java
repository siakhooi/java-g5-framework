/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.button;

/**
 *
 * @author Ng Siak Hooi
 */
public interface ButtonEventInterface {

    /**
     * event when button in <code>ButtonBuilder</code> is clicked.
     * 
     * @param name of button.
     */
    public void buttonClick(String name);
}
