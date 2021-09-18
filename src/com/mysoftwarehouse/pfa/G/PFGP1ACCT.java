/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.G;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.pfa.conf.IMPORT;
import com.mysoftwarehouse.pfa.data.PFACC;
import com.mysoftwarehouse.pfa.db.ACC.ACC1;
import com.mysoftwarehouse.pfa.db.ACCT.ACCT;
import com.mysoftwarehouse.pfa.db.DB.DataEnum;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFGP1ACCT extends ProcessForm {

    private String id;

    @Override
    public void init() {
        id = cmd.global.texts.get(USR.PFUSR_ID);

        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("PFGP1ACCT.Process", 1);
    }

    @Override
    public void run() throws ProcessException {
        String rsName = "PFGP1ACCT";
        int n = 0;
        try {
            ACCT.select_All(this, rsName, id);
            cmd.db.last(rsName);
            n = cmd.db.getResultSet(rsName).getRow();
            super.setMinorTotalCount(n);
            cmd.db.first(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFGP1ACCT.error", ex);
            String msg = "PFGP1ACCT.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);

            super.cancelNow();
        }
        if (n == 0) {
            String title = "PFGP1ACCT.NoRecordMessage.title";
            String message = "PFGP1ACCT.NoRecordMessage.description";
            cmd.common.showMessage(DialogMessageType.INFORMATION,
                    title, message);
        } else {
            try {
                do {
                    createRecord(rsName);
                    super.minorCompleted();
                } while (cmd.db.next(rsName));
            } catch (SQLException ex) {
                cmd.log.severe("PFGP1ACCT.error", ex);
                String msg = "PFGP1ACCT.error.{0}";
                msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
                super.setI18nMessage(msg);
                super.cancelNow();
            }
        }
        super.completed();
    }

    private void createRecord(String rsName) throws SQLException, ProcessException {
        String format = IMPORT.ACC_DATE_FORMAT;
        int seq = cmd.db.getInteger(rsName, "Seq");
        String acc = cmd.db.getString(rsName, "Acc");
        String Nme = cmd.db.getString(rsName, "Nme");
        String AccTyp = cmd.db.getString(rsName, "AccTyp");
        String Remark = cmd.db.getString(rsName, "Remark");
        Calendar BalDte = cmd.cal.getNow();
        try {
            BalDte = cmd.cal.getCalendar(
                    cmd.db.getString(rsName, "BalDte"), format);
        } catch (Exception ex) {
            cmd.log.severe("PFGP1ACCT.error", ex);
            String msg = "PFGP1ACCT.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            return;
        }
        if (BalDte == null) {
            return;
        }
        BigDecimal BalAmt = BigDecimal.ZERO;
        try {
            BalAmt = new BigDecimal(
                    cmd.db.getString(rsName, "BalAmt"));
        } catch (Exception ex) {
            cmd.log.severe("PFGP1ACCT.error", ex);
            String msg = "PFGP1ACCT.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            return;
        }
        if (BalAmt == null) {
            return;
        }
        if (!DataEnum.AccTyp.contains(AccTyp)) {
            Exception ex = new Exception("Wrong Account Type!");
            cmd.log.severe("PFGP1ACCT.error", ex);
            String msg = "PFGP1ACCT.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
        } else {
            try {
                cmd.db.begin();
                ACC1.insert(this, id, acc, Nme, AccTyp, "N", BalAmt, BalDte, Remark,
                        PFACC.Status.A.code, true);
                ACCT.delete(this, id, seq);
                cmd.db.commit();
            } catch (SQLException ex) {
                try {
                    cmd.db.rollback();
                } catch (SQLException ex3) {
                }
                cmd.log.severe("PFGP1ACCT.error", ex);
                String msg = "PFGP1ACCT.error.{0}";
                msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
                super.setI18nMessage(msg);
//                cmd.db.rollback();
            //throw ex;
            }
        }
    }

    @Override
    public String getFormTitle() {
        return "PFGP1ACCT.title";
    }
}
