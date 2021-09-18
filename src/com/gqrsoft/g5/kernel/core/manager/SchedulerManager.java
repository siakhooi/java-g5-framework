/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager;

import com.gqrsoft.g5.kernel.core.AbstractCoreComponent;
import com.gqrsoft.g5.kernel.core.manager.cron.SchedulerTask;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.util.Calendar;
import java.util.Timer;

/**
 *
 * @author Ng Siak Hooi
 */
public class SchedulerManager extends AbstractCoreComponent {

    private Timer timer = new Timer();

    private SchedulerTask createSchedulerTask(Class<? extends UserFormInterface> userForm) {
        SchedulerTask st = new SchedulerTask(userForm, core());
        return st;
    }

    public void schedule(Class<? extends UserFormInterface> userForm, Calendar time) {
        SchedulerTask st = createSchedulerTask(userForm);
        timer.schedule(st, time.getTime());
    }

    public void schedule(Class<? extends UserFormInterface> userForm, Calendar time, long period) {
        SchedulerTask st = createSchedulerTask(userForm);
        timer.schedule(st, time.getTime(), period);
    }

    public void schedule(Class<? extends UserFormInterface> userForm, long delay) {
        SchedulerTask st = createSchedulerTask(userForm);
        timer.schedule(st, delay);
    }

    public void schedule(Class<? extends UserFormInterface> userForm, long delay, long period) {
        SchedulerTask st = createSchedulerTask(userForm);
        timer.schedule(st, delay, period);
    }

    public void scheduleAtFixedRate(Class<? extends UserFormInterface> userForm, Calendar time, long period) {
        SchedulerTask st = createSchedulerTask(userForm);
        timer.scheduleAtFixedRate(st, time.getTime(), period);
    }

    public void scheduleAtFixedRate(Class<? extends UserFormInterface> userForm, long delay, long period) {
        SchedulerTask st = createSchedulerTask(userForm);
        timer.scheduleAtFixedRate(st, delay, period);
    }
}
