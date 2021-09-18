/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.J;

import com.mysoftwarehouse.bs.HJ.H.*;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.RCP.RCP_JJ3RCPD;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSJJ3RCPD extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        String r = REPORT.REPORT_PATH + "BSJX3RCPD.jasper";
        InputStream in = BSJJ3RCPD.class.getResourceAsStream(r);
        super.loadCompiledReport(in);
    }
    String FrmCus = "";
    String ToCus = "";
    boolean active, inactive;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSJJ3RCPD";
            FrmCus = cmd.in.map.texts.get(MAP.BSCUS.FROM);
            ToCus = cmd.in.map.texts.get(MAP.BSCUS.TO);
            active = cmd.in.map.booleans.get(MAP.BSCUS.ACTIVE).booleanValue();
            inactive = cmd.in.map.booleans.get(MAP.BSCUS.INACTIVE).booleanValue();
            String Cmp =GET.Cmp(this);
            RCP_JJ3RCPD.select(this, rsName, Cmp, FrmCus, ToCus, active, inactive);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSJJ3RCPD.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSJJ3RCPD.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmCus = cmd.data.ifNull(FrmCus, "-");
        ToCus = cmd.data.ifNull(ToCus, "-");
        String reportSubTitle = "BSJJ3RCPD.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmCus, ToCus);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSJJ3RCPD.title";
    }
}
