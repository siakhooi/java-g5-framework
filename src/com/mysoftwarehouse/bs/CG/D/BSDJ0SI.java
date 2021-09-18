/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.D;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.SI.SI_DJ0SI;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSDJ0SI extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = BSDJ0SI.class.getResourceAsStream(
                REPORT.REPORT_PATH + "BSDX0SI.jasper");
        super.loadCompiledReport(in);
    }
    String FrmItm = "";
    String ToItm = "";
    boolean active, inactive;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSDJ0SI";
            String Cmp = GET.Cmp(this);
            FrmItm = cmd.in.map.texts.get(MAP.BSSI.FROM);
            ToItm = cmd.in.map.texts.get(MAP.BSSI.TO);
            active = cmd.in.map.booleans.get(MAP.BSSI.ACTIVE).booleanValue();
            inactive = cmd.in.map.booleans.get(MAP.BSSI.INACTIVE).booleanValue();
            SI_DJ0SI.select(this, rsName, Cmp, FrmItm, ToItm, active, inactive);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSDJ0SI.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSDJ0SI.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmItm = cmd.data.ifNull(FrmItm, "-");
        ToItm = cmd.data.ifNull(ToItm, "-");
        String reportSubTitle = "BSDJ0SI.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmItm, ToItm);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSDJ0SI.title";
    }
}
