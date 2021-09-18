/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CMP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class CMPH {

    static void createInsert(UserFormInterface form, String Cmp) throws SQLException {
        String HisAct = "I";
        String HisRmk = "";
        create(form, Cmp, HisAct, HisRmk);
    }

    static void createDelete(UserFormInterface form, String Cmp) throws SQLException {
        String HisAct = "D";
        String HisRmk = "";
        create(form, Cmp, HisAct, HisRmk);
    }

    static void createUpdate(UserFormInterface form, String Cmp) throws SQLException {
        String HisAct = "U";
        String HisRmk = "";
        create(form, Cmp, HisAct, HisRmk);
    }

    private static void create(UserFormInterface form,
            String cmp, String HisAct, String HisRmk) throws SQLException {
        Calendar HisDte = form.cmd().cal.getNow();
        String psName = "CMPH.create";

        String sql = "INSERT INTO BSCMPH (" +
                "Cmp, HisSeq, HisDte, HisAct, HisRmk," +
                "Nme, RegNo, PriAdd, PriCnt, WebSte, CurCde) " +
                "SELECT Cmp, NEXT VALUE FOR BSCMPHSEQ, ?, ?, ?, " +
                "Nme, RegNo, PriAdd, PriCnt, WebSte, CurCde FROM " +
                "BSCMP WHERE Cmp=? ";

        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, HisDte);
        form.cmd().db.setParameter(psName, i++, HisAct);
        form.cmd().db.setParameter(psName, i++, HisRmk);
        form.cmd().db.setParameter(psName, i++, cmp);
        form.cmd().db.execUpdate(psName);
    }
}
