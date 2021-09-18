/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.Z_postpone;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.mysoftwarehouse.pfa.conf.REPORT;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.Calendar;

/**
 * This class in postponed to next version: Data Integrity Check
 * @author Ng Siak Hooi
 */
public class PFHJ0DBCHECK extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        InputStream in = PFHJ0DBCHECK.class.getResourceAsStream(
                "/com/mysoftwarehouse/pfa/H/PFHJ0DBCHECK.jasper");
        super.loadCompiledReport(in);
    }
    String id = "";

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        id = cmd.global.texts.get(USR.PFUSR_ID);
        ResultSet rs = cmd.in.map.sqlResultsets.get("DBCHECK");
        super.useResultSetDataSource(rs);
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
        super.putParameter(REPORT.USER_ID, id);

        Calendar printDte = cmd.cal.getNow();
        super.putParameter(REPORT.PRINT_DATE, printDte);

        String reportTitle = "PFHJ0DBCHECK.title";
        reportTitle = cmd.lang.getString(reportTitle);
        super.putParameter(REPORT.REPORT_TITLE, reportTitle);

        String reportSubTitle = "PFHJ0DBCHECK.subtitle";
        reportSubTitle = cmd.lang.getString(reportSubTitle);
        super.putParameter(REPORT.REPORT_SUBTITLE, reportSubTitle);

//        String endOfReport = REPORT.RB_END_OF_REPORT;
//        endOfReport = cmd.lang.getString(endOfReport);
//        super.putParameter(REPORT.END_OF_REPORT, endOfReport);
    }

    @Override
    public String getFormTitle() {
        return "PFHJ0DBCHECK.title";
    }

    @Override
    protected File getPdfFile() {
        return null;
    }
}
