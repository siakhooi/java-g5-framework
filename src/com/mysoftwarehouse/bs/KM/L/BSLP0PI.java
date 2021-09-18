/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.L;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PI.PI;
import com.mysoftwarehouse.bs.db.PI.PID;
import com.mysoftwarehouse.bs.db.PI.PIP;
import com.mysoftwarehouse.bs.db.PIV.PIVD;
import com.mysoftwarehouse.bs.db.POR.PORD;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSLP0PI extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSLP0PI.CheckBSPOR", 1);
        addProcess("BSLP0PI.CheckBSPIV", 1);
        addProcess("BSLP0PI.Begin", 1);

        addProcess("BSLP0PI.DeleteBSPI", 1);
        addProcess("BSLP0PI.DeleteBSPID", 1);
        addProcess("BSLP0PI.DeleteBSPIP", 1);
        addProcess("BSLP0PI.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String cmp = GET.Cmp(this);
        String itm = cmd.in.map.texts.get(MAP.BSPI.ITM);
        String title = "", message = "";

        try {
            if (PORD.useITM(this, cmp, itm)) {
                title = "BSLP0PI.error.ITMInPOR.title";
                message = "BSLP0PI.error.ITMInPOR.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, itm);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            if (PIVD.useITM(this, cmp, itm)) {
                title = "BSLP0PI.error.ITMInPIV.title";
                message = "BSLP0PI.error.ITMInPIV.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, itm);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();

            cmd.db.begin();
            super.completed();

            PI.delete(this, cmp, itm);
            super.completed();
            PID.delete(this, cmp, itm);
            super.completed();
            PIP.delete(this, cmp, itm);
            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("BSLP0PI.error", ex);
            String msg = "BSLP0PI.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "BSLP0PI.title";
    }
}
