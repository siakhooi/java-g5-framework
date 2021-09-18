/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.C;

import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.mysoftwarehouse.pfa.D.PFDL_TXN;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFCL1TXN extends PFDL_TXN {

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
                "ORDER BY PFTXN.TxnId, PFTXN.TxnDte";

        String psName = "PFCL1TXN";
        String rsName = "PFCL1TXN";
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        try {
            cmd.db.setStatement(psName, sql);
            cmd.db.setParameter(psName, 1, id);
            cmd.db.execQuery(psName, rsName);
            super.useSQL(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFCL1TXN.error", ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    "PFCL1TXN.error",
                    "PFCL1TXN.error.description");
        }
    }

    @Override
    public String getFormTitle() {
        return "PFCL1TXN.title";
    }
}
