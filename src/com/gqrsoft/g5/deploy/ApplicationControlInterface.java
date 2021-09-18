/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.deploy;

import com.gqrsoft.g5.developer.form.UserFormInterface;

/**
 * @author Ng Siak Hooi
 */
public interface ApplicationControlInterface {

    /**
     * return the first <code>userForm</code> to be executed of the application.
     * @return 
     */
    public Class<? extends UserFormInterface> getApplicationStartUserForm();

    /**
     * return the <code>userForm</code> to be executed before the application exit.
     * @return
     */
    public Class<? extends UserFormInterface> getApplicationExitUserForm();

    /**
     * return the locale base name of all <code>userForm</code>s unless explicitly specified 
     * in the <code>userForm</code>.
     * @return
     */
    public String getApplicationLevelLocaleBaseName();

    public String getApplicationLoggerTitle();
}
