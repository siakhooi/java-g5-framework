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
public class PFDL0TXN extends PFDL_TXN {

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        String sql = "SELECT PFTXN.TxnId, PFTXN.TxnTyp, PFTXN.TxnDte, " +
                "PFTXN.FrmAcc, PFTXN.ToAcc, " +
                "PFTXN.RefNo, PFTXN.Dsc, PFTXN.Remark, " +
                "PFTXNE.Dbt, PFTXNE.Crt, PFTXNE.AcuAmt as Amt " +
                "FROM PFTXNE, PFTXN WHERE " +
                "PFTXNE.Id=PFTXN.Id " +
                "AND PFTXNE.TxnId=PFTXN.TxnId " +
                "AND PFTXN.ID=? " +
                "AND PFTXNE.Acc=? " +
                "ORDER BY PFTXN.TxnDte, PFTXN.TxnId";

        String psName = "PFDL0TXN";
        String rsName = "PFDL0TXN";
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        String Acc = cmd.in.map.texts.get(ACC.PFACC_ACC);
        try {
            cmd.db.setStatement(psName, sql);
            cmd.db.setParameter(psName, 1, id);
            cmd.db.setParameter(psName, 2, Acc);
            cmd.db.execQuery(psName, rsName);
            super.useSQL(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFDL0TXN.error", ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    "PFDL0TXN.error",
                    "PFDL0TXN.error.description");
        }
    }

    @Override
    public String getFormI18nTitle() {
        String acc = cmd.in.map.texts.get(ACC.PFACC_ACC);
        return cmd.lang.getString(getFormTitle(), acc);
    }

    @Override
    public String getFormTitle() {
        return "PFDL0TXN.title.{0}";
    }
}
