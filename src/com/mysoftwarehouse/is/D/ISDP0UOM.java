/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.D;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.ITM.ITM;
import com.mysoftwarehouse.is.db.TXN.TXN;
import com.mysoftwarehouse.is.db.UOM.UOM;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISDP0UOM extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("ISDP0UOM.CheckISITM", 1);
        addProcess("ISDP0UOM.Begin", 1);

        addProcess("ISDP0UOM.DeleteISUOM", 1);
        addProcess("ISDP0UOM.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String Whs = GET.Whs(this);
        String Uom = cmd.in.map.texts.get(MAP.ISUOM.UOM);
        String title = "", message = "";

        try {
            if (ITM.useUOM(this, Whs, Uom)) {
                title = "ISDP0UOM.error.UOMInITM.title";
                message = "ISDP0UOM.error.UOMInITM.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, Uom);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();

            cmd.db.begin();
            super.completed();

            UOM.delete(this, Whs, Uom);
            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("ISDP0UOM.error", ex);
            String msg = "ISDP0UOM.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "ISDP0UOM.title";
    }
}
