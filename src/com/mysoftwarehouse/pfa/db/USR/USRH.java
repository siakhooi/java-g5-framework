/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.USR;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class USRH {

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

    static void createUpdate(UserFormInterface form, String id) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, id, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String id, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "USRH.create";
        String sql = "INSERT INTO PFUSRH (" +
                "Id, HisSeq, HisDte, HisAct, HisRmk," +
                "FstNme, LstNme, Email, Dob, Gender, Career, " +
                "Ind, MrtSts, Add1, Add2, " +
                "City, ZipCde, State, Ctry) " +
                "SELECT Id, NEXT VALUE FOR PFUSRHSEQ, ?, ?, ?, " +
                "FstNme, LstNme, Email, Dob, Gender, Career, " +
                "Ind, MrtSts, Add1, Add2, " +
                "City, ZipCde, State, Ctry FROM " +
                "PFUSR WHERE Id=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.execUpdate(psName);
    }
}
