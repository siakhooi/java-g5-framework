/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.dist.rockey;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.rockey.BSZE0;

/**
 *
 * @author Ng Siak Hooi
 */
public class ApplicationControl implements com.gqrsoft.g5.deploy.ApplicationControlInterface {

    @Override
    public String getApplicationLevelLocaleBaseName() {
        return "com.mysoftwarehouse.bs.rockey.resources.lang.bs";
    }

    @Override
    public Class<? extends UserFormInterface> getApplicationStartUserForm() {
        return BSZE0.class;
    }

    @Override
    public Class<? extends UserFormInterface> getApplicationExitUserForm() {
        return null;
    }

    @Override
    public String getApplicationLoggerTitle() {
        return "mysoftwarehouse.bs";
    }
}
