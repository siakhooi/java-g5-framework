/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.TXN.TXNE;

import com.mysoftwarehouse.pfa.AU.PFAP1USR;
import com.mysoftwarehouse.pfa.G.PFGP0ACC;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class TXNE {

    public static void delete(
            PFAP1USR form, String id) throws SQLException {
        String psName = "TXNE.D1";
        String sql = "DELETE FROM PFTXNE WHERE Id=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.execUpdate(psName);
    }

    public static void update_AccountRename(PFGP0ACC form,
            String id, String FrmAcc, String ToAcc) throws SQLException {
        String psName = "TXNE.R1";
        String sql = "UPDATE PFTXNE SET " +
                "Acc=? " +
                "WHERE Id=? " +
                "AND Acc=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToAcc);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, FrmAcc);
        form.cmd().db.execUpdate(psName);
    }
}
