/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.ITM;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class ITMH {

    static void createInsert(UserFormInterface form, String Whs, String Itm) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Whs, Itm, HisAct, HisRmk);
    }

    static void createDelete(UserFormInterface form, String Whs, String Itm) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Whs, Itm, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Whs, String Itm) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Whs, Itm, HisAct, HisRmk);
    }
    static void createUpdate(UserFormInterface form, String Whs, String Itm, String HisRmk) throws SQLException {
        String HisAct = "U";
        create(form, Whs, Itm, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Whs, String Itm, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "ITMH.I";

        String sql = "INSERT INTO ISITMH (" +
                "Whs, HisSeq, HisDte, HisAct, HisRmk," +
                "Itm, Nme, Uom, Qty, SsQty, StdCst, WacCst, Remark) " +
                "SELECT Whs, NEXT VALUE FOR ISITMHSEQ, ?, ?, ?, " +
                "Itm, Nme, Uom, Qty, SsQty, StdCst, WacCst, Remark FROM " +
                "ISITM WHERE Whs=? AND Itm=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Whs);
        form.cmd().db.setParameter(psName, i++, Itm);
        form.cmd().db.execUpdate(psName);
    }
}
