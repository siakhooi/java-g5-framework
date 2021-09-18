/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CMP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBS0CMP;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class CMPCH {

    static void createInsert(BSBS0CMP form, String Cmp, int seqCnt) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, seqCnt, HisAct, HisRmk);
    }

    static void createUpdate(BSBS0CMP form, String Cmp, int seqCnt) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, seqCnt, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Cmp, int Seq, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "CMPCH.create";

        String sql = "INSERT INTO BSCMPCH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk," +
                "Seq, FstNme, LstNme, Title, Tel, Fax, Mobile, " +
                "Email, Remark) " +
                "SELECT Cmp, NEXT VALUE FOR BSCMPCHSEQ, ?, ?, ?, " +
                "Seq, FstNme, LstNme, Title, Tel, Fax, Mobile, " +
                "Email, Remark FROM " +
                "BSCMPC WHERE Cmp=? AND Seq=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Seq);
        form.cmd().db.execUpdate(psName);
    }
}
