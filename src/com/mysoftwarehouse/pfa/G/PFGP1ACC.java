/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.G;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.db.ACCM.ACCM;
import com.mysoftwarehouse.pfa.db.ACCY.ACCY;
import com.mysoftwarehouse.pfa.db.CFG.CFG;
import com.mysoftwarehouse.pfa.db.TXN.TXN;
import com.mysoftwarehouse.pfa.db.TXN.TXN;
import com.mysoftwarehouse.pfa.db.TXN.TXN2;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFGP1ACC extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(true);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("PFGP1ACC.CheckPFCFG", 1);
        addProcess("PFGP1ACC.CheckPFTXN", 1);
        addProcess("PFGP1ACC.Begin", 1);

        //TODO: remove from lang
        //addProcess("PFGP1ACC.CreateLogRecord", 1);
        addProcess("PFGP1ACC.DeletePFTXN.Balance", 1);
        addProcess("PFGP1ACC.DeletePFACC", 1);
        addProcess("PFGP1ACC.DeletePFACCY", 1);
        addProcess("PFGP1ACC.DeletePFACCM", 1);
//        addProcess("PFGP1ACC.DeletePFACCT", 1);
//        addProcess("PFGP1ACC.DeletePFTXNT", 1);
        addProcess("PFGP1ACC.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        String acc = cmd.in.map.texts.get(ACC.PFACC_ACC);
        String title = "", message = "";

        try {
            if (CFG.useACC(this, id, acc)) {
                title = "PFGP1ACC.error.ACCInCFG.title";
                message = "PFGP1ACC.error.ACCInCFG.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, acc);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            if (TXN.useACC(this, id, acc)) {
                title = "PFGP1ACC.error.ACCInTXN.title";
                message = "PFGP1ACC.error.ACCInTXN.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, acc);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }

            cmd.db.begin();
            super.completed();

//            ACCH.createDelete(this, id, acc);
//            super.completed();
            TXN2.delete_AccountBalance(this, id, acc);
            super.completed();
            ACC.delete(this, id, acc);
            super.completed();
            ACCY.delete(this, id, acc);
            super.completed();
            ACCM.delete(this, id, acc);
            super.completed();
//            ACCT.deleteACCT(this, id, acc);
//            super.completed();
//            TXNT.deleteTXNT(this, id, acc);
//            super.completed();

            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("PFGP1ACC.error", ex);
            String msg = "PFGP1ACC.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            
            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "PFGP1ACC.title";
    }
}
