/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.conf;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * to keep name/id for cmd.global('')
 * @author Ng Siak Hooi
 */
public class INI {

    private static String LANG = "language.current";
    private static String defaultLanguage = "en_US";

    public static String readLanguage(UserFormInterface form) {
        try {
            File f = PATH.getConfigurationFile(form);
            Properties p = new Properties();
            p.load(new FileReader(f));
            return p.getProperty(LANG, defaultLanguage);
        } catch (FileNotFoundException ex) {
            form.cmd().log.sysSevere("INI.error", ex);
            return defaultLanguage;
        } catch (IOException ex) {
            form.cmd().log.sysSevere("INI.error", ex);
            return defaultLanguage;
        }
    }

    public static void writeLanguage(UserFormInterface form, String language) {
        try {
            File f = PATH.getConfigurationFile(form);
            Properties p = new Properties();
            if (f.exists()) {
                p.load(new FileReader(f));
            }
            p.setProperty(LANG, language);
            p.store(new FileWriter(f), "");
        } catch (FileNotFoundException ex) {
            form.cmd().log.sysSevere("INI.error", ex);
        } catch (IOException ex) {
            form.cmd().log.sysSevere("INI.error", ex);
        }
    }
}
