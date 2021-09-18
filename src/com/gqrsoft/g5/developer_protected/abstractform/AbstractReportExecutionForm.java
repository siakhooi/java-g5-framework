/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.gqrsoft.g5.developer.publicobject.ReportExecutionException;
import com.gqrsoft.g5.kernel.core.util.CONSOLE;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractReportExecutionForm extends ProcessForm {

    private Map parameters;
    //private JasperDesign jasperDesign;
    private JasperReport jasperReport;
    //private ReportDesign reportDesign;
    private HashMap<String, JasperReport> allCompiledSubreports = new HashMap<String, JasperReport>();
    private JasperPrint jasperPrint;
    private int executionCount = 1;
    private java.sql.Connection dbConnection = null;
    private JRDataSource dataSource = null;

    /**
     * 
     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
     */
    protected abstract void initReport() throws ReportExecutionException;

    /**
     * 
     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
     */
    protected abstract void initExecution() throws ReportExecutionException;

    /**
     * 
     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
     */
    protected abstract void prepareExecution() throws ReportExecutionException;

    /**
     * 
     * @return
     */
    protected abstract File getPdfFile();

    /**
     * 
     * @param enterType
     */
    protected final void setExecutionMethod(FormEnum.EnterType enterType) {
        cmd.in.formEnterType = enterType;
    }

    /**
     * 
     * @param n
     */
    protected final void setExecutionCount(int n) {
        this.executionCount = n;
    }

//    /**
//     * 
//     * @param rs
//     */
//    protected final void loadReport(ReportDesign rs) {
//        rs.init();
//        jasperDesign = rs.jasperDesign;
//        jasperReport = null;
//        reportDesign = rs;
////        rs.allSubreports;
//    }

//    /**
//     * 
//     * @param is
//     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
//     */
//    protected final void loadReport(InputStream is) throws ReportExecutionException {
//        try {
//            jasperDesign = JRXmlLoader.load(is);
//            jasperReport = null;
//            reportDesign = null;
//        } catch (JRException ex) {
//            String msg = "ReportExecutionForm.error.loadReport.InputStream";
//            msg = cmd.lang.getSystemString(msg);
//            throw new ReportExecutionException(msg, ex);
//        }
//    }

//    /**
//     * 
//     * @param sourceFile
//     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
//     */
//    protected final void loadReport(File sourceFile) throws ReportExecutionException {
//        try {
//            jasperDesign = JRXmlLoader.load(sourceFile);
//            jasperReport = null;
//            reportDesign = null;
//        } catch (JRException ex) {
//            String msg = "ReportExecutionForm.error.loadReport.File";
//            msg = cmd.lang.getSystemString(msg);
//            throw new ReportExecutionException(msg, ex);
//        }
//    }
    /**
     * 
     * @param is
     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
     */
    protected final void loadCompiledReport(InputStream is) throws ReportExecutionException {
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(is);
//            jasperDesign = null;
//            reportDesign = null;
        } catch (JRException ex) {
            String msg = "ReportExecutionForm.error.loadCompiledReport.InputStream";
            msg = cmd.lang.getSystemString(msg);
            throw new ReportExecutionException(msg, ex);
        }
    }

    /**
     * 
     * @param sourceFile
     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
     */
    protected final void loadCompiledReport(File sourceFile) throws ReportExecutionException {
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(sourceFile);
//            jasperDesign = null;
//            reportDesign = null;
        } catch (JRException ex) {
            String msg = "ReportExecutionForm.error.loadCompiledReport.File";
            msg = cmd.lang.getSystemString(msg);
            throw new ReportExecutionException(msg, ex);
        }
    }

