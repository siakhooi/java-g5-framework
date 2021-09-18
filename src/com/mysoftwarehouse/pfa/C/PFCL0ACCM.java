/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.C;

import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.mysoftwarehouse.pfa.db.ACC.ACC_CL0ACCM;
import com.mysoftwarehouse.pfa.db.ACCM.ACCM1;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFCL0ACCM extends PFCL_ACC {

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        String rsName = "PFCL0ACCM";
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        int year = cmd.cal.getYear();
        int month = cmd.cal.getMonth();
        try {
            cmd.db.smartStart();
            ACCM1.prepare(this, id, year, month);
            cmd.db.smartComplete();
            ACC_CL0ACCM.select(this, rsName, id, year, month);
            super.useSQL(rsName);
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex2) {
            }
            cmd.log.severe("PFCL0ACCM.error", ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    "PFCL0ACCM.error",
                    "PFCL0ACCM.error.description");
        }
    }

    @Override
    public String getFormTitle() {
        return "PFCL0ACCM.title";
    }
}
