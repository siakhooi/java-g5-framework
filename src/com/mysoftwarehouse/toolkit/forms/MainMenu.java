/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.forms;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.info.AboutApplicationForm;
import com.gqrsoft.g5.developer.info.AboutEngineForm;
import com.gqrsoft.g5.developer.info.AboutSystemPropertiesForm;
import com.gqrsoft.g5.developer.info.ReleaseNoteMenuForm;
import com.mysoftwarehouse.toolkit.ToolkitConfiguration;
import com.mysoftwarehouse.toolkit.lib.PathGenerator;

/**
 *
 * @author Ng Siak Hooi
 */
public class MainMenu extends MenuForm {

    @Override
    public void build() {
        String title = "", description = "", name = "";

        title = "MainMenu.file.title";
        description = "MainMenu.file.description";
        super.openCategory(title, description);

        title = "MainMenu.showDemo.title";
        description = "MainMenu.showDemo.description";
        super.addApplication(com.mysoftwarehouse.toolkit.demo.InitProcessForm.class,
                title, description);

        name = "hsqldb";
        title = "MainMenu.showHsqldbAdministration.title";
        description = "MainMenu.showHsqldbAdministration.description";
        super.addApplication(name, title, description);

        name = "help";
        title = "MainMenu.showHelp.title";
        description = "MainMenu.showHelp.description";
        super.addApplication(name, title, description);

        name = "exit";
        title = "MainMenu.exit.title";
        description = "MainMenu.exit.description";
        super.addApplication(name, title, description);

        super.closeCategory();

        title = "MainMenu.about.title";
        description = "MainMenu.about.description";
        super.openCategory(title, description);

        title = "MainMenu.aboutengine.title";
        description = "MainMenu.aboutengine.description";
        super.addApplication(AboutEngineForm.class, title, description, false);

        title = "MainMenu.aboutapplication.title";
        description = "MainMenu.aboutapplication.description";
        super.addApplication(AboutApplicationForm.class, title, description, false);

        title = "MainMenu.enginereleasenote.title";
        description = "MainMenu.enginereleasenote.description";
        super.addApplication(ReleaseNoteMenuForm.class, title, description);

        title = "MainMenu.systemproperties.title";
        description = "MainMenu.systemproperties.description";
        super.addApplication(AboutSystemPropertiesForm.class, title, description, false);
        super.closeCategory();

    }

    @Override
    public void commandActivated(String commandTitle, String title, String description) {
        if ("hsqldb".equals(commandTitle)) {
            String jdbc = PathGenerator.getDatabasePath(this);
            String j=cmd.argv.getOptionValue("hsql", jdbc);
            cmd.dba.startHsqldbDatabaseManager(j,
                    ToolkitConfiguration.DATABASE_USERNAME,
                    ToolkitConfiguration.DATABASE_PASSWORD);
        } else if ("help".equals(commandTitle)) {
            String s = cmd.argv.getHelp(
                    ToolkitConfiguration.COMMAND_LINE_SYNTAX,
                    ToolkitConfiguration.HELP_COLUMNS);
            ViewHelp f = new ViewHelp();
            cmd.out.stringValue = s;
            cmd.form.execute(f);
        } else if ("exit".equals(commandTitle)) {
            cmd.form.closeApplication();
        }
    }

    @Override
    public String getFormTitle() {
        return "";
    }

    @Override
    public String getFormI18nTitle() {
        return "Toolkit Main Menu";
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeApplication();
    }
}