//    protected final void applyTemplate(ReportTemplateControl rtc, InputStream is) throws ReportExecutionException {
//        try {
//            JasperDesign jd = JRXmlLoader.load(is);
//            applyTemplate(rtc, jd);
//        } catch (JRException ex) {
//            String msg = "ReportExecutionForm.error.applyTemplate.InputStream";
//            msg = cmd.lang.getSystemString(msg);
//            throw new ReportExecutionException(msg, ex);
//        }
//    }
//
//    protected final void applyTemplate(ReportTemplateControl rtc, File sourceFile) throws ReportExecutionException {
//        try {
//            JasperDesign jd = JRXmlLoader.load(sourceFile);
//            applyTemplate(rtc, jd);
//        } catch (JRException ex) {
//            String msg = "ReportExecutionForm.error.applyTemplate.File";
//            msg = cmd.lang.getSystemString(msg);
//            throw new ReportExecutionException(msg, ex);
//        }
//    }
//
//    private final void applyTemplate(ReportTemplateControl rtc, JasperDesign jd) throws ReportExecutionException {
//        if (jasperDesign == null) {
//            String msg = "ReportExecutionForm.error.applyTemplate.SourceIsNull";
//            msg = cmd.lang.getSystemString(msg);
//            throw new ReportExecutionException(msg);
//        }
//        JasperDesignEntityMigrator.move(cmd, jasperDesign, jd);
//        if (rtc.replaceBackground) {
//            jasperDesign.setBackground(jd.getBackground());
//        }
//        if (rtc.replaceTitle) {
//            jasperDesign.setTitle(jd.getTitle());
//        }
//        if (rtc.replacePageFooter) {
//            jasperDesign.setPageFooter(jd.getPageFooter());
//        }
//        if (rtc.replacePageHeader) {
//            jasperDesign.setPageHeader(jd.getPageHeader());
//        }
//        if (rtc.replaceLastPageFooter) {
//            jasperDesign.setLastPageFooter(jd.getLastPageFooter());
//        }
//        if (rtc.replaceNoData) {
//            jasperDesign.setNoData(jd.getNoData());
//        }
//    }
    @Override
    public final void init() {
        super.setUserManualStart(false);
        super.setUserManualClose(false);
        super.setUserAllowCancel(true);

        String i18nTitle = "";
        i18nTitle = "ReportExecutionForm.process.InitializeReport";
        i18nTitle = cmd.lang.getSystemString(i18nTitle);
        addI18nProcess(i18nTitle, 1);
//        i18nTitle = "ReportExecutionForm.process.CompileReportDesign";
//        i18nTitle = cmd.lang.getSystemString(i18nTitle);
//        addI18nProcess(i18nTitle, 2);
//        i18nTitle = "ReportExecutionForm.process.CompileSubreportDesign";
//        i18nTitle = cmd.lang.getSystemString(i18nTitle);
//        addI18nProcess(i18nTitle, 2);
        i18nTitle = "ReportExecutionForm.process.ExecuteReport";
        i18nTitle = cmd.lang.getSystemString(i18nTitle);
        addI18nProcess(i18nTitle, 3);
    }

    @Override
    public final void run() throws ProcessException {
        try {
            initReport();
            completed();
//            compileReportDesign();
//            completed();
//            compileSubreportDesign();
//            completed();
            executeReport();
            completed();
        } catch (ReportExecutionException ex) {

            String message = ex.getMessage();
            //ex.printStackTrace();

            setI18nMessage(message);
            throw new ProcessException(false, message);
        }

    }

