/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m60;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloProcessNoDisplay extends ProcessForm {

    @Override
    public boolean showThisForm() {
        return false;
    }

    @Override
    public void init() {
        addI18nProcess("my1", 1);
    }

    @Override
    public void run() throws ProcessException {
        cmd.debug.println("test: HelloProcessNoDisplay");
        completed();
    }

    @Override
    public String getFormTitle() {
        return "HelloProcessNoDisplay.title";
    }
}
