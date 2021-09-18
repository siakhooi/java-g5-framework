/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.G;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.db.ACCM.ACCM;
import com.mysoftwarehouse.pfa.db.ACCY.ACCY;
import com.mysoftwarehouse.pfa.db.CFG.CFG;
import com.mysoftwarehouse.pfa.db.TXN.TXN;
import com.mysoftwarehouse.pfa.db.TXN.TXNE.TXNE;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFGP0ACC extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(true);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("PFGP0ACC.Begin", 1);
        //addProcess("PFGP0ACC.CreateLogRecord", 1);
        addProcess("PFGP0ACC.UpdatePFCFG", 1);
        addProcess("PFGP0ACC.UpdatePFACC", 1);
        addProcess("PFGP0ACC.UpdatePFTXN", 1);
        addProcess("PFGP0ACC.UpdatePFTXNE", 1);
        addProcess("PFGP0ACC.UpdatePFACCY", 1);
        addProcess("PFGP0ACC.UpdatePFACCM", 1);
//        addProcess("PFGP0ACC.UpdatePFACCT", 1);
//        addProcess("PFGP0ACC.UpdatePFTXNT", 1);
        addProcess("PFGP0ACC.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        String FrmAcc = cmd.in.map.texts.get(ACC.FROMACC);
        String ToAcc = cmd.in.map.texts.get(ACC.TOACC);

        try {
            cmd.db.begin();
            super.completed();

            //ACCH.createRename(this, id, FrmAcc, ToAcc);
            //super.completed();
            CFG.update_AccountRename(this, id, FrmAcc, ToAcc);
            super.completed();
            ACC.update_AccountRename(this, id, FrmAcc, ToAcc);
            super.completed();

            TXN.update_AccountRename(this, id, FrmAcc, ToAcc);
            super.completed();
            TXNE.update_AccountRename(this, id, FrmAcc, ToAcc);
            super.completed();

            ACCY.update_AccountRename(this, id, FrmAcc, ToAcc);
            super.completed();
            ACCM.update_AccountRename(this, id, FrmAcc, ToAcc);
            super.completed();
//            ACCT.updateACCT_AccountRename(this, id, FrmAcc, ToAcc);
//            super.completed();
//            TXNT.updateTXNT_AccountRename(this, id, FrmAcc, ToAcc);
//            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex2) {
            }
            cmd.log.severe("PFGP0ACC.error", ex);
            String msg = "PFGP0ACC.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "PFGP0ACC.title";
    }
}
