/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.C;

import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.mysoftwarehouse.pfa.db.ACC.ACC_CL3ACCY;
import com.mysoftwarehouse.pfa.db.ACCY.ACCY1;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFCL3ACCY extends PFCL_ACC {

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        String rsName = "PFCL3ACCY";
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        int year = cmd.cal.getYear();

        try {
            cmd.db.begin();
            ACCY1.prepare(this, id, year);
            cmd.db.commit();
            //ACC2.selectACC_PFCL3ACCY(this, rsName, id, year);
            ACC_CL3ACCY.select(this, rsName, id, year);
            super.useSQL(rsName);
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex2) {
            }
            cmd.log.severe("PFCL3ACCY.error", ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    "PFCL3ACCY.error",
                    "PFCL3ACCY.error.description");
        }
    }

    @Override
    public String getFormTitle() {
        return "PFCL3ACCY.title";
    }
}
