/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.E;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.conf.REPORT;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.LOC.LOC_EJ0LOC;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISEJ0LOC extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = ISEJ0LOC.class.getResourceAsStream(
                REPORT.REPORT_PATH + "ISEX0LOC.jasper");
        super.loadCompiledReport(in);
    }
    String FrmLoc = "";
    String ToLoc = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "ISEJ0LOC";
            String Whs = GET.Whs(this);
            FrmLoc = cmd.in.map.texts.get(MAP.ISLOC.FROM);
            ToLoc = cmd.in.map.texts.get(MAP.ISLOC.TO);
           LOC_EJ0LOC.select(this, rsName, Whs, FrmLoc, ToLoc);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("ISEJ0LOC.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Whs = GET.Whs(this);
        super.putParameter(REPORT.USER_ID, Whs);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "ISEJ0LOC.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmLoc = cmd.data.ifNull(FrmLoc, "-");
        ToLoc = cmd.data.ifNull(ToLoc, "-");
        String reportSubTitle = "ISEJ0LOC.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmLoc, ToLoc);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "ISEJ0LOC.title";
    }
}
