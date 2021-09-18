/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.lib;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.toolkit.ToolkitConfiguration;
import java.io.File;

/**
 *
 * @author Ng Siak Hooi
 */
public class PathGenerator {

    public static String getDatabasePath(UserFormInterface form) {
        File dbName = new File(getApplicationPath(form),
                ToolkitConfiguration.DATABASE_NAME);
        String jdbcUrl = "jdbc:hsqldb:" + dbName.getAbsolutePath();

        return jdbcUrl;
    }

    public static File getApplicationPath(UserFormInterface form) {
        File g5Dir = new File(form.cmd().sysprop.userDirectory(),
                ToolkitConfiguration.VENDOR_DIRECTORY);
        File appDir = new File(g5Dir,
                ToolkitConfiguration.APPLICATION_DIRECTORY);
        appDir.mkdirs();
        return appDir;
    }

    public static String getApplicationLogFilePath(UserFormInterface form) {
        File d = getApplicationPath(form);
        File log = new File(d, ToolkitConfiguration.APPLICATION_LOG_FILE);
        return log.getAbsolutePath();
    }
    public static String getSystemLogFilePath(UserFormInterface form) {
        File d = getApplicationPath(form);
        File log = new File(d, ToolkitConfiguration.SYSTEM_LOG_FILE);
        return log.getAbsolutePath();
    }
}
