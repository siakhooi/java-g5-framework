/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.B;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.is.A.ISAM0;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISBP0WHS extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("ISBP0WHS.LoadConfiguration", 1);
        addProcess("ISBP0WHS.StartMainMenu", 1);
    }

    @Override
    public void run() throws ProcessException {
        loadConfiguration();
        super.completed();
        startMainMenu();
        super.completed();
    }

    private void loadConfiguration() throws ProcessException {
//        try {
//            String Whs = GET.Whs(this);
//            String rsName = "ISBP0WHS.Whs";
//            WHS.select(this, rsName, Whs);
//            String CstTyp = cmd.db.getString(rsName, "CstTyp");
//            cmd.global.texts.put(MAP.ISWHS.CSTTYP, CstTyp);
//
//        } catch (SQLException ex) {
//            cmd.log.severe("ISBP0WHS.error", ex);
//            String msg = "ISBP0WHS.error.{0}";
//            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
//            super.setI18nMessage(msg);
//        }
    }

    private void startMainMenu() {
        UserFormInterface f;
        f = cmd.form.create(ISAM0.class);
        cmd.form.executeInNewThread(f);
    }

    @Override
    public String getFormTitle() {
        return "ISBP0WHS.title";
    }
}
