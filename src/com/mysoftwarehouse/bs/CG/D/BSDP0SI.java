/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.D;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.INV.INVD;
import com.mysoftwarehouse.bs.db.QTT.QTTD;
import com.mysoftwarehouse.bs.db.RCP.RCPD;
import com.mysoftwarehouse.bs.db.SI.SI;
import com.mysoftwarehouse.bs.db.SI.SID;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSDP0SI extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSDP0SI.CheckBSQTT", 1);
        addProcess("BSDP0SI.CheckBSINV", 1);
        addProcess("BSDP0SI.CheckBSRCP", 1);
        addProcess("BSDP0SI.Begin", 1);

        addProcess("BSDP0SI.DeleteBSSI", 1);
        addProcess("BSDP0SI.DeleteBSSID", 1);
        addProcess("BSDP0SI.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String cmp = GET.Cmp(this);
        String itm = cmd.in.map.texts.get(MAP.BSSI.ITM);
        String title = "", message = "";

        try {
            if (QTTD.useITM(this, cmp, itm)) {
                title = "BSDP0SI.error.ITMInQTT.title";
                message = "BSDP0SI.error.ITMInQTT.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, itm);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            if (INVD.useITM(this, cmp, itm)) {
                title = "BSDP0SI.error.ITMInINV.title";
                message = "BSDP0SI.error.ITMInINV.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, itm);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            if (RCPD.useITM(this, cmp, itm)) {
                title = "BSDP0SI.error.ITMInRCP.title";
                message = "BSDP0SI.error.ITMInRCP.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, itm);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            
            cmd.db.begin();
            super.completed();

            SI.delete(this, cmp, itm);
            super.completed();
            SID.delete(this, cmp, itm);
            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("BSDP0SI.error", ex);
            String msg = "BSDP0SI.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            
            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "BSDP0SI.title";
    }
}
