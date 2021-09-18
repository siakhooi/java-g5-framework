/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.CMP.CMP_BJ0CMP;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBJ0CMP extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = BSBJ0CMP.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSBX0CMP.jasper");
        super.loadCompiledReport(in);
    }
    String frmCmp = "";
    String toCmp = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSBJ0CMP";
            frmCmp = cmd.in.map.texts.get(MAP.BSCMP.FROM);
            toCmp = cmd.in.map.texts.get(MAP.BSCMP.TO);
            CMP_BJ0CMP.select(this, rsName, frmCmp, toCmp);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSBJ0CMP.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSBJ0CMP.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        frmCmp = cmd.data.ifNull(frmCmp, "-");
        toCmp = cmd.data.ifNull(toCmp, "-");
        String reportSubTitle = "BSBJ0CMP.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, frmCmp, toCmp);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSBJ0CMP.title";
    }
}
