/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m70;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloListWithVectorNoButton extends HelloListWithVector {

    @Override
    public String getFormTitle() {
        return "HelloListWithVectorNoButton.title";
    }

    @Override
    public boolean showButtons() {
        return false;
    }
}
