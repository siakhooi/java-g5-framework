/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.J;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.DONGLE;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.RcpEnum;
import com.mysoftwarehouse.bs.db.CFG.CFGI;
import com.mysoftwarehouse.bs.db.RCP.RCP_JJ0RCP;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSJJ0RCP extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        String fmt = cmd.in.map.texts.get(MAP.BSCFG.RCPFORMAT);
        String r;
        InputStream in;
        switch (RcpEnum.Format.get(fmt)) {
            case B:
                r = REPORT.REPORT_PATH + "BSJX0RCP_HB.jasper";
                in = BSJJ0RCP.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
            case T:
                r = REPORT.REPORT_PATH + "BSJX0RCP_HT.jasper";
                in = BSJJ0RCP.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
            case I:
                r = REPORT.REPORT_PATH + "BSJX0RCP_HI.jasper";
                in = BSJJ0RCP.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
        }
    }
    String RcpNo = "";
    String rsNameRcpj = "BSJJ0RCP.rsRcpj";
    boolean hasRcpj = false;
    String rsNameRcpr = "BSJJ0RCP.rsRcpr";
    boolean hasRcpr = false;
    String rsNameCmp = "BSJJ0RCP.rsCmp";
    String rsName = "BSJJ0RCP.rcp";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            RcpNo = cmd.in.map.texts.get(MAP.BSRCP.FROM);
            String Cmp = GET.Cmp(this);
            RCP_JJ0RCP.select(this, rsName, Cmp, RcpNo);
                    
            RCP_JJ0RCP.selectRcpj(this, rsNameRcpj, Cmp, RcpNo);
            if (cmd.db.next(rsNameRcpj)) {
                hasRcpj = true;
                cmd.db.beforeFirst(rsNameRcpj);
            }
            RCP_JJ0RCP.selectRcpr(this, rsNameRcpr, Cmp, RcpNo);
            if (cmd.db.next(rsNameRcpr)) {
                hasRcpr = true;
                cmd.db.beforeFirst(rsNameRcpr);
            }
            
            RCP_JJ0RCP.selectCmp(this, rsNameCmp, Cmp);
            super.useResultSetDataSource(rsNameCmp);

        } catch (SQLException ex) {
            cmd.log.severe("BSJJ0RCP.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);

        String fmt = cmd.in.map.texts.get(MAP.BSCFG.RCPFORMAT);
        switch (RcpEnum.Format.get(fmt)) {
            case B:
                break;
            case T:
                break;
            case I:
                super.putParameter("BSJX0RCP.IMG.HEAD", CFGI.getLetterHead(this, Cmp));
                break;
        }

        InputStream qttj = BSJJ0RCP.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSJX0RCP_J.jasper");
        super.putCompiledSubreport("BSJX0RCP.SR.RCPJ", qttj);
        super.putDataSource("BSJX0RCP.DS.RCPJ", cmd.db.getResultSet(rsNameRcpj));
        super.putParameter("BSJX0RCP.HasRCPJ", new Boolean(hasRcpj));

        InputStream qttr = BSJJ0RCP.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSJX0RCP_R.jasper");
        super.putCompiledSubreport("BSJX0RCP.SR.RCPR", qttr);
        super.putDataSource("BSJX0RCP.DS.RCPR", cmd.db.getResultSet(rsNameRcpr));
        super.putParameter("BSJX0RCP.HasRCPR", new Boolean(hasRcpr));

        InputStream qtt = BSJJ0RCP.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSJX0RCP.jasper");
        super.putCompiledSubreport("BSJX0RCP.SR.RCP", qtt);
        super.putDataSource("BSJX0RCP.DS.RCP", cmd.db.getResultSet(rsName));

        super.putParameter(REPORT.SHOW_DEMO, new Boolean(DONGLE.isDemoMode(this)));
        super.putParameter(REPORT.CURCDE, GET.CurCde(this));
        super.putParameter(REPORT.CMPS_SIGN1,
                cmd.global.texts.get(MAP.BSCMPS.RCPSIGN1));
        super.putParameter(REPORT.CMPS_SIGN2,
                cmd.global.texts.get(MAP.BSCMPS.RCPSIGN2));
    }

    @Override
    public String getFormTitle() {
        return "BSJJ0RCP.title";
    }
}
