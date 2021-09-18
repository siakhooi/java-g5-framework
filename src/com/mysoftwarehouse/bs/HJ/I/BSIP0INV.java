/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.I;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.HJ.H.BSHL1QTT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.INV.INV;
import com.mysoftwarehouse.bs.db.INV.INVA;
import com.mysoftwarehouse.bs.db.INV.INVC;
import com.mysoftwarehouse.bs.db.INV.INVD;
import com.mysoftwarehouse.bs.db.INV.INVJ;
import com.mysoftwarehouse.bs.db.INV.INVJ_Amt;
import com.mysoftwarehouse.bs.db.INV.INVR;
import com.mysoftwarehouse.bs.db.INV.INV_InvNo;
import com.mysoftwarehouse.bs.db.QTT.QTT;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSIP0INV extends ProcessForm {

    private int mtc = 8;

    @Override
    public void init() {
        super.setUserAllowCancel(true);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSIP0INV.SelectQtt", 1);
        addProcess("BSIP0INV.Begin", 1);
        addProcess("BSIP0INV.CreateInv", mtc);
        addProcess("BSIP0INV.Commit", 1);
    }
    private String QttNo = "";
    private String Cmp = "";

    @Override
    public void run() throws ProcessException {
        Cmp = GET.Cmp(this);
        try {
            selectQtt();
            super.completed();
            cmd.db.begin();
            super.completed();

            super.setMinorTotalCount(mtc);
            String InvNo = INV_InvNo.getNextInvNo(this, Cmp);
            super.minorCompleted();
            INV.copyFrmQtt(this, Cmp, InvNo, QttNo);
            super.minorCompleted();
            INVD.copyFrmQtt(this, Cmp, InvNo, QttNo);
            super.minorCompleted();
            INVA.copyFrmQtt(this, Cmp, InvNo, QttNo);
            super.minorCompleted();
            INVC.copyFrmQtt(this, Cmp, InvNo, QttNo);
            super.minorCompleted();
            INVJ.copyFrmQtt(this, Cmp, InvNo, QttNo);
            super.minorCompleted();
            INVR.copyFrmQtt(this, Cmp, InvNo, QttNo);
            super.minorCompleted();
            INVJ_Amt.calculate(this, Cmp, InvNo);
            super.minorCompleted();

            super.completed();
            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex3) {
            }
            cmd.log.severe("BSIP0INV.error", ex);
            String msg = "BSIP0INV.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "BSIP0INV.title";
    }

    private void selectQtt() throws ProcessException, SQLException {
        QttNo = "";
        BSHL1QTT f = new BSHL1QTT();
        cmd.form.execute(f);
        QttNo = cmd.out.stringValue;
        if (cmd.data.isNull(QttNo)) {
            super.cancelNow();
            return;
        }
        if (!QTT.hasQtt(this, Cmp, QttNo)) {
            String msg = "QTT.error.NotExist";
            super.setMessage(msg);
            super.cancelNow();
            return;
        }
    }
}
