/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager.lang;

import com.gqrsoft.g5.developer.publicobject.LanguageEnum.SystemLanguage;
import com.gqrsoft.g5.kernel.core.util.CONSOLE;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Ng Siak Hooi
 */
public class ApplicationLanguage {

    public SystemLanguage systemLanguage;
    public String code = "";
    public String name = "";
    public Locale applicationLocale;
    public Locale systemLocale;
    private ClassLoader applicationLocaleClassLoader;
    private ClassLoader systemLocaleClassLoader;

    public void init(String code, String name,
            SystemLanguage systemLanguage,
            String language, String countryCode, String variant,
            ClassLoader classLoader) {
        this.code = code;
        this.name = name;
        this.systemLanguage = systemLanguage;
        systemLocale = new Locale(
                systemLanguage.language,
                systemLanguage.countryCode,
                systemLanguage.variant);
        applicationLocale = new Locale(
                language, countryCode, variant);
        applicationLocaleClassLoader = classLoader;
        systemLocaleClassLoader = ApplicationLanguage.class.getClassLoader();
    }
    private HashMap<String, ResourceBundle> allResources =
            new HashMap<String, ResourceBundle>();

    public ResourceBundle getSystemResourceBundle(String baseName) {
        return getResourceBundle(baseName, systemLocale, systemLocaleClassLoader);
    }

    public ResourceBundle getApplicationResourceBundle(String baseName) {
        return getResourceBundle(baseName, applicationLocale, applicationLocaleClassLoader);
    }

    private ResourceBundle getResourceBundle(String baseName, Locale locale, ClassLoader loader) {
        if (allResources.containsKey(baseName)) {
            return allResources.get(baseName);
        } else {
            try {
                ResourceBundle rb = ResourceBundle.getBundle(baseName, locale, loader);
                allResources.put(baseName, rb);
                return rb;
            } catch (Exception e) {
                String msg =
                        "Unable to load ResourceBundle: " + baseName + ":" + locale.toString();
                CONSOLE.error(msg, e);
                return null;
            }
        }
    }

//    public ResourceBundle getResourceBundle(String baseName, ClassLoader loader) {
//        if (allResources.containsKey(baseName)) {
//            return allResources.get(baseName);
//        } else {
//            try {
//                ResourceBundle rb = ResourceBundle.getBundle(baseName, locale, loader);
//                allResources.put(baseName, rb);
//                return rb;
//            } catch (Exception e) {
//                CONSOLE.error("Unable to load ResourceBundle: " + baseName + ":" + locale.toString());
//                return null;
//            }
//        }
//    }
}
