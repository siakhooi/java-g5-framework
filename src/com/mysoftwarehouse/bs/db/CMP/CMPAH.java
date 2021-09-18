/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CMP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import com.mysoftwarehouse.bs.B.BSBS0CMP;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class CMPAH {

    static void createInsert(BSBS0CMP form, String Cmp, int seqAddr) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, seqAddr, HisAct, HisRmk);
    }

    static void createUpdate(BSBS0CMP form, String Cmp, int seqAddr) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, seqAddr, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Cmp, int Seq, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "CMPAH.create";

        String sql = "INSERT INTO BSCMPAH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk," +
                "Seq, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, " +
                "Fax, Remark) " +
                "SELECT Cmp, NEXT VALUE FOR BSCMPAHSEQ, ?, ?, ?, " +
                "Seq, Add1, Add2, City, ZipCde, Stte, Ctry, Tel, " +
                "Fax, Remark FROM " +
                "BSCMPA WHERE Cmp=? AND Seq=? ";

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
