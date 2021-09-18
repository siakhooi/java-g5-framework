/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.ITM;

import com.mysoftwarehouse.is.C.ISCJ1ITMB;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ITM_CJ1ITMB {

    public static void select(ISCJ1ITMB form, String rsName,
            String Whs, String FrmLoc, String ToLoc) throws SQLException {

        String sql = "SELECT *, A.Nme ItmNme, B.Qty LocQty,  C.Nme LocNme FROM ISITM A, ISITMB B, ISLOC C ";
        boolean hasFrmLoc = !form.cmd().data.isNull(FrmLoc);
        boolean hasToLoc = !form.cmd().data.isNull(ToLoc);
        sql += "WHERE Whs=? ";
        sql += "AND A.Whs=B.Whs ";
        sql += "AND A.Itm=B.Itm ";
        sql += "AND A.Whs=C.Whs ";
        sql += "AND B.Loc=C.Loc ";

        if (hasFrmLoc) {
            sql += "AND B.Loc>=? ";
        }
        if (hasToLoc) {
            sql += "AND B.Loc<=? ";
        }
        sql += "ORDER BY A.Whs, B.Loc, A.Itm ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Whs);
        if (hasFrmLoc) {
            form.cmd().db.setParameter(rsName, i++, FrmLoc);
        }
        if (hasToLoc) {
            form.cmd().db.setParameter(rsName, i++, ToLoc);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }

}
