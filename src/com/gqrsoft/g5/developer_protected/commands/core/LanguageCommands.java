/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.core;

import com.gqrsoft.g5.developer.publicform.SelectLanguageForm;
import com.gqrsoft.g5.developer.publicobject.LanguageEnum;
import com.gqrsoft.g5.developer.publicobject.LanguageEnum.SystemLanguage;
import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import com.gqrsoft.g5.kernel.core.manager.LanguageManager;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Ng Siak Hooi
 */
public class LanguageCommands extends AbstractCommandComponent {

    public void selectLanguage() {
        this.getFormControl().cmd.form.execute(new SelectLanguageForm());
    }

    public void addLanguage(LanguageEnum.BuiltInApplicationLanguage b) {
        addLanguage(
                b.code, b.name, b.syslang,
                b.lang, b.country, b.variant,
                LanguageManager.class.getClassLoader());
    }

//    public ResourceBundle getApplicationResourceBundle() {
//        return getFormControl().core.lang.getApplicationResourceBundle(
//                getFormControl().userForm.getFormLevelLocaleBaseName());
//    }
    public Locale getCurrentLocale() {
        return getFormControl().core.lang.getCurrentLocale();
    }

    public String getCurrentLanguageCode() {
        return getFormControl().core.lang.getCurrentLanguageCode();
    }

    public ResourceBundle getResourceBundle(String baseName) {
        return getFormControl().core.lang.getApplicationResourceBundle(baseName);
    }
    //application's all other basename
    public void addBaseName(String baseName) {
        getFormControl().core.lang.allApplicationBaseNames.add(baseName);
    }
    //basename that can override system text
    public void addSystemBaseName(String baseName) {
        getFormControl().core.lang.allApplicationSystemBaseNames.add(baseName);
    }

    public String getString(String s, String... values) {
        return getFormControl().core.lang.getApplicationString(
                getFormControl().userForm.getFormLevelLocaleBaseName(),
                null,
                s,
                values);
    }

    public String getCustomString(String customBaseName, String s, String... values) {
        return getFormControl().core.lang.getApplicationString(
                getFormControl().userForm.getFormLevelLocaleBaseName(),
                customBaseName,
                s,
                values);
    }

    public String getSystemString(String s, String... values) {
        return getFormControl().core.lang.getSystemString(s, values);
    }

    public void addLanguage(String code, String name,
            SystemLanguage systemLanguage,
            String language, String countryCode, String variant,
            ClassLoader classLoader) {
        getFormControl().core.lang.addLanguage(
                code, name, systemLanguage,
                language, countryCode, variant,
                classLoader);
    }

    public boolean useLanguage(String code) {
        return getFormControl().core.lang.useLanguage(code);
    }

    public boolean setDefaultLanguage(String code) {
        return getFormControl().core.lang.setDefaultLanguage(code);
    }

    public String getLanguageName(String code) {
        return getFormControl().core.lang.getLanguage(code).name;
    }

    public String[] getLanguageList() {
        return getFormControl().core.lang.getLanguageList();
    }

    public String formatCalendar(Calendar d, String mask) {
        return getFormControl().core.lang.formatCalendar(d, mask);
    }

    public String formatDouble(double d, String mask) {
        return getFormControl().core.lang.formatDouble(d, mask);
    }
}
