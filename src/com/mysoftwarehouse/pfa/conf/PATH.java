/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.conf;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.io.File;

/**
 * @author Ng Siak Hooi
 */
public class PATH {

    public static String getBaseDirectory(UserFormInterface form) {
        if (form.cmd().argv.hasOption("d")) {
            return form.cmd().sysprop.userDirectory();
        } else {
            return form.cmd().sysprop.userHome();
        }
    }

    public static File getApplicationBaseDirectory(UserFormInterface form) {
        File g5Dir = new File(getBaseDirectory(form), APP.VENDOR_DIRECTORY);
        return new File(g5Dir, APP.APPLICATION_DIRECTORY);
    }

    public static String getDatabaseJDBCUrl(UserFormInterface form) {
        File dbName = new File(getApplicationBaseDirectory(form),
                APP.DATABASE_NAME);
        String jdbcUrl = "jdbc:hsqldb:" + dbName.getAbsolutePath();
        return jdbcUrl;
    }

    static File getConfigurationFile(UserFormInterface form) {
        return new File(getApplicationBaseDirectory(form),
                APP.CONFIG_FILE);
    }
}
