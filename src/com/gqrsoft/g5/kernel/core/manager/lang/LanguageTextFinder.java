/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager.lang;

import com.gqrsoft.g5.kernel.core.AbstractCoreComponent;
import com.gqrsoft.g5.kernel.core.util.CONSOLE;
import com.gqrsoft.g5.kernel.core.util.NULL;
import java.util.ResourceBundle;

/**
 *
 * @author Ng Siak Hooi
 */
public class LanguageTextFinder extends AbstractCoreComponent {

    private LanguageTextFinderResult result;

    public LanguageTextFinder(LanguageTextFinderResult r) {
        this.result = r;
    }
    private ApplicationLanguage lang;
    private String baseName;
    //private ClassLoader loader;
    private int finding = 0;

    public void find() {
//        CONSOLE.println(result.applicationBaseName);
//        CONSOLE.println(result.customBaseName);
//        CONSOLE.println(result.formBaseName);
//        CONSOLE.println(result.originalText);
//        CONSOLE.println(result.systemBaseName);
//        CONSOLE.println(result.currentLanguage.code);
//        CONSOLE.println(result.defaultLanguage.code);


        lang = result.currentLanguage;

        searchLanguage();
        if (result.found) {
            return;
        }
        lang = result.defaultLanguage;
        searchLanguage();

        if (finding == 0) {
            CONSOLE.error("Lang Error: No base name.");
        }
        if (!result.found) {
            CONSOLE.error(
                    "Lang Error:" +
                    result.systemBaseName + ":" +
                    result.applicationBaseName + ":" +
                    result.formBaseName + ":" +
                    result.customBaseName + ":" +
                    result.originalText);
        }
    }

    private void searchLanguage() {
        //loader = core().ctrl().getClass().getClassLoader();

        baseName = result.customBaseName;
        if (!NULL.isNull(baseName)) {
            searchBaseName(false);
            if (result.found) {
                return;
            }
        }

        baseName = result.formBaseName;
        if (!NULL.isNull(baseName)) {
            searchBaseName(false);
            if (result.found) {
                return;
            }
        }

        baseName = result.applicationBaseName;
        if (!NULL.isNull(baseName)) {
            searchBaseName(false);
            if (result.found) {
                return;
            }
        }

        for (String baseName1 : core().lang.allApplicationBaseNames) {
            if (!NULL.isNull(baseName1)) {
                baseName = baseName1;
                searchBaseName(false);
                if (result.found) {
                    return;
                }
            }
        }

        for (String baseName1 : core().lang.allApplicationSystemBaseNames) {
            if (!NULL.isNull(baseName1)) {
                baseName = baseName1;
                searchBaseName(true);
                if (result.found) {
                    return;
                }
            }
        }

        //        loader = this.getClass().getClassLoader();
        baseName = result.systemBaseName;
        if (!NULL.isNull(baseName)) {
            searchBaseName(true);
            if (result.found) {
                return;
            }
        }
        for (String baseName1 : core().lang.allSystemBaseNames) {
            if (!NULL.isNull(baseName1)) {
                baseName = baseName1;
                searchBaseName(true);
                if (result.found) {
                    return;
                }
            }
        }
    }

    private void searchBaseName(boolean system) {
        finding++;
        ResourceBundle rb;
        if (system) {
            rb = lang.getSystemResourceBundle(baseName);
        } else {
            rb = lang.getApplicationResourceBundle(baseName);
        }
        if (rb != null) {
            try {
                result.translatedText = rb.getString(result.originalText);
                result.found = true;
            } catch (Exception e) {
            //ignore, find next resources
            }
        }

        if (finding == 1 && !result.found) {
            String msg =
                    "Lang Warning [" +
                    lang.code + "] " +
                    baseName + ": " +
                    result.originalText;
            core().log.getEngineLogger().finest(msg);
        //CONSOLE.error(msg);
        }
    }
    /*
    private ResourceBundle searchResourceBundle(String baseName){
    ResourceBundle rb;
    rb=currentLanguage.getResourceBundle(
    EngineConfiguration.SYSTEM_LANGUAGE_BASE_NAME);
    if(rb!=null) return rb;
    rb=defaultLanguage.getResourceBundle(baseName);
    if(rb!=null) return rb;
    rb=defaultLanguage.getResourceBundle(
    EngineConfiguration.SYSTEM_LANGUAGE_BASE_NAME);
    return rb;
    }
     */
}
