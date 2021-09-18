/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.H;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.QTT.QTT_HJ2QTT;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSHJ2QTT extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = BSHJ2QTT.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSHX2QTT.jasper");
        super.loadCompiledReport(in);
    }
    Calendar frmDte = null, toDte = null;
    String Cmp = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSHJ2QTT";
            Cmp = GET.Cmp(this);
            frmDte = cmd.in.map.calendars.get(MAP.BSQTT.FROMDATE);
            toDte = cmd.in.map.calendars.get(MAP.BSQTT.TODATE);

            QTT_HJ2QTT.select(this, rsName, Cmp, frmDte, toDte);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSHJ2QTT.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSHJ2QTT.title";
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
        String reportSubTitle = "BSHJ2QTT.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle,
                frmDteText, toDteText);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);
        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSHJ2QTT.title";
    }
}
