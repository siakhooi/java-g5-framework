/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACCM;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACCM2 {

    public static void adjust(
            UserFormInterface form, String id, String acc,
            BigDecimal amt, BigDecimal dbt, BigDecimal crt,
            int txnCntAdjustment, int y, int m) throws SQLException {
        ACCM1.prepare(form, id, acc, y, m);
        String psName = "ACCM.A1";
        String sql = "UPDATE PFACCM SET " +
                "TxnCnt=TxnCnt+?, " +
                "TtlDbt=TtlDbt+?, " +
                "TtlCrt=TtlCrt+?, " +
                "TtlAmt=TtlAmt+? WHERE " +
                "Id=? AND Acc=? AND Year=? AND Month=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, txnCntAdjustment);
        form.cmd().db.setParameter(psName, i++, dbt);
        form.cmd().db.setParameter(psName, i++, crt);
        form.cmd().db.setParameter(psName, i++, amt);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, m);
        form.cmd().db.execUpdate(psName);
    }

//    private static void check(UserFormInterface form, String id,
//            String acc, int y, int m) throws SQLException {
//        String psName = "ACCM_check";
//        String sql = "INSERT INTO PFACCM (" +
//                "Id, Acc, Year, Month, TxnCnt, TtlDbt, TtlCrt, TtlAmt) " +
//                "SELECT Id, Acc, ?, ?, " +
//                "0, 0, 0, 0 " +
//                "FROM PFACC WHERE Id=? AND Acc=? AND Acc NOT IN (" +
//                "SELECT Acc FROM PFACCM WHERE ID=? AND Year=? AND Month=?)";
//        form.cmd().db.setStatement(psName, sql);
//        int i = 1;
//        form.cmd().db.setParameter(psName, i++, y);
//        form.cmd().db.setParameter(psName, i++, m);
//        form.cmd().db.setParameter(psName, i++, id);
//        form.cmd().db.setParameter(psName, i++, acc);
//        form.cmd().db.setParameter(psName, i++, id);
//        form.cmd().db.setParameter(psName, i++, y);
//        form.cmd().db.setParameter(psName, i++, m);
//        form.cmd().db.execUpdate(psName);
//    }
}
