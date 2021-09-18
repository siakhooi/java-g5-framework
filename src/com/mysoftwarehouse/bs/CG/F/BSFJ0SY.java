/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.F;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.SY.SY_FJ0SY;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSFJ0SY extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = BSFJ0SY.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSFX0SY.jasper");
        super.loadCompiledReport(in);
    }
    String FrmPayTyp = "";
    String ToPayTyp = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSFJ0SY";
            FrmPayTyp = cmd.in.map.texts.get(MAP.BSSY.FROM);
            ToPayTyp = cmd.in.map.texts.get(MAP.BSSY.TO);
            String Cmp = GET.Cmp(this);
            SY_FJ0SY.select(this, rsName, Cmp, FrmPayTyp, ToPayTyp);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSFJ0SY.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSFJ0SY.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmPayTyp = cmd.data.ifNull(FrmPayTyp, "-");
        ToPayTyp = cmd.data.ifNull(ToPayTyp, "-");
        String reportSubTitle = "BSFJ0SY.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmPayTyp, ToPayTyp);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSFJ0SY.title";
    }
}
