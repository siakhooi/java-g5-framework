/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACC;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACCH {

    static void createDelete(UserFormInterface form,
            String id, String acc) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, id, acc, acc, HisAct, HisRmk);
    }

    static void createRename(UserFormInterface form,
            String id, String FrmAcc, String ToAcc) throws SQLException {
        String HisAct = "R";
        String HisRmk = "Rename to " + ToAcc;
        create(form, id, FrmAcc, FrmAcc, HisAct, HisRmk);
        HisAct = "R";
        HisRmk = "Rename from " + FrmAcc;
        create(form, id, FrmAcc, ToAcc, HisAct, HisRmk);
    }

    static void createInsert(UserFormInterface form,
            String id, String acc) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, id, acc, acc, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form,
            String id, String acc) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, id, acc, acc, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String id, String acc, String newAcc, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "ACCH.create";
        String sql = "INSERT INTO PFACCH (" +
                "Id, HisSeq, HisDte, HisAct, HisRmk," +
                "Acc, Nme, AccTyp, AccGrp, SysTyp, " +
                "BalAmt, BalDte, BalTxnId, Remark, Status, ShowInMain) " +
                "SELECT Id, NEXT VALUE FOR PFACCHSEQ, ?, ?, ?, " +
                "?, Nme, AccTyp, AccGrp, SysTyp, " +
                "BalAmt, BalDte, BalTxnId, Remark, Status, ShowInMain FROM " +
                "PFACC WHERE Id=? AND Acc=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, newAcc);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.execUpdate(psName);
    }
}
