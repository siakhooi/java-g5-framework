/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core;

import com.gqrsoft.g5.deploy.Starter;
import com.gqrsoft.g5.deploy.ApplicationControlInterface;
import com.gqrsoft.g5.deploy.ApplicationInformationInterface;
import com.gqrsoft.g5.kernel.core.manager.ArgumentManager;
import com.gqrsoft.g5.kernel.core.manager.ConfigurationManager;
import com.gqrsoft.g5.kernel.core.manager.DatabaseManager;
import com.gqrsoft.g5.kernel.core.manager.FormExecutor;
import com.gqrsoft.g5.kernel.core.manager.LanguageManager;
import com.gqrsoft.g5.kernel.core.manager.LogManager;
import com.gqrsoft.g5.kernel.core.manager.SchedulerManager;
import com.gqrsoft.g5.kernel.core.manager.WindowsManager;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer_protected.parameters.GlobalParameters;
import com.gqrsoft.g5.kernel.core.manager.GlobalBroadcastManager;
import com.gqrsoft.g5.kernel.core.manager.SystemTrayExecutor;

public class Core {

    private Starter starter;
    private ApplicationInformationInterface info;
    private ApplicationControlInterface ctrl;
    public final LogManager log = new LogManager();
    public final ConfigurationManager config = new ConfigurationManager();
    public final ArgumentManager argv = new ArgumentManager();
    public final SchedulerManager cron = new SchedulerManager();
    public final LanguageManager lang = new LanguageManager();
    public final DatabaseManager dbm = new DatabaseManager();
    public final FormExecutor form = new FormExecutor();
    public final SystemTrayExecutor sysTray = new SystemTrayExecutor();
    public final GlobalBroadcastManager gbc = new GlobalBroadcastManager();
    public final WindowsManager win = new WindowsManager();
    public final GlobalParameters param = new GlobalParameters();

    public void init(Starter starter) {
        this.starter = starter;
    }

    public void init(ApplicationInformationInterface info) {
        this.info = info;
    }

    public void init(ApplicationControlInterface ctrl) {
        this.ctrl = ctrl;
    }

    public ApplicationInformationInterface info() {
        return this.info;
    }

    public ApplicationControlInterface ctrl() {
        return this.ctrl;
    }

    public Core() {
        //argv().setCore();
        lang.setCore(this);
        cron.setCore(this);
        form.setCore(this);
        win.setCore(this);
        dbm.setCore(this);
        sysTray.setCore(this);
    }

    public void run() {
        UserFormInterface firstForm =
                form.create(ctrl().getApplicationStartUserForm());
        form.execute(null, firstForm, true, true);
    }

    public void exit() {
        if (ctrl().getApplicationExitUserForm() != null) {
            UserFormInterface exitForm = form.create(ctrl().getApplicationExitUserForm());
            form.execute(null, exitForm, true, true);
        }
        dbm.closeAll();
        System.exit(0);
    }
    //private BannerManager banner;
    //public BannerManager banner(){ return this.banner; }
}
