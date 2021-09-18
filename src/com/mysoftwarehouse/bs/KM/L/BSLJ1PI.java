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
import com.mysoftwarehouse.bs.db.PI.PI_LJ1PI;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSLJ1PI extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = BSLJ1PI.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSLX1PI.jasper");
        super.loadCompiledReport(in);
    }
    String frmSup = "";
    String toSup = "";
    boolean active, inactive;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSLJ1PI";
            String Cmp = GET.Cmp(this);
            frmSup = cmd.in.map.texts.get(MAP.BSSUP.FROM);
            toSup = cmd.in.map.texts.get(MAP.BSSUP.TO);
            active = cmd.in.map.booleans.get(MAP.BSPI.ACTIVE).booleanValue();
            inactive = cmd.in.map.booleans.get(MAP.BSPI.INACTIVE).booleanValue();
            PI_LJ1PI.select(this, rsName, Cmp, frmSup, toSup, active, inactive);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSLJ1PI.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSLJ1PI.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        frmSup = cmd.data.ifNull(frmSup, "-");
        toSup = cmd.data.ifNull(toSup, "-");
        String reportSubTitle = "BSLJ1PI.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, frmSup, toSup);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSLJ1PI.title";
    }
}
