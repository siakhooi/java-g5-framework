/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.CFG;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class TxnId {

    public static int nextTxnId(UserFormInterface form, String id) 
            throws SQLException {
        String psName = "TXN.I1";
        String sql = "UPDATE PFCFG SET CurTxnId=NEXT VALUE FOR PFTXNSEQ " +
                "WHERE Id=? ";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.execUpdate(psName);

        sql = "SELECT CurTxnId FROM PFCFG WHERE Id=? ";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.execQuery(psName, psName);
        form.cmd().db.first(psName);
        return form.cmd().db.getInteger(psName, "CurTxnId");
    }
}