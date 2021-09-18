/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.A.BSAM0;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.CFG.CFG;
import com.mysoftwarehouse.bs.db.CMP.CMP;
import com.mysoftwarehouse.bs.db.CMP.CMPS;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBP0CMP extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSBP0CMP.LoadConfiguration", 1);
        addProcess("BSBP0CMP.StartMainMenu", 1);
    }

    @Override
    public void run() throws ProcessException {
        loadConfiguration();
        super.completed();
        startMainMenu();
        super.completed();
    }

    private void loadConfiguration() throws ProcessException {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSBP0CMP.CFG";
            CFG.select(this, rsName, Cmp);

            String QttForm = cmd.db.getString(rsName, "PrtFmtQtt");
            cmd.global.texts.put(MAP.BSCFG.QTTFORMAT, QttForm);

            String InvForm = cmd.db.getString(rsName, "PrtFmtInv");
            cmd.global.texts.put(MAP.BSCFG.INVFORMAT, InvForm);

            String RcpForm = cmd.db.getString(rsName, "PrtFmtRcp");
            cmd.global.texts.put(MAP.BSCFG.RCPFORMAT, RcpForm);

            String PorForm = cmd.db.getString(rsName, "PrtFmtPor");
            cmd.global.texts.put(MAP.BSCFG.PORFORMAT, PorForm);

            String PivForm = cmd.db.getString(rsName, "PrtFmtPiv");
            cmd.global.texts.put(MAP.BSCFG.PIVFORMAT, PivForm);

            rsName = "BSBP0CMP.CMP";
            CMP.select(this, rsName, Cmp);
            String curCde = cmd.db.getString(rsName, "CurCde");
            cmd.global.texts.put(MAP.BSCMP.CURCDE, curCde);

            rsName = "BSBP0CMP.CMPS";
            CMPS.select(this, rsName, Cmp);
            String QttSign1 = cmd.db.getString(rsName, "QttSign1");
            cmd.global.texts.put(MAP.BSCMPS.QTTSIGN1, QttSign1);
            String QttSign2 = cmd.db.getString(rsName, "QttSign2");
            cmd.global.texts.put(MAP.BSCMPS.QTTSIGN2, QttSign2);

            String InvSign1 = cmd.db.getString(rsName, "InvSign1");
            cmd.global.texts.put(MAP.BSCMPS.INVSIGN1, InvSign1);
            String InvSign2 = cmd.db.getString(rsName, "InvSign2");
            cmd.global.texts.put(MAP.BSCMPS.INVSIGN2, InvSign2);

            String RcpSign1 = cmd.db.getString(rsName, "RcpSign1");
            cmd.global.texts.put(MAP.BSCMPS.RCPSIGN1, RcpSign1);
            String RcpSign2 = cmd.db.getString(rsName, "RcpSign2");
            cmd.global.texts.put(MAP.BSCMPS.RCPSIGN2, RcpSign2);

            String PorSign1 = cmd.db.getString(rsName, "PorSign1");
            cmd.global.texts.put(MAP.BSCMPS.PORSIGN1, PorSign1);
            String PorSign2 = cmd.db.getString(rsName, "PorSign2");
            cmd.global.texts.put(MAP.BSCMPS.PORSIGN2, PorSign2);

            String PivSign1 = cmd.db.getString(rsName, "PivSign1");
            cmd.global.texts.put(MAP.BSCMPS.PIVSIGN1, PivSign1);
            String PivSign2 = cmd.db.getString(rsName, "PivSign2");
            cmd.global.texts.put(MAP.BSCMPS.PIVSIGN2, PivSign2);

        } catch (SQLException ex) {
            cmd.log.severe("BSBP0CMP.error", ex);
            String msg = "BSBP0CMP.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
        }
    }

    private void startMainMenu() {
        UserFormInterface f;
        f = cmd.form.create(BSAM0.class);
        cmd.form.executeInNewThread(f);
    }

    @Override
    public String getFormTitle() {
        return "BSBP0CMP.title";
    }
}
