/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.F;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.conf.REPORT;
import com.mysoftwarehouse.pfa.db.ACC.ACC_FJ1ACC;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFFJ2ACC extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = PFFJ2ACC.class.getResourceAsStream(
                REPORT.REPORT_PATH + "PFFX2ACC.jasper");
        super.loadCompiledReport(in);
    }
    String id = "";
    String accType = "";
    String reportType = "";
    Calendar year = null;
    Calendar yearmonth = null;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "PFFJ2ACC";
            id = cmd.global.texts.get(USR.PFUSR_ID);

            accType = cmd.in.map.texts.get(ACC.RPTACCTYP);
            reportType = cmd.in.map.texts.get(ACC.RPTTYP);
            year = cmd.in.map.calendars.get(ACC.RPTYEAR);
            yearmonth = cmd.in.map.calendars.get(ACC.RPTYEARMONTH);

            //use same as PFFJ1ACC
            ACC_FJ1ACC.select(this, rsName, id, accType,
                    reportType, year, yearmonth);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFFJ2ACC.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        super.putParameter(REPORT.USER_ID, id);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "PFFJ2ACC.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        String aTyp = "AccTyp." + accType;
        aTyp = cmd.lang.getString(aTyp);

        String rType = "PFFJ2ACC.ReportType.M";
        String rPeriod = cmd.lang.formatCalendar(yearmonth, "MMM yyyy");
        if ("Y".equals(reportType)) {
            rType = "PFFJ2ACC.ReportType.Y";
            rPeriod = cmd.lang.formatCalendar(year, "yyyy");
        }
        rType = cmd.lang.getString(rType);
        String reportSubTitle = "PFFJ2ACC.subtitle.{0}.{1}.{2}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, aTyp, rType, rPeriod);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "PFFJ2ACC.title";
    }
}
