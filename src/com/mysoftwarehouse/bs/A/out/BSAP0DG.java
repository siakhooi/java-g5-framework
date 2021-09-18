/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A.out;

import com.mysoftwarehouse.bs.A.*;
import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSAP0DG extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);
        

        addProcess("BSAP0DG.OpenDongle", 1);
        addProcess("BSAP0DG.ReadDongle", 1);
        addProcess("BSAP0DG.showInfo", 1);
    }

    @Override
    public void run() throws ProcessException {
        openDongle();
        super.completed();
        readDongle();
        super.completed();
        showInfo();
        super.completed();
    }
    @Override
    public String getFormTitle() {
        return "BSAP0DG.title";
    }

    private void openDongle() {
      //  throw new UnsupportedOperationException("Not yet implemented");
    }

    private void readDongle() {
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private void showInfo() {
        cmd.form.execute(cmd.form.create(BSAT0DG.class));
    }
}
