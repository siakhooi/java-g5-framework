/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.G;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.conf.REPORT;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.TXN.TXN_GJ0TXNA;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISGJ0TXNA extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = ISGJ0TXNA.class.getResourceAsStream(
                REPORT.REPORT_PATH + "ISGX0TXNA.jasper");
        super.loadCompiledReport(in);
    }
    String FrmItm = "";
    String ToItm = "";
    Calendar FrmDte = null, ToDte = null;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "ISGJ0TXNA";
            String Whs = GET.Whs(this);
            FrmItm = cmd.in.map.texts.get(MAP.ISTXN.FROM);
            ToItm = cmd.in.map.texts.get(MAP.ISTXN.TO);
            FrmDte = cmd.in.map.calendars.get(MAP.ISTXN.FROMDATE);
            ToDte = cmd.in.map.calendars.get(MAP.ISTXN.TODATE);
            TXN_GJ0TXNA.select(this, rsName, Whs, FrmItm, ToItm, FrmDte, ToDte);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("ISGJ0TXNA.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Whs = GET.Whs(this);
        super.putParameter(REPORT.USER_ID, Whs);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "ISGJ0TXNA.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmItm = cmd.data.ifNull(FrmItm, "-");
        ToItm = cmd.data.ifNull(ToItm, "-");
        String frmDteText = "-";
        if (FrmDte != null) {
            frmDteText = cmd.lang.formatCalendar(FrmDte, "MMM dd, yyyy");
        }
        String toDteText = "-";
        if (ToDte != null) {
            toDteText = cmd.lang.formatCalendar(ToDte, "MMM dd, yyyy");
        }

        String reportSubTitle = "ISGJ0TXNA.subtitle.{0}.{1}.{2}.{3}";
        reportSubTitle = cmd.lang.getString(reportSubTitle,
                FrmItm, ToItm, frmDteText, toDteText);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "ISGJ0TXNA.title";
    }
}
