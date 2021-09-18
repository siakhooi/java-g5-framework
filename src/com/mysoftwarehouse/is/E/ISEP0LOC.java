/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.E;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.ITM.ITMB;
import com.mysoftwarehouse.is.db.LOC.LOC;
import com.mysoftwarehouse.is.db.TXN.TXNA;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISEP0LOC extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("ISEP0LOC.CheckISTXNA", 1);
        addProcess("ISEP0LOC.CheckISITMB", 1);
        addProcess("ISEP0LOC.Begin", 1);

        addProcess("ISEP0LOC.DeleteISLOC", 1);
        addProcess("ISEP0LOC.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String Whs = GET.Whs(this);
        String Loc = cmd.in.map.texts.get(MAP.ISLOC.LOC);
        String title = "", message = "";

        try {
            if (TXNA.useLoc(this, Whs, Loc)) {
                title = "ISEP0LOC.error.LOCInTXNA.title";
                message = "ISEP0LOC.error.LOCInTXNA.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, Loc);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            if (ITMB.useLoc(this, Whs, Loc)) {
                title = "ISEP0LOC.error.LOCInITMB.title";
                message = "ISEP0LOC.error.LOCInITMB.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, Loc);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();

            cmd.db.begin();
            super.completed();

            LOC.delete(this, Whs, Loc);
            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("ISEP0LOC.error", ex);
            String msg = "ISEP0LOC.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "ISEP0LOC.title";
    }
}
