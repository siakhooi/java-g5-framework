/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.J;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.HJ.I.BSIL1INV;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.INV.INV;
import com.mysoftwarehouse.bs.db.RCP.RCP;
import com.mysoftwarehouse.bs.db.RCP.RCPA;
import com.mysoftwarehouse.bs.db.RCP.RCPC;
import com.mysoftwarehouse.bs.db.RCP.RCPD;
import com.mysoftwarehouse.bs.db.RCP.RCPJ;
import com.mysoftwarehouse.bs.db.RCP.RCPJ_Amt;
import com.mysoftwarehouse.bs.db.RCP.RCPR;
import com.mysoftwarehouse.bs.db.RCP.RCP_RcpNo;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSJP0RCP extends ProcessForm {

    private int mtc = 8;

    @Override
    public void init() {
        super.setUserAllowCancel(true);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSJP0RCP.SelectInv", 1);
        addProcess("BSJP0RCP.Begin", 1);
        addProcess("BSJP0RCP.CreateRcp", mtc);
        addProcess("BSJP0RCP.Commit", 1);
    }
    private String InvNo = "";
    private String Cmp = "";

    @Override
    public void run() throws ProcessException {
        Cmp = GET.Cmp(this);
        String title = "", message = "";
        try {
            selectInv();
            super.completed();
            cmd.db.begin();
            super.completed();

            super.setMinorTotalCount(mtc);
            String RcpNo = RCP_RcpNo.getNextRcpNo(this, Cmp);
            super.minorCompleted();
            RCP.copyFrmInv(this, Cmp, RcpNo, InvNo);
            super.minorCompleted();
            RCPD.copyFrmInv(this, Cmp, RcpNo, InvNo);
            super.minorCompleted();
            RCPA.copyFrmInv(this, Cmp, RcpNo, InvNo);
            super.minorCompleted();
            RCPC.copyFrmInv(this, Cmp, RcpNo, InvNo);
            super.minorCompleted();
            RCPJ.copyFrmInv(this, Cmp, RcpNo, InvNo);
            super.minorCompleted();
            RCPR.copyFrmInv(this, Cmp, RcpNo, InvNo);
            super.minorCompleted();
            RCPJ_Amt.calculate(this, Cmp, RcpNo);
            super.minorCompleted();
            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("BSJP0RCP.error", ex);
            String msg = "BSJP0RCP.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            super.cancelNow();
        }
    }

    private void selectInv() throws ProcessException, SQLException {
        InvNo = "";
        BSIL1INV f = new BSIL1INV();
        cmd.form.execute(f);
        InvNo = cmd.out.stringValue;
        if (cmd.data.isNull(InvNo)) {
            super.cancelNow();
            return;
        }
        if (!INV.hasInv(this, Cmp, InvNo)) {
            String msg = "INV.error.NotExist";
            super.setMessage(msg);
            super.cancelNow();
            return;
        }
    }

    @Override
    public String getFormTitle() {
        return "BSJP0RCP.title";
    }
}
