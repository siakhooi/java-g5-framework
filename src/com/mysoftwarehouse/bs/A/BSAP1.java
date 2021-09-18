/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSAP1 extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

//        addProcess("BSAP1.ShutdownScheduleJob", 1);
        addProcess("BSAP1.ShutdownDatabase", 1);
    }

    @Override
    public void run() throws ProcessException {
  //      shutdownScheduleJob();
//        super.completed();
        shutdownDatabase();
        super.completed();
    }

    private void shutdownScheduleJob() {
    //TODO
    }

    private void shutdownDatabase() throws ProcessException {
        try {
            //String sql = "SHUTDOWN";
            String sql = "SHUTDOWN COMPACT";
            String psName = "BSAP1";
            cmd.db.setStatement(psName, sql);
            cmd.db.execUpdate(psName);
        } catch (SQLException ex) {
            cmd.log.severe("BSAP1.error", ex);
            String msg = "BSAP1.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            super.processCancel();
        }
    }

    @Override
    public String getFormTitle() {
        return "BSAP1.title";
    }
}
