/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CUS;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
class CUSH {

    static void createDelete(UserFormInterface form, String Cmp, String Cus) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Cmp, Cus, HisAct, HisRmk);
    }

    static void createInsert(UserFormInterface form, String Cmp, String Cus) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, Cus, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Cmp, String Cus) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, Cus, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Cmp, String Cus, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "CUSH.create";

        String sql = "INSERT INTO BSCUSH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk," +
                "Cus, Nme, Typ, RegNo, PriAdd, PriCnt, WebSte, Status, Remark) " +
                "SELECT Cmp, NEXT VALUE FOR BSCUSHSEQ, ?, ?, ?, " +
                "Cus, Nme, Typ, RegNo, PriAdd, PriCnt, WebSte, Status, Remark FROM " +
                "BSCUS WHERE Cmp=? AND Cus=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Cus);
        form.cmd().db.execUpdate(psName);
    }
}
