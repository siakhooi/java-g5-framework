/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.I;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.ITM.ITM;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISIP0ITM extends ProcessForm {

    private ArrayList<String> itms;

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(true);
        super.setLogListVisible(false);

        itms = (ArrayList<String>) cmd.in.objectValue;
        addProcess("ISIP0ITM.Begin", 1);
        addProcess("ISIP0ITM.Copy", itms.size());
        addProcess("ISIP0ITM.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String Whs = GET.Whs(this);
        String title = "", message = "";
        try {

            cmd.db.begin();
            super.completed();
            for (String Itm : itms) {
                String s = "ISIP0ITM.Copying.{0}";
                s = cmd.lang.getString(s, Itm);
                super.setI18nProgressMessage(s);
                ITM.copyWacToStd(this, Whs, Itm);
                super.completed();
            }

            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("ISIP0ITM.error", ex);
            String msg = "ISIP0ITM.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "ISIP0ITM.title";
    }
}
