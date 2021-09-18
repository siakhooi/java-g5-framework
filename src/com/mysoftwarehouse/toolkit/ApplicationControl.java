/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.toolkit.forms.InitializeProcess;

/**
 *
 * @author Ng Siak Hooi
 */
public class ApplicationControl implements com.gqrsoft.g5.deploy.ApplicationControlInterface {

    @Override
    public String getApplicationLevelLocaleBaseName() {
        return "com.mysoftwarehouse.toolkit.resources.languages.toolkit";
    }

    @Override
    public Class<? extends UserFormInterface> getApplicationStartUserForm() {
        return InitializeProcess.class;
    }

    @Override
    public Class<? extends UserFormInterface> getApplicationExitUserForm() {
        return null;
    }

    @Override
    public String getApplicationLoggerTitle() {
        return "mysoftwarehouse.toolkit";
    }
}
