/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.mysoftwarehouse.is.db.TXN;

import com.mysoftwarehouse.is.G.ISGJ0TXN;
import com.mysoftwarehouse.util.CalendarTool;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXN_GJ0TXN {

    public static void select(ISGJ0TXN form, String rsName, 
            String Whs, String FrmItm, String ToItm, 
            Calendar FrmDte, Calendar ToDte) throws SQLException {


        String sql = "SELECT *, B.Nme ItmNme, C.Typ TxnTyp FROM ISTXN A, ISITM B, ISTCD C ";
        boolean hasFrmItm = !form.cmd().data.isNull(FrmItm);
        boolean hasToItm = !form.cmd().data.isNull(ToItm);
        boolean hasFrmDte = FrmDte != null;
        boolean hasToDte = ToDte != null;
        sql += "WHERE A.Whs=? ";
        sql += "AND A.Whs=B.Whs ";
        sql += "AND A.Itm=B.Itm ";
        sql += "AND A.Whs=C.Whs ";
        sql += "AND A.Tcd=C.Tcd ";

        if (hasFrmItm) {
            sql += "AND B.Itm>=? ";
        }
        if (hasToItm) {
            sql += "AND B.Itm<=? ";
        }
        if (hasFrmDte) {
            sql += "AND A.TxnDte>=? ";
        }
        if (hasToDte) {
            sql += "AND A.TxnDte<? ";
        }
        sql += "ORDER BY A.Whs, A.TxnDte, B.Itm, A.TxnTme ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Whs);
        if (hasFrmItm) {
            form.cmd().db.setParameter(rsName, i++, FrmItm);
        }
        if (hasToItm) {
            form.cmd().db.setParameter(rsName, i++, ToItm);
        }
        if (hasFrmDte) {
            Calendar f = CalendarTool.copy(FrmDte);
            f = CalendarTool.getDate(f);
            form.cmd().db.setParameter(rsName, i++, f);
        }
        if (hasToDte) {
            Calendar t = CalendarTool.copy(ToDte);
            t.add(Calendar.DAY_OF_MONTH, 1);
            t = CalendarTool.getDate(t);
            form.cmd().db.setParameter(rsName, i++, t);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
