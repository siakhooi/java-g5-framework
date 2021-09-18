/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.E;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.conf.REPORT;
import com.mysoftwarehouse.pfa.db.ACC.ACC_EJ1ACC;
import com.mysoftwarehouse.pfa.db.ACCM.ACCM1;
import com.mysoftwarehouse.pfa.db.ACCY.ACCY1;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFEJ1ACC extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = PFEJ1ACC.class.getResourceAsStream(
                REPORT.REPORT_PATH + "PFEX1ACC.jasper");
        super.loadCompiledReport(in);
    }
    String id = "";
    String frmAcc = "";
    String toAcc = "";
    String reportType = "";
    Calendar year = null;
    Calendar yearmonth = null;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "PFEJ1ACC";
            id = cmd.global.texts.get(USR.PFUSR_ID);
            frmAcc = cmd.in.map.texts.get(ACC.FROMACC);
            toAcc = cmd.in.map.texts.get(ACC.TOACC);
            reportType = cmd.in.map.texts.get(ACC.RPTTYP);
            year = cmd.in.map.calendars.get(ACC.RPTYEAR);
            yearmonth = cmd.in.map.calendars.get(ACC.RPTYEARMONTH);

            boolean monthType = "M".equals(reportType);
            int y = 0, m = 0;
            cmd.db.begin();
            if (monthType) {
                y = cmd.cal.getYear(yearmonth);
                m = cmd.cal.getMonth(yearmonth);
                ACCM1.prepare(this, id, y, m);
            } else {
                y = cmd.cal.getYear(year);
                ACCY1.prepare(this, id, y);
            }
            cmd.db.commit();
            ACC_EJ1ACC.select(this, rsName, id, frmAcc, toAcc,
                    reportType, year, yearmonth);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex2) {
            }
            cmd.log.severe("PFEJ1ACC.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        super.putParameter(REPORT.USER_ID, id);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "PFEJ1ACC.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        String rType = "PFEJ1ACC.ReportType.M";
        String rPeriod = cmd.lang.formatCalendar(yearmonth, "MMM yyyy");
        if ("Y".equals(reportType)) {
            rType = "PFEJ1ACC.ReportType.Y";
            rPeriod = cmd.lang.formatCalendar(year, "yyyy");
        }
        frmAcc = cmd.data.ifNull(frmAcc, "-");
        toAcc = cmd.data.ifNull(toAcc, "-");
        rType = cmd.lang.getString(rType);
        String reportSubTitle = "PFEJ1ACC.subtitle.{0}.{1}.{2}.{3}";
        reportSubTitle = cmd.lang.getString(reportSubTitle,
                frmAcc, toAcc, rType, rPeriod);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "PFEJ1ACC.title";
    }
}