//    private void compileReportDesign() throws ReportExecutionException {
//        if (jasperDesign == null) {
//            return;
//        }
//        try {
//            jasperReport = JasperCompileManager.compileReport(
//                    jasperDesign);
//        } catch (JRException ex) {
//            String msg = "ReportExecutionForm.error.CompileReportDesign";
//            msg = cmd.lang.getSystemString(msg);
//            throw new ReportExecutionException(msg, ex);
//        }
//    }
//
//    private void compileSubreportDesign() throws ReportExecutionException {
//        allCompiledSubreports = new HashMap<String, JasperReport>();
//        if (reportDesign == null) {
//            return;
//        }
//        try {
//            for (String keyName : reportDesign.allSubreports.keySet()) {
//                ReportDesign rd = reportDesign.allSubreports.get(keyName);
//                JasperReport jr = JasperCompileManager.compileReport(
//                        rd.jasperDesign);
//                allCompiledSubreports.put(keyName, jr);
//            }
//        } catch (JRException ex) {
//            String msg = "ReportExecutionForm.error.CompileSubreportDesign";
//            msg = cmd.lang.getSystemString(msg);
//            throw new ReportExecutionException(msg, ex);
//        }
//    }
    private void executeReport() throws ProcessException, ReportExecutionException {
        useDefaultConnection();
        prepareExecution();
        setMinorTotalCount(executionCount * 5);
        for (int i = 0; i < executionCount; i++) {
            prepareParameters();
            initExecution();
            minorCompleted();
            fillReport();
            minorCompleted(2);
            printReport();
            minorCompleted(2);
        }
    }

    @SuppressWarnings("unchecked")
    private void prepareParameters() {
        parameters = new HashMap();
        parameters.putAll(allCompiledSubreports);
    //ResourceBundle rb = cmd.lang.getApplicationResourceBundle();
    //parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, rb);
    }

    @SuppressWarnings("unchecked")
    protected final void putResourceBundle(String baseName) {
        String name = JRParameter.REPORT_RESOURCE_BUNDLE;
        ResourceBundle value = cmd.lang.getResourceBundle(baseName);
        parameters.put(name, value);
    }

    private void fillReport() throws ReportExecutionException {
        try {
            if (dbConnection != null) {
                jasperPrint = JasperFillManager.fillReport(
                        jasperReport,
                        parameters,
                        dbConnection);
            } else if (dataSource != null) {
                jasperPrint = JasperFillManager.fillReport(
                        jasperReport,
                        parameters,
                        dataSource);
            } else {
                cmd.log.log(Level.SEVERE, "no data!");
                CONSOLE.error("no data!");
            //error: no data
            }
        } catch (JRException ex) {
            String msg = "ReportExecutionForm.error.FillReport";
            msg = cmd.lang.getSystemString(msg);
            cmd.log.log(Level.SEVERE, msg, ex);
            throw new ReportExecutionException(msg, ex);

        }
    }

    private void printReport() throws ReportExecutionException {
        try {
            switch (cmd.in.formEnterType) {
                case REPORTQUICKPRINT:
                    JasperPrintManager.printReport(
                            jasperPrint, false);
                    break;
                case REPORTSAVEASPDF:
                    File i = getPdfFile();
                    if (i == null) {
                        return;
                    }
                    JasperExportManager.exportReportToPdfFile(
                            jasperPrint, i.getAbsolutePath());
                    break;
                case REPORTPRINT:
                    JasperPrintManager.printReport(
                            jasperPrint, true);
                    break;
                case REPORTVIEW:
                default:
                    JasperViewer.viewReport(jasperPrint, false,
                            cmd.lang.getCurrentLocale());
                    break;
            }
        } catch (JRException ex) {
            String msg = "ReportExecutionForm.error.PrintReport";
            msg = cmd.lang.getSystemString(msg);
            cmd.log.log(Level.SEVERE, msg, ex);
            throw new ReportExecutionException(msg, ex);
        }

    }

    /**
     * 
     */
    protected final void useDefaultConnection() {
        dbConnection = cmd.db.getThreadConnection();
        dataSource = null;
    }

    /**
     * 
     * @param cnt
     */
    protected final void useConnection(java.sql.Connection cnt) {
        dbConnection = cnt;
        dataSource = null;
    }

    /**
     * 
     * @param ds
     */
    protected final void useDataSource(JRDataSource ds) {
        dbConnection = null;
        dataSource = ds;
    }

    /**
     * 
     * @param rs
     */
    protected final void useResultSetDataSource(ResultSet rs) {
        useDataSource(new JRResultSetDataSource(rs));
    }

    /**
     * 
     * @param rsName
     */
    protected final void useResultSetDataSource(String rsName) {
        useResultSetDataSource(cmd.db.getResultSet(rsName));
    }

    /**
     * 
     * @param model
     */
    protected final void useDataSource(TableModel model) {
        useDataSource(new JRTableModelDataSource(model));
    }

    /**
     * 
     * @param count
     */
    protected final void useEmptyDataSource(int count) {
        useDataSource(new JREmptyDataSource(count));
    }

    /**
     * 
     * @param csvFile
     * @throws java.io.FileNotFoundException
     */
    protected final void useCsvDataSource(File csvFile) throws FileNotFoundException {
        JRCsvDataSource csv = new JRCsvDataSource(csvFile);
        csv.setUseFirstRowAsHeader(true);
        useDataSource(csv);
    }

    /**
     * 
     * @param csvFile
     * @param charsetName
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     */
    protected final void useCsvDataSource(File csvFile, String charsetName) throws FileNotFoundException, UnsupportedEncodingException {
        JRCsvDataSource csv = new JRCsvDataSource(csvFile, charsetName);
        csv.setUseFirstRowAsHeader(true);
        useDataSource(csv);
    }

    /**
     * 
     * @param is
     */
    protected final void useCsvDataSource(InputStream is) {
        JRCsvDataSource csv = new JRCsvDataSource(is);
        csv.setUseFirstRowAsHeader(true);
        useDataSource(csv);
    }

    /**
     * 
     * @param is
     * @param charsetName
     * @throws java.io.UnsupportedEncodingException
     */
    protected final void useCsvDataSource(InputStream is, String charsetName) throws UnsupportedEncodingException {
        JRCsvDataSource csv = new JRCsvDataSource(is, charsetName);
        csv.setUseFirstRowAsHeader(true);
        useDataSource(csv);
    }

    /**
     * 
     * @param csvText
     */
    protected final void useCsvDataSource(String csvText) {
        JRCsvDataSource csv = new JRCsvDataSource(new StringReader(csvText));
        csv.setUseFirstRowAsHeader(true);
        useDataSource(csv);
    }

    /**
     * 
     * @param ds
     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
     */
    protected final void useDataSource(String ds) throws ReportExecutionException {
        if (ds == null) {
            String msg = "ReportExecutionForm.error.useDataSource.ParameterIsNull";
            msg = cmd.lang.getSystemString(msg);
            throw new ReportExecutionException(msg);
        }
        if (!parameters.containsKey(ds)) {
            String msg = "ReportExecutionForm.error.useDataSource.ParameterNotFound";
            msg = cmd.lang.getSystemString(msg);
            throw new ReportExecutionException(msg);
        }
        Object b = parameters.get(ds);
        if (b instanceof JRDataSource) {
            dbConnection = null;
            dataSource = (JRDataSource) b;
        } else {
            String msg = "ReportExecutionForm.error.useDataSource.WrongParameter";
            msg = cmd.lang.getSystemString(msg);
            throw new ReportExecutionException(msg);
        }
    }

    /**
     * 
     * @param name
     * @param value
     */
    @SuppressWarnings("unchecked")
    protected final void putParameter(String name, Object value) {
        parameters.put(name, value);
    }
