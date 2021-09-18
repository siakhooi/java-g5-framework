/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.G;

import com.mysoftwarehouse.bs.db.ST.ST;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSGL4ST extends BSGL_ST {

    @Override
    protected void query(String rsName, String Cmp) throws SQLException {
        ST.select_All(this, rsName, Cmp);
    }

    @Override
    public String getFormTitle() {
        return "BSGL4ST.title";
    }
}
