/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.O;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PIV.PIV_OJ3PIV;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSOJ3PIV extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        String r = REPORT.REPORT_PATH + "BSOX3PIV.jasper";
        InputStream in = BSOJ3PIV.class.getResourceAsStream(r);
        super.loadCompiledReport(in);
    }
    String FrmSup = "";
    String ToSup = "";
    boolean active, inactive;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSOJ3PIV";
            FrmSup = cmd.in.map.texts.get(MAP.BSSUP.FROM);
            ToSup = cmd.in.map.texts.get(MAP.BSSUP.TO);
            active = cmd.in.map.booleans.get(MAP.BSSUP.ACTIVE).booleanValue();
            inactive = cmd.in.map.booleans.get(MAP.BSSUP.INACTIVE).booleanValue();
            String Cmp = GET.Cmp(this);
            PIV_OJ3PIV.select(this, rsName, Cmp, FrmSup, ToSup, active, inactive);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSOJ3PIV.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSOJ3PIV.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmSup = cmd.data.ifNull(FrmSup, "-");
        ToSup = cmd.data.ifNull(ToSup, "-");
        String reportSubTitle = "BSOJ3PIV.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmSup, ToSup);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSOJ3PIV.title";
    }
}
