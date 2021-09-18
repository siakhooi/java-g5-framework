/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A.out;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSAP0REG extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSAP0REG.OpenWebConnection", 1);
        addProcess("BSAP0REG.SendDongleId", 1);
        addProcess("BSAP0REG.readDongleInformation", 1);
    }

    @Override
    public void run() throws ProcessException {
        openWeb();
        super.completed();
        sendDongleId();
        super.completed();
        readDongleInformation();
        super.completed();
    }
    @Override
    public String getFormTitle() {
        return "BSAP0REG.title";
    }

    private void openWeb() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void readDongleInformation() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendDongleId() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
