/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.ST;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
class STH {

    static void createInsert(UserFormInterface form, String Cmp, String Trm) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, Trm, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Cmp, String Trm) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, Trm, HisAct, HisRmk);
    }

    static void createDelete(UserFormInterface form, String Cmp, String Trm) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Cmp, Trm, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Cmp, String Trm, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "STH.create";

        String sql = "INSERT INTO BSSTH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk, " +
                "Trm, Nme, ForQtt, ForInv, ForRcp, Status) " +
                "SELECT Cmp, NEXT VALUE FOR BSSTHSEQ, ?, ?, ?, " +
                "Trm, Nme, ForQtt, ForInv, ForRcp, Status FROM " +
                "BSST WHERE Cmp=? AND Trm=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Trm);
        form.cmd().db.execUpdate(psName);
    }
}
