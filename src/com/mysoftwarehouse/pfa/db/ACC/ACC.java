/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACC;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.AU.PFAP1USR;
import com.mysoftwarehouse.pfa.G.PFGP0ACC;
import com.mysoftwarehouse.pfa.G.PFGP1ACC;
import com.mysoftwarehouse.pfa.G.PFGP2ACC;
import com.mysoftwarehouse.pfa.conf.DBVALUE;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACC {

    public static String PFACC_ACC = "PFACC_ACC";
    public static String PFACC_ACCNAME = "PFACC_ACCNAME";
    public static String PFACC_ACCTYP = "PFACC_ACCTYP";
    //
    public static String RPTACCTYP = "RPTACCTYP";
    public static String FROMACC = "FROMACC";
    public static String TOACC = "TOACC";
    public static String FROMDATE = "FROMDATE";
    public static String TODATE = "TODATE";
    public static String PERIOD = "PERIOD";
    public static String RPTTYP = "RPTTYP";
    public static String RPTYEAR = "RPTYEAR";
    public static String RPTYEARMONTH = "RPTYEARMONTH";

    public static void delete(PFAP1USR form, String id) throws SQLException {
        String sql = "DELETE FROM PFACC WHERE Id=? ";
        String psName = "ACC.D1";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(
            PFGP1ACC form, String id, String acc) throws SQLException {
        ACCH.createDelete(form, id, acc);
        String sql = "DELETE FROM PFACC WHERE Id=? AND Acc=? ";
        String psName = "ACC.D2";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.setParameter(psName, 2, acc);
        form.cmd().db.execUpdate(psName);
    }

    public static String generateSampleAccountCode(
            PFGP2ACC form,
            String id, String accountCode)
            throws SQLException, Exception {
        String Separator = DBVALUE.SAMPLE_ACCOUNT_SEPARATOR;
        int maxNumber = DBVALUE.SAMPLE_ACCOUNT_MAX_TRY;
        String rsName = "SAMPLE";
        for (int i = 0; i < maxNumber; i++) {
            String newAcc = accountCode + Separator + i;
            if (!select(form, rsName, id, newAcc)) {
                return newAcc;
            }
        }
        throw new Exception("No Sample Account Code Generated!");
    }

    public static boolean select(UserFormInterface form,
            String rsName, String id, String acc)
            throws SQLException {
        String sql = "SELECT * FROM PFACC WHERE Id=? AND Acc=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, id);
        form.cmd().db.setParameter(rsName, 2, acc);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static boolean hasACC(UserFormInterface form,
            String id, String acc) throws SQLException {
        String rsName = "ACC.H";
        return select(form, rsName, id, acc);
    }

    public static void update_AccountRename(
            PFGP0ACC form,
            String id, String FrmAcc, String ToAcc)
            throws SQLException {
        ACCH.createRename(form, id, FrmAcc, ToAcc);
        String psName = "ACC.U3";
        String sql = "UPDATE PFACC SET Acc=? WHERE Id=? AND Acc=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToAcc);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, FrmAcc);
        form.cmd().db.execUpdate(psName);
    }
}
