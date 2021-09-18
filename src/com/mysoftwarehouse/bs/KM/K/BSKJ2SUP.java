/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.K;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.SUP.SUP_KJ2SUP;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSKJ2SUP extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = BSKJ0SUP.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSKX2SUP.jasper");
        super.loadCompiledReport(in);
    }
    String frmSup = "";
    String toSup = "";
    boolean active, inactive;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSKJ2SUP";
            frmSup = cmd.in.map.texts.get(MAP.BSSUP.FROM);
            toSup = cmd.in.map.texts.get(MAP.BSSUP.TO);
            active = cmd.in.map.booleans.get(MAP.BSSUP.ACTIVE).booleanValue();
            inactive = cmd.in.map.booleans.get(MAP.BSSUP.INACTIVE).booleanValue();
            String Cmp = GET.Cmp(this);
            SUP_KJ2SUP.select(this, rsName, Cmp, frmSup, toSup, active, inactive);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSKJ2SUP.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSKJ2SUP.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        frmSup = cmd.data.ifNull(frmSup, "-");
        toSup = cmd.data.ifNull(toSup, "-");
        String reportSubTitle = "BSKJ2SUP.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, frmSup, toSup);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSKJ2SUP.title";
    }
}
