/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CMP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.B.BSBE0CMPS;
import com.mysoftwarehouse.bs.B.BSBP2CMP;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class CMPSH {

    static void createDelete(BSBP2CMP form, String Cmp) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Cmp, HisAct, HisRmk);
    }

    static void createInsert(UserFormInterface form, String Cmp) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, HisAct, HisRmk);
    }

    static void createUpdate(BSBE0CMPS form, String Cmp) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String Cmp, String HisAct, String HisRmk)
            throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "CMPSH.create";
        String sql = "INSERT INTO BSCMPSH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk," +
                "QttSign1, QttSign2, InvSign1, InvSign2, RcpSign1, RcpSign2, " +
                "PorSign1, PorSign2, PivSign1, PivSign2) " +
                "SELECT Cmp, NEXT VALUE FOR BSCMPSHSEQ, " +
                "?, ?, ?, " +
                "QttSign1, QttSign2, InvSign1, InvSign2, RcpSign1, RcpSign2, " +
                "PorSign1, PorSign2, PivSign1, PivSign2 FROM " +
                "BSCMPS WHERE Cmp=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.execUpdate(psName);
    }
}
