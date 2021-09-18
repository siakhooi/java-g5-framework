/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.B;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.conf.REPORT;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.WHS.WHS_BJ0WHS;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISBJ0WHS extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = ISBJ0WHS.class.getResourceAsStream(
                REPORT.REPORT_PATH + "ISBX0WHS.jasper");
        super.loadCompiledReport(in);
    }
    String frmWhs = "";
    String toWhs = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "ISBJ0WHS";
            frmWhs = cmd.in.map.texts.get(MAP.ISWHS.FROM);
            toWhs = cmd.in.map.texts.get(MAP.ISWHS.TO);
            WHS_BJ0WHS.select(this, rsName, frmWhs, toWhs);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("ISBJ0WHS.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Whs = GET.Whs(this);
        super.putParameter(REPORT.USER_ID, Whs);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "ISBJ0WHS.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        frmWhs = cmd.data.ifNull(frmWhs, "-");
        toWhs = cmd.data.ifNull(toWhs, "-");
        String reportSubTitle = "ISBJ0WHS.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, frmWhs, toWhs);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "ISBJ0WHS.title";
    }
}
