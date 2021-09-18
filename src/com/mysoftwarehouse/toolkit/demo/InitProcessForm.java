/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.LanguageEnum.BuiltInApplicationLanguage;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.toolkit.demo.m20.blank.HelloWorld;
import com.mysoftwarehouse.toolkit.demo.m60.HelloProcessNoDisplay;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author Ng Siak Hooi
 */
public class InitProcessForm extends ProcessForm {

    @Override
    public String getFormTitle() {
        return "InitProcessForm.title";
    }

    @Override
    public void userCancel() throws ProcessException {
        cmd.form.closeApplication();
    }

    @Override
    public void init() {
        cmd.lang.addBaseName("com.mysoftwarehouse.toolkit.demo.resources.languages.demo");
        
        super.setUserManualStart(false);
        super.setUserManualClose(false);
        super.setUserAllowCancel(false);
        addProcess("InitProcessForm.setupDatabase", 1);
        addProcess("InitProcessForm.setupLanguage", 1);
        addProcess("InitProcessForm.setupLog", 1);
        addProcess("InitProcessForm.setupArgument", 1);
        addProcess("InitProcessForm.setupLookAndFeel", 1);
        addProcess("InitProcessForm.startMenu", 1);
    //    addProcess("InitProcessForm.setupSchedule", 1);
    }

    @Override
    public void run() throws ProcessException {
        connectDatabase();
        completed();
        setupLanguage();
        completed();
        setupLog();
        completed();
        //      setupArgument();
        completed();
        setupLookAndFeel();
        completed();
        startMenu();
        completed();
        setupSchedule();
        completed();
    }

    private void setupLanguage() {
        cmd.lang.addLanguage(BuiltInApplicationLanguage.MS_MY);
        cmd.lang.addLanguage(BuiltInApplicationLanguage.ZH_CN);
        cmd.lang.addLanguage(BuiltInApplicationLanguage.ZH_TW);
    }

    private void setupSchedule() {
        cmd.cron.schedule(HelloWorld.class, 6000);
        cmd.cron.schedule(HelloProcessNoDisplay.class, 8000);
    }

    private void setupLog() {
        try {
            File f = getApplicationPath();
            File log = new File(f, "g5.log");
            cmd.log.attachSystem(log.getAbsolutePath());
            cmd.log.sysLog(Level.SEVERE, "engine log started");
            File log2 = new File(f, "demo.log");
            cmd.log.attachApplication(log2.getAbsolutePath());
            cmd.log.log(Level.SEVERE, "application log started");
        } catch (IOException ex) {
            cmd.debug.error("setuplog error");
        }
    }

    private void setupLookAndFeel() {
        try {
            cmd.looknfeel.useSystem();
        } catch (Exception ex) {
            cmd.log.log(Level.SEVERE, "setupLookAndFeel", ex);
        }
    }

    private void startMenu() {
        MenuForm mf = (MenuForm) cmd.form.create(
                DemoConfiguration.MENU_FORM);
        cmd.form.executeInNewThread(mf);
    }

    private void connectDatabase() {
        String jdbcUrl = getDatabasePath();

//        Properties p=new Properties();
//        p.put("ifexists", "true");
        try {
            //    String j = jdbcUrl + ";ifexists=true";
            cmd.dba.addHsqldbDatabase(
                    DemoConfiguration.DATABASE_CONNECTION_CODE, jdbcUrl,
                    DemoConfiguration.DATABASE_USERNAME,
                    DemoConfiguration.DATABASE_PASSWORD);
            cmd.dba.setDefaultDatabase(
                    DemoConfiguration.DATABASE_CONNECTION_CODE);
        } catch (Exception ex) {
            cmd.log.log(Level.SEVERE, jdbcUrl, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    "InitProcessForm.ConnectDatabase.Error.Title",
                    "InitProcessForm.ConnectDatabase.Error.Message");
            cmd.form.closeApplication();
        }
    }

//    private void setupNewDatabase(String jdbcUrl) {
//        try {
//            cmd.dba.addHsqldbDatabase(Configuration.DATABASE_CONNECTION_CODE, jdbcUrl, Configuration.DATABASE_USERNAME, Configuration.DATABASE_PASSWORD);
//            cmd.dba.setDefaultDatabase(Configuration.DATABASE_CONNECTION_CODE);
//            cmd.form.execute(new GenerateData());
//        } catch (Exception ex) {
//            cmd.log.log(Level.SEVERE, jdbcUrl, ex);
//            cmd.common.showMessage(DialogMessageType.ERROR,
//                    "InitProcessForm.ConnectDatabase.Error.Title",
//                    "InitProcessForm.ConnectDatabase.Error.Message");
//            cmd.form.closeApplication();
//        }
//    }
    private String getDatabasePath() {
        File dbName = new File(getApplicationPath(),
                DemoConfiguration.DATABASE_NAME);
        String jdbcUrl = "jdbc:hsqldb:" + dbName.getAbsolutePath();

        return jdbcUrl;
    }

    private File getApplicationPath() {
        File g5Dir = new File(cmd.sysprop.userDirectory(),
                DemoConfiguration.VENDOR_DIRECTORY);
        File appDir = new File(g5Dir,
                DemoConfiguration.APPLICATION_DIRECTORY);
        appDir.mkdirs();
        return appDir;
    }
//    private void connectDatabase() {
//        try {
//            Properties p = new Properties();
//            p.setProperty("initialSize", "0");
//            p.setProperty("maxActive", "1");
//            p.setProperty("maxIdle", "1");
//            p.setProperty("minIdle", "0");
//            p.setProperty("maxWait", "10"); //10 seconds
//            cmd.dba.addHsqldbDatabase("default", "jdbc:hsqldb:file:testdb", "sa", "", p);
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IllegalAccessException ex) {
//            ex.printStackTrace();
//        } catch (InstantiationException ex) {
//            ex.printStackTrace();
//        }
//
//    }
}
