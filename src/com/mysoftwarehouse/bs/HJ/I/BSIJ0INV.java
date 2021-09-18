/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.I;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.DONGLE;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.InvEnum;
import com.mysoftwarehouse.bs.db.CFG.CFGI;
import com.mysoftwarehouse.bs.db.INV.INV_IJ0INV;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSIJ0INV extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        String fmt = cmd.in.map.texts.get(MAP.BSCFG.INVFORMAT);
        String r;
        InputStream in;
        switch (InvEnum.Format.get(fmt)) {
            case B:
                r = REPORT.REPORT_PATH + "BSIX0INV_HB.jasper";
                in = BSIJ0INV.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
            case T:
                r = REPORT.REPORT_PATH + "BSIX0INV_HT.jasper";
                in = BSIJ0INV.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
            case I:
                r = REPORT.REPORT_PATH + "BSIX0INV_HI.jasper";
                in = BSIJ0INV.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
        }
    }
    String InvNo = "";
    String rsNameInvj = "BSIJ0INV.rsInvj";
    boolean hasInvj = false;
    String rsNameInvr = "BSIJ0INV.rsInvr";
    boolean hasInvr = false;
    String rsNameCmp = "BSIJ0INV.rsCmp";
    String rsName = "BSIJ0INV.inv";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            InvNo = cmd.in.map.texts.get(MAP.BSINV.FROM);
            String Cmp = GET.Cmp(this);
            INV_IJ0INV.select(this, rsName, Cmp, InvNo);
            
            INV_IJ0INV.selectInvj(this, rsNameInvj, Cmp, InvNo);
            if (cmd.db.next(rsNameInvj)) {
                hasInvj = true;
                cmd.db.beforeFirst(rsNameInvj);
            }
            INV_IJ0INV.selectInvr(this, rsNameInvr, Cmp, InvNo);
            if (cmd.db.next(rsNameInvr)) {
                hasInvr = true;
                cmd.db.beforeFirst(rsNameInvr);
            }
            
            INV_IJ0INV.selectCmp(this, rsNameCmp, Cmp);
            super.useResultSetDataSource(rsNameCmp);
            
        } catch (SQLException ex) {
            cmd.log.severe("BSIJ0INV.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);

        String fmt = cmd.in.map.texts.get(MAP.BSCFG.INVFORMAT);
        switch (InvEnum.Format.get(fmt)) {
            case B:
                break;
            case T:
                break;
            case I:
                super.putParameter("BSIX0INV.IMG.HEAD", CFGI.getLetterHead(this, Cmp));
                break;
        }

        InputStream invj = BSIJ0INV.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSIX0INV_J.jasper");
        super.putCompiledSubreport("BSIX0INV.SR.INVJ", invj);
        super.putDataSource("BSIX0INV.DS.INVJ", cmd.db.getResultSet(rsNameInvj));
        super.putParameter("BSIX0INV.HasINVJ", new Boolean(hasInvj));

        InputStream invr = BSIJ0INV.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSIX0INV_R.jasper");
        super.putCompiledSubreport("BSIX0INV.SR.INVR", invr);
        super.putDataSource("BSIX0INV.DS.INVR", cmd.db.getResultSet(rsNameInvr));
        super.putParameter("BSIX0INV.HasINVR", new Boolean(hasInvr));

        InputStream inv = BSIJ0INV.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSIX0INV.jasper");
        super.putCompiledSubreport("BSIX0INV.SR.INV", inv);
        super.putDataSource("BSIX0INV.DS.INV", cmd.db.getResultSet(rsName));

        
        super.putParameter(REPORT.SHOW_DEMO, new Boolean(DONGLE.isDemoMode(this)));
        super.putParameter(REPORT.CURCDE, GET.CurCde(this));
        super.putParameter(REPORT.CMPS_SIGN1,
                cmd.global.texts.get(MAP.BSCMPS.INVSIGN1));
        super.putParameter(REPORT.CMPS_SIGN2,
                cmd.global.texts.get(MAP.BSCMPS.INVSIGN2));
    }

    @Override
    public String getFormTitle() {
        return "BSIJ0INV.title";
    }
}
