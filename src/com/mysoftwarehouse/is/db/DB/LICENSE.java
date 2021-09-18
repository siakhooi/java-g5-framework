/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.db.DB;

import com.mysoftwarehouse.is.A.ISAM0;
import com.mysoftwarehouse.is.A.ISAP0;
import com.mysoftwarehouse.is.A.ISAP0WAIT;
import com.mysoftwarehouse.is.db.ITM.ITM;
import com.mysoftwarehouse.is.db.LOC.LOC;
import com.mysoftwarehouse.is.db.TCD.TCD;
import com.mysoftwarehouse.is.db.TXN.TXN;
import com.mysoftwarehouse.is.db.UOM.UOM;
import com.mysoftwarehouse.is.db.WHS.WHS;

/**
 *
 * @author Ng Siak Hooi
 */
public class LICENSE {

    private static final String GLOBAL_TOTAL_RECORDS = "Global.Total";
    private static final int FREE_QTY_LEVEL = 30;
    private static final int DEMO_LEVEL = 180;
    private static final int RECORD_PER_SECOND = 6;

    public static void check(ISAM0 form) {
        int Total = form.cmd().global.integers.get(GLOBAL_TOTAL_RECORDS);
        if (Total <= FREE_QTY_LEVEL) {
            return;
        }
        int r = form.cmd().random.getInt(FREE_QTY_LEVEL, DEMO_LEVEL);
        if (r < Total) {
            if (Total > DEMO_LEVEL) {
                Total = DEMO_LEVEL;
            }
            int n = Total / RECORD_PER_SECOND;
            ISAP0WAIT f = new ISAP0WAIT();
            form.cmd().out.intValue = n;
            form.cmd().form.execute(f);
        }
    }

    public static void check(ISAP0 form) {
        int Whs = WHS.getCount(form);
        int Itm = ITM.getCount(form);
        int Loc = LOC.getCount(form);
        int Tcd = TCD.getCount(form);
        int Uom = UOM.getCount(form);
        int Txn = TXN.getCount(form);

        int Total = Whs + Itm + Loc + Tcd + Uom + Txn;
        form.cmd().global.integers.put(GLOBAL_TOTAL_RECORDS, Total);
    }
}
