/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.A;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.form.SystemTrayMenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.ut.conf.APP;
import com.mysoftwarehouse.ut.conf.GLOBAL;
import com.mysoftwarehouse.ut.conf.PATH;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Ng Siak Hooi
 */
public class UTAP0 extends ProcessForm {

    private File applicationDir;

    @Override
    public void init() {
        //cmd.lang.addBaseName("com.mysoftwarehouse.bs.resources.lang.bs");
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("UTAP0.SetupLanguage", 1);
//        addProcess("InitProcessForm.setupArgument", 1);
        addProcess("UTAP0.SetupLookAndFeel", 1);
        addProcess("UTAP0.SetupUserDirectory", 1);
        addProcess("UTAP0.SetupLog", 1);
        addProcess("UTAP0.StartSystemTray", 1);
    }

    @Override
    public void run() throws ProcessException {
        setupLanguage();
        super.completed();
        setupLookAndFeel();
        super.completed();
        setupUserDirectory();
        super.completed();
        setupLogFile();
        super.completed();
        startSystemTray();
        super.completed();
    }

    private void setupLanguage() {
        String[] allBaseName = {
            "com.mysoftwarehouse.ut.resources.lang.ut_A"
        };
        for (String bn : allBaseName) {
            cmd.lang.addBaseName(bn);
        }
//        cmd.lang.addLanguage(BuiltInApplicationLanguage.MS_MY);
//        String s=INI.readLanguage(this);
//        cmd.lang.setDefaultLanguage(s);
    }

    private void setupLookAndFeel() throws ProcessException {
        try {
            cmd.looknfeel.useSystem();
        } catch (Exception ex) {
            String msg = "UTAP0.error.LookAndFeel.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            cmd.debug.error(msg);
        }
    }

    private void setupUserDirectory() {
//        File g5Dir = new File(PATH.getBaseDirectory(this),
//                APP.VENDOR_DIRECTORY);
//        applicationDir = new File(g5Dir, APP.APPLICATION_DIRECTORY);
        applicationDir = PATH.getApplicationBaseDirectory(this);
        applicationDir.mkdirs();
        cmd.global.files.put(GLOBAL.APP_PATH, applicationDir);
        cmd.global.texts.put(GLOBAL.APP_PATH, applicationDir.getAbsolutePath());
    }

    private void setupLogFile() throws ProcessException {
        try {
            File f = applicationDir;
            File log = new File(f, APP.SYSTEM_LOG_FILE);
            cmd.log.attachSystem(log.getAbsolutePath());
            cmd.log.sysInfo("System Log Started");
            File log2 = new File(f, APP.APPLICATION_LOG_FILE);
            cmd.log.attachApplication(log2.getAbsolutePath());
            cmd.log.info("Application Log Started");
        } catch (IOException ex) {
            String msg = "UTAP0.error.setuplog.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            cmd.debug.error(msg);
            cmd.form.closeApplication(false);
        }
    }

    private void startSystemTray() {
        try {
            cmd.form.setLastWindowsCheck(false);
            UserFormInterface f = cmd.form.create(UTAM0.class);
            cmd.systray.show((SystemTrayMenuForm) f);
        } catch (AWTException ex) {
            cmd.log.sysSevere("UTAP0.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "UTAP0.title";
    }
}
