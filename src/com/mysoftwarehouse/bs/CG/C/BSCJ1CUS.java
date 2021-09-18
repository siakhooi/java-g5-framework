/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.C;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.CUS.CUS_CJ1CUS;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSCJ1CUS extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = BSCJ0CUS.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSCX1CUS.jasper");
        super.loadCompiledReport(in);
    }
    String frmCus = "";
    String toCus = "";
    boolean active, inactive;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSCJ1CUS";
            frmCus = cmd.in.map.texts.get(MAP.BSCUS.FROM);
            toCus = cmd.in.map.texts.get(MAP.BSCUS.TO);
            active = cmd.in.map.booleans.get(MAP.BSCUS.ACTIVE).booleanValue();
            inactive = cmd.in.map.booleans.get(MAP.BSCUS.INACTIVE).booleanValue();
            String Cmp =GET.Cmp(this);
            CUS_CJ1CUS.select(this, rsName, Cmp, frmCus, toCus, active, inactive);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSCJ1CUS.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp =GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSCJ1CUS.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        frmCus = cmd.data.ifNull(frmCus, "-");
        toCus = cmd.data.ifNull(toCus, "-");
        String reportSubTitle = "BSCJ1CUS.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, frmCus, toCus);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSCJ1CUS.title";
    }
}
