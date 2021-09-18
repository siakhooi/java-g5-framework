/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.G;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.ST.ST_GJ0ST;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSGJ0ST extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = BSGJ0ST.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSGX0ST.jasper");
        super.loadCompiledReport(in);
    }
    String FrmTrm = "";
    String ToTrm = "";
    boolean active, inactive;
    boolean forQtt, forInv, forRcp;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSGJ0ST";
            String Cmp=GET.Cmp(this);
            FrmTrm = cmd.in.map.texts.get(MAP.BSST.FROM);
            ToTrm = cmd.in.map.texts.get(MAP.BSST.TO);
            active = cmd.in.map.booleans.get(MAP.BSST.ACTIVE).booleanValue();
            inactive = cmd.in.map.booleans.get(MAP.BSST.INACTIVE).booleanValue();
            forQtt = cmd.in.map.booleans.get(MAP.BSST.FORQTT).booleanValue();
            forInv = cmd.in.map.booleans.get(MAP.BSST.FORINV).booleanValue();
            forRcp = cmd.in.map.booleans.get(MAP.BSST.FORRCP).booleanValue();

            ST_GJ0ST.select(this, rsName, Cmp, FrmTrm, ToTrm, active, inactive,
                    forQtt, forInv, forRcp);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSGJ0ST.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSGJ0ST.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmTrm = cmd.data.ifNull(FrmTrm, "-");
        ToTrm = cmd.data.ifNull(ToTrm, "-");
        String reportSubTitle = "BSGJ0ST.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmTrm, ToTrm);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSGJ0ST.title";
    }
}
