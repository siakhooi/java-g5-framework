/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.info.AboutApplicationForm;
import com.gqrsoft.g5.developer.info.AboutEngineForm;
import com.gqrsoft.g5.developer.info.AboutSystemPropertiesForm;
import com.gqrsoft.g5.developer.info.ReleaseNoteMenuForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.FormEnum.FrameState;
import com.mysoftwarehouse.toolkit.demo.m20.blank.HelloWorld;

/**
 *
 * @author Ng Siak Hooi
 */
public class DemoMenuBar extends MenuForm {

    @Override
    public void build() {
        openI18nRole("System", "System Menu");
        addI18nApplication("exit", "Exit", "");
        super.setIcon(cmd.icon.getCloseIcon(cmd.icon.getDefaultHeight()));
        closeRole();
        openI18nRole("Thread", "Test Thread");
        addI18nApplication(HelloWorld.class, "Hello World (new Thread)", "", true);
        addI18nApplication(HelloWorld.class, "Hello World (not new Thread)", "", false);
        closeRole();
        openI18nRole("DB", "Database Menu");
        addI18nApplication("org.hsqldb.util.DatabaseManager", "HSQLDB Database Manager", "Start HSQLDB Database Manager");
        addI18nApplication("org.hsqldb.util.DatabaseManagerSwing", "HSQLDB Database Manager (Swing)", "");
//        addI18nApplication("org.hsqldb.util.Transfer", "HSQLDB Transfer", "");
//        addI18nApplication("org.hsqldb.util.QueryTool", "HSQLDB QueryTool", "");
        addI18nApplication("org.hsqldb.util.SqlTool", "HSQLDB SqlTool", "");
        closeRole();
        openI18nRole("Frame Menu", "Test Frame");
        addI18nApplication("max", "Maximize Windows", "");
        addI18nApplication("maxv", "Maximize Windows (Vertical)", "");
        addI18nApplication("maxh", "Maximize Windows (Horizontal)", "");
        addI18nApplication("maxn", "Restore Windows", "");
        addI18nApplication("maxi", "Iconize Windows", "");
        closeRole();
        openI18nRole("Language", "");
        addI18nApplication("selectlang", "Select Language", "");
        closeRole();
//        openI18nRole("File", "File Menu");
//        openI18nCategory("File Edit", "File Edit Menu");
//        addI18nApplication("fileedit1", "File Edit 1", "File Edit 1 Menu");
//        addI18nApplication("fileedit2", "File Edit 2", "File Edit 2 Menu");
//        addI18nApplication("fileedit3", "File Edit 3", "File Edit 3 Menu");
//        addI18nApplication("fileedit4", "File Edit 4", "File Edit 4 Menu");
//        closeCategory();
//        openI18nCategory("File Open", "File Open Menu");
//        addI18nApplication("fileopen1", "File Open 1", "File Open 1 Menu");
//        addI18nApplication("fileopen2", "File Open 2", "File Open 2 Menu");
//        addI18nApplication("fileopen3", "File Open 3", "File Open 3 Menu");
//        closeCategory();
//        closeRole();
//        openI18nRole("Directory", "Directory Menu");
//        openI18nCategory("Directory Save", "Directory Save Menu");
//        addI18nApplication("dirsave1", "Directory Save 1", "Directory Save 1 Menu");
//        addI18nApplication("dirsave2", "Directory Save 2", "Directory Save 2 Menu");
//        closeCategory();
//        openI18nCategory("Directory Exit", "Directory Exit Menu");
//        addI18nApplication("direxit1", "Directory Exit 1", "Directory Exit 1 Menu");
//        closeCategory();
//        closeRole();
        openI18nRole("Help", "Help Menu");
        String title = "MainMenu.aboutengine.title";
        String description = "MainMenu.aboutengine.description";
        super.addApplication(AboutEngineForm.class, title, description, false);

        title = "MainMenu.aboutapplication.title";
        description = "MainMenu.aboutapplication.description";
        super.addApplication(AboutApplicationForm.class, title, description, false);

        title = "MainMenu.enginereleasenote.title";
        description = "MainMenu.enginereleasenote.description";
        super.addApplication(ReleaseNoteMenuForm.class, title, description, false);

        title = "MainMenu.systemproperties.title";
        description = "MainMenu.systemproperties.description";
        super.addApplication(AboutSystemPropertiesForm.class, title, description, false);

        closeRole();
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }

    @Override
    public void commandActivated(String name, String title, String description) {
        if ("org.hsqldb.util.DatabaseManager".equals(name)) {
            String[] b = {
                "--noexit",
                "--driver",
                "org.hsqldb.jdbcDriver",
                "--url",
                "jdbc:hsqldb:file:/mysoftwarehouse/demo/demodb",
                "--user",
                "sa"
            };
//            org.hsqldb.util.DatabaseManager.main(new String[0]);
            org.hsqldb.util.DatabaseManager.main(b);

        } else if ("org.hsqldb.util.DatabaseManagerSwing".equals(name)) {
            org.hsqldb.util.DatabaseManagerSwing.main(new String[0]);
        } else if ("org.hsqldb.util.SqlTool".equals(name)) {
            org.hsqldb.util.SqlTool.main(new String[0]);
        } else if ("max".equals(name)) {
            cmd.frame.setState(FrameState.MAXIMIZED_BOTH);
        } else if ("maxv".equals(name)) {
            cmd.frame.setState(FrameState.MAXIMIZED_VERT);
        } else if ("maxh".equals(name)) {
            cmd.frame.setState(FrameState.MAXIMIZED_HORIZ);
        } else if ("maxn".equals(name)) {
            cmd.frame.setState(FrameState.NORMAL);
        } else if ("maxi".equals(name)) {
            cmd.frame.setState(FrameState.ICONIFIED);
        } else if ("selectlang".equals(name)) {
            cmd.lang.selectLanguage();
        } else if ("exit".equals(name)) {
            cmd.form.closeApplication();
//        } else if ("aboutEngine".equals(name)) {
//            cmd.engine.showAbout();
//        } else if ("aboutDemo".equals(name)) {
//            cmd.engine.showAboutApplication();
        } else {
            cmd.common.showI18nMessage(DialogMessageType.INFORMATION,
                    name, name);
        }
    }

    @Override
    public String getFormTitle() {
        return "";
    }
}
