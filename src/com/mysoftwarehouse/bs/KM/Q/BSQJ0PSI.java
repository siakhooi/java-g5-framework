/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.Q;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PSI.PSI_QJ0PSI;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSQJ0PSI extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = BSQJ0PSI.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSQX0PSI.jasper");
        super.loadCompiledReport(in);
    }
    String FrmSpcInst = "";
    String ToSpcInst = "";
    boolean active, inactive;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSQJ0PSI";
            String Cmp=GET.Cmp(this);
            FrmSpcInst = cmd.in.map.texts.get(MAP.BSPSI.FROM);
            ToSpcInst = cmd.in.map.texts.get(MAP.BSPSI.TO);
            active = cmd.in.map.booleans.get(MAP.BSPSI.ACTIVE).booleanValue();
            inactive = cmd.in.map.booleans.get(MAP.BSPSI.INACTIVE).booleanValue();

            PSI_QJ0PSI.select(this, rsName, Cmp, FrmSpcInst, ToSpcInst, active, inactive);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSQJ0PSI.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSQJ0PSI.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmSpcInst = cmd.data.ifNull(FrmSpcInst, "-");
        ToSpcInst = cmd.data.ifNull(ToSpcInst, "-");
        String reportSubTitle = "BSQJ0PSI.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmSpcInst, ToSpcInst);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSQJ0PSI.title";
    }
}
