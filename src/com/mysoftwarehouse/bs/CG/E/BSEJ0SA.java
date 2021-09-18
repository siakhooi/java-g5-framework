/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.E;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.SA.SA_EJ0SA;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSEJ0SA extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = BSEJ0SA.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSEX0SA.jasper");
        super.loadCompiledReport(in);
    }
    String FrmAdj = "";
    String ToAdj = "";
    boolean active, inactive;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSEJ0SA";
            FrmAdj = cmd.in.map.texts.get(MAP.BSSA.FROM);
            ToAdj = cmd.in.map.texts.get(MAP.BSSA.TO);
            active = cmd.in.map.booleans.get(MAP.BSSA.ACTIVE).booleanValue();
            inactive = cmd.in.map.booleans.get(MAP.BSSA.INACTIVE).booleanValue();
            String Cmp = GET.Cmp(this);
            SA_EJ0SA.select(this, rsName, Cmp, FrmAdj, ToAdj, active, inactive);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSEJ0SA.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSEJ0SA.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmAdj = cmd.data.ifNull(FrmAdj, "-");
        ToAdj = cmd.data.ifNull(ToAdj, "-");
        String reportSubTitle = "BSEJ0SA.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmAdj, ToAdj);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSEJ0SA.title";
    }
}
