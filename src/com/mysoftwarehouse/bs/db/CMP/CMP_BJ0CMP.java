/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CMP;

import com.mysoftwarehouse.bs.B.BSBJ0CMP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class CMP_BJ0CMP {

    public static void select(BSBJ0CMP form, String rsName,
            String frmCmp, String toCmp) throws SQLException {

        String sql = "SELECT * FROM BSCMP A, BSCMPA B ";
        boolean hasFrmCmp = !form.cmd().data.isNull(frmCmp);
        boolean hasToCmp = !form.cmd().data.isNull(toCmp);
        sql += "WHERE A.Cmp=B.Cmp ";
        sql += "AND A.PriAdd=B.Seq ";
        if (hasFrmCmp) {
            sql += "AND A.Cmp>=? ";
        }
        if (hasToCmp) {
            sql += "AND A.Cmp<=? ";
        }
        sql += "ORDER BY A.Cmp ";

        int i = 1;
        form.cmd().db.setStatement(rsName, sql);
        if (hasFrmCmp) {
            form.cmd().db.setParameter(rsName, i++, frmCmp);
        }
        if (hasToCmp) {
            form.cmd().db.setParameter(rsName, i++, toCmp);
        }
        form.cmd().db.execQuery(rsName, rsName);
    }
}
