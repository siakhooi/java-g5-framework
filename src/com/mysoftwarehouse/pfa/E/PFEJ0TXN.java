/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.E;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.conf.REPORT;
import com.mysoftwarehouse.pfa.db.TXN.TXN_EJ0TXN;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFEJ0TXN extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = PFEJ0TXN.class.getResourceAsStream(
                REPORT.REPORT_PATH + "PFEX0TXN.jasper");
        super.loadCompiledReport(in);
    }
    String frmAcc = "";
    String toAcc = "";
    Calendar frmDte = null, toDte = null;
    String id = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "PFEJ0TXN";
            frmAcc = cmd.in.map.texts.get(ACC.FROMACC);
            toAcc = cmd.in.map.texts.get(ACC.TOACC);
            id = cmd.global.texts.get(USR.PFUSR_ID);
            frmDte = cmd.in.map.calendars.get(ACC.FROMDATE);
            toDte = cmd.in.map.calendars.get(ACC.TODATE);

            TXN_EJ0TXN.select(this, rsName, id, frmAcc, toAcc, frmDte, toDte);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFEJ0TXN.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        super.putParameter(REPORT.USER_ID, id);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "PFEJ0TXN.title";
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
        frmAcc = cmd.data.ifNull(frmAcc, "-");
        toAcc = cmd.data.ifNull(toAcc, "-");
        String reportSubTitle = "PFEJ0TXN.subtitle.{0}.{1}.{2}.{3}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, frmAcc, toAcc,
                frmDteText, toDteText);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);
        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "PFEJ0TXN.title";
    }
}
