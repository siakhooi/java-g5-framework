/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.dist;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.ut.A.UTAP0;

/**
 *
 * @author Ng Siak Hooi
 */
public class ApplicationControl implements com.gqrsoft.g5.deploy.ApplicationControlInterface {

    @Override
    public String getApplicationLevelLocaleBaseName() {
        return "com.mysoftwarehouse.ut.resources.lang.ut";
    }

    @Override
    public Class<? extends UserFormInterface> getApplicationStartUserForm() {
        return UTAP0.class;
    }

    @Override
    public Class<? extends UserFormInterface> getApplicationExitUserForm() {
        return null;
    }

    @Override
    public String getApplicationLoggerTitle() {
        return "mysoftwarehouse.ut";
    }
}
