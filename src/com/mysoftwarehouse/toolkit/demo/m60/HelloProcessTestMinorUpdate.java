/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m60;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloProcessTestMinorUpdate extends ProcessForm {

    @Override
    public void init() {
        super.setLogListVisible(true);
        super.addI18nProcess("Hello Process 1", 4);
        super.addI18nProcess("Hello Process 2", 2);
        super.addI18nProcess("Hello Process 3", 4);
        super.addI18nProcess("Hello Process 4", 4);
        super.addI18nProcess("Hello Process 5", 4);
        super.addI18nProcess("Hello Process 6", 4);
    }

    @Override
    public void run() throws ProcessException {
        sleep(800);
        super.completed();
        doMinor(80, 10);
        super.completed();
        doMinor(10, 80);
        super.completed();
        doMinor(400, 2);
        super.completed();
        cmd.common.showI18nMessage(DialogMessageType.QUESTION,
                "confirm", "press to continue");
        doMinor(80, 20, 2);
        super.completed();
        doMinor(6400, 10, 80);
        super.completed();
    }

    @Override
    public String getFormTitle() {
        return "HelloProcessTestMinorUpdate.title";
    }

    private void sleep(int n) {
        try {
            cmd.sys.sleep(n);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void doMinor(int n, int sleep) throws ProcessException {
        setMinorTotalCount(n);
        for (int i = 0; i < n; i++) {
            setI18nMessage("update " + i + " of " + n);
            sleep(sleep);
            minorCompleted();
        }
    }

    private void doMinor(int n, int sleep, int minorComplete) throws ProcessException {
        setMinorTotalCount(n);
        for (int i = 0; i < n; i+=minorComplete) {
            setI18nMessage("update " + i + " of " + n);
            sleep(sleep);
            minorCompleted(minorComplete);
        }
    }
}
