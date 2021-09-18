/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryField.util;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.kernel.control.FormControl;

/**
 *
 * @author Ng Siak Hooi
 */
public class ShowHelpFactory {

    public static void showHelp(
            FormControl formControl, AbstractEntryField entryField) {
        String i = EntryFieldHelpHtmlTextGenerator.generateHtmlText(
                formControl, entryField);
        //ViewHtmlForm f = new ViewHtmlForm();
        UserFormInterface f = formControl.cmd.form.create(
                EngineConfiguration.Entry.DEFAULT_VIEW_HELP_FORM);

        formControl.out.message = i;
        String s = "EntryField.Help.Title";
        formControl.out.i18nTitle = formControl.cmd.lang.getSystemString(s);
        formControl.out.fieldName = entryField.field.fieldName;
        formControl.cmd.form.execute(f);
    }
}
