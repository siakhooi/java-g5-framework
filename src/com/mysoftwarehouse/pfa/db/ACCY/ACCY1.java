/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACCY;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACCY1 {

    public static void prepare(
            UserFormInterface form, String id, int year) throws SQLException {
        String psName = "ACCY1";
        String sql = "INSERT INTO PFACCY (" +
                "Id, Acc, Year, TxnCnt, TtlDbt, TtlCrt, TtlAmt) " +
                "SELECT Id, Acc, ?, " +
                "0, 0, 0, 0 " +
                "FROM PFACC WHERE Id=? AND Acc NOT IN (" +
                "SELECT Acc FROM PFACCY WHERE Id=? AND Year=?)";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, year);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, year);
        form.cmd().db.execUpdate(psName);
    }

    static void prepare(UserFormInterface form,
            String id, String acc, int y) throws SQLException {
        String psName = "ACCY1.2";
        String sql = "INSERT INTO PFACCY (" +
                "Id, Acc, Year, TxnCnt, TtlDbt, TtlCrt, TtlAmt) " +
                "SELECT Id, Acc, ?, " +
                "0, 0, 0, 0 " +
                "FROM PFACC WHERE Id=? AND Acc=? AND Acc NOT IN (" +
                "SELECT Acc FROM PFACCY WHERE Id=? AND Acc=? AND Year=?)";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, y);
        form.cmd().db.execUpdate(psName);
    }
}
