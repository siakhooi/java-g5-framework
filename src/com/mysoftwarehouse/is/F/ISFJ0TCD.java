/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.F;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.conf.REPORT;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.TCD.TCD_FJ0TCD;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISFJ0TCD extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = ISFJ0TCD.class.getResourceAsStream(
                REPORT.REPORT_PATH + "ISFX0TCD.jasper");
        super.loadCompiledReport(in);
    }
    String FrmTcd = "";
    String ToTcd = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "ISFJ0TCD";
            String Whs = GET.Whs(this);
            FrmTcd = cmd.in.map.texts.get(MAP.ISTCD.FROM);
            ToTcd = cmd.in.map.texts.get(MAP.ISTCD.TO);
           TCD_FJ0TCD.select(this, rsName, Whs, FrmTcd, ToTcd);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("ISFJ0TCD.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Whs = GET.Whs(this);
        super.putParameter(REPORT.USER_ID, Whs);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "ISFJ0TCD.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmTcd = cmd.data.ifNull(FrmTcd, "-");
        ToTcd = cmd.data.ifNull(ToTcd, "-");
        String reportSubTitle = "ISFJ0TCD.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmTcd, ToTcd);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "ISFJ0TCD.title";
    }
}
