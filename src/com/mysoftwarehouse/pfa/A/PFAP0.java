/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.A;

import com.mysoftwarehouse.pfa.AU.PFAL0USR;
import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.pfa.conf.APP;
import com.mysoftwarehouse.pfa.conf.DBCHECK;
import com.mysoftwarehouse.pfa.conf.GLOBAL;
import com.mysoftwarehouse.pfa.conf.PATH;
import com.mysoftwarehouse.pfa.db.DB.DB;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFAP0 extends ProcessForm {

    private File applicationDir;

    @Override
    public void init() {
        //cmd.lang.addBaseName("com.mysoftwarehouse.pfa.resources.lang.pfa");
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("PFAP0.SetupLanguage", 1);
        addProcess("PFAP0.SetupArgument", 1);
        addProcess("PFAP0.SetupLookAndFeel", 1);
        addProcess("PFAP0.SetupUserDirectory", 1);
        addProcess("PFAP0.SetupLog", 1);
        addProcess("PFAP0.ConnectDatabase", 1);
        addProcess("PFAP0.CheckDatabase", 1);
        addProcess("PFAP0.StartUserList", 1);
    }

    @Override
    public void run() throws ProcessException {
        setupLanguage();
        super.completed();
        setupArgument();
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
        startUserList();
        super.completed();
    }

    private void setupLanguage() {
        String[] allBaseName = {
            "com.mysoftwarehouse.pfa.resources.lang.pfa_db",
            "com.mysoftwarehouse.pfa.resources.lang.pfa_db_enum",
            "com.mysoftwarehouse.pfa.resources.lang.pfa_A",
            "com.mysoftwarehouse.pfa.resources.lang.pfa_B",
            "com.mysoftwarehouse.pfa.resources.lang.pfa_C",
            "com.mysoftwarehouse.pfa.resources.lang.pfa_D",
            "com.mysoftwarehouse.pfa.resources.lang.pfa_E",
            "com.mysoftwarehouse.pfa.resources.lang.pfa_F",
            "com.mysoftwarehouse.pfa.resources.lang.pfa_G",
            "com.mysoftwarehouse.pfa.resources.lang.pfa_H",
            "com.mysoftwarehouse.pfa.resources.lang.pfa_M",
            "com.mysoftwarehouse.pfa.resources.lang.pfa_N"
        };
        for (String bn : allBaseName) {
            cmd.lang.addBaseName(bn);
        }
    }
    private void setupArgument() {
        try {
            cmd.argv.addOption("d", "", false, "Use Current Directory For Database");
            cmd.argv.parse();
        } catch (ParseException ex) {
            Logger.getLogger(PFAP0.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setupLookAndFeel() throws ProcessException {
        try {
            cmd.looknfeel.useSystem();
        } catch (Exception ex) {
            String msg = "PFAP0.error.LookAndFeel.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            cmd.debug.error(msg);
        }
    }

    private void setupUserDirectory() {
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
            String msg = "PFAP0.error.setuplog.{0}";
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
            String title = "PFAP0.error.ConnectDatabase";
            cmd.log.severe(title, ex);
            title = cmd.lang.getString(title);
            String message = "PFAP0.error.ConnectDatabase.{0}";
            message = cmd.lang.getString(message, jdbcUrl);

            cmd.common.showI18nMessage(DialogMessageType.ERROR,
                    title, message);
            cmd.form.closeApplication(false);
        }
    }

    private void checkDatabase() throws ProcessException {
        String rsName = "PFAP0";
        boolean repeat = false;
        do {
            repeat = false;
            try {
                DB.select(this, rsName, DBCHECK.PFDB_TYP_KEY);
            } catch (SQLException ex) {
                cmd.log.severe("PFAP0.error", ex);
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
                if (!showUpgradeFrom100()) {
                    cmd.form.closeApplication(false);
                }
			}else if (version == DBCHECK.VERSION_101) {
                if (!showUpgradeFrom101()) {
                    cmd.form.closeApplication(false);
                }
            } else if (version != DBCHECK.VERSION_102) {
                showError(version);
                cmd.form.closeApplication(false);
            }
        } catch (SQLException ex) {
            cmd.log.severe("PFAP0.error", ex);
            String msg = "PFAP0.error.checkDatabase.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            cmd.form.closeApplication(false);
        }
    }

    private void showError(int version) {
        String name = "PFAP0.VersionError.title";
        String msg = "PFAP0.VersionError.message.{0}";
        name = cmd.lang.getString(name);
        msg = cmd.lang.getString(msg, cmd.data.int2String(version));
        cmd.common.showI18nMessage(DialogMessageType.ERROR, name, msg);
    }

    private boolean showUpgradeFrom100() {
        String name = "PFAP0.Upgrade100to102.title";
        String msg = "PFAP0.Upgrade100to102.message";
        name = cmd.lang.getString(name);
        msg = cmd.lang.getString(msg);
        boolean i = cmd.common.showI18nConfirmation(
                DialogMessageType.QUESTION, name, msg);
        if (i) {
            cmd.out.booleanValue = false;
			UserFormInterface f = cmd.form.create(PFAP0DB100To102.class);
            cmd.form.execute(f);
            return cmd.out.booleanValue;
        } else {
            return false;
        }
    }
    private boolean showUpgradeFrom101() {
        String name = "PFAP0.Upgrade101to102.title";
        String msg = "PFAP0.Upgrade101to102.message";
        name = cmd.lang.getString(name);
        msg = cmd.lang.getString(msg);
        boolean i = cmd.common.showI18nConfirmation(
                DialogMessageType.QUESTION, name, msg);
        if (i) {
            cmd.out.booleanValue = false;
			UserFormInterface f = cmd.form.create(PFAP0DB101To102.class);
            cmd.form.execute(f);
            return cmd.out.booleanValue;
        } else {
            return false;
        }
	}

    private boolean showWelcome() {
        String name = "PFAP0.Welcome.title";
        String msg = "PFAP0.Welcome.message.{0}";
        name = cmd.lang.getString(name);
        String appName = cmd.appinfo.getApplicationName();
        appName = cmd.lang.getString(appName);
        msg = cmd.lang.getString(msg, appName);
        boolean i = cmd.common.showI18nConfirmation(
                DialogMessageType.QUESTION, name, msg);
        if (i) {
            cmd.out.booleanValue = false;
			UserFormInterface f = cmd.form.create(PFAP0DB102.class);
            cmd.form.execute(f);
            return cmd.out.booleanValue;
        } else {
            return false;
        }
    }

    private void startUserList() {
        UserFormInterface f = cmd.form.create(PFAL0USR.class);
        cmd.form.executeInNewThread(f);
    }

    @Override
    public String getFormTitle() {
        return "PFAP0.title";
    }
}
