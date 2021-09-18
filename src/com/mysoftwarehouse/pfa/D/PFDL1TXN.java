/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.D;

import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFDL1TXN extends PFDL_TXN {

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        String accTyp = cmd.in.map.texts.get(ACC.PFACC_ACCTYP);
        String amtField = "PFTXNE.AcuAmt";
        if (accTyp.equals("I") || accTyp.equals("E")) {
            amtField = "PFTXNE.YearAcuAmt";
        }
        String sql = "SELECT PFTXN.TxnId, PFTXN.TxnTyp, PFTXN.TxnDte, " +
                "PFTXN.FrmAcc, PFTXN.ToAcc, " +
                "PFTXN.RefNo, PFTXN.Dsc, PFTXN.Remark, " +
                "PFTXNE.Dbt, PFTXNE.Crt, " + amtField + " as Amt " +
                "FROM PFTXNE, PFTXN WHERE " +
                "PFTXNE.ID=PFTXN.ID AND PFTXNE.TxnId=PFTXN.TxnId " +
                "AND PFTXN.ID=? " +
                "AND PFTXNE.Acc=? " +
                "AND YEAR(PFTXN.TxnDte)=? " +
                "ORDER BY PFTXN.TxnDte, PFTXN.TxnId ";
        String psName = "PFDL1TXN";
        String rsName = "PFDL1TXN";
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        String acc = cmd.in.map.texts.get(ACC.PFACC_ACC);
        int year = cmd.cal.getYear();

        try {
            cmd.db.setStatement(psName, sql);
            cmd.db.setParameter(psName, 1, id);
            cmd.db.setParameter(psName, 2, acc);
            cmd.db.setParameter(psName, 3, year);
            cmd.db.execQuery(psName, rsName);
            super.useSQL(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFDL1TXN.error", ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    "PFDL1TXN.error",
                    "PFDL1TXN.error.description");
        }
    }

    @Override
    public String getFormI18nTitle() {
        String acc = cmd.in.map.texts.get(ACC.PFACC_ACC);
        return cmd.lang.getString(getFormTitle(), acc);
    }

    @Override
    public String getFormTitle() {
        return "PFDL1TXN.title.{0}";
    }
}
