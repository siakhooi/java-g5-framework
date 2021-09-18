/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.ACCM;

import com.mysoftwarehouse.pfa.AU.PFAP1USR;
import com.mysoftwarehouse.pfa.G.PFGP0ACC;
import com.mysoftwarehouse.pfa.G.PFGP1ACC;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ACCM {

    public static void delete(
            PFAP1USR form, String id) throws SQLException {
        String psName = "ACCM.D1";
        String sql = "DELETE FROM PFACCM WHERE Id=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.execUpdate(psName);
    }

    public static void delete(
            PFGP1ACC form, String id, String acc) throws SQLException {
        String psName = "ACCM.D2";
        String sql = "DELETE FROM PFACCM " +
                "WHERE Id=? " +
                "AND Acc=? " +
                "AND TtlDbt=0 " +
                "AND TtlCrt=0 " +
                "AND TtlAmt=0 ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.execUpdate(psName);
    }

    public static void update_AccountRename(
            PFGP0ACC form, String id, String FrmAcc, String ToAcc)
            throws SQLException {
        String psName = "ACCM.R1";
        String sql = "UPDATE PFACCM SET Acc=? WHERE Id=? AND Acc=? ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToAcc);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, FrmAcc);
        form.cmd().db.execUpdate(psName);
    }
}
