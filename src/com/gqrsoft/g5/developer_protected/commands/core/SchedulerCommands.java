/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.core;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class SchedulerCommands extends AbstractCommandComponent {

    public void schedule(Class<? extends UserFormInterface> userForm, Calendar time) {
        getFormControl().core.cron.schedule(
                userForm,
                time);
    }

    public void schedule(Class<? extends UserFormInterface> userForm, Calendar time, long periodInMilliseconds) {
        getFormControl().core.cron.schedule(
                userForm,
                time,
                periodInMilliseconds);
    }

    public void schedule(Class<? extends UserFormInterface> userForm, long delayInMilliseconds) {
        getFormControl().core.cron.schedule(
                userForm,
                delayInMilliseconds);
    }

    public void schedule(Class<? extends UserFormInterface> userForm, long delayInMilliseconds, long periodInMilliseconds) {
        getFormControl().core.cron.schedule(
                userForm,
                delayInMilliseconds,
                periodInMilliseconds);
    }

    public void scheduleAtFixedRate(Class<? extends UserFormInterface> userForm, Calendar time, long period) {
        getFormControl().core.cron.scheduleAtFixedRate(
                userForm,
                time,
                period);
    }

    public void scheduleAtFixedRate(Class<? extends UserFormInterface> userForm, long delay, long period) {
        getFormControl().core.cron.scheduleAtFixedRate(
                userForm,
                delay,
                period);
    }
}
