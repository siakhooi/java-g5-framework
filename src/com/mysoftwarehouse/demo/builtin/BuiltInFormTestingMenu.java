/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.demo.builtin;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.publicform.ConfirmationDialogForm;
import com.gqrsoft.g5.developer.publicform.MessageDialogForm;
import com.gqrsoft.g5.developer.publicform.SelectCalendarForm;
import com.gqrsoft.g5.developer.publicform.SelectColorForm;
import com.gqrsoft.g5.developer.publicform.SelectDirectoryForm;
import com.gqrsoft.g5.developer.publicform.SelectOpenExtensionFileForm;
import com.gqrsoft.g5.developer.publicform.SelectOpenFileForm;
import com.gqrsoft.g5.developer.publicform.SelectSaveExtensionFileForm;
import com.gqrsoft.g5.developer.publicform.SelectSaveFileForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import java.awt.Color;
import java.io.File;

/**
 *
 * @author Ng Siak Hooi
 */
public class BuiltInFormTestingMenu extends MenuForm {

    @Override
    public void build() {
        super.addI18nApplication("confirm", "Confirmation Dialog", "");
        super.addI18nApplication("message", "Message Dialog", "");
        super.addI18nApplication("calendar", "Select Calendar", "");
        super.addI18nApplication("color", "Select Color", "");
        super.addI18nApplication("directory", "Select Directory", "");
        super.addI18nApplication("openfile", "Select Open File", "");
        super.addI18nApplication("openfileext", "Select Open File With Extension Filter", "");
        super.addI18nApplication("savefile", "Select Save File", "");
        super.addI18nApplication("savefileext", "Select Save File With Extension Filter", "");

    }

    @Override
    public void commandActivated(String name, String title, String description) {
        if ("confirm".equals(name)) {
            ConfirmationDialogForm c = new ConfirmationDialogForm();
            cmd.out.dialogMessageType = DialogMessageType.QUESTION;
            cmd.out.i18nTitle = "Test Confirmation Dialog";
            cmd.out.message = "Hello Testing Confirmation Dialog";
            cmd.form.execute(c);
            cmd.debug.println("back: " + cmd.out.booleanValue);
        } else if ("message".equals(name)) {
            MessageDialogForm c = new MessageDialogForm();
            cmd.out.dialogMessageType = DialogMessageType.QUESTION;
            cmd.out.i18nTitle = "Test Message Dialog";
            cmd.out.message = "Hello Testing Message Dialog";
            cmd.form.execute(c);
        } else if ("calendar".equals(name)) {
            SelectCalendarForm c = new SelectCalendarForm();
            cmd.out.calendarValue = cmd.cal.getCalendar("20070202", "yyyyMMdd");
            // cmd.out.calendarValue = null;
            cmd.form.execute(c);
            cmd.debug.println(cmd.lang.formatCalendar(cmd.out.calendarValue, "yyyyMMdd"));
        } else if ("color".equals(name)) {
            SelectColorForm c = new SelectColorForm();
            cmd.out.colorValue = Color.RED;
            // cmd.out.calendarValue = null;
            cmd.form.execute(c);
            cmd.debug.println(cmd.data.Color2Hex(cmd.out.colorValue));
        } else if ("directory".equals(name)) {
            SelectDirectoryForm c = new SelectDirectoryForm();
            cmd.out.fileValue = new File(cmd.sysprop.userDirectory());
            cmd.form.execute(c);
            cmd.debug.println(cmd.out.fileValue.getAbsolutePath());
        } else if ("openfile".equals(name)) {
            SelectOpenFileForm c = new SelectOpenFileForm();
            cmd.out.fileValue = new File(cmd.sysprop.userDirectory());
            cmd.form.execute(c);
            cmd.debug.println(cmd.out.fileValue.getAbsolutePath());
        } else if ("openfileext".equals(name)) {
            SelectOpenExtensionFileForm c = new SelectOpenExtensionFileForm();
            cmd.out.fileValue = new File(cmd.sysprop.userDirectory());
            cmd.out.extension = "bMp";
            cmd.out.i18nExtensionDescription = "pretty pictures";
            cmd.form.execute(c);
            cmd.debug.println(cmd.out.fileValue.getAbsolutePath());

        } else if ("savefile".equals(name)) {
            SelectSaveFileForm c = new SelectSaveFileForm();
            cmd.out.fileValue = new File(cmd.sysprop.userDirectory());
            cmd.form.execute(c);
            cmd.debug.println(cmd.out.fileValue.getAbsolutePath());
        } else if ("savefileext".equals(name)) {
            SelectSaveExtensionFileForm c = new SelectSaveExtensionFileForm();
            cmd.out.fileValue = new File(cmd.sysprop.userDirectory());
            cmd.out.extension = "bMp";
            cmd.out.i18nExtensionDescription = "pretty pictures";
            cmd.form.execute(c);
            cmd.debug.println(cmd.out.fileValue.getAbsolutePath());

        }
    }

    @Override
    public String getFormTitle() {
        return "HelloBuiltIn.title";
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
