/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.AU;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.pfa.data.PFCFG;
import com.mysoftwarehouse.pfa.db.CFG.CFG;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFAP0USR extends ProcessForm {

    private String MainAccLstTyp;

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("PFAP0USR.LoadConfiguration", 1);
        addProcess("PFAP0USR.StartMainForm", 1);
    }

    @Override
    public void run() throws ProcessException {
        loadConfiguration();
        super.completed();
        startMainForm();
        super.completed();
    }

    private void loadConfiguration() throws ProcessException {
        try {
            String id = cmd.global.texts.get(USR.PFUSR_ID);
            String rsName = "PFAP0USR";
            CFG.select(this, rsName, id);

            MainAccLstTyp = cmd.db.getString(rsName, "MainAccLstTyp");
            String CapAcc = cmd.db.getString(rsName, "CapAcc");
            String IncAcc = cmd.db.getString(rsName, "IncAcc");
            String RtIncAcc = cmd.db.getString(rsName, "RtIncAcc");
            String DefRecAcc = cmd.db.getString(rsName, "DefRecAcc");
            String ShowHelp = cmd.db.getString(rsName, "ShowHelp");

            cmd.global.texts.put(CFG.PFCFG_CAPITAL_ACCOUNT, CapAcc);
            cmd.global.texts.put(CFG.PFCFG_INCOME_ACCOUNT, IncAcc);
            cmd.global.texts.put(CFG.PFCFG_RETAIN_INCOME_ACCOUNT, RtIncAcc);
            cmd.global.texts.put(CFG.PFCFG_DEF_RECON_ACCOUNT, DefRecAcc);
            cmd.global.booleans.put(CFG.PFCFG_SHOWHELP, cmd.data.isTrue(ShowHelp));
        } catch (SQLException ex) {
            cmd.log.severe("PFAP0USR.error", ex);
            String msg = "PFAP0USR.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
        }
    }

    private void startMainForm() {
        UserFormInterface f;
        f = cmd.form.create(PFCFG.MainListType.getClass(MainAccLstTyp));
//        if ("A".equals(MainAccLstTyp)) {
//            f = cmd.form.create(PFCL2ACCY.class);
//        } else if ("B".equals(MainAccLstTyp)) {
//            f = cmd.form.create(PFCL0ACCY.class);
//        } else if ("Y".equals(MainAccLstTyp)) {
//            f = cmd.form.create(PFCL1ACCY.class);
//        } else if ("M".equals(MainAccLstTyp)) {
//            f = cmd.form.create(PFCL0ACCM.class);
//        } else {
//            f = cmd.form.create(PFCL2ACCY.class);
//        }
        if (f == null) {
            f = cmd.form.create(PFCFG.MainListType.A.formClass);
        }
        cmd.form.executeInNewThread(f);
    }

    @Override
    public String getFormTitle() {
        return "PFAP0USR.title";
    }
}
