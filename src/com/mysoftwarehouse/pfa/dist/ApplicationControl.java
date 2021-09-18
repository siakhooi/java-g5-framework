/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.dist;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.A.PFAP0;
import com.mysoftwarehouse.pfa.A.PFAP1;

/**
 *
 * @author Ng Siak Hooi
 */
public class ApplicationControl implements com.gqrsoft.g5.deploy.ApplicationControlInterface {

    @Override
    public String getApplicationLevelLocaleBaseName() {
        return "com.mysoftwarehouse.pfa.resources.lang.pfa";
    }

    @Override
    public Class<? extends UserFormInterface> getApplicationStartUserForm() {
        return PFAP0.class;
    }

    @Override
    public Class<? extends UserFormInterface> getApplicationExitUserForm() {
        return PFAP1.class;
    }

    @Override
    public String getApplicationLoggerTitle() {
        return "mysoftwarehouse.pfa";
    }
}
