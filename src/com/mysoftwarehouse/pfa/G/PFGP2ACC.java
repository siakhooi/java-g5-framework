/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.G;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.pfa.data.PFACC;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.db.ACC.ACC1;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFGP2ACC extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(true);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("PFGP2ACC.Setup", 1);
        addProcess("PFGP2ACC.CreateAccount", 9);

    }

    @Override
    public void run() throws ProcessException {
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        int selectedRows[] = cmd.in.intValues;
        SampleAccountResource sar = new SampleAccountResource();
        sar.init(this);
        sar.addAsset();
        sar.addLiability();
        sar.addIncome();
        sar.addExpenses();
        Vector<Vector<Object>> all = sar.getAll();
        super.completed();
        super.setMinorTotalCount(selectedRows.length);
        Calendar balDte = cmd.cal.getNow();
        for (int i = 0; i < selectedRows.length; i++) {
            int r = selectedRows[i];
            String accountCode = (String) all.elementAt(r).elementAt(0);
            String accountName = (String) all.elementAt(r).elementAt(1);
            String accountType = (String) all.elementAt(r).elementAt(2);
            String accountRemark = (String) all.elementAt(r).elementAt(3);
            try {
                cmd.db.begin();
                accountCode = ACC.generateSampleAccountCode(this, id, accountCode);
                ACC1.insert(this, id, accountCode,
                        accountName, accountType, "N", BigDecimal.ZERO,
                        balDte, accountRemark,
                        PFACC.Status.A.code, true);
                cmd.db.commit();
            } catch (SQLException ex) {
                try {
                    cmd.log.severe("PFGP2ACC.error", ex);
                    String msg = "PFGP2ACC.error.{0}";
                    msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
                    super.setI18nMessage(msg);
                    cmd.db.rollback();
                } catch (SQLException ex1) {
                    cmd.log.severe("PFGP2ACC.error", ex1);
                }
            } catch (Exception ex) {
                cmd.log.severe("PFGP2ACC.error", ex);
                String msg = "PFGP2ACC.error.{0}";
                msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
                super.setI18nMessage(msg);
            }
            super.minorCompleted();
        }
        super.completed();
    }

    @Override
    public String getFormTitle() {
        return "PFGP2ACC.title";
    }
}
