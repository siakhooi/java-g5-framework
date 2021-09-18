/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A.out;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSAP1DG extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSAP1DG.OpenDongle", 1);
        addProcess("BSAP1DG.ReadDongle", 1);
        addProcess("BSAP1DG.putGlobal", 1);
    }
    @Override
    public boolean showThisForm() {
        return true;
    }

    @Override
    public void run() throws ProcessException {
        openDongle();
        super.completed();
        readDongle();
        super.completed();
        putGlobal();
        super.completed();
    }
    @Override
    public String getFormTitle() {
        return "BSAP1DG.title";
    }

    private void openDongle() {
        
    }

    private void putGlobal() {
        
    }

    private void readDongle() {
        
    }

}
