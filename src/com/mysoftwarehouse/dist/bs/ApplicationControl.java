/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.dist.bs;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.A.BSAP0;
import com.mysoftwarehouse.bs.A.BSAP1;

/**
 *
 * @author Ng Siak Hooi
 */
public class ApplicationControl implements com.gqrsoft.g5.deploy.ApplicationControlInterface {

    @Override
    public String getApplicationLevelLocaleBaseName() {
        return "com.mysoftwarehouse.bs.resources.lang.bs";
    }

    @Override
    public Class<? extends UserFormInterface> getApplicationStartUserForm() {
        return BSAP0.class;
    }

    @Override
    public Class<? extends UserFormInterface> getApplicationExitUserForm() {
        return BSAP1.class;
    }

    @Override
    public String getApplicationLoggerTitle() {
        return "mysoftwarehouse.bs";
    }
}
