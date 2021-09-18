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
public class BSAP1REG extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSAP1REG.OpenWebConnection", 1);
        addProcess("BSAP1REG.SendDongleInformation", 1);
        addProcess("BSAP1REG.showResult", 1);
    }

    @Override
    public void run() throws ProcessException {
        openWeb();
        super.completed();
        sendDongleInformation();
        super.completed();
        showResult();
        super.completed();
    }
    @Override
    public String getFormTitle() {
        return "BSAP1REG.title";
    }

    private void openWeb() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendDongleInformation() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void showResult() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
