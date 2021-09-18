/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.N;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.POR.POR_NJ2POR;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSNJ2POR extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = BSNJ2POR.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSNX2POR.jasper");
        super.loadCompiledReport(in);
    }
    Calendar frmDte = null, toDte = null;
    String Cmp = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSNJ2POR";
            Cmp = GET.Cmp(this);
            frmDte = cmd.in.map.calendars.get(MAP.BSPOR.FROMDATE);
            toDte = cmd.in.map.calendars.get(MAP.BSPOR.TODATE);

            POR_NJ2POR.select(this, rsName, Cmp, frmDte, toDte);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSNJ2POR.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSNJ2POR.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        String frmDteText = "-";
        if (frmDte != null) {
            frmDteText = cmd.lang.formatCalendar(frmDte, "MMM dd, yyyy");
        }
        String toDteText = "-";
        if (toDte != null) {
            toDteText = cmd.lang.formatCalendar(toDte, "MMM dd, yyyy");
        }
        String reportSubTitle = "BSNJ2POR.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle,
                frmDteText, toDteText);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);
        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSNJ2POR.title";
    }
}
