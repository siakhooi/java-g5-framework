/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.CFG;

import com.mysoftwarehouse.pfa.AU.PFAS0USR;
import com.mysoftwarehouse.pfa.conf.DBVALUE;
import com.mysoftwarehouse.pfa.data.PFACC;
import com.mysoftwarehouse.pfa.db.ACC.ACC1;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class CFG2 {

    public static void insert(PFAS0USR form, String id)
            throws SQLException {
        String psName = "CFG2.I1";
        String MainAccLstTyp =
                DBVALUE.DEFAULT_MAIN_ACCOUNT_LIST_TYPE;
        String CapAcc =
                DBVALUE.DEFAULT_CAPITAL_ACCOUNT;
        String IncAcc =
                DBVALUE.DEFAULT_INCOME_ACCOUNT;
        String RtIncAcc =
                DBVALUE.DEFAULT_RETAINED_INCOME_ACCOUNT;
        String DefRecAcc =
                DBVALUE.DEFAULT_DEFAULT_RECON_ACCOUNT;
        boolean ShowHelp = DBVALUE.DEFAULT_SHOW_HELP;

        String sql = "INSERT INTO PFCFG (" +
                "Id, MainAccLstTyp, CapAcc, " +
                "IncAcc, RtIncAcc, DefRecAcc, ShowHelp" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?) ";
        form.cmd().db.setStatement(psName, sql);
        int i = 1;
        form.cmd().db.setParameter(psName, i++, id);
        form.cmd().db.setParameter(psName, i++, MainAccLstTyp);
        form.cmd().db.setParameter(psName, i++, CapAcc);
        form.cmd().db.setParameter(psName, i++, IncAcc);
        form.cmd().db.setParameter(psName, i++, RtIncAcc);
        form.cmd().db.setParameter(psName, i++, DefRecAcc);
        form.cmd().db.setParameter(psName, i++, form.cmd().data.boolean2String(ShowHelp));
        form.cmd().db.execUpdate(psName);
        CFGH.createInsert(form, id);

        form.cmd().global.texts.put(
                CFG.PFCFG_CAPITAL_ACCOUNT, CapAcc);
        form.cmd().global.texts.put(
                CFG.PFCFG_INCOME_ACCOUNT, IncAcc);
        form.cmd().global.texts.put(
                CFG.PFCFG_RETAIN_INCOME_ACCOUNT, RtIncAcc);
        form.cmd().global.texts.put(
                CFG.PFCFG_DEF_RECON_ACCOUNT, DefRecAcc);
        form.cmd().global.booleans.put(
                CFG.PFCFG_SHOWHELP, ShowHelp);

        String acc = DBVALUE.DEFAULT_CAPITAL_ACCOUNT;
        String Nme = DBVALUE.DEFAULT_CAPITAL_ACCOUNT_NAME;
        String AccTyp = "C";
        String SysTyp = "Y";
        Calendar BalDte = form.cmd().cal.getNow();
        String Remark = "";
        ACC1.insertSystemACC(form, id, acc, Nme, AccTyp, SysTyp,
                BalDte, Remark, PFACC.Status.A.code, false);

        acc = DBVALUE.DEFAULT_INCOME_ACCOUNT;
        Nme = DBVALUE.DEFAULT_INCOME_ACCOUNT_NAME;
        ACC1.insertSystemACC(form, id, acc, Nme, AccTyp, SysTyp,
                BalDte, Remark, PFACC.Status.A.code, false);

        acc = DBVALUE.DEFAULT_RETAINED_INCOME_ACCOUNT;
        Nme = DBVALUE.DEFAULT_RETAINED_INCOME_ACCOUNT_NAME;
        ACC1.insertSystemACC(form, id, acc, Nme, AccTyp, SysTyp,
                BalDte, Remark, PFACC.Status.A.code, false);

        acc = DBVALUE.DEFAULT_DEFAULT_RECON_ACCOUNT;
        Nme = DBVALUE.DEFAULT_DEFAULT_RECON_ACCOUNT_NAME;
        AccTyp = "E";
        SysTyp = "N";
        ACC1.insert(form, id, acc, Nme, AccTyp, SysTyp,
                BigDecimal.ZERO, BalDte, Remark,
                PFACC.Status.A.code, true);
    }
}
