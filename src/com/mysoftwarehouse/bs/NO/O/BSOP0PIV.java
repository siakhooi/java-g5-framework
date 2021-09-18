/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.O;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.NO.N.BSNL1POR;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PIV.PIV;
import com.mysoftwarehouse.bs.db.PIV.PIVA;
import com.mysoftwarehouse.bs.db.PIV.PIVC;
import com.mysoftwarehouse.bs.db.PIV.PIVD;
import com.mysoftwarehouse.bs.db.PIV.PIV_Amt;
import com.mysoftwarehouse.bs.db.PIV.PIV_PivNo;
import com.mysoftwarehouse.bs.db.POR.POR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSOP0PIV extends ProcessForm {

    private int mtc = 8;

    @Override
    public void init() {
        super.setUserAllowCancel(true);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSOP0PIV.SelectPor", 1);
        addProcess("BSOP0PIV.Begin", 1);
        addProcess("BSOP0PIV.CreatePiv", mtc);
        addProcess("BSOP0PIV.Commit", 1);
    }
    private String PorNo = "";
    private String Cmp = "";

    @Override
    public void run() throws ProcessException {
        Cmp = GET.Cmp(this);
        try {
            selectPor();
            super.completed();
            cmd.db.begin();
            super.completed();

            super.setMinorTotalCount(mtc);
            String PivNo = PIV_PivNo.getNextPivNo(this, Cmp);
            super.minorCompleted();
            PIV.copyFrmPor(this, Cmp, PivNo, PorNo);
            super.minorCompleted();
            PIVD.copyFrmPor(this, Cmp, PivNo, PorNo);
            super.minorCompleted();
            PIVA.copyFrmPor(this, Cmp, PivNo, PorNo);
            super.minorCompleted();
            PIVC.copyFrmPor(this, Cmp, PivNo, PorNo);
            super.minorCompleted();
            PIV_Amt.calculate(this, Cmp, PivNo);
            super.minorCompleted();

            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("BSOP0PIV.error", ex);
            String msg = "BSOP0PIV.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "BSOP0PIV.title";
    }

    private void selectPor() throws ProcessException, SQLException {
        PorNo = "";
        BSNL1POR f = new BSNL1POR();
        cmd.form.execute(f);
        PorNo = cmd.out.stringValue;
        if (cmd.data.isNull(PorNo)) {
            super.cancelNow();
            return;
        }
        if (!POR.hasPor(this, Cmp, PorNo)) {
            String msg = "POR.error.NotExist";
            super.setMessage(msg);
            super.cancelNow();
            return;
        }
    }
}
