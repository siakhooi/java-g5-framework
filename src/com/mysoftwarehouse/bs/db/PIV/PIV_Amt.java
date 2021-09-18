/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.PIV;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PIV_Amt {

    public static void calculate(UserFormInterface form,
            String Cmp, String PivNo) throws SQLException {
        BigDecimal grossAmt = _getGrossAmt(form, Cmp, PivNo);
        _updateTtlAmt(form, Cmp, PivNo, grossAmt, grossAmt);
    }

    private static BigDecimal _getGrossAmt(UserFormInterface form,
            String Cmp, String PivNo) throws SQLException {
        String sql = "SELECT SUM(TtlAmt) Amt FROM BSPIVD WHERE Cmp=? AND PivNo=?";
        String psName = "PIV_Amt.S1";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.execQuery(psName, psName);
        if (form.cmd().db.next(psName)) {
            BigDecimal w = form.cmd().db.getBigDecimal(psName, "Amt");
            if (w == null) {
                return BigDecimal.ZERO;
            } else {
                return w;
            }
        } else {
            return BigDecimal.ZERO;
        }
    }

    private static void _updateTtlAmt(UserFormInterface form,
            String Cmp, String PivNo, BigDecimal TtlGrsAmt,
            BigDecimal TtlNetAmt) throws SQLException {
        String sql = "UPDATE BSPIV SET TtlGrsAmt=?, TtlAmt=? WHERE Cmp=? AND PivNo=?";
        String psName = "PIV_Amt.U1";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, TtlGrsAmt);
        form.cmd().db.setParameter(psName, i++, TtlNetAmt);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PivNo);
        form.cmd().db.execUpdate(psName);
    }
}
