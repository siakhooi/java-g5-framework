/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.frame;

import com.gqrsoft.g5.kernel.core.Core;
import com.gqrsoft.g5.developer.form.UserFormInterface;

/**
 *
 * @author Ng Siak Hooi
 */
public class WindowTitleGenerator {

    public static String getWindowTitle(
            Core core,
            UserFormInterface userForm) {
        String appName = core.info().getApplicationName();
        String formTitle = userForm.getFormI18nTitle();
        String windowTitle = "WindowTitle.%APPNAME.%FORMTITLE";
        windowTitle = core.lang.getSystemString(windowTitle);
        appName = core.lang.getApplicationString(
                userForm.getFormLevelLocaleBaseName(),
                null,
                appName);
        windowTitle = windowTitle.replaceAll("%APPNAME", appName);
        windowTitle = windowTitle.replaceAll("%FORMTITLE", formTitle);

        return windowTitle;
    }
}
