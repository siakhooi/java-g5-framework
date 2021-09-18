/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.UOM;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class UOMH {

    static void createInsert(UserFormInterface form, String Whs, String Uom) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Whs, Uom, HisAct, HisRmk);
    }

    static void createDelete(UserFormInterface form, String Whs, String Uom) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Whs, Uom, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Whs, String Uom) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Whs, Uom, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Whs, String Uom, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "UOMH.I";

        String sql = "INSERT INTO ISUOMH (" +
                "Whs, HisSeq, HisDte, HisAct, HisRmk," +
                "Uom, Nme, Remark) " +
                "SELECT Whs, NEXT VALUE FOR ISUOMHSEQ, ?, ?, ?, " +
                "Uom, Nme, Remark FROM " +
                "ISUOM WHERE Whs=? AND Uom=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Uom);
        form.cmd().db.execUpdate(psName);
    }
}
