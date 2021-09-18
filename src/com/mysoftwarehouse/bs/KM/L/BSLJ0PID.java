/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.L;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PI.PI_LJ0PID;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSLJ0PID extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = BSLJ0PID.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSLX0PID.jasper");
        super.loadCompiledReport(in);
    }
    String FrmItm = "";
    String ToItm = "";
    boolean active, inactive;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSLJ0PID";
            String Cmp = GET.Cmp(this);
            FrmItm = cmd.in.map.texts.get(MAP.BSSI.FROM);
            ToItm = cmd.in.map.texts.get(MAP.BSSI.TO);
            active = cmd.in.map.booleans.get(MAP.BSSI.ACTIVE).booleanValue();
            inactive = cmd.in.map.booleans.get(MAP.BSSI.INACTIVE).booleanValue();
            PI_LJ0PID.select(this, rsName, Cmp, FrmItm, ToItm, active, inactive);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSLJ0PID.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSLJ0PID.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmItm = cmd.data.ifNull(FrmItm, "-");
        ToItm = cmd.data.ifNull(ToItm, "-");
        String reportSubTitle = "BSLJ0PID.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmItm, ToItm);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSLJ0PID.title";
    }
}
