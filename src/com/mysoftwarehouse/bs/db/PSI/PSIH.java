/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PSI;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
class PSIH {

    static void createInsert(UserFormInterface form, String Cmp, String SpcInst) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, SpcInst, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Cmp, String SpcInst) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, SpcInst, HisAct, HisRmk);
    }

    static void createDelete(UserFormInterface form, String Cmp, String SpcInst) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Cmp, SpcInst, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Cmp, String SpcInst, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "PSIH.create";

        String sql = "INSERT INTO BSPSIH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk, " +
                "SpcInst, Nme, Status) " +
                "SELECT Cmp, NEXT VALUE FOR BSPSIHSEQ, ?, ?, ?, " +
                "SpcInst, Nme, Status FROM " +
                "BSPSI WHERE Cmp=? AND SpcInst=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, SpcInst);
        form.cmd().db.execUpdate(psName);
    }
}
