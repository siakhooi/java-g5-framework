/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SY;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
class SYH {

    static void createDelete(UserFormInterface form, String Cmp, String PayTyp) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Cmp, PayTyp, HisAct, HisRmk);
    }

    static void createInsert(UserFormInterface form, String Cmp, String PayTyp) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, PayTyp, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Cmp, String PayTyp) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, PayTyp, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Cmp, String PayTyp, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "SYH.create";

        String sql = "INSERT INTO BSSYH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk, " +
                "PayTyp, Nme) " +
                "SELECT Cmp, NEXT VALUE FOR BSSYHSEQ, ?, ?, ?, " +
                "PayTyp, Nme FROM " +
                "BSSY WHERE Cmp=? AND PayTyp=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PayTyp);
        form.cmd().db.execUpdate(psName);
    }
}
