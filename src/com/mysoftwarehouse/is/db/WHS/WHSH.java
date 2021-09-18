/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.WHS;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class WHSH {

    static void createInsert(UserFormInterface form, String Whs) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Whs, HisAct, HisRmk);
    }

    static void createDelete(UserFormInterface form, String Whs) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Whs, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Whs) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Whs, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Whs, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "WHSH.I";

        String sql = "INSERT INTO ISWHSH (" +
                "Whs, HisSeq, HisDte, HisAct, HisRmk," +
                "Nme, CstTyp, Remark) " +
                "SELECT Whs, NEXT VALUE FOR ISWHSHSEQ, ?, ?, ?, " +
                "Nme, CstTyp, Remark FROM " +
                "ISWHS WHERE Whs=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.execUpdate(psName);
    }
}
