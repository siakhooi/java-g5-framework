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
public class HelloProcess extends ProcessForm {

    @Override
    public void userCancel() throws ProcessException {
        super.setI18nProgressMessage("user 111 Cancelled");
    }

    @Override
    public void init() {
        super.setUserAllowCancel(true);
        super.setUserManualStart(false);
        super.setUserManualClose(true);
        super.addProcess("HelloProcess.process.1", 1);
        super.addI18nProcess("Hello Process 2", 2);
        super.addI18nProcess("Hello Process 3", 3);
        super.addI18nProcess("Hello Process 4", 4);
    }

    @Override
    public void run() throws ProcessException {
        sleep(800);
        super.completed();
        sleep(800);
        super.completed();
        sleep(800);
        super.completed();
        super.disableCancellation();
        sleep(800);
        super.completed();
    }

    @Override
    public String getFormTitle() {
        return "HelloProcess.title";
    }

    private void sleep(int n) {
        try {
            cmd.sys.sleep(n);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
