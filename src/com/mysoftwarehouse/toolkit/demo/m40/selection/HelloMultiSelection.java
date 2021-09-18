/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m40.selection;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloMultiSelection extends HelloSingleSelection {

    @Override
    protected boolean isMultiSelect() {
        return true;
    }
    @Override
    public String getFormTitle() {
        return "HelloMultiSelection.title";
    }
}
