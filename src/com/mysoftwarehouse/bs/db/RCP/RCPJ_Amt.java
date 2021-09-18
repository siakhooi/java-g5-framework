/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.RCP;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.db.SA.SA_Calc;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class RCPJ_Amt {

    public static void calculate(UserFormInterface form,
            String Cmp, String RcpNo) throws SQLException {
        BigDecimal grossAmt = _getGrossAmt(form, Cmp, RcpNo);
        BigDecimal netAmt = grossAmt;

        String sql = "SELECT * FROM BSRCPJ WHERE Cmp=? AND RcpNo=? ORDER BY Prio, Seq";
        String psName = "RCPJ_Amt.S1";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.execQuery(psName, psName);
        BigDecimal totalAdjustmentAmt = BigDecimal.ZERO;
        int currentPriority = 0;
        boolean firsttime = true;
        while (form.cmd().db.next(psName)) {
            int Prio = form.cmd().db.getInteger(psName, "Prio");
            String Type = form.cmd().db.getString(psName, "Typ");
            BigDecimal Amt = form.cmd().db.getBigDecimal(psName, "Amt");
            int Seq = form.cmd().db.getInteger(psName, "Seq");
            if (firsttime) {
                firsttime = false;
                currentPriority = Prio;
            }
            if (currentPriority != Prio) { //priority changed
                netAmt = netAmt.add(totalAdjustmentAmt);
                totalAdjustmentAmt = BigDecimal.ZERO;
                currentPriority = Prio;
            }
            BigDecimal adjAmt = SA_Calc.calculate(netAmt, Type, Amt);
            _updateAdjAmt(form, Cmp, RcpNo, Seq, adjAmt, netAmt.add(totalAdjustmentAmt));
            totalAdjustmentAmt = totalAdjustmentAmt.add(adjAmt);

        }
        netAmt = netAmt.add(totalAdjustmentAmt);
        _updateTtlAmt(form, Cmp, RcpNo, grossAmt, netAmt);
    }

    private static BigDecimal _getGrossAmt(UserFormInterface form,
            String Cmp, String RcpNo) throws SQLException {
        String sql = "SELECT SUM(TtlAmt) Amt FROM BSRCPD WHERE Cmp=? AND RcpNo=?";
        String psName = "RCPJ_Amt.S2";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, RcpNo);
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

    private static void _updateAdjAmt(UserFormInterface form, String Cmp,
            String RcpNo, int Seq, BigDecimal EffAmt, BigDecimal OpenAmt) throws SQLException {
        String sql = "UPDATE BSRCPJ SET EffAmt=?, OpenAmt=?, NewAmt=? " +
                "WHERE Cmp=? AND RcpNo=? AND Seq=? ";
        String psName = "RCPJ_Amt.U1";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, EffAmt);
        form.cmd().db.setParameter(psName, i++, OpenAmt);
        form.cmd().db.setParameter(psName, i++, EffAmt.add(OpenAmt));
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.setParameter(psName, i++, Seq);
        form.cmd().db.execUpdate(psName);
    }

    private static void _updateTtlAmt(UserFormInterface form,
            String Cmp, String RcpNo, BigDecimal TtlGrsAmt,
            BigDecimal TtlNetAmt) throws SQLException {
        String sql = "UPDATE BSRCP SET TtlGrsAmt=?, TtlAmt=? WHERE Cmp=? AND RcpNo=?";
        String psName = "RCPJ_Amt.U2";
        int i = 1;
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, i++, TtlGrsAmt);
        form.cmd().db.setParameter(psName, i++, TtlNetAmt);
        form.cmd().db.setParameter(psName, i++, Cmp);
        form.cmd().db.setParameter(psName, i++, RcpNo);
        form.cmd().db.execUpdate(psName);
    }
}
