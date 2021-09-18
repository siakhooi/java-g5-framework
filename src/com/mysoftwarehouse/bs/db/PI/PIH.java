/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PI;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
class PIH {

    static void createDelete(UserFormInterface form, String Cmp, String Itm) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Cmp, Itm, HisAct, HisRmk);
    }

    static void createInsert(UserFormInterface form, String Cmp, String Itm) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, Itm, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Cmp, String Itm) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, Itm, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Cmp, String Itm, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "PIH.create";

        String sql = "INSERT INTO BSPIH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk, " +
                "Itm, Nme, Status) " +
                "SELECT Cmp, NEXT VALUE FOR BSPIHSEQ, ?, ?, ?, " +
                "Itm, Nme, Status FROM " +
                "BSPI WHERE Cmp=? AND Itm=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }
}
