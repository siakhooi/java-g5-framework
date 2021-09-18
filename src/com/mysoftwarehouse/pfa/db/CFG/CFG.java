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
public class CFG {

    public static String PFCFG_CAPITAL_ACCOUNT = "PFCFG_CAPITAL_ACCOUNT";
    public static String PFCFG_INCOME_ACCOUNT = "PFCFG_INCOME_ACCOUNT";
    public static String PFCFG_RETAIN_INCOME_ACCOUNT = "PFCFG_RETAIN_INCOME_ACCOUNT";
    public static String PFCFG_DEF_RECON_ACCOUNT = "PFCFG_DEF_RECON_ACCOUNT";
    public static String PFCFG_SHOWHELP = "PFCFG_SHOWHELP";

    public static void delete(UserFormInterface form, String id)
            throws SQLException {
        CFGH.createDelete(form, id);
        String sql = "DELETE FROM PFCFG WHERE Id=? ";
        String psName = "CFG.D";
        form.cmd().db.setStatement(psName, sql);
        form.cmd().db.setParameter(psName, 1, id);
        form.cmd().db.execUpdate(psName);
    }

    public static boolean select(UserFormInterface form,
            String rsName, String id) throws SQLException {
        String sql = "SELECT * FROM PFCFG WHERE Id=? ";
        form.cmd().db.setStatement(rsName, sql);
        form.cmd().db.setParameter(rsName, 1, id);
        form.cmd().db.execQuery(rsName, rsName);
        return form.cmd().db.next(rsName);
    }

    public static void update(UserFormInterface form, String id,
            String mainAccLstTyp, String defRecAcc, boolean showHelp) throws SQLException {
        String sql = "UPDATE PFCFG SET " +
                "MainAccLstTyp=?, " +
                "DefRecAcc=?, " +
                "ShowHelp=? " +
                "WHERE Id=? ";
        String psName = "CFG.U1";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, mainAccLstTyp);
        form.cmd().db.setParameter(psName, i++, defRecAcc);
        form.cmd().db.setParameter(psName, i++, form.cmd().data.boolean2String(showHelp));
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.execUpdate(psName);
        CFGH.createUpdate(form, id);
    }

    public static void update_AccountRename(UserFormInterface form,
            String id, String FrmAcc, String ToAcc) throws SQLException {
        String sql = "UPDATE PFCFG SET " +
                "DefRecAcc=? " +
                "WHERE Id=? " +
                "AND DefRecAcc=? ";
        String psName = "CFG.R1";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, ToAcc);
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, FrmAcc);
        int r = form.cmd().db.execUpdate(psName);
        if (r > 0) {
            CFGH.createRename(form, id);
        }
    }

    public static boolean useACC(UserFormInterface form,
            String id, String acc) throws SQLException {
        String sql = "SELECT * FROM PFCFG WHERE " +
                "Id=? AND " +
                "(CapAcc=? OR IncAcc=? OR RtIncAcc=? OR DefRecAcc=? ) ";
        String psName = "CFG.S3";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.setParameter(psName, i++, acc);
        form.cmd().db.execQuery(psName, psName);
        return form.cmd().db.next(psName);
    }
}
