/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.K;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PIV.PIV;
import com.mysoftwarehouse.bs.db.POR.POR;
import com.mysoftwarehouse.bs.db.SUP.SUP;
import com.mysoftwarehouse.bs.db.SUP.SUPA;
import com.mysoftwarehouse.bs.db.SUP.SUPC;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSKP0SUP extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSKP0SUP.CheckBSPOR", 1);
        addProcess("BSKP0SUP.CheckBSPIV", 1);
        addProcess("BSKP0SUP.Begin", 1);
        addProcess("BSKP0SUP.DeleteBSSUP", 1);
        addProcess("BSKP0SUP.DeleteBSSUPA", 1);
        addProcess("BSKP0SUP.DeleteBSSUPC", 1);
        addProcess("BSKP0SUP.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String cmp = GET.Cmp(this);
        String sup = cmd.in.map.texts.get(MAP.BSSUP.SUP);
        String title = "", message = "";

        try {
            if (POR.useSUP(this, cmp, sup)) {
                title = "BSKP0SUP.error.SUPInPOR.title";
                message = "BSKP0SUP.error.SUPInPOR.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, sup);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            if (PIV.useSUP(this, cmp, sup)) {
                title = "BSKP0SUP.error.SUPInPIV.title";
                message = "BSKP0SUP.error.SUPInPIV.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, sup);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();

            cmd.db.begin();
            super.completed();

            SUP.delete(this, cmp, sup);
            super.completed();
            SUPA.delete(this, cmp, sup);
            super.completed();
            SUPC.delete(this, cmp, sup);
            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("BSKP0SUP.error", ex);
            String msg = "BSKP0SUP.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "BSKP0SUP.title";
    }
}
