/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.USR;

import com.mysoftwarehouse.pfa.AU.PFAL0USR;
import com.mysoftwarehouse.pfa.AU.PFAP1USR;
import com.mysoftwarehouse.pfa.AU.PFAS0USR;
import com.mysoftwarehouse.pfa.db.CFG.CFG2;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class USR {

    public static String PFUSR_ID = "PFUSR_ID";

    public static void delete(PFAP1USR form, String id)
            throws SQLException {
        USRH.createDelete(form, id);

        String sql = "DELETE FROM PFUSR WHERE Id=? ";
        String psName = "USR.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(
            PFAS0USR form, String rsName,
            String id) throws SQLException {
        String sql = "SELECT * FROM PFUSR WHERE Id=?";
        String psName = "USR.S";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.execQuery(psName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void select_All(
            PFAL0USR form, String rsName) throws SQLException {
        String sql = "SELECT * FROM PFUSR ORDER BY Id";
        String psName = "USR.S2";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.execQuery(psName, rsName);
    }

    public static void insert(PFAS0USR form,
            String id,
            String FstNme, String LstNme, Calendar Dob,
            String Gender, String Career, String Ind, String MrtSts,
            String Address1, String Address2, String City,
            String ZipCode, String State, String Ctry) throws SQLException {
        String sql = "INSERT INTO PFUSR (Id, FstNme, LstNme, Email, " +
                "Dob, Gender, Career, Ind, MrtSts, Add1, Add2, " +
                "City, ZipCde, State, Ctry) VALUES(?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        String psName = "USR.I4";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, FstNme);
        form.cmd().db.setParameter(psName, i++, LstNme);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, Dob);
        form.cmd().db.setParameter(psName, i++, Gender);
        form.cmd().db.setParameter(psName, i++, Career);
        form.cmd().db.setParameter(psName, i++, Ind);
        form.cmd().db.setParameter(psName, i++, MrtSts);
        form.cmd().db.setParameter(psName, i++, Address1);
        form.cmd().db.setParameter(psName, i++, Address2);
        form.cmd().db.setParameter(psName, i++, City);
        form.cmd().db.setParameter(psName, i++, ZipCode);
        form.cmd().db.setParameter(psName, i++, State);
        form.cmd().db.setParameter(psName, i++, Ctry);
        form.cmd().db.execUpdate(psName);

        CFG2.insert(form, id);

        USRH.createInsert(form, id);
    }

    public static void update(PFAS0USR form,
            String id,
            String FstNme, String LstNme, Calendar Dob,
            String Gender, String Career, String Ind, String MrtSts,
            String Address1, String Address2, String City,
            String ZipCode, String State, String Ctry)
            throws SQLException {
        String sql = "UPDATE PFUSR SET FstNme=?, LstNme=?, Email=?, " +
                "Dob=?, Gender=?, Career=?, Ind=?, MrtSts=?, " +
                "Add1=?, Add2=?, City=?, ZipCde=?, State=?, Ctry=? " +
                "WHERE Id=? ";
        String psName = "USR.U3";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, FstNme);
        form.cmd().db.setParameter(psName, i++, LstNme);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, Dob);
        form.cmd().db.setParameter(psName, i++, Gender);
        form.cmd().db.setParameter(psName, i++, Career);
        form.cmd().db.setParameter(psName, i++, Ind);
        form.cmd().db.setParameter(psName, i++, MrtSts);
        form.cmd().db.setParameter(psName, i++, Address1);
        form.cmd().db.setParameter(psName, i++, Address2);
        form.cmd().db.setParameter(psName, i++, City);
        form.cmd().db.setParameter(psName, i++, ZipCode);
        form.cmd().db.setParameter(psName, i++, State);
        form.cmd().db.setParameter(psName, i++, Ctry);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.execUpdate(psName);
        USRH.createUpdate(form, id);
    }
}
