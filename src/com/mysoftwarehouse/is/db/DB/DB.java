/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.DB;

import com.mysoftwarehouse.is.A.ISAP0;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class DB {

    public static boolean select(ISAP0 form, String rsName, String DbTypKey)
            throws SQLException {
        String sql = "SELECT Version FROM ISDB WHERE Typ=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, DbTypKey);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }
}
