/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.LanguageEnum.BuiltInApplicationLanguage;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.B.BSBL0CMP;
import com.mysoftwarehouse.bs.conf.APP;
import com.mysoftwarehouse.bs.conf.DBCHECK;
import com.mysoftwarehouse.bs.conf.GLOBAL;
import com.mysoftwarehouse.bs.conf.INI;
import com.mysoftwarehouse.bs.conf.PATH;
import com.mysoftwarehouse.bs.db.DB.DB;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSAP0 extends ProcessForm {

    private File applicationDir;

    @Override
    public void init() {
        //cmd.lang.addBaseName("com.mysoftwarehouse.bs.resources.lang.bs");
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSAP0.SetupLanguage", 1);
//        addProcess("InitProcessForm.setupArgument", 1);

        addProcess("BSAP0.SetupLookAndFeel", 1);
        addProcess("BSAP0.SetupUserDirectory", 1);
        addProcess("BSAP0.SetupLog", 1);
        addProcess("BSAP0.ConnectDatabase", 1);
        addProcess("BSAP0.CheckDatabase", 1);
        //addProcess("BSAP0.StartDongleChecker", 1);
        addProcess("BSAP0.StartCompanyList", 1);
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
        setupDatabase();
        super.completed();
        checkDatabase();
        super.completed();
        //startDongleChecker();
        //super.completed();
        startCompanyList();
        super.completed();
    }

    private void setupLanguage() {
        String[] allBaseName = {
            "com.mysoftwarehouse.bs.resources.lang.bs_A",
            "com.mysoftwarehouse.bs.resources.lang.bs_B",
            "com.mysoftwarehouse.bs.resources.lang.bs_C",
            "com.mysoftwarehouse.bs.resources.lang.bs_D",
            "com.mysoftwarehouse.bs.resources.lang.bs_E",
            "com.mysoftwarehouse.bs.resources.lang.bs_F",
            "com.mysoftwarehouse.bs.resources.lang.bs_G",
            "com.mysoftwarehouse.bs.resources.lang.bs_H",
            "com.mysoftwarehouse.bs.resources.lang.bs_I",
            "com.mysoftwarehouse.bs.resources.lang.bs_J",
            "com.mysoftwarehouse.bs.resources.lang.bs_K",
            "com.mysoftwarehouse.bs.resources.lang.bs_L",
            "com.mysoftwarehouse.bs.resources.lang.bs_M",
            "com.mysoftwarehouse.bs.resources.lang.bs_Q",
            "com.mysoftwarehouse.bs.resources.lang.bs_N",
            "com.mysoftwarehouse.bs.resources.lang.bs_O",
            "com.mysoftwarehouse.bs.resources.lang.bs_P",
            "com.mysoftwarehouse.bs.resources.lang.bs_db",
            "com.mysoftwarehouse.bs.resources.lang.bs_db_err",
            "com.mysoftwarehouse.bs.resources.lang.bs_db_help"
        };
        for (String bn : allBaseName) {
            cmd.lang.addBaseName(bn);
        }
        cmd.lang.addLanguage(BuiltInApplicationLanguage.MS_MY);
        String s=INI.readLanguage(this);
        cmd.lang.setDefaultLanguage(s);
    }

    private void setupLookAndFeel() throws ProcessException {
        try {
            cmd.looknfeel.useSystem();
        } catch (Exception ex) {
            String msg = "BSAP0.error.LookAndFeel.{0}";
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
            String msg = "BSAP0.error.setuplog.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            cmd.debug.error(msg);
            cmd.form.closeApplication(false);
        }
    }

    private void setupDatabase() {
        String jdbcUrl = PATH.getDatabaseJDBCUrl(this);
        try {
            cmd.log.info("connect to: " + jdbcUrl);
            cmd.dba.addHsqldbDatabase(
                    APP.DATABASE_CONNECTION_CODE,
                    jdbcUrl,
                    APP.DATABASE_USERNAME,
                    APP.DATABASE_PASSWORD);
            cmd.dba.setDefaultDatabase(
                    APP.DATABASE_CONNECTION_CODE);
            cmd.db.initThreadDBConnection();
            cmd.db.useThreadDBConnection();
            cmd.global.texts.put(GLOBAL.JDBC_URL, jdbcUrl);
        } catch (Exception ex) {
            String title = "BSAP0.error.ConnectDatabase";
            cmd.log.severe(title, ex);
            title = cmd.lang.getString(title);
            String message = "BSAP0.error.ConnectDatabase.{0}";
            message = cmd.lang.getString(message, jdbcUrl);

            cmd.common.showI18nMessage(DialogMessageType.ERROR,
                    title, message);
            cmd.form.closeApplication(false);
        }
    }

    private void checkDatabase() throws ProcessException {
        String rsName = "BSAP0";
        boolean repeat = false;
        do {
            repeat = false;
            try {
                DB.select(this, rsName, DBCHECK.BSDB_TYP_KEY);
            } catch (SQLException ex) {
                cmd.log.severe("BSAP0.error.checkdb", ex);
                if (showWelcome()) {
                    repeat = true;
                } else {
                    cmd.form.closeApplication(false);
                    return;
                }
            }
        } while (repeat);
        try {
            cmd.db.first(rsName);
            int version = cmd.db.getInteger(rsName, "Version");
            if (version == DBCHECK.VERSION_100) {
                if (!upgrade()) {
                    cmd.form.closeApplication(false);
                }
            } else if (version != DBCHECK.VERSION_101) {
                if (!showWelcome()) {
                    cmd.form.closeApplication(false);
                }
            }
        } catch (SQLException ex) {
            cmd.log.severe("BSAP0.error.checkDatabase", ex);
            String msg = "BSAP0.error.checkDatabase.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            cmd.form.closeApplication(false);
        }
    }

    private boolean upgrade() {
        String name = "BSAP0.Upgrade.title";
        String msg = "BSAP0.Upgrade.message";
        boolean i = cmd.common.showConfirmation(
                DialogMessageType.QUESTION, name, msg);
        if (i) {
            UserFormInterface f = cmd.form.create(BSAP0DB101.class);
            cmd.form.execute(f);
            return cmd.out.booleanValue;
        } else {
            return false;
        }
    }

    private boolean showWelcome() {
        String name = "BSAP0.Welcome.title";
        String msg = "BSAP0.Welcome.message.{0}";
        name = cmd.lang.getString(name);
        String appName = cmd.appinfo.getApplicationName();
        appName = cmd.lang.getString(appName);
        msg = cmd.lang.getString(msg, appName);
        boolean i = cmd.common.showI18nConfirmation(
                DialogMessageType.QUESTION, name, msg);
        if (i) {
            UserFormInterface f = cmd.form.create(BSAP0DB100.class);
            cmd.form.execute(f);
            if (!cmd.out.booleanValue) {
                return cmd.out.booleanValue;
            }
            UserFormInterface f2 = cmd.form.create(BSAP0DB101.class);
            cmd.form.execute(f2);
            return cmd.out.booleanValue;
        } else {
            return false;
        }
    }

//    private String getDatabaseJDBCUrl() {
//        File dbName = new File(applicationDir, APP.DATABASE_NAME);
//        String jdbcUrl = "jdbc:hsqldb:" + dbName.getAbsolutePath();
//        return jdbcUrl;
//    }
    private void startCompanyList() {
        UserFormInterface f = cmd.form.create(BSBL0CMP.class);
        cmd.form.executeInNewThread(f);
    }

//    private void startDongleChecker() {
//        cmd.cron.schedule(BSAP1DG.class,
//                APP.DONGLE_START_TIME, APP.DONGLE_CHECK_RATE);
//    }
    @Override
    public String getFormTitle() {
        return "BSAP0.title";
    }
}
