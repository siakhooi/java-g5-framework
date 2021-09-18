/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.UOM;

import com.mysoftwarehouse.is.D.ISDJ0UOM;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class UOM_DJ0UOM {

    public static void select(ISDJ0UOM form, String rsName,
            String Whs, String FrmUom, String ToUom) throws SQLException {

        String sql = "SELECT * FROM ISUOM ";
        boolean hasFrmUom = !form.cmd().data.isNull(FrmUom);
        boolean hasToUom = !form.cmd().data.isNull(ToUom);
        sql += "WHERE Whs=? ";

        if (hasFrmUom) {
            sql += "AND Uom>=? ";
        }
        if (hasToUom) {
            sql += "AND Uom<=? ";
        }
        sql += "ORDER BY Whs, Uom ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, i++, Whs);
        if (hasFrmUom) {
            form.cmd().db.setParameter(rsName, i++, FrmUom);
        }
        if (hasToUom) {
            form.cmd().db.setParameter(rsName, i++, ToUom);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
