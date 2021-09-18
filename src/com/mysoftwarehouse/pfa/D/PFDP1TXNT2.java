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
import com.mysoftwarehouse.pfa.db.TXN.TXN;
import com.mysoftwarehouse.pfa.db.TXN.TXN2;
import com.mysoftwarehouse.pfa.db.TXNT2.TXNT2;
import com.mysoftwarehouse.pfa.db.CFG.TxnId;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFDP1TXNT2 extends ProcessForm {

    private String id;

    @Override
    public void init() {
        id = cmd.global.texts.get(USR.PFUSR_ID);

        super.setUserAllowCancel(true);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("PFDP1TXNT2.Process", 1);
    }

    @Override
    public void run() throws ProcessException {
        String rsName = "PFDP1TXNT2";
        int n = 0;
        try {
            TXNT2.select_All(this, rsName, id);
            cmd.db.last(rsName);
            n = cmd.db.getResultSet(rsName).getRow();
            super.setMinorTotalCount(n);
            cmd.db.first(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFDP1TXNT2.error", ex);
            String msg = "PFDP1TXNT2.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            super.cancelNow();
        }
        if (n == 0) {
            String title = "PFDP1TXNT2.NoRecordMessage.title";
            String message = "PFDP1TXNT2.NoRecordMessage.description";
            cmd.common.showMessage(DialogMessageType.INFORMATION,
                    title, message);
        } else {
            try {
                do {
                    deleteRecord(rsName);
                    super.minorCompleted();
                } while (cmd.db.next(rsName));
            } catch (SQLException ex) {
                cmd.log.severe("PFDP1TXNT2.error", ex);
                String msg = "PFDP1TXNT2.error.{0}";
                msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
                super.setI18nMessage(msg);
                super.cancelNow();
            }
        }
        super.completed();
    }

    private void deleteRecord(String rsName) throws SQLException, ProcessException {
        int seq = cmd.db.getInteger(rsName, "Seq");
        String txnIdText = cmd.db.getString(rsName, "TxnId");
		cmd.log.info("Delete TxnId: " + txnIdText);
        if (txnIdText == null) {
            return;
        }
		int txnId=-1;
        try {
            txnId =  Integer.parseInt(txnIdText);
			if(!TXN.selectRN(this, "PFDP1TXNT2.RS1", id, txnId)) return; // Record not exist
        } catch (Exception ex) {
            cmd.log.severe("PFDP1TXNT2.error", ex);
            String msg = "PFDP1TXNT2.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            return;
        }
		
		try {
			cmd.db.begin();
			TXN2.delete(this, id, txnId);
			TXNT2.delete(this, id, seq);
			cmd.db.commit();
		} catch (SQLException ex) {
			try {
				cmd.db.rollback();
			} catch (SQLException ex2) {
			}
			cmd.log.severe("PFDP1TXNT2.error", ex);
			String msg = "PFDP1TXNT2.error.{0}";
			msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
			super.setI18nMessage(msg);
			throw ex;
		}
    }

    @Override
    public String getFormTitle() {
        return "PFDP1TXNT2.title";
    }
}
