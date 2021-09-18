/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.SUP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
class SUPH {

    static void createDelete(UserFormInterface form, String Cmp, String Sup) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Cmp, Sup, HisAct, HisRmk);
    }

    static void createInsert(UserFormInterface form, String Cmp, String Sup) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, Sup, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Cmp, String Sup) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, Sup, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Cmp, String Sup, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "SUPH.create";

        String sql = "INSERT INTO BSSUPH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk," +
                "Sup, Nme, Typ, RegNo, PriAdd, PriCnt, WebSte, Status, Remark) " +
                "SELECT Cmp, NEXT VALUE FOR BSSUPHSEQ, ?, ?, ?, " +
                "Sup, Nme, Typ, RegNo, PriAdd, PriCnt, WebSte, Status, Remark FROM " +
                "BSSUP WHERE Cmp=? AND Sup=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.execUpdate(psName);
    }
}
