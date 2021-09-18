/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.design;

import com.gqrsoft.g5.experimental.reportdesign.g5abstract.AbstractChartDesign;
import com.gqrsoft.g5.experimental.reportdesign.g5abstract.ReportFactory;
import com.gqrsoft.g5.experimental.reportdesign.g5public.BarcodeEnum;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ElementDesignEnum;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ReportDesignEnum;
import com.gqrsoft.g5.experimental.reportdesign.g5public.StyleDesignEnum;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignBreak;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignEllipse;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignLine;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignRectangle;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JRDesignSubreportParameter;
import net.sf.jasperreports.engine.design.JRDesignSubreportReturnValue;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class ElementGroupDesign {

    public JRDesignElementGroup elementGroup;
    protected JRDesignElement currentElement;
    protected JRDesignTextField currentTextField;
    protected JRDesignRectangle currentRectangle;
    protected JRDesignImage currentImage;
    protected JRDesignSubreport currentSubreport;
    protected int x = 0;
    protected int y = 0;
    protected int h = 0;
    protected int w = 0;
    protected JasperDesign jasperDesign;
    public HashMap<String, ReportDesign> allSubreports;

    public void init(JasperDesign jd, JRDesignElementGroup eg, HashMap<String, ReportDesign> allSubreports) {
        this.jasperDesign = jd;
        elementGroup = eg;
        this.allSubreports = allSubreports;
    }

    protected final void setX(int x) {
        int ew = currentElement.getWidth();
        currentElement.setX(x);
        this.x = x + ew;
    }

    protected final void setY(int y) {
        currentElement.setY(y);
        this.y = y;
    }

    protected final void setW(int w) {
        int ex = currentElement.getX();
        int ew = currentElement.getWidth();
        currentElement.setWidth(w);
        this.x = ex - ew + w;
    }

    protected final void setH(int h) {
        currentElement.setHeight(h);
    }

    protected final void setXYWH(int x, int y, int w, int h) {
        setX(x);
        setY(y);
        setW(w);
        setH(h);
    }

    protected final void setXY(int x, int y) {
        setX(x);
        setY(y);
    }

    protected final void setWH(int w, int h) {
        setW(w);
        setH(h);
    }

    protected final void setElementStyle(String styleName) {
        JRDesignStyle style = (JRDesignStyle) jasperDesign.getStylesMap().get(styleName);
        currentElement.setStyle(style);
    }

    protected final void newLine() {
        y = y + h;
        x = 0;
    }

    protected final int getAvailablePageWidth() {
        return jasperDesign.getPageWidth() -
                jasperDesign.getLeftMargin() -
                jasperDesign.getRightMargin();
    }

    protected final void setElementPrintWhenExpression(String exp) {
        JRDesignExpression j = ReportFactory.createExpression(Boolean.class, exp);
        currentElement.setPrintWhenExpression(j);
    }

    protected final void setElementPrintWhenGroupChanges(String groupName) {
        JRDesignGroup g = (JRDesignGroup) jasperDesign.getGroupsMap().get(groupName);
        currentElement.setPrintWhenGroupChanges(g);
    }

    protected final void setElementPrintRepeatedValues(boolean v) {
        currentElement.setPrintRepeatedValues(v);
    }

    protected final void setElementPrintInFirstWholeBand(boolean v) {
        currentElement.setPrintInFirstWholeBand(v);
    }

    protected final void setElementPrintWhenDetailOverflows(boolean v) {
        currentElement.setPrintWhenDetailOverflows(v);
    }

    protected final void setElementRemoveLineWhenBlank(boolean v) {
        currentElement.setRemoveLineWhenBlank(v);
    }

    protected final void setElementPositionType(ElementDesignEnum.PositionType p) {
        currentElement.setPositionType(p.value);
    }

    protected final void setElementStretchType(ElementDesignEnum.StretchType p) {
        currentElement.setStretchType(p.value);
    }

    protected final void addBreak(ElementDesignEnum.BreakType v) {
        addBreak(v, y + h);
    }

    protected final void addBreak(ElementDesignEnum.BreakType v, int y) {
        JRDesignBreak b = new JRDesignBreak();
        b.setType(v.value);
        b.setY(y);
        this.y = y + 1;
        currentElement = b;
        elementGroup.addElement(b);
    }

    protected final void addPageBreak() {
        addBreak(ElementDesignEnum.BreakType.PAGE);
    }

    protected final void addPageBreak(int y) {
        addBreak(ElementDesignEnum.BreakType.PAGE, y);
    }

    protected final void addColumnBreak() {
        addBreak(ElementDesignEnum.BreakType.COLUMN);
    }

    protected final void addColumnBreak(int y) {
        addBreak(ElementDesignEnum.BreakType.COLUMN, y);
    }

    protected final void addStaticText(String text) {
        addStaticText(text, w, h);
    }

    protected final void addStaticText(String text, int w) {
        addStaticText(text, w, h);
    }

    protected final void addStaticText(String text, int w, int h) {
        JRDesignStaticText t = new JRDesignStaticText();
        t.setText(text);
        currentElement = t;
        elementGroup.addElement(t);
        this.setXYWH(x, y, w, h);
        this.x = x + w;
    }

    protected final void addTextField(String expression, Class clazz) {
        addTextField(expression, clazz, w, h);
    }

    protected final void addTextField(String expression, Class clazz, int w) {
        addTextField(expression, clazz, w, h);
    }

    protected final void addTextField(String expression, Class clazz, int w, int h) {
        JRDesignTextField t = new JRDesignTextField();
        JRDesignExpression e =
                ReportFactory.createExpression(clazz, expression);

        t.setExpression(e);
        currentElement = t;
        currentTextField = t;
        elementGroup.addElement(t);
        this.setXYWH(x, y, w, h);
        this.x = x + w;
    }

    protected final void setTextFieldPattern(String s) {
        currentTextField.setPattern(s);
    }

    protected final void setTextFieldBlankWhenNull(boolean v) {
        currentTextField.setBlankWhenNull(v);
    }

    protected final void setTextFieldStretchVerticalWithOverflow(boolean v) {
        currentTextField.setStretchWithOverflow(v);
    }

    protected final void setTextFieldEvaluationGroup(String groupName) {
        JRDesignGroup g = (JRDesignGroup) jasperDesign.getGroupsMap().get(groupName);
        currentTextField.setEvaluationGroup(g);
    }

    protected final void setTextFieldEvaluationTime(ElementDesignEnum.EvaluationTime t) {
        currentTextField.setEvaluationTime(t.value);
    }

    protected final void addLine(ElementDesignEnum.LineDirection v, int w, int h) {
        addLine(v, x, y, w, h);
    }

    protected final void addLine(ElementDesignEnum.LineDirection v, int x, int y, int w, int h) {
        JRDesignLine d = new JRDesignLine();
        d.setDirection(v.value);
        currentElement = d;
        elementGroup.addElement(d);
        this.setXYWH(x, y, w, h);
        this.x = x + w;
    }

    protected final void addRectangle(int w) {
        addRectangle(x, y, w, h);
    }

    protected final void addRectangle(int w, int h) {
        addRectangle(x, y, w, h);
    }

    protected final void addRectangle(int x, int y, int w, int h) {
        JRDesignRectangle d = new JRDesignRectangle();
        currentElement = d;
        currentRectangle = d;
        elementGroup.addElement(d);
        this.setXYWH(x, y, w, h);
        this.x = x + w;
    }

    protected final void setRectangleRadius(int r) {
        currentRectangle.setRadius(r);
    }

    protected final void addEllipse(int w, int h) {
        addEllipse(x, y, w, h);
    }

    protected final void addEllipse(int x, int y, int w, int h) {
        JRDesignEllipse d = new JRDesignEllipse(jasperDesign);
        currentElement = d;
        elementGroup.addElement(d);
        this.setXYWH(x, y, w, h);
        this.x = x + w;
    }

    /**
     * 
     * @param w
     * @param h
     * @param clazz - either String/File/URL/InputStream/Image
     * @param expression
     */
    protected final void addImage(int w, int h,
            Class clazz, String expression) {
        addImage(x, y, w, h, clazz, expression);
    }

    protected final void addImage(int x, int y, int w, int h,
            Class clazz, String expression) {
        JRDesignImage d = new JRDesignImage(jasperDesign);
        JRDesignExpression e = ReportFactory.createExpression(clazz, expression);
        d.setExpression(e);
        currentElement = d;
        currentImage = d;
        elementGroup.addElement(d);
        this.setXYWH(x, y, w, h);
        this.x = x + w;
    }

    protected final void addBarcode(int x, int y, int w, int h, BarcodeEnum.Type1 t,
            int barcodeWidth, int barcodeHeight,
            String textExpression, boolean showText) {
        String exp = ReportFactory.getBarcodeExpression(
                t.type, textExpression, showText, false,
                "null", barcodeWidth, barcodeHeight);
        addImage(x, y, w, h, BufferedImage.class, exp);
    }

    protected final void addBarcode(int w, int h, BarcodeEnum.Type1 t,
            int barcodeWidth, int barcodeHeight,
            String textExpression, boolean showText) {
        addBarcode(x, y, w, h, t, barcodeWidth, barcodeHeight,
                textExpression, showText);
    }

    protected final void addBarcode(int x, int y, int w, int h, BarcodeEnum.Type2 t,
            int barcodeWidth, int barcodeHeight,
            String textExpression, boolean showText, boolean checksum) {
        String exp = ReportFactory.getBarcodeExpression(
                t.type, textExpression, showText, checksum,
                "null", barcodeWidth, barcodeHeight);
        addImage(x, y, w, h, BufferedImage.class, exp);
    }

    protected final void addBarcode(int w, int h, BarcodeEnum.Type2 t,
            int barcodeWidth, int barcodeHeight,
            String textExpression, boolean showText, boolean checksum) {
        addBarcode(x, y, w, h, t, barcodeWidth, barcodeHeight,
                textExpression, showText, checksum);
    }

    protected final void addBarcode(int x, int y, int w, int h, BarcodeEnum.Type3 t,
            int barcodeWidth, int barcodeHeight,
            String textExpression, boolean showText, boolean checksum,
            String applicationIdentifier) {
        String exp = ReportFactory.getBarcodeExpression(
                t.type, textExpression, showText, checksum,
                applicationIdentifier, barcodeWidth, barcodeHeight);
        addImage(x, y, w, h, BufferedImage.class, exp);
    }

    protected final void addBarcode(int w, int h, BarcodeEnum.Type3 t,
            int barcodeWidth, int barcodeHeight,
            String textExpression, boolean showText, boolean checksum,
            String applicationIdentifier) {
        addBarcode(x, y, w, h, t, barcodeWidth, barcodeHeight,
                textExpression, showText, checksum, applicationIdentifier);
    }

    protected final void setImageLazyLoad(boolean value) {
        currentImage.setLazy(value);
    }

    protected final void setImageOnErrorType(ElementDesignEnum.ImageOnErrorType d) {
        currentImage.setOnErrorType(d.value);
    }

    protected final void setImageScaleImage(StyleDesignEnum.ScaleImage d) {
        currentImage.setScaleImage(d.value);
    }

    protected final void addSubreport(
            int w, int h, Class clazz, String expression) {
        addSubreport(x, y, w, h, clazz, expression);
    }

    /**
     * 
     * @param x 
     * @param y 
     * @param w 
     * @param h 
     * @param clazz - either String/File/URL/InputStream/JasperReport
     * @param expression
     */
    protected final void addSubreport(
            int x, int y, int w, int h, Class clazz, String expression) {
        JRDesignSubreport d = new JRDesignSubreport(jasperDesign);
        JRDesignExpression e = ReportFactory.createExpression(clazz,
                expression);
        d.setExpression(e);
        currentElement = d;
        currentSubreport = d;
        elementGroup.addElement(d);
        this.setXYWH(x, y, w, h);
        this.x = x + w;
//
//        if (useDBConnection()) {
//            setSubreportConnectionExpression(
//                    JRDesignParameter.REPORT_CONNECTION);
//        } else {
//            setSubreportConnectionExpression(
//                    JRDesignParameter.REPORT_DATA_SOURCE);
//        }
    }

    private String getParameterExpression(String parameterName) {
        return "$P{" + parameterName + "}";
    }

    protected final void addSubreport(
            int w, int h, String parameterName, ReportDesign rd) {
        addSubreport(x, y, w, h, parameterName, rd);
    }

    protected final void addSubreport(
            int x, int y, int w, int h, String parameterName, ReportDesign rd) {
        JRDesignSubreport d = new JRDesignSubreport(jasperDesign);
        JRDesignExpression e = ReportFactory.createExpression(
                JasperReport.class, getParameterExpression(parameterName));
        d.setExpression(e);
        currentElement = d;
        currentSubreport = d;
        elementGroup.addElement(d);
        rd.init();
        allSubreports.putAll(rd.allSubreports);
        allSubreports.put(parameterName, rd);

        this.setXYWH(x, y, w, h);
        this.x = x + w;

//        if (useDBConnection()) {
    }

    protected final void addSubreportParameter(
            String parameterName, Class clazz, String expression) throws JRException {
        JRDesignSubreportParameter d = new JRDesignSubreportParameter();
        d.setName(parameterName);
        JRDesignExpression e = ReportFactory.createExpression(clazz, expression);
        d.setExpression(e);
        currentSubreport.addParameter(d);
    }

    protected final void addSubreportReturnValue(
            String variableName,
            String toParentVariableName,
            ReportDesignEnum.CalculationType t) {
        JRDesignSubreportReturnValue d = new JRDesignSubreportReturnValue();
        d.setSubreportVariable(variableName);
        d.setToVariable(toParentVariableName);
        d.setCalculation(t.value);
        currentSubreport.addReturnValue(d);
    }

    protected final void setSubreportDataSourceExpression(Class clazz, String expression) {
        JRDesignExpression e = ReportFactory.createExpression(clazz, expression);
        currentSubreport.setDataSourceExpression(e);
    }

    protected final void setSubreportDataSourceExpression(String expression) {
        setSubreportDataSourceExpression(JRDataSource.class, expression);
    }

    protected final void setSubreportUseDefaultDataSource() {
        setSubreportDataSourceExpression(
                JRDataSource.class, JRDesignParameter.REPORT_DATA_SOURCE);
    }

    /**
     * 
     * @param clazz
     * @param expression  $P{REPORT_CONNECTION}
     */
    protected final void setSubreportConnectionExpression(Class clazz, String expression) {
        JRDesignExpression e = ReportFactory.createExpression(clazz, expression);
        currentSubreport.setConnectionExpression(e);
    }

    protected final void setSubreportConnectionExpression(String expression) {
        setSubreportConnectionExpression(java.sql.Connection.class, expression);
    }

    protected final void setSubreportUseDefaultConnection() {
        setSubreportConnectionExpression(
                java.sql.Connection.class, JRDesignParameter.REPORT_CONNECTION);
    }

    protected final void setSubreportUsingCache(boolean value) {
        currentSubreport.setUsingCache(new Boolean(value));
    }

    protected final void addChart(int w, int h, AbstractChartDesign cd) {
        addChart(x, y, w, h, cd);
    }

    protected final void addChart(int x, int y, int w, int h, AbstractChartDesign cd) {
        currentElement = cd.chart;
        elementGroup.addElement(cd.chart);
        this.setXYWH(x, y, w, h);
        this.x = x + w;
    }
}
