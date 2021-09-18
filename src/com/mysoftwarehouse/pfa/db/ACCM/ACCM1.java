/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACCM;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.F.PFFJ0ACCM;
import com.mysoftwarehouse.pfa.db.CFG.CFG;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACCM1 {

    public static void prepare(
            UserFormInterface form, String id, int year, int month)
            throws SQLException {
        String psName = "ACCM1";
        String sql = "INSERT INTO PFACCM (" +
                "Id, Acc, Year, Month, TxnCnt, TtlDbt, TtlCrt, TtlAmt) " +
                "SELECT Id, Acc, ?, ?, " +
                "0, 0, 0, 0 " +
                "FROM PFACC " +
                "WHERE Id=? " +
                "AND Acc NOT IN (" +
                "SELECT Acc FROM PFACCM " +
                "WHERE Id=? AND Year=? AND Month=?)";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, year);
        form.cmd().db.setParameter(psName, i++, month);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, year);
        form.cmd().db.setParameter(psName, i++, month);
        form.cmd().db.execUpdate(psName);
    }

    static void prepare(
            UserFormInterface form,
            String id, String acc, int y, int m) throws SQLException {
        String psName = "ACCM1.2";
        String sql = "INSERT INTO PFACCM (" +
                "Id, Acc, Year, Month, TxnCnt, TtlDbt, TtlCrt, TtlAmt) " +
                "SELECT Id, Acc, ?, ?, 0, 0, 0, 0 " +
                "FROM PFACC " +
                "WHERE Id=? " +
                "AND Acc=? AND Acc NOT IN (" +
                "SELECT Acc FROM PFACCM " +
                "WHERE Id=? AND Acc=? AND Year=? AND Month=?)";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, m);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, m);
        form.cmd().db.execUpdate(psName);
    }

    public static void prepareIncomeAccountValue(
            PFFJ0ACCM form, String id, int y, int m) throws SQLException {
        String acc = form.cmd().global.texts.get(CFG.PFCFG_INCOME_ACCOUNT);
        String psName = "ACCM1.D2";
        String sql = "DELETE FROM PFACCM " +
                "WHERE Id=? " +
                "AND Acc=? " +
                "AND Year=? " +
                "AND Month=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, m);
        form.cmd().db.execUpdate(psName);

        psName = "ACCM1.I2";
        sql = "INSERT INTO PFACCM (Id, Acc, Year, Month, TxnCnt, TtlDbt, TtlCrt, TtlAmt)" +
                "SELECT ?, ?, ?, ?, 0, " +
                "COALESCE(Sum(TtlDbt), 0), " +
                "COALESCE(Sum(TtlCrt), 0), " +
                "COALESCE(Sum(TtlCrt)-Sum(TtlDbt), 0) " +
                "FROM PFACCM, PFACC " +
                "WHERE PFACCM.Id=? " +
                "AND PFACCM.Id=PFACC.Id " +
                "AND PFACCM.Acc=PFACC.Acc " +
                "AND PFACC.AccTyp IN ('I', 'E') " +
                "AND PFACCM.Year=? " +
                "AND PFACCM.Month=? ";
        form.cmd().db.setStatement(psName, sql);
        i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, m);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, m);
        i = form.cmd().db.execUpdate(psName);
        if (i > 1) {
            form.cmd().log.sysSevere("Error: More than 1 Income Record generated!");
        }
    }

    public static void prepareRetainedIncomeAccountValue(
            PFFJ0ACCM form, String id, int y, int m) throws SQLException {
        String acc = form.cmd().global.texts.get(CFG.PFCFG_RETAIN_INCOME_ACCOUNT);
        String psName = "ACCM1.D3";
        String sql = "DELETE FROM PFACCM " +
                "WHERE Id=? " +
                "AND Acc=? " +
                "AND Year=? " +
                "AND Month=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, m);
        form.cmd().db.execUpdate(psName);

        psName = "ACCM1.I3";
        sql = "INSERT INTO PFACCM (Id, Acc, Year, Month, TxnCnt, TtlDbt, TtlCrt, TtlAmt)" +
                "SELECT ?, ?, ?, ?, 0, " +
                "COALESCE(Sum(TtlDbt), 0), " +
                "COALESCE(Sum(TtlCrt), 0), " +
                "COALESCE(Sum(TtlCrt)-Sum(TtlDbt), 0) " +
                "FROM PFACCM, PFACC " +
                "WHERE PFACCM.Id=? " +
                "AND PFACCM.Id=PFACC.Id " +
                "AND PFACCM.Acc=PFACC.Acc " +
                "AND PFACC.AccTyp IN ('I', 'E') " +
                "AND (PFACCM.Year<? OR (PFACCM.Year=? AND PFACCM.Month<?)) ";
        form.cmd().db.setStatement(psName, sql);
        i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, m);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, m);
        i = form.cmd().db.execUpdate(psName);
        if (i > 1) {
            form.cmd().log.sysSevere("Error: More than 1 Income Record generated!");
        }
    }
}
