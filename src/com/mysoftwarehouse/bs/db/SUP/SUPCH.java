/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.mysoftwarehouse.bs.db.SUP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
class SUPCH {
    static void createInsert(UserFormInterface form, String Cmp, String Sup, int seqCnt) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, Sup, seqCnt, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Cmp, String Sup, int seqCnt) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, Sup, seqCnt, HisAct, HisRmk);
    }
    private static void create(UserFormInterface form,
            String Cmp, String Sup, int Seq, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "SUPCH.create";
        
        String sql = "INSERT INTO BSSUPCH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk," +
                "Sup, Seq, FstNme, LstNme, Title, Tel, Fax, Mobile, " +
                "Email, Remark) " +
                "SELECT Cmp, NEXT VALUE FOR BSSUPCHSEQ, ?, ?, ?, " +
                "Sup, Seq, FstNme, LstNme, Title, Tel, Fax, Mobile, " +
                "Email, Remark FROM " +
                "BSSUPC WHERE Cmp=? AND Sup=? AND Seq=? ";
        
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, Sup);
        form.cmd().db.setParameter(psName, i++, Seq);
        form.cmd().db.execUpdate(psName);
    }
}
