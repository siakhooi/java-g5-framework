/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m90;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import java.io.File;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloReportExecutionWithCompiledReport extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        String name = "/com/mysoftwarehouse/toolkit/demo/resources/reports/demo1.jasper";
        super.loadCompiledReport(
                HelloReportExecutionWithCompiledReport.class.getResourceAsStream(name));
    //setExecutionMethod(EnterType.REPORTSAVEASPDF);
    //setPDFFile(new File("/mysoftwarehouse/demo/sample.pdf"));
    //setExecutionMethod(EnterType.REPORTPRINT);
    }

    @Override
    public String getFormTitle() {
        return "HelloReportExecutionWithCompiledReport.title";
    }

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        super.useDefaultConnection();
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
    }

    @Override
    protected File getPdfFile() {
        return cmd.common.chooseSavePDFFile(null);
    }
}
