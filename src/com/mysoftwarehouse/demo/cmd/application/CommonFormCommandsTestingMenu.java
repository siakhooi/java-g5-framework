/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.demo.cmd.application;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class CommonFormCommandsTestingMenu extends MenuForm {

    @Override
    public void build() {
        super.addI18nApplication("showI18nMessage", "showI18nMessage", "");
        super.addI18nApplication("showMessage", "showMessage", "");
        super.addI18nApplication("showI18nConfirmation", "showI18nConfirmation", "");
        super.addI18nApplication("showConfirmation", "showConfirmation", "");
        super.addI18nApplication("chooseCalendar", "chooseCalendar", "");
        super.addI18nApplication("chooseColor", "chooseColor", "");
        super.addI18nApplication("viewImage", "viewImage", "");
        super.addI18nApplication("viewI18nImage", "viewI18nImage", "");
        super.addI18nApplication("viewHTML", "viewHTML", "");
        super.addI18nApplication("viewI18nHTML", "viewI18nHTML", "");
        super.addI18nApplication("chooseOpenDirectory", "chooseOpenDirectory", "");
        super.addI18nApplication("chooseSaveFile", "chooseSaveFile", "");
        super.addI18nApplication("chooseSavePDFFile", "chooseSavePDFFile", "");
        super.addI18nApplication("chooseSaveCSVFile", "chooseSaveCSVFile", "");
        super.addI18nApplication("chooseOpenFile", "chooseOpenFile", "");

        super.addI18nApplication("chooseSaveFileExt", "chooseSaveFile/BMP", "");
        super.addI18nApplication("chooseOpenFileExt", "chooseOpenFile/BMP", "");
    }

    @Override
    public void commandActivated(String name, String title, String description) {
        if ("showI18nMessage".equals(name)) {
            cmd.common.showI18nMessage(DialogMessageType.ERROR,
                    "i18nTitle", "i18nMessage");
        } else if ("showMessage".equals(name)) {
            cmd.common.showMessage(DialogMessageType.INFORMATION,
                    "title", "message");
        } else if ("showI18nConfirmation".equals(name)) {
            boolean i = cmd.common.showI18nConfirmation(DialogMessageType.QUESTION,
                    "i18nTitle", "i18nMessage");
            cmd.debug.println("return: " + i);
        } else if ("showConfirmation".equals(name)) {
            boolean i = cmd.common.showConfirmation(DialogMessageType.WARNING,
                    "title", "message");
            cmd.debug.println("return: " + i);
        } else if ("chooseCalendar".equals(name)) {
            Calendar i = cmd.common.chooseCalendar(
                    cmd.cal.getCalendar("19971231", "yyyyMMdd"));
            cmd.debug.println("return: " + i);
        } else if ("chooseColor".equals(name)) {
            Color i = cmd.common.chooseColor(Color.RED);
            cmd.debug.println("return: " + i);
        } else if ("viewImage".equals(name)) {
            Image i = cmd.appinfo.getApplicationLogo().getImage();
            cmd.common.viewImage(i, "title");
        } else if ("viewI18nImage".equals(name)) {
            Image i = cmd.appinfo.getApplicationLogo().getImage();
            cmd.common.viewI18nImage(i, "i18nTitle");
        } else if ("viewHTML".equals(name)) {
            String s = "<HTML><BODY><H1>viewHTML</H1></BODY></HTML>";
            cmd.common.viewHTML(s, "title");
        } else if ("viewI18nHTML".equals(name)) {
            String s = "<HTML><BODY><H1>viewI18nHTML</H1></BODY></HTML>";
            cmd.common.viewI18nHTML(s, "i18nTitle");
        } else if ("chooseOpenDirectory".equals(name)) {
            File i = new File(cmd.sysprop.userDirectory());
            File b = cmd.common.chooseOpenDirectory(i);
            cmd.debug.println("return: " + b.getAbsolutePath());
        } else if ("chooseSaveFile".equals(name)) {
            File i = new File(cmd.sysprop.userDirectory());
            File b = cmd.common.chooseSaveFile(i);
            cmd.debug.println("return: " + b.getAbsolutePath());
        } else if ("chooseSavePDFFile".equals(name)) {
            File i = new File(cmd.sysprop.userDirectory());
            File b = cmd.common.chooseSavePDFFile(i);
            cmd.debug.println("return: " + b.getAbsolutePath());
        } else if ("chooseSaveCSVFile".equals(name)) {
            File i = new File(cmd.sysprop.userDirectory());
            File b = cmd.common.chooseSaveCSVFile(i);
            cmd.debug.println("return: " + b.getAbsolutePath());
        } else if ("chooseOpenFile".equals(name)) {
            File i = new File(cmd.sysprop.userDirectory());
            File b = cmd.common.chooseOpenFile(i);
            cmd.debug.println("return: " + b.getAbsolutePath());
        } else if ("chooseSaveFileExt".equals(name)) {
            File i = new File(cmd.sysprop.userDirectory());
            File b = cmd.common.chooseSaveFile(i, "i18nTitle", "BMP", "lousy");
            cmd.debug.println("return: " + b.getAbsolutePath());
        } else if ("chooseOpenFileExt".equals(name)) {
            File i = new File(cmd.sysprop.userDirectory());
            File b = cmd.common.chooseOpenFile(i, "i18nTitle", "BMP", "lousy");
            cmd.debug.println("return: " + b.getAbsolutePath());
        }
    /*
    public void saveTableModelToCSVFile(TableModel tm, File file) {
    public void saveTableModelToCSVFile(TableModel tm, File file, int[] selected) {
     */

    }

    @Override
    public String getFormTitle() {
        return "CommonFormCommandsTestingMenu.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
