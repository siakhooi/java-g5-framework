/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SA;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
class SAH {

    static void createDelete(UserFormInterface form, String Cmp, String Adj) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Cmp, Adj, HisAct, HisRmk);
    }

    static void createInsert(UserFormInterface form, String Cmp, String Adj) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, Adj, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Cmp, String Adj) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, Adj, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Cmp, String Adj, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "SAH.create";

        String sql = "INSERT INTO BSSAH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk, " +
                "Adj, Nme, Typ, Prio, Amt, Dflt, Status) " +
                "SELECT Cmp, NEXT VALUE FOR BSSAHSEQ, ?, ?, ?, " +
                "Adj, Nme, Typ, Prio, Amt, Dflt, Status FROM " +
                "BSSA WHERE Cmp=? AND Adj=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Adj);
        form.cmd().db.execUpdate(psName);
    }
}
