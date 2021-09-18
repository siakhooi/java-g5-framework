/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.forms;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.toolkit.ToolkitConfiguration;
import com.mysoftwarehouse.toolkit.lib.PathGenerator;
import java.io.IOException;

/**
 *
 * @author Ng Siak Hooi
 */
public class InitializeProcess extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(true);
        super.setUserManualStart(false);
        super.setUserManualClose(false);

        String title = "";
        int weight = 1;
        title = "InitializeProcess.registerLogFile";
        super.addProcess(title, weight);
        title = "InitializeProcess.parseCommandLine";
        super.addProcess(title, weight);
        title = "InitializeProcess.connectDatabase";
        super.addProcess(title, weight);
        title = "InitializeProcess.startMainScreen";
        super.addProcess(title, weight);
    }

    @Override
    public void userCancel() throws ProcessException {
        cmd.form.closeApplication();
    }

    @Override
    public void run() throws ProcessException {
        registerLogFile();
        completed();
        parseCommandLine();
        completed();
        addResourceBundle();
        completed();
        connectDatabase();
        completed();
        startMainScreen();
        completed();
    }

    private void registerLogFile() {
        try {
            cmd.log.attachSystem(PathGenerator.getSystemLogFilePath(this));
            cmd.log.attachApplication(PathGenerator.getApplicationLogFilePath(this));
        } catch (IOException ex) {
            cmd.log.severe("InitializeProcess.registerLogFile", ex);
            ex.printStackTrace();
        }
    }

    private void startMainScreen() {
        UserFormInterface menu = cmd.form.create(MainScreen.class);
        cmd.form.executeInNewThread(menu);
    }

    private void addResourceBundle() {
        String r = cmd.argv.getOptionValue("R", "");
        if (!cmd.data.isNull(r)) {
            cmd.lang.addBaseName(r);
        }
    }

    private void parseCommandLine() {
        try {
            cmd.argv.addOption("h", "help", false, "show this help");
            cmd.argv.addOption("R", "resource", true, "resource bundle name, auto added");
            cmd.argv.addOption("hsql", "", true, "hsql connection url");
            for (int i = 0; i < ToolkitConfiguration.TOTAL_MENU_ITEM; i++) {
                String opt = "f" + i;
                cmd.argv.addOption(opt, "", true, "Class Form " + i);
                opt = "t" + i;
                cmd.argv.addOption(opt, "", true, "Title Form " + i);
            }
            cmd.argv.parse();
            if (cmd.argv.hasOption("h")) {
                String commandLineSyntax = ToolkitConfiguration.COMMAND_LINE_SYNTAX;
                cmd.argv.printHelp(commandLineSyntax);
                cmd.out.stringValue =
                        cmd.argv.getHelp(commandLineSyntax, ToolkitConfiguration.HELP_COLUMNS);
                ViewHelp vh = new ViewHelp();
                cmd.form.execute(vh);
                cmd.form.closeApplication();
            }
        } catch (Exception e) {
            cmd.log.severe("cmd.argv.parse", e);
        }
    }

    @Override
    public String getFormTitle() {
        return "InitializeProcess.title";
    }

    private void connectDatabase() {
        String jdbcUrl = PathGenerator.getDatabasePath(this);
        if (cmd.argv.hasOption("hsql")) {
            jdbcUrl = cmd.argv.getOptionValue("hsql");
        }
        try {
            //    String j = jdbcUrl + ";ifexists=true";
            cmd.dba.addHsqldbDatabase(
                    ToolkitConfiguration.DATABASE_CONNECTION_CODE, jdbcUrl,
                    ToolkitConfiguration.DATABASE_USERNAME,
                    ToolkitConfiguration.DATABASE_PASSWORD);
            cmd.dba.setDefaultDatabase(
                    ToolkitConfiguration.DATABASE_CONNECTION_CODE);
        } catch (Exception ex) {
            cmd.log.severe(jdbcUrl, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    "InitProcessForm.ConnectDatabase.Error.Title",
                    "InitProcessForm.ConnectDatabase.Error.Message");
            cmd.form.closeApplication();
        }
    }

//    private String getDatabasePath() {
//        File dbName = new File(getApplicationPath(),
//                ToolkitConfiguration.DATABASE_NAME);
//        String jdbcUrl = "jdbc:hsqldb:" + dbName.getAbsolutePath();
//
//        return jdbcUrl;
//    }
//
//    private File getApplicationPath() {
//        File g5Dir = new File(cmd.sysprop.userDirectory(),
//                ToolkitConfiguration.VENDOR_DIRECTORY);
//        File appDir = new File(g5Dir,
//                ToolkitConfiguration.APPLICATION_DIRECTORY);
//        appDir.mkdirs();
//        return appDir;
//    }
}
