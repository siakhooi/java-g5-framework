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
public class HelloProcessTestIndeterminate extends ProcessForm {

    @Override
    public void init() {
        super.addI18nProcess("Hello Process 1", 1);
        super.addI18nProcess("Hello Process 2", 2);
        super.addI18nProcess("Hello Process 3", 3);
        super.addI18nProcess("Hello Process 4", 4);
    }

    @Override
    public void run() throws ProcessException {
        super.setMinorTotalCount(-1);
        sleep(800);
        super.completed();
        super.setMinorTotalCount(20);
        for (int i = 0; i < 5; i ++) {
            sleep(200);
            minorCompleted(2);
        }
        super.completed();
        doMinor(160, 10, 2);
        super.completed();
        super.setMinorTotalCount(30);
        for (int i = 0; i < 5; i ++) {
            setI18nMessage("update " + i + " of " + 5);
            sleep(200);
            minorCompleted(2);
        }
        super.setMinorTotalCount(-1);
        for (int i = 0; i < 5; i ++) {
            setI18nMessage("update " + i + " of " + 5);
            sleep(200);
            minorCompleted(2);
        }
        
        super.completed();
        sleep(800);
        super.completed();
    }

    @Override
    public String getFormTitle() {
        return "HelloProcessTestIndeterminate.title";
    }

    private void sleep(int n) {
        try {
            cmd.sys.sleep(n);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void doMinor(int n, int sleep, int minorComplete) throws ProcessException {
        setMinorTotalCount(n);
        for (int i = 0; i < n; i += minorComplete) {
            setI18nMessage("update " + i + " of " + n);
            sleep(sleep);
            minorCompleted(minorComplete);
        }
    }
}
