/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.M;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PY.PY;
import com.mysoftwarehouse.bs.db.PIV.PIV;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSMP0PY extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSMP0PY.CheckBSPIV", 1);
        addProcess("BSMP0PY.Begin", 1);
        addProcess("BSMP0PY.DeleteBSPY", 1);
        addProcess("BSMP0PY.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        String cmp = GET.Cmp(this);
        String PayTyp = cmd.in.map.texts.get(MAP.BSPY.PAYTYP);
        String title = "", message = "";

        try {
            if (PIV.usePY(this, cmp, PayTyp)) {
                title = "BSMP0PY.error.PYInPIV.title";
                message = "BSMP0PY.error.PYInPIV.message.{0}";
                title = cmd.lang.getString(title);
                message = cmd.lang.getString(message, PayTyp);
                cmd.common.showMessage(DialogMessageType.ERROR, title, message);
                super.cancelNow();
            }
            super.completed();
            cmd.db.begin();
            super.completed();

            PY.delete(this, cmp, PayTyp);
            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("BSMP0PY.error", ex);
            String msg = "BSMP0PY.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "BSMP0PY.title";
    }
}
