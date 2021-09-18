/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.H;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.conf.REPORT;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.QTT.QTT_HJ3QTTD;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSHJ3QTTD extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        String r = REPORT.REPORT_PATH + "BSHX3QTTD.jasper";
        InputStream in = BSHJ3QTTD.class.getResourceAsStream(r);
        super.loadCompiledReport(in);
    }
    String FrmCus = "";
    String ToCus = "";
    boolean active, inactive;

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "BSHJ3QTTD";
            FrmCus = cmd.in.map.texts.get(MAP.BSCUS.FROM);
            ToCus = cmd.in.map.texts.get(MAP.BSCUS.TO);
            active = cmd.in.map.booleans.get(MAP.BSCUS.ACTIVE).booleanValue();
            inactive = cmd.in.map.booleans.get(MAP.BSCUS.INACTIVE).booleanValue();
            String Cmp = GET.Cmp(this);
            QTT_HJ3QTTD.select(this, rsName, Cmp, FrmCus, ToCus, active, inactive);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("BSHJ3QTTD.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        String Cmp = GET.Cmp(this);
        super.putParameter(REPORT.USER_ID, Cmp);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "BSHJ3QTTD.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        FrmCus = cmd.data.ifNull(FrmCus, "-");
        ToCus = cmd.data.ifNull(ToCus, "-");
        String reportSubTitle = "BSHJ3QTTD.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, FrmCus, ToCus);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "BSHJ3QTTD.title";
    }
}
