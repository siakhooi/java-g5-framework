/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.H;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.conf.REPORT;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.ITM.ITM_HJ0ITM;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISHJ0ITM extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = ISHJ0ITM.class.getResourceAsStream(
                REPORT.REPORT_PATH + "ISHX0ITM.jasper");
        super.loadCompiledReport(in);
    }
    String FrmItm = "";
    String ToItm = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "ISHJ0ITM";
            String Whs = GET.Whs(this);
            FrmItm = cmd.in.map.texts.get(MAP.ISITM.FROM);
            ToItm = cmd.in.map.texts.get(MAP.ISITM.TO);
           ITM_HJ0ITM.select(this, rsName, Whs, FrmItm, ToItm);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("ISHJ0ITM.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Whs = GET.Whs(this);
        super.putParameter(REPORT.USER_ID, Whs);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "ISHJ0ITM.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmItm = cmd.data.ifNull(FrmItm, "-");
        ToItm = cmd.data.ifNull(ToItm, "-");
        String reportSubTitle = "ISHJ0ITM.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmItm, ToItm);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "ISHJ0ITM.title";
    }
}
