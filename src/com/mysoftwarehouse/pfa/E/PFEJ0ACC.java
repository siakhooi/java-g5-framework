/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.E;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.conf.REPORT;
import com.mysoftwarehouse.pfa.db.ACC.ACC_EJ0ACC;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFEJ0ACC extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = PFEJ0ACC.class.getResourceAsStream(
                REPORT.REPORT_PATH + "PFEX0ACC.jasper");
        super.loadCompiledReport(in);
    }
    String frmAcc = "";
    String toAcc = "";
    String id = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String rsName = "PFEJ0ACC";
            frmAcc = cmd.in.map.texts.get(ACC.FROMACC);
            toAcc = cmd.in.map.texts.get(ACC.TOACC);
            id = cmd.global.texts.get(USR.PFUSR_ID);
            ACC_EJ0ACC.select(this, rsName, id, frmAcc, toAcc);
            super.useResultSetDataSource(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFEJ0ACC.error", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        super.putParameter(REPORT.USER_ID, id);

        String printDte = cmd.cal.getISO8601FormattedNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "PFEJ0ACC.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        frmAcc = cmd.data.ifNull(frmAcc, "-");
        toAcc = cmd.data.ifNull(toAcc, "-");
        String reportSubTitle = "PFEJ0ACC.subtitle.{0}.{1}";
        reportSubTitle = cmd.lang.getString(reportSubTitle, frmAcc, toAcc);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

        super.putResourceBundle(REPORT.RESOURCEBUNDLENAME);
    }

    @Override
    public String getFormTitle() {
        return "PFEJ0ACC.title";
    }
}
