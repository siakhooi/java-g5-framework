/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.dist.is;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.is.A.ISAP0;
import com.mysoftwarehouse.is.A.ISAP1;

/**
 *
 * @author Ng Siak Hooi
 */
public class ApplicationControl implements com.gqrsoft.g5.deploy.ApplicationControlInterface {

    @Override
    public String getApplicationLevelLocaleBaseName() {
        return "com.mysoftwarehouse.is.resources.lang.is";
    }

    @Override
    public Class<? extends UserFormInterface> getApplicationStartUserForm() {
        return ISAP0.class;
    }

    @Override
    public Class<? extends UserFormInterface> getApplicationExitUserForm() {
        return ISAP1.class;
    }

    @Override
    public String getApplicationLoggerTitle() {
        return "mysoftwarehouse.is";
    }
}
