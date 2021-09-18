/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.gqrsoft.g5.developer_protected.abstractform.AbstractProcessForm;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class ProcessForm extends AbstractProcessForm implements FormHideable {

    /**
     * 
     */
    public abstract void init();

    /**
     * 
     * @throws com.gqrsoft.g5.developer.publicobject.ProcessException
     */
    public abstract void run() throws ProcessException;

    /**
     * 
     * @return
     */
//    public boolean isUserManualStart() {
//        return true;
//    }
//
//    /**
//     * 
//     * @return
//     */
//    public boolean isUserManualClose() {
//        return true;
//    }
//
//    /**
//     * 
//     * @return
//     */
//    public boolean isUserAllowCancel() {
//        return true;
//    }
//
//    /**
//     * 
//     * @return
//     */
//    public boolean showLogList() {
//        return false;
//    }

    /**
     * 
     * @throws com.gqrsoft.g5.developer.publicobject.ProcessException
     */
    public void userCancel() throws ProcessException {
    }

    /**
     * 
     * @return
     */
    @Override
    public boolean showThisForm() {
        return true;
    }

    /**
     * 
     * @throws com.gqrsoft.g5.developer.publicobject.ProcessException
     */
    public void processCancel() throws ProcessException {
    }
}
