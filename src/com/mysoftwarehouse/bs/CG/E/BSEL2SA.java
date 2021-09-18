/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.E;

import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.SA.SA;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSEL2SA extends BSEL1SA {

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSEL2SA";
            SA.select_All(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSEL2SA.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title,
                    "BSEL2SA.error.description");
        }
    }

    @Override
    public String getFormTitle() {
        return "BSEL2SA.title";
    }
}
