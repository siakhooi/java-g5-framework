/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.TCD;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class TCDH {

    static void createInsert(UserFormInterface form, String Whs, String Tcd) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Whs, Tcd, HisAct, HisRmk);
    }

    static void createDelete(UserFormInterface form, String Whs, String Tcd) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Whs, Tcd, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Whs, String Tcd) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Whs, Tcd, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Whs, String Tcd, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "TCDH.I";

        String sql = "INSERT INTO ISTCDH (" +
                "Whs, HisSeq, HisDte, HisAct, HisRmk," +
                "Tcd, Typ, Nme, Remark) " +
                "SELECT Whs, NEXT VALUE FOR ISTCDHSEQ, ?, ?, ?, " +
                "Tcd, Typ, Nme, Remark FROM " +
                "ISTCD WHERE Whs=? AND Tcd=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Tcd);
        form.cmd().db.execUpdate(psName);
    }
}
