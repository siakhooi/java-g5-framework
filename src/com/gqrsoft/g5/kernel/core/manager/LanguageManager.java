/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager;

import com.gqrsoft.g5.developer.publicobject.LanguageEnum;
import com.gqrsoft.g5.developer.publicobject.LanguageEnum.SystemLanguage;
import com.gqrsoft.g5.kernel.core.AbstractCoreComponent;
import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.core.manager.lang.ApplicationLanguage;
import com.gqrsoft.g5.kernel.core.manager.lang.LanguageTextFinder;
import com.gqrsoft.g5.kernel.core.manager.lang.LanguageTextFinderResult;
import com.gqrsoft.g5.kernel.core.util.NULL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class LanguageManager extends AbstractCoreComponent {

    private ApplicationLanguage defaultLanguage;
    private ApplicationLanguage currentLanguage;
    private LinkedHashMap<String, ApplicationLanguage> allLanguages =
            new LinkedHashMap<String, ApplicationLanguage>();
    //this is not customizable by application developer
    //purpose: so that not all engine text in one file.
    public final Vector<String> allSystemBaseNames =
            new Vector<String>();
    //purpose: to allow application to override system text
    public final Vector<String> allApplicationSystemBaseNames =
            new Vector<String>();
    //add by developers
    //purpose: so that not all engine text in one file.
    public final Vector<String> allApplicationBaseNames =
            new Vector<String>();

    public void addLanguage(String code, String name,
            SystemLanguage systemLanguage,
            String language, String countryCode, String variant,
            ClassLoader classLoader) {
        ApplicationLanguage al = new ApplicationLanguage();
        al.init(code, name, systemLanguage,
                language, countryCode, variant,
                classLoader);
        allLanguages.put(code, al);
    }

    public ResourceBundle getApplicationResourceBundle(String baseName) {
        ResourceBundle rb;
        rb = currentLanguage.getApplicationResourceBundle(baseName);
        if (rb != null) {
            return rb;
        }
        return defaultLanguage.getApplicationResourceBundle(baseName);
    }

    public String getCurrentLanguageCode() {
        return currentLanguage.code;
    }

//    public ResourceBundle getApplicationResourceBundle(String formLevelLocaleBaseName) {
//        ResourceBundle rb;
//        if (formLevelLocaleBaseName != null) {
//            rb = currentLanguage.getApplicationResourceBundle(formLevelLocaleBaseName);
//            if (rb != null) {
//                return rb;
//            }
//        }
//        rb = currentLanguage.getApplicationResourceBundle(
//                core().ctrl().getApplicationLevelLocaleBaseName());
//        if (rb != null) {
//            return rb;
//        }
//        if (formLevelLocaleBaseName != null) {
//            rb = defaultLanguage.getApplicationResourceBundle(formLevelLocaleBaseName);
//            if (rb != null) {
//                return rb;
//            }
//        }
//        return defaultLanguage.getApplicationResourceBundle(
//                core().ctrl().getApplicationLevelLocaleBaseName());
//    }
    public ApplicationLanguage getLanguage(String code) {
        if (allLanguages.containsKey(code)) {
            return allLanguages.get(code);
        } else {
            return null;
        }
    }

    public String[] getLanguageList() {
        return allLanguages.keySet().toArray(new String[0]);
    }

    public boolean useLanguage(String code) {
        if (allLanguages.containsKey(code)) {
            currentLanguage = allLanguages.get(code);
            return true;
        } else {
            //DEBUG.println("Unable to find language code: "+code);
            return false;
        }
    }

    public boolean setDefaultLanguage(String code) {
        if (allLanguages.containsKey(code)) {
            currentLanguage = allLanguages.get(code);
            defaultLanguage = currentLanguage;
            Locale.setDefault(currentLanguage.applicationLocale);
            return true;
        } else {
            //DEBUG.println("Unable to find language code: "+code);
            return false;
        }
    }

    public LanguageManager() {
//        for (LanguageEnum.BuiltInApplicationLanguage b : LanguageEnum.BuiltInApplicationLanguage.values()) {
//            addLanguage(
//                    b.code, b.name, b.syslang,
//                    b.lang, b.country, b.variant,
//                    LanguageManager.class.getClassLoader());
//        }
        LanguageEnum.BuiltInApplicationLanguage b = EngineConfiguration.Language.DEFAULT_LANGUAGE;
        addLanguage(
                b.code, b.name, b.syslang,
                b.lang, b.country, b.variant,
                LanguageManager.class.getClassLoader());
        setDefaultLanguage(EngineConfiguration.Language.DEFAULT_LANGUAGE.code);

        for (String t : EngineConfiguration.Language.allSystemBaseNames) {
            allSystemBaseNames.add(t);
        }
    }

    private String getString(
            String systemBaseName,
            String applicationBaseName,
            String formBaseName,
            String customBaseName,
            String s,
            String... values) {
        if (NULL.isNull(s)) {
            return "";
        }
        LanguageTextFinderResult r = new LanguageTextFinderResult();
        r.originalText = s;
        r.systemBaseName = systemBaseName;
        r.applicationBaseName = applicationBaseName;
        r.formBaseName = formBaseName;
        r.customBaseName = customBaseName;
        r.currentLanguage = currentLanguage;
        r.defaultLanguage = defaultLanguage;

        LanguageTextFinder f = new LanguageTextFinder(r);
        f.setCore(core());
        f.find();
        if (!r.found) {
            return r.originalText;
        }
        String b = r.translatedText;
        for (int i = 0; i < values.length; i++) {
            String v = values[i];
            if (v == null) {
                v = "(null)";
            }
            String p = "\\{" + i + "\\}";
            b = b.replaceAll(p, v);
        }
        return b;
    }

    public String getSystemString(String s, String... values) {
        return getString(
                EngineConfiguration.Language.SYSTEM_LANGUAGE_BASE_NAME,
                null,
                null,
                null,
                s,
                values);
    }

    public String getApplicationString(
            String formBaseName,
            String customBaseName,
            String s,
            String... values) {
        return getString(
                EngineConfiguration.Language.SYSTEM_LANGUAGE_BASE_NAME,
                core().ctrl().getApplicationLevelLocaleBaseName(),
                formBaseName,
                customBaseName,
                s,
                values);
    }

    public Locale getCurrentLocale() {
        return currentLanguage.applicationLocale;
    }

    public String formatCalendar(Calendar d, String mask) {
        SimpleDateFormat temp;
        temp = new SimpleDateFormat(mask, currentLanguage.applicationLocale);
//        temp = new SimpleDateFormat(mask, currentLanguage.systemLocale);
//        temp = new SimpleDateFormat(mask);
        return temp.format(d.getTime()).trim();
    }

    public String formatDouble(double d, String mask) {
        DecimalFormat temp;
        temp = new DecimalFormat(mask,
                new DecimalFormatSymbols(currentLanguage.applicationLocale));
//        temp = new DecimalFormat(mask, 
//                new DecimalFormatSymbols(currentLanguage.systemLocale));
//        temp = new DecimalFormat(mask);
        return temp.format(d).trim();
    }
}
