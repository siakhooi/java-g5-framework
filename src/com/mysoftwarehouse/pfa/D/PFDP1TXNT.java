/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.D;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.pfa.conf.IMPORT;
import com.mysoftwarehouse.pfa.db.DB.DataEnum;
import com.mysoftwarehouse.pfa.db.TXN.TXN2;
import com.mysoftwarehouse.pfa.db.TXNT.TXNT;
import com.mysoftwarehouse.pfa.db.CFG.TxnId;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFDP1TXNT extends ProcessForm {

    private String id;

    @Override
    public void init() {
        id = cmd.global.texts.get(USR.PFUSR_ID);

        super.setUserAllowCancel(true);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("PFDP1TXNT.Process", 1);
    }

    @Override
    public void run() throws ProcessException {
        String rsName = "PFDP1TXNT";
        int n = 0;
        try {
            TXNT.select_All(this, rsName, id);
            cmd.db.last(rsName);
            n = cmd.db.getResultSet(rsName).getRow();
            super.setMinorTotalCount(n);
            cmd.db.first(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFDP1TXNT.error", ex);
            String msg = "PFDP1TXNT.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            super.cancelNow();
        }
        if (n == 0) {
            String title = "PFDP1TXNT.NoRecordMessage.title";
            String message = "PFDP1TXNT.NoRecordMessage.description";
            cmd.common.showMessage(DialogMessageType.INFORMATION,
                    title, message);
        } else {
            try {
                do {
                    createRecord(rsName);
                    super.minorCompleted();
                } while (cmd.db.next(rsName));
            } catch (SQLException ex) {
                cmd.log.severe("PFDP1TXNT.error", ex);
                String msg = "PFDP1TXNT.error.{0}";
                msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
                super.setI18nMessage(msg);
                super.cancelNow();
            }
        }
        super.completed();
    }

    private void createRecord(String rsName) throws SQLException, ProcessException {
        String format = IMPORT.TXN_DATE_FORMAT;

        int seq = cmd.db.getInteger(rsName, "Seq");
        String txnTyp = cmd.db.getString(rsName, "TxnTyp");
        String desc = cmd.db.getString(rsName, "Dsc");
        String frmAcc = cmd.db.getString(rsName, "FrmAcc");
        String toAcc = cmd.db.getString(rsName, "ToAcc");
        String refNo = cmd.db.getString(rsName, "RefNo");
        String remark = cmd.db.getString(rsName, "Remark");
		String txnDteText = cmd.db.getString(rsName, "TxnDte");
		String UsrAmtText= cmd.db.getString(rsName, "UsrAmt");
		
		cmd.log.info("Create Txn: " + txnDteText+","+frmAcc+","+toAcc+","+txnTyp+","+UsrAmtText+","+desc);
        Calendar txnDte = cmd.cal.getNow();
        try {
            txnDte = cmd.cal.getCalendar(txnDteText, format);
        } catch (Exception ex) {
            cmd.log.severe("PFDP1TXNT.error", ex);
            String msg = "PFDP1TXNT.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            return;
        }
        if (txnDte == null) {
            return;
        }
        BigDecimal txnAmt = BigDecimal.ZERO;

        try {
            //String i=cmd.db.getString(rsName, "UsrAmt");
            txnAmt = new BigDecimal(UsrAmtText);
        } catch (Exception ex) {
            cmd.log.severe("PFDP1TXNT.error", ex);
            String msg = "PFDP1TXNT.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            return;
        }
        if (txnAmt == null) {
            return;
        }
        if (!DataEnum.TxnTyp.contains(txnTyp) ||
                txnTyp.equals(DataEnum.TxnTyp.B.code)) {
            Exception ex = new Exception("Wrong Transactions Type!");
            cmd.log.severe("PFDP1TXNT.error", ex);
            String msg = "PFDP1TXNT.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
        } else {
            //
            try {
                cmd.db.begin();
                int txnId = TxnId.nextTxnId(this, id);
                TXN2.insert(this, id, txnId, txnTyp,
                        txnDte, frmAcc, txnAmt, toAcc, refNo,
                        desc, remark);
                TXNT.delete(this, id, seq);
                cmd.db.commit();
            } catch (SQLException ex) {
                try {
                    cmd.db.rollback();
                } catch (SQLException ex2) {
                }
//                cmd.db.rollback();
                //throw ex;
                cmd.log.severe("PFDP1TXNT.error", ex);
                String msg = "PFDP1TXNT.error.{0}";
                msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
                super.setI18nMessage(msg);
                throw ex;
            }
        }
    }

    @Override
    public String getFormTitle() {
        return "PFDP1TXNT.title";
    }
}
