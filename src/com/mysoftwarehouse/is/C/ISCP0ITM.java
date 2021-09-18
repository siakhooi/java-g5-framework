/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.C;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.ITM.ITM;
import com.mysoftwarehouse.is.db.ITM.ITMB;
import com.mysoftwarehouse.is.db.TXN.TXNA;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISCP0ITM extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("ISCP0ITM.CheckISTXNA", 1);
        addProcess("ISCP0ITM.CheckISITM", 1);
        addProcess("ISCP0ITM.CheckISITMB", 1);
        addProcess("ISCP0ITM.Begin", 1);

        addProcess("ISCP0ITM.DeleteISITM", 1);
        //addProcess("ISCP0ITM.DeleteISITMA", 1);
        addProcess("ISCP0ITM.DeleteISITMB", 1);
        addProcess("ISCP0ITM.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String Whs = GET.Whs(this);
        String Itm = cmd.in.map.texts.get(MAP.ISITM.ITM);
        String title = "", message = "";

        try {
            if (TXNA.useITM(this, Whs, Itm)) {
                title = "ISCP0ITM.error.ITMInTXNA.title";
                message = "ISCP0ITM.error.ITMInTXNA.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, Itm);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            if (ITM.hasQty(this, Whs, Itm)) {
                title = "ISCP0ITM.error.ITMQtyNotZero.title";
                message = "ISCP0ITM.error.ITMQtyNotZero.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, Itm);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            if (ITMB.hasQty(this, Whs, Itm)) {
                title = "ISCP0ITM.error.ITMLocQtyNotZero.title";
                message = "ISCP0ITM.error.ITMLocQtyNotZero.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, Itm);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();

            cmd.db.begin();
            super.completed();

            ITM.delete(this, Whs, Itm);
            super.completed();
//            ITMA.delete(this, Whs, Itm);
//            super.completed();
            ITMB.delete(this, Whs, Itm);
            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("ISCP0ITM.error", ex);
            String msg = "ISCP0ITM.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "ISCP0ITM.title";
    }
}
