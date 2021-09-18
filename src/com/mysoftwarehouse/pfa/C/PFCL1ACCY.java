/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.C;

import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.mysoftwarehouse.pfa.db.ACC.ACC_CL1ACCY;
import com.mysoftwarehouse.pfa.db.ACCY.ACCY1;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFCL1ACCY extends PFCL_ACC {

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        String rsName = "PFCL1ACCY";
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        int year = cmd.cal.getYear();

        try {
            cmd.db.smartStart();
            ACCY1.prepare(this, id, year);
            cmd.db.smartComplete();
            ACC_CL1ACCY.select(this, rsName, id, year);
            super.useSQL(rsName);
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex2) {
            }
            cmd.log.severe("PFCL1ACCY.error", ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    "PFCL1ACCY.error",
                    "PFCL1ACCY.error.description");
        }
    }

    @Override
    public String getFormTitle() {
        return "PFCL1ACCY.title";
    }
}
