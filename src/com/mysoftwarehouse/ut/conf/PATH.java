/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.conf;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.io.File;

/**
 * @author Ng Siak Hooi
 */
public class PATH {

    public static String getBaseDirectory(UserFormInterface form) {
        //return form.cmd().sysprop.userDirectory();
        return form.cmd().sysprop.userHome();
    }

    public static File getApplicationBaseDirectory(UserFormInterface form) {
        File g5Dir = new File(getBaseDirectory(form), APP.VENDOR_DIRECTORY);
        return new File(g5Dir, APP.APPLICATION_DIRECTORY);
    }

    static File getConfigurationFile(UserFormInterface form) {
        return new File(getApplicationBaseDirectory(form),
                APP.CONFIG_FILE);
    }
}
