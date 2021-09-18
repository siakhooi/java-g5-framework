/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.INV;

import com.mysoftwarehouse.bs.HJ.I.BSIS0INV;
import com.mysoftwarehouse.bs.data.SaEnum;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class INVJ_load {

    public static void loadDefaultAdjustment(BSIS0INV form, String Cmp, String InvNo) throws SQLException {
        String sql = "INSERT INTO BSINVJ (" +
                "Cmp, InvNo, Seq, Adj, Nme, Typ, Prio, Amt, EffAmt, OpenAmt, NewAmt) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String psName = "INVJ_load.I";
        form.cmd().db.setStatement(psName, sql);

        String sql2 = "SELECT * FROM BSSA WHERE Cmp=? AND Dflt=? AND Status=? ";
        String psName2 = "INVJ_load.S1";
        String rsName2 = "INVJ_load.S1";
        form.cmd().db.setStatement(psName2, sql2);
        int i = 1;
        form.cmd().db.setParameter(psName2, i++, Cmp);
        form.cmd().db.setParameter(psName2, i++, "Y");
        form.cmd().db.setParameter(psName2, i++, SaEnum.Status.A.typ);
        form.cmd().db.execQuery(psName2, rsName2);
        int seq = 0;
        while (form.cmd().db.next(rsName2)) {
            String Adj = form.cmd().db.getString(rsName2, "Adj");
            String Nme = form.cmd().db.getString(rsName2, "Nme");
            String Typ = form.cmd().db.getString(rsName2, "Typ");
            int Prio = form.cmd().db.getInteger(rsName2, "Prio");
            BigDecimal Amt = form.cmd().db.getBigDecimal(rsName2, "Amt");

            i = 1;
            form.cmd().db.setParameter(psName, i++, Cmp);
            form.cmd().db.setParameter(psName, i++, InvNo);
            form.cmd().db.setParameter(psName, i++, seq++);
            form.cmd().db.setParameter(psName, i++, Adj);
            form.cmd().db.setParameter(psName, i++, Nme);
            form.cmd().db.setParameter(psName, i++, Typ);
            form.cmd().db.setParameter(psName, i++, Prio);
            form.cmd().db.setParameter(psName, i++, Amt);
            form.cmd().db.setParameter(psName, i++, BigDecimal.ZERO);
            form.cmd().db.setParameter(psName, i++, BigDecimal.ZERO);
            form.cmd().db.setParameter(psName, i++, BigDecimal.ZERO);
            form.cmd().db.execUpdate(psName);
        }
    }
}
