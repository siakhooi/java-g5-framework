/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.C;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.conf.REPORT;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.ITM.ITM_CJ1ITMB;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISCJ1ITMB extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = ISCJ1ITMB.class.getResourceAsStream(
                REPORT.REPORT_PATH + "ISCX1ITMB.jasper");
        super.loadCompiledReport(in);
    }
    String FrmLoc = "";
    String ToLoc = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "ISCJ1ITMB";
            String Whs = GET.Whs(this);
            FrmLoc = cmd.in.map.texts.get(MAP.ISLOC.FROM);
            ToLoc = cmd.in.map.texts.get(MAP.ISLOC.TO);
            ITM_CJ1ITMB.select(this, rsName, Whs, FrmLoc, ToLoc);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("ISCJ1ITMB.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Whs = GET.Whs(this);
        super.putParameter(REPORT.USER_ID, Whs);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "ISCJ1ITMB.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmLoc = cmd.data.ifNull(FrmLoc, "-");
        ToLoc = cmd.data.ifNull(ToLoc, "-");
        String reportSubTitle = "ISCJ1ITMB.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmLoc, ToLoc);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "ISCJ1ITMB.title";
    }
}
