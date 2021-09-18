/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.POR;

import com.mysoftwarehouse.bs.db.QTT.*;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.db.SA.SA_Calc;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class POR_Amt {

    public static void calculate(UserFormInterface form,
            String Cmp, String PorNo) throws SQLException {
        BigDecimal grossAmt = _getGrossAmt(form, Cmp, PorNo);
        _updateTtlAmt(form, Cmp, PorNo, grossAmt, grossAmt);
    }

    private static BigDecimal _getGrossAmt(UserFormInterface form,
            String Cmp, String PorNo) throws SQLException {
        String sql = "SELECT SUM(TtlAmt) Amt FROM BSPORD WHERE Cmp=? AND PorNo=?";
        String psName = "POR_Amt.S1";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PorNo);
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
            String Cmp, String PorNo, BigDecimal TtlGrsAmt,
            BigDecimal TtlNetAmt) throws SQLException {
        String sql = "UPDATE BSPOR SET TtlGrsAmt=?, TtlAmt=? WHERE Cmp=? AND PorNo=?";
        String psName = "POR_Amt.U1";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, TtlGrsAmt);
        form.cmd().db.setParameter(psName, i++, TtlNetAmt);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, PorNo);
        form.cmd().db.execUpdate(psName);
    }
}
