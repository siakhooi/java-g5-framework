/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.C;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.CUS.CUS;
import com.mysoftwarehouse.bs.db.CUS.CUSA;
import com.mysoftwarehouse.bs.db.CUS.CUSC;
import com.mysoftwarehouse.bs.db.INV.INV;
import com.mysoftwarehouse.bs.db.QTT.QTT;
import com.mysoftwarehouse.bs.db.RCP.RCP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSCP0CUS extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSCP0CUS.CheckBSQTT", 1);
        addProcess("BSCP0CUS.CheckBSINV", 1);
        addProcess("BSCP0CUS.CheckBSRCP", 1);
        addProcess("BSCP0CUS.Begin", 1);
        addProcess("BSCP0CUS.DeleteBSCUS", 1);
        addProcess("BSCP0CUS.DeleteBSCUSA", 1);
        addProcess("BSCP0CUS.DeleteBSCUSC", 1);
        addProcess("BSCP0CUS.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String cmp = GET.Cmp(this);
        String cus = cmd.in.map.texts.get(MAP.BSCUS.CUS);
        String title = "", message = "";

        try {
            if (QTT.useCUS(this, cmp, cus)) {
                title = "BSCP0CUS.error.CUSInQTT.title";
                message = "BSCP0CUS.error.CUSInQTT.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, cus);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            if (INV.useCUS(this, cmp, cus)) {
                title = "BSCP0CUS.error.CUSInINV.title";
                message = "BSCP0CUS.error.CUSInINV.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, cus);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            if (RCP.useCUS(this, cmp, cus)) {
                title = "BSCP0CUS.error.CUSInRCP.title";
                message = "BSCP0CUS.error.CUSInRCP.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, cus);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            
            cmd.db.begin();
            super.completed();

            CUS.delete(this, cmp, cus);
            super.completed();
            CUSA.delete(this, cmp, cus);
            super.completed();
            CUSC.delete(this, cmp, cus);
            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("BSCP0CUS.error", ex);
            String msg = "BSCP0CUS.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            
            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "BSCP0CUS.title";
    }
}
