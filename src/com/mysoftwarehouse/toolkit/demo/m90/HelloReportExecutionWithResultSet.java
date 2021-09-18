/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m90;

import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloReportExecutionWithResultSet extends ReportExecutionForm {

    @Override
    protected void initReport() throws ReportExecutionException {
        String name = "/com/mysoftwarehouse/toolkit/demo/resources/reports/demo1.jasper";
        super.loadCompiledReport(HelloReportExecutionWithResultSet.class.getResourceAsStream(name));
    //setExecutionMethod(EnterType.REPORTSAVEASPDF);
    //setPDFFile(new File("/mysoftwarehouse/demo/sample.pdf"));
    //setExecutionMethod(EnterType.REPORTPRINT);
    }

    @Override
    public String getFormTitle() {
        return "HelloReportExecutionWithResultSet.title";
    }

    @Override
    protected void prepareExecution() throws ReportExecutionException {
        try {
            String sql = "select * from member where member like '%3'";
            cmd.db.setStatement("ps", sql);
            cmd.db.execQuery("ps", "rs");
            useResultSetDataSource(cmd.db.getResultSet("rs"));
        } catch (SQLException ex) {
            cmd.log.log(Level.SEVERE, "Error Retrieve Data", ex);
        }
    }

    @Override
    protected void initExecution() throws ReportExecutionException {
    //super.putParameter("APPLICATION_NAME", "HelloWorld");
    }

    @Override
    protected File getPdfFile() {
        return cmd.common.chooseSavePDFFile(null);
    }
}
