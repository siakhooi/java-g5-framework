/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.B;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.db.ITM.ITM;
import com.mysoftwarehouse.is.db.ITM.ITMB;
import com.mysoftwarehouse.is.db.LOC.LOC;
import com.mysoftwarehouse.is.db.TCD.TCD;
import com.mysoftwarehouse.is.db.TXN.TXN;
import com.mysoftwarehouse.is.db.TXN.TXNA;
import com.mysoftwarehouse.is.db.UOM.UOM;
import com.mysoftwarehouse.is.db.WHS.WHS;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISBP2WHS extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(true);
        super.setLogListVisible(false);

        addProcess("ISBP2WHS.Begin", 1);
        addProcess("ISBP2WHS.DeleteISWHS", 1);
        addProcess("ISBP2WHS.DeleteISITM", 1);
        //addProcess("ISBP2WHS.DeleteISITMA", 1);
        addProcess("ISBP2WHS.DeleteISITMB", 1);
        addProcess("ISBP2WHS.DeleteISLOC", 1);
        addProcess("ISBP2WHS.DeleteISUOM", 1);
        addProcess("ISBP2WHS.DeleteISTCD", 1);
        addProcess("ISBP2WHS.DeleteISTXN", 1);
        addProcess("ISBP2WHS.DeleteISTXNA", 1);
        addProcess("ISBP2WHS.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        try {
            cmd.db.begin();
            String Whs = cmd.in.map.texts.get(MAP.ISWHS.WHS);
            super.completed();
            WHS.delete(this, Whs);
            super.completed();
            ITM.delete(this, Whs);
            super.completed();
//            ITMA.delete(this, Whs);
//            super.completed();
            ITMB.delete(this, Whs);
            super.completed();
            LOC.delete(this, Whs);
            super.completed();
            UOM.delete(this, Whs);
            super.completed();
            TCD.delete(this, Whs);
            super.completed();
            TXN.delete(this, Whs);
            super.completed();
            TXNA.delete(this, Whs);
            super.completed();

            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            cmd.log.severe("ISBP2WHS.error", ex);
            String msg = "ISBP2WHS.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
            }
            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "ISBP2WHS.title";
    }
}
