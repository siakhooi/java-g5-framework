/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.F;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.conf.REPORT;
import com.mysoftwarehouse.pfa.db.ACC.ACC_FJ0ACCM;
import com.mysoftwarehouse.pfa.db.ACCM.ACCM1;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFFJ0ACCM extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = PFFJ0ACCM.class.getResourceAsStream(
                REPORT.REPORT_PATH + "PFFX0ACCM.jasper");
        super.loadCompiledReport(in);
    }
    String id = "";
    Calendar period = null;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "PFFJ0ACCM";
            period = cmd.in.map.calendars.get(ACC.PERIOD);
            id = cmd.global.texts.get(USR.PFUSR_ID);
            cmd.db.begin();
            int y = cmd.cal.getYear(period);
            int m = cmd.cal.getMonth(period);
            ACCM1.prepare(this, id, y, m);
            cmd.db.commit();
            ACC_FJ0ACCM.select(this, rsName, id, period);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            try {
                cmd.db.rollback();
            } catch (SQLException ex2) {
            }
            cmd.log.severe("PFFJ0ACCM.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        super.putParameter(REPORT.USER_ID, id);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "PFFJ0ACCM.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        String periodText = cmd.lang.formatCalendar(period, "MMM yyyy");
        String reportSubTitle = "PFFJ0ACCM.subtitle.{0}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, periodText);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "PFFJ0ACCM.title";
    }
}
