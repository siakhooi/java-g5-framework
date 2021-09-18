/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.ITM;

import com.mysoftwarehouse.is.C.ISCJ0ITMB;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ITM_CJ0ITMB {

    public static void select(ISCJ0ITMB form, String rsName,
            String Whs, String FrmItm, String ToItm) throws SQLException {

        String sql = "SELECT *, A.Nme ItmNme, B.Qty LocQty, C.Nme LocNme FROM ISITM A, ISITMB B, ISLOC C ";
        boolean hasFrmItm = !form.cmd().data.isNull(FrmItm);
        boolean hasToItm = !form.cmd().data.isNull(ToItm);
        sql += "WHERE Whs=? ";
        sql += "AND A.Whs=B.Whs ";
        sql += "AND A.Itm=B.Itm ";
        sql += "AND A.Whs=C.Whs ";
        sql += "AND B.Loc=C.Loc ";


        if (hasFrmItm) {
            sql += "AND A.Itm>=? ";
        }
        if (hasToItm) {
            sql += "AND A.Itm<=? ";
        }
        sql += "ORDER BY A.Whs, A.Itm, B.Loc ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Whs);
        if (hasFrmItm) {
            form.cmd().db.setParameter(rsName, i++, FrmItm);
        }
        if (hasToItm) {
            form.cmd().db.setParameter(rsName, i++, ToItm);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