//    protected final void addDataSource(String name, String rsName) {
//        addDataSource(name, cmd.db.getSQLResultSet(rsName));
//    }
    /**
     * 
     * @param name
     * @param ds
     */
    protected final void addDataSource(String name, JRDataSource ds) {
        putParameter(name, ds);
    }

    /**
     * 
     * @param name
     * @param rs
     */
    protected final void putDataSource(String name, java.sql.ResultSet rs) {
        JRResultSetDataSource ds = new JRResultSetDataSource(rs);
        putParameter(name, ds);
    }

    /**
     * 
     * @param name
     * @param tm
     */
    protected final void putDataSource(String name, TableModel tm) {
        JRTableModelDataSource ds = new JRTableModelDataSource(tm);
        putParameter(name, ds);
    }

//    /**
//     * 
//     * @param name
//     * @param is
//     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
//     */
//    protected final void putSubreport(
//            String name, InputStream is) throws ReportExecutionException {
//        try {
//            JasperDesign d = JRXmlLoader.load(is);
//            JasperReport i = JasperCompileManager.compileReport(d);
//            putParameter(name, i);
//        } catch (JRException ex) {
//            String msg = "ReportExecutionForm.error.addSubreport.InputStream";
//            msg = cmd.lang.getSystemString(msg);
//            cmd.log.log(Level.SEVERE, msg, ex);
//            throw new ReportExecutionException(msg, ex);
//        }
//    }
//
//    /**
//     * 
//     * @param name
//     * @param sourceFile
//     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
//     */
//    protected final void putSubreport(
//            String name, File sourceFile) throws ReportExecutionException {
//        try {
//            JasperDesign d = JRXmlLoader.load(sourceFile);
//            JasperReport i = JasperCompileManager.compileReport(d);
//            putParameter(name, i);
//        } catch (JRException ex) {
//            String msg = "ReportExecutionForm.error.addSubreport.File";
//            msg = cmd.lang.getSystemString(msg);
//            cmd.log.log(Level.SEVERE, msg, ex);
//            throw new ReportExecutionException(msg, ex);
//        }
//    }
    /**
     * 
     * @param name
     * @param is
     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
     */
    protected final void putCompiledSubreport(
            String name, InputStream is) throws ReportExecutionException {
        try {
            JasperReport i = (JasperReport) JRLoader.loadObject(is);
            putParameter(name, i);
        } catch (JRException ex) {
            String msg = "ReportExecutionForm.error.addCompiledSubreport.InputStream";
            msg = cmd.lang.getSystemString(msg);
            cmd.log.log(Level.SEVERE, msg, ex);
            throw new ReportExecutionException(msg, ex);
        }
    }

    /**
     * 
     * @param name
     * @param sourceFile
     * @throws com.gqrsoft.g5.developer.publicobject.ReportExecutionException
     */
    protected final void putCompiledSubreport(
            String name, File sourceFile) throws ReportExecutionException {
        try {
            JasperReport i = (JasperReport) JRLoader.loadObject(sourceFile);
            putParameter(name, i);
        } catch (JRException ex) {
            String msg = "ReportExecutionForm.error.addCompiledSubreport.File";
            msg = cmd.lang.getSystemString(msg);
            cmd.log.log(Level.SEVERE, msg, ex);
            throw new ReportExecutionException(msg, ex);
        }
    }
}
