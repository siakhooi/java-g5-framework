/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.CFG;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class CFGH {

    static void createDelete(UserFormInterface form, String id) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, id, HisAct, HisRmk);
    }

    static void createInsert(UserFormInterface form, String id) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, id, HisAct, HisRmk);
    }

    static void createRename(UserFormInterface form, String id) throws SQLException {
        String HisAct = "R";
        String HisRmk = "";
        create(form, id, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String id) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, id, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String id, String HisAct, String HisRmk)
            throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "CFGH.create";
        String sql = "INSERT INTO PFCFGH (" +
                "Id, HisSeq, HisDte, HisAct, HisRmk," +
                "MainAccLstTyp, CapAcc, IncAcc, RtIncAcc, DefRecAcc, ShowHelp) " +
                "SELECT Id, NEXT VALUE FOR PFCFGHSEQ, " +
                "?, ?, ?, " +
                "MainAccLstTyp, CapAcc, " +
                "IncAcc, RtIncAcc, DefRecAcc, ShowHelp FROM " +
                "PFCFG WHERE Id=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.execUpdate(psName);
    }
}
