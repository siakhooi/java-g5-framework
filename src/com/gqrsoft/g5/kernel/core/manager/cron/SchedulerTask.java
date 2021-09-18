/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager.cron;

import com.gqrsoft.g5.kernel.core.Core;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.util.TimerTask;
import java.util.logging.Level;

/**
 *
 * @author Ng Siak Hooi
 */
public class SchedulerTask extends TimerTask {

    private Class<? extends UserFormInterface> userForm;
    private Core core;

    public SchedulerTask(Class<? extends UserFormInterface> userForm, Core core) {
        this.userForm = userForm;
        this.core = core;
    }

    @Override
    public void run() {
        UserFormInterface uf = core.form.create(userForm);
        if (uf == null) {
            String msg = "ScheduleTask.error.createForm";
            msg = core.lang.getSystemString(msg);
            core.log.getEngineLogger().log(Level.SEVERE, msg);
            this.cancel();

        } else {
            core.form.execute(null,
                    uf,
                    true,
                    true);
        }
    }
}
