/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.F;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.RCP.RCP;
import com.mysoftwarehouse.bs.db.SY.SY;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSFP0SY extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSFP0SY.CheckBSRCP", 1);
        addProcess("BSFP0SY.Begin", 1);
        addProcess("BSFP0SY.DeleteBSSY", 1);
        addProcess("BSFP0SY.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String cmp = GET.Cmp(this);
        String PayTyp = cmd.in.map.texts.get(MAP.BSSY.PAYTYP);
        String title = "", message = "";

        try {
            if (RCP.useSY(this, cmp, PayTyp)) {
                title = "BSFP0SY.error.SYInRCP.title";
                message = "BSFP0SY.error.SYInRCP.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, PayTyp);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();

            cmd.db.begin();
            super.completed();

            SY.delete(this, cmp, PayTyp);
            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("BSFP0SY.error", ex);
            String msg = "BSFP0SY.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "BSFP0SY.title";
    }
}
