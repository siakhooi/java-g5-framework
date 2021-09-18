/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5abstract;

import com.gqrsoft.g5.experimental.reportdesign.design.DatasetDesign;
import com.gqrsoft.g5.experimental.reportdesign.design.ElementGroupDesign;
import com.gqrsoft.g5.experimental.reportdesign.design.ReportDesign;
import com.gqrsoft.g5.experimental.reportdesign.design.StyleDesign;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ReportDesignEnum;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractReportDesign {

    public JasperDesign jasperDesign;
    public HashMap<String, ReportDesign> allSubreports;
    private JRDesignBand currentBand;

    public abstract void definePageInfo();

    public abstract void defineImports();

    public abstract void defineDataset();

    public abstract void defineScriptlet();

    public abstract void defineStyles();

    public abstract void defineBands();

    public final void init() {
        jasperDesign = new JasperDesign();
        allSubreports = new HashMap<String, ReportDesign>();
        definePageInfo();
        defineImports();
        defineScriptlet();
        defineDataset();
        defineStyles();
        defineBands();
    }

    protected final void setReportName(String name) {
        jasperDesign.setName(name);
    }

    protected final void setReportPagination(boolean usePagination) {
        jasperDesign.setIgnorePagination(!usePagination);
    }

    protected final void setPageWidth(int w) {
        jasperDesign.setPageWidth(w);
    }

    protected final void setPageHeight(int h) {
        jasperDesign.setPageHeight(h);
    }

    protected final void setPageOrientation(ReportDesignEnum.Orientation v) {
        jasperDesign.setOrientation(v.value);
    }

    protected final void setPaperType(ReportDesignEnum.PaperType v) {
        jasperDesign.setPageWidth(v.width);
        jasperDesign.setPageHeight(v.height);
    }

    protected final void setPageTopMargin(int value) {
        jasperDesign.setTopMargin(value);
    }

    protected final void setPageBottomMargin(int value) {
        jasperDesign.setBottomMargin(value);
    }

    protected final void setPageLeftMargin(int value) {
        jasperDesign.setLeftMargin(value);
    }

    protected final void setPageRightMargin(int value) {
        jasperDesign.setRightMargin(value);
    }

    protected final void setReportShowSummaryOnNewPage(boolean isSummaryNewPage) {
        jasperDesign.setSummaryNewPage(isSummaryNewPage);
    }

    protected final void setReportShowTitleOnNewPage(boolean isTitleNewPage) {
        jasperDesign.setTitleNewPage(isTitleNewPage);
    }

    protected final void setReportWhenNoData(ReportDesignEnum.WhenNoDataType v) {
        jasperDesign.setWhenNoDataType(v.value);
    }

    protected final void setReportColumnCount(int n) {
        jasperDesign.setColumnCount(n);
    }

    protected final void setReportColumnSpacing(int n) {
        jasperDesign.setColumnSpacing(n);
    }

    protected final void setReportColumnWidth(int n) {
        jasperDesign.setColumnWidth(n);
    }

    protected final void setReportColumnPrintOrder(ReportDesignEnum.ColumnPrintOrder v) {
        jasperDesign.setPrintOrder(v.value);
    }

    protected final void setReportColumnFloatFooter(boolean value) {
        jasperDesign.setFloatColumnFooter(value);
    }

    protected final void addImports(String packageName) {
        jasperDesign.addImport(packageName);
    }

    protected final void setScriptlet(String className) {
        jasperDesign.setScriptletClass(className);
    }

    protected final void setMainDataset(DatasetDesign ds) {
        ds.init(true);
        jasperDesign.setMainDataset(ds.dataset);
    }

    protected final void addDataset(DatasetDesign ds) throws JRException {
        ds.init(false);
        jasperDesign.addDataset(ds.dataset);
    }

    protected final void setReportDefaultStyle(StyleDesign sd) {
        jasperDesign.setDefaultStyle(sd.style);
    }

    protected final void addStyle(StyleDesign sd) throws JRException {
        JRDesignStyle s = sd.style;
        jasperDesign.addStyle(s);
    }

    protected final void setTitleBand(ElementGroupDesign eg) {
        currentBand = new JRDesignBand();
        eg.init(jasperDesign, currentBand, allSubreports);
        jasperDesign.setTitle(currentBand);
    }

    protected final void setPageHeaderBand(ElementGroupDesign eg) {
        currentBand = new JRDesignBand();
        eg.init(jasperDesign, currentBand, allSubreports);
        jasperDesign.setPageHeader(currentBand);
    }

    protected final void setPageFooterBand(ElementGroupDesign eg) {
        currentBand = new JRDesignBand();
        eg.init(jasperDesign, currentBand, allSubreports);
        jasperDesign.setPageFooter(currentBand);
    }

    protected final void setLastPageFooterBand(ElementGroupDesign eg) {
        currentBand = new JRDesignBand();
        eg.init(jasperDesign, currentBand, allSubreports);
        jasperDesign.setLastPageFooter(currentBand);
    }

    protected final void setColumnHeaderBand(ElementGroupDesign eg) {
        currentBand = new JRDesignBand();
        eg.init(jasperDesign, currentBand, allSubreports);
        jasperDesign.setColumnHeader(currentBand);
    }

    protected final void setColumnFooterBand(ElementGroupDesign eg) {
        currentBand = new JRDesignBand();
        eg.init(jasperDesign, currentBand, allSubreports);
        jasperDesign.setColumnFooter(currentBand);
    }

    protected final void setDetailBand(ElementGroupDesign eg) {
        currentBand = new JRDesignBand();
        eg.init(jasperDesign, currentBand, allSubreports);
        jasperDesign.setDetail(currentBand);
    }

    protected final void setSummaryBand(ElementGroupDesign eg) {
        currentBand = new JRDesignBand();
        eg.init(jasperDesign, currentBand, allSubreports);
        jasperDesign.setSummary(currentBand);
    }

    protected final void setNoDataBand(ElementGroupDesign eg) {
        currentBand = new JRDesignBand();
        eg.init(jasperDesign, currentBand, allSubreports);
        jasperDesign.setNoData(currentBand);
    }

    protected final void setGroupHeaderBand(String groupName, ElementGroupDesign eg) {
        currentBand = new JRDesignBand();
        eg.init(jasperDesign, currentBand, allSubreports);
        JRDesignGroup g = (JRDesignGroup) jasperDesign.getGroupsMap().get(groupName);
        g.setGroupHeader(currentBand);
    }

    protected final void setGroupFooterBand(String groupName, ElementGroupDesign eg) {
        currentBand = new JRDesignBand();
        eg.init(jasperDesign, currentBand, allSubreports);
        JRDesignGroup g = (JRDesignGroup) jasperDesign.getGroupsMap().get(groupName);
        g.setGroupFooter(currentBand);
    }

    protected final void setBandHeight(int h) {
        currentBand.setHeight(h);
    }

    protected final void setBandPrintWhenExpressionTrue(String expression) {
        JRDesignExpression c = ReportFactory.createExpression(Boolean.class, expression);
        currentBand.setPrintWhenExpression(c);
    }

    protected final void setBandSplitAllow(boolean value) {
        currentBand.setSplitAllowed(value);
    }
}
