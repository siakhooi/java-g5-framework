/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.O;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.DONGLE;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PivEnum;
import com.mysoftwarehouse.bs.db.CFG.CFGI;
import com.mysoftwarehouse.bs.db.PIV.PIV_OJ0PIV;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSOJ0PIV extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        String fmt = cmd.in.map.texts.get(MAP.BSCFG.PIVFORMAT);
        String r;
        InputStream in;
        switch (PivEnum.Format.get(fmt)) {
            case B:
                r = REPORT.REPORT_PATH + "BSOX0PIV_HB.jasper";
                in = BSOJ0PIV.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
            case T:
                r = REPORT.REPORT_PATH + "BSOX0PIV_HT.jasper";
                in = BSOJ0PIV.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
            case I:
                r = REPORT.REPORT_PATH + "BSOX0PIV_HI.jasper";
                in = BSOJ0PIV.class.getResourceAsStream(r);
                super.loadCompiledReport(in);
                break;
        }
    }
    String PivNo = "";
    String rsNameCmp = "BSOJ0PIV.rsCmp";
    String rsName = "BSOJ0PIV.piv";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {

            PivNo = cmd.in.map.texts.get(MAP.BSPIV.FROM);
            String Cmp = GET.Cmp(this);
            PIV_OJ0PIV.select(this, rsName, Cmp, PivNo);

            PIV_OJ0PIV.selectCmp(this, rsNameCmp, Cmp);
            super.useResultSetDataSource(rsNameCmp);

        } catch (SQLException ex) {
            cmd.log.severe("BSOJ0PIV.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);

        String fmt = cmd.in.map.texts.get(MAP.BSCFG.PIVFORMAT);
        switch (PivEnum.Format.get(fmt)) {
            case B:
                break;
            case T:
                break;
            case I:
                super.putParameter("BSOX0PIV.IMG.HEAD", CFGI.getLetterHead(this, Cmp));
                break;
        }

        InputStream piv = BSOJ0PIV.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSOX0PIV.jasper");
        super.putCompiledSubreport("BSOX0PIV.SR.PIV", piv);
        super.putDataSource("BSOX0PIV.DS.PIV", cmd.db.getResultSet(rsName));

        super.putParameter(REPORT.SHOW_DEMO, new Boolean(DONGLE.isDemoMode(this)));
        super.putParameter(REPORT.CURCDE, GET.CurCde(this));
        super.putParameter(REPORT.CMPS_SIGN1,
                cmd.global.texts.get(MAP.BSCMPS.PIVSIGN1));
        super.putParameter(REPORT.CMPS_SIGN2,
                cmd.global.texts.get(MAP.BSCMPS.PIVSIGN2));
    }

    @Override
    public String getFormTitle() {
        return "BSOJ0PIV.title";
    }
}
