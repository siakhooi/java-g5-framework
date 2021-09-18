/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.D;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.conf.REPORT;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.UOM.UOM_DJ0UOM;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISDJ0UOM extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = ISDJ0UOM.class.getResourceAsStream(
                REPORT.REPORT_PATH + "ISDX0UOM.jasper");
        super.loadCompiledReport(in);
    }
    String FrmUom = "";
    String ToUom = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "ISDJ0UOM";
            String Whs = GET.Whs(this);
            FrmUom = cmd.in.map.texts.get(MAP.ISUOM.FROM);
            ToUom = cmd.in.map.texts.get(MAP.ISUOM.TO);
           UOM_DJ0UOM.select(this, rsName, Whs, FrmUom, ToUom);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("ISDJ0UOM.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Whs = GET.Whs(this);
        super.putParameter(REPORT.USER_ID, Whs);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "ISDJ0UOM.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmUom = cmd.data.ifNull(FrmUom, "-");
        ToUom = cmd.data.ifNull(ToUom, "-");
        String reportSubTitle = "ISDJ0UOM.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmUom, ToUom);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "ISDJ0UOM.title";
    }
}
