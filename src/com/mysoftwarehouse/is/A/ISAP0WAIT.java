/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.A;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISAP0WAIT extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(true);
        super.setUserManualStart(false);
        super.setLogListVisible(false);
        super.addProcess("ISAP0WAIT.wait", cmd.in.intValue);
    }

    @Override
    public void run() throws ProcessException {
        super.setMinorTotalCount(cmd.in.intValue);
        for (int i = 0; i < cmd.in.intValue; i++) {
            try {
                String s = "ISAP0WAIT.{0}.{1}";
                s = cmd.lang.getString(s,
                        cmd.data.int2String(i),
                        cmd.data.int2String(cmd.in.intValue));
                super.setI18nMessage(s);
                cmd.sys.sleep(1000);
            } catch (InterruptedException ex) {
            }
            super.minorCompleted();
        }
    }

    @Override
    public String getFormTitle() {
        return "ISAP0WAIT.title";
    }
}
