/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.F;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.TCD.TCD;
import com.mysoftwarehouse.is.db.TXN.TXN;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISFP0TCD extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("ISFP0TCD.CheckISTXN", 1);
        addProcess("ISFP0TCD.Begin", 1);

        addProcess("ISFP0TCD.DeleteISTCD", 1);
        addProcess("ISFP0TCD.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String Whs = GET.Whs(this);
        String Tcd = cmd.in.map.texts.get(MAP.ISTCD.TCD);
        String title = "", message = "";

        try {
            if (TXN.useTcd(this, Whs, Tcd)) {
                title = "ISFP0TCD.error.TCDInTXN.title";
                message = "ISFP0TCD.error.TCDInTXN.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, Tcd);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();

            cmd.db.begin();
            super.completed();

            TCD.delete(this, Whs, Tcd);
            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("ISFP0TCD.error", ex);
            String msg = "ISFP0TCD.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "ISFP0TCD.title";
    }
}
