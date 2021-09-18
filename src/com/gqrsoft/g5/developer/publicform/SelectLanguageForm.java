/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import com.gqrsoft.g5.developer.form.SelectionForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import java.util.LinkedHashMap;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class SelectLanguageForm extends SelectionForm {

    @Override
    protected void buildSelection() {
        LinkedHashMap<String, String> allValues = new LinkedHashMap<String, String>();
        Vector<String> selectedValues = new Vector<String>();
        String[] langCode = cmd.lang.getLanguageList();
        for (String code : langCode) {
            String name = cmd.lang.getLanguageName(code);
            name = cmd.lang.getSystemString(name);
            allValues.put(code, name);
        }
        selectedValues.add(cmd.lang.getCurrentLanguageCode());
        this.use(allValues, selectedValues);
    }

    @Override
    protected void userCancelled() {
        cmd.form.closeForm();
    }

    @Override
    protected void userSelected(Vector<String> selectedValues) {
        cmd.lang.useLanguage(selectedValues.firstElement());
        String code = selectedValues.firstElement();
        String n = cmd.lang.getLanguageName(code);
        n = cmd.lang.getSystemString(n);
        String title = "SelectLanguageForm.msg.title.lang.changed";
        String msg = "SelectLanguageForm.msg.lang.changed.{0}";
        title = cmd.lang.getSystemString(title);
        msg = cmd.lang.getSystemString(msg, n);
        cmd.common.showI18nMessage(DialogMessageType.INFORMATION, title, msg);
        cmd.form.closeForm();
    }

    @Override
    public final String getFormI18nTitle() {
        if (cmd.in.i18nTitle == null) {
            String title = "SelectLanguageForm.title";
            return cmd.lang.getSystemString(title);
        } else {
            return cmd.in.i18nTitle;
        }
    }

    @Override
    public final String getFormTitle() {
        return null;
    }
}
