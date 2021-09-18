/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.AU;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.db.ACCM.ACCM;
import com.mysoftwarehouse.pfa.db.ACCT.ACCT;
import com.mysoftwarehouse.pfa.db.ACCY.ACCY;
import com.mysoftwarehouse.pfa.db.CFG.CFG;
import com.mysoftwarehouse.pfa.db.TXN.TXN;
import com.mysoftwarehouse.pfa.db.TXN.TXNE.TXNE;
import com.mysoftwarehouse.pfa.db.TXNT.TXNT;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFAP1USR extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(true);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("PFAP1USR.Begin", 1);
        //addProcess("PFAP1USR.CreateLogRecord", 1);
        addProcess("PFAP1USR.DeletePFUSR", 1);
        addProcess("PFAP1USR.DeletePFCFG", 1);
        addProcess("PFAP1USR.DeletePFACC", 1);
        addProcess("PFAP1USR.DeletePFTXN", 1);
        addProcess("PFAP1USR.DeletePFTXNE", 1);
        addProcess("PFAP1USR.DeletePFACCY", 1);
        addProcess("PFAP1USR.DeletePFACCM", 1);
        addProcess("PFAP1USR.DeletePFACCT", 1);
        addProcess("PFAP1USR.DeletePFTXNT", 1);
        addProcess("PFAP1USR.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        try {
            cmd.db.begin();
            String id = cmd.in.map.texts.get(USR.PFUSR_ID);
            super.completed();
            //USRH.createDelete(this, id);
            //super.completed();
            USR.delete(this, id);
            super.completed();
            CFG.delete(this, id);
            super.completed();
            ACC.delete(this, id);
            super.completed();
            TXN.delete(this, id);
            super.completed();
            TXNE.delete(this, id);
            super.completed();
            ACCY.delete(this, id);
            super.completed();
            ACCM.delete(this, id);
            super.completed();
            ACCT.delete(this, id);
            super.completed();
            TXNT.delete(this, id);
            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            cmd.log.severe("PFAP1USR.error", ex);
            String msg = "PFAP1USR.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
            }
            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "PFAP1USR.title";
    }
}
