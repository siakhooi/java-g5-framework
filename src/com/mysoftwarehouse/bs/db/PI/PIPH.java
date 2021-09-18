/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PI;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.KM.L.BSLP0PI;
import com.mysoftwarehouse.bs.KM.L.BSLS0PIP;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
class PIPH {

    static void createDelete(UserFormInterface form, String Cmp, String Itm, String Sup) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Cmp, Itm, Sup, HisAct, HisRmk);
    }

    static void createInsert(UserFormInterface form, String Cmp, String Itm, String Sup) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, Itm, Sup, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Cmp, String Itm, String Sup) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, Itm, Sup, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Cmp, String Itm, String Sup, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "PIPH.create";

        String sql = "INSERT INTO BSPIPH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk, " +
                "Itm, Sup, Price, Status, Remark) " +
                "SELECT Cmp, NEXT VALUE FOR BSPIPHSEQ, ?, ?, ?, " +
                "Itm, Sup, Price, Status, Remark FROM " +
                "BSPIP WHERE Cmp=? AND Itm=? AND Sup=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.execUpdate(psName);
    }
}
