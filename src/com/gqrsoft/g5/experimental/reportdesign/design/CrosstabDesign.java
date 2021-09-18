/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.design;

import com.gqrsoft.g5.experimental.reportdesign.g5abstract.ReportFactory;
import com.gqrsoft.g5.experimental.reportdesign.g5public.CrosstabEnum;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ReportDesignEnum;
import java.util.HashMap;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabBucket;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabDataset;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabParameter;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDatasetParameter;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public class CrosstabDesign {

    JRDesignCrosstab crosstab = new JRDesignCrosstab();
    JRDesignCrosstabDataset dataset;
    private JRDesignCrosstabMeasure currentMeasure;
    private JasperDesign jasperDesign;
    private HashMap<String, ReportDesign> allSubreports;
    private JRDesignCrosstabGroup currentCrosstabGroup;
    private JRDesignCrosstabBucket currentCrosstabBucket;
    private JRDesignDatasetRun currentDatasetRun;

    public CrosstabDesign(JasperDesign jd, HashMap<String, ReportDesign> allSubreports) {
        this.jasperDesign = jd;
        this.allSubreports = allSubreports;
        dataset = crosstab.getDesignDataset();
    }

    protected final void setColumnBreakOffset(int n) {
        crosstab.setColumnBreakOffset(n);
    }

    protected final void setRunDirection(CrosstabEnum.RunDirection v) {
        crosstab.setRunDirection(v.direction);
    }

    protected final void setRepeatColumnHeaders(boolean value) {
        crosstab.setRepeatColumnHeaders(value);
    }

    protected final void setRepeatRowHeaders(boolean value) {
        crosstab.setRepeatRowHeaders(value);
    }

    protected final void addMeasure(String measureName) throws JRException {
        currentMeasure = new JRDesignCrosstabMeasure();
        currentMeasure.setName(measureName);
        crosstab.addMeasure(currentMeasure);
    }

    protected final void setMeasureCalculation(
            ReportDesignEnum.CalculationType t) {
        currentMeasure.setCalculation(t.value);
    }

    protected final void setMeasurePercentageOfType(
            CrosstabEnum.PercentageOfType t) {
        currentMeasure.setPercentageOfType(t.percentageOfType);
    }

    protected final void setMeasurePercentageCalculatorClassName(
            String name) {
        currentMeasure.setPercentageCalculatorClassName(name);
    }

    protected final void setMeasureValueExpression(
            Class clazz, String expression) {
        JRDesignExpression e =
                ReportFactory.createExpression(clazz, expression);
        currentMeasure.setValueExpression(e);
    }

    protected final void setMeasureValueClassName(String className) {
        currentMeasure.setValueClassName(className);
    }

    protected final void setHeader(ElementGroupDesign eg) {
        JRDesignCellContents c = new JRDesignCellContents();
        eg.init(jasperDesign, c, allSubreports);
        crosstab.setHeaderCell(c);
    }

    protected final void setWhenNoData(ElementGroupDesign eg) {
        JRDesignCellContents c = new JRDesignCellContents();
        eg.init(jasperDesign, c, allSubreports);
        crosstab.setWhenNoDataCell(c);
    }

    protected final void addCell(int w, int h, ElementGroupDesign eg,
            String colGroup, String rowGroup) throws JRException {
        JRDesignCrosstabCell d = new JRDesignCrosstabCell();
        d.setHeight(new Integer(h));
        d.setWidth(new Integer(w));
        if (colGroup != null) {
            d.setColumnTotalGroup(colGroup);
        }
        if (rowGroup != null) {
            d.setRowTotalGroup(rowGroup);
        }
        JRDesignCellContents c = new JRDesignCellContents();
        eg.init(jasperDesign, c, allSubreports);
        d.setContents(c);
        crosstab.addCell(d);
    }

    protected final void addColumnGroup(String groupName, int height,
            CrosstabEnum.ColumnGroupHeaderPosition t) throws JRException {
        JRDesignCrosstabColumnGroup d = new JRDesignCrosstabColumnGroup();
        d.setName(groupName);
        d.setHeight(height);
        d.setPosition(t.columnGroupHeaderPosition);
        crosstab.addColumnGroup(d);
        currentCrosstabGroup = d;
        JRDesignCrosstabBucket d1 = new JRDesignCrosstabBucket();
        d.setBucket(d1);
        currentCrosstabBucket = d1;

    }

    protected final void addRowGroup(String groupName, int width,
            CrosstabEnum.RowGroupHeaderPosition t) throws JRException {
        JRDesignCrosstabRowGroup d = new JRDesignCrosstabRowGroup();
        d.setName(groupName);
        d.setWidth(width);
        d.setPosition(t.rowGroupHeaderPosition);
        crosstab.addRowGroup(d);
        currentCrosstabGroup = d;
        JRDesignCrosstabBucket d1 = new JRDesignCrosstabBucket();
        d.setBucket(d1);
        currentCrosstabBucket = d1;
    }

    protected final void setGroupTotalPosition(
            CrosstabEnum.GroupTotalPosition t) {
        currentCrosstabGroup.setTotalPosition(t.groupTotalPosition);
    }

    protected final void setGroupBucketExpression(
            Class clazz, String expression) {
        JRDesignExpression e =
                ReportFactory.createExpression(clazz, expression);
        currentCrosstabBucket.setExpression(e);
    }

    /**
     * 
     * @param clazz - Comparator
     * @param expression
     */
    protected final void setGroupBucketComparatorExpression(
            Class clazz, String expression) {
        JRDesignExpression e =
                ReportFactory.createExpression(clazz, expression);
        currentCrosstabBucket.setComparatorExpression(e);
    }

    protected final void setGroupBucketOrder(
            CrosstabEnum.GroupBucketOrder t) {
        currentCrosstabBucket.setOrder(t.groupBucketOrder);
    }

    protected final void setGroupHeader(ElementGroupDesign eg) {
        JRDesignCellContents c = new JRDesignCellContents();
        eg.init(jasperDesign, c, allSubreports);
        currentCrosstabGroup.setHeader(c);
    }

    protected final void setGroupTotalHeader(ElementGroupDesign eg) {
        JRDesignCellContents c = new JRDesignCellContents();
        eg.init(jasperDesign, c, allSubreports);
        currentCrosstabGroup.setTotalHeader(c);
    }

    protected final void setDatasetResetType(ReportDesignEnum.ResetType r) {
        dataset.setResetType(r.value);
    }

    protected final void setDatasetResetGroupName(String groupName) {
        JRDesignGroup g =
                (JRDesignGroup) jasperDesign.getGroupsMap().get(groupName);
        dataset.setResetGroup(g);
    }

    protected final void setDatasetIncrementType(ReportDesignEnum.ResetType r) {
        dataset.setIncrementType(r.value);
    }

    protected final void setDatasetIncrementGroupName(String groupName) {
        JRDesignGroup g =
                (JRDesignGroup) jasperDesign.getGroupsMap().get(groupName);
        dataset.setIncrementGroup(g);
    }

    protected final void setDatasetIncrementWhenExpressionTrue(String expression) {
        JRDesignExpression c =
                ReportFactory.createExpression(Boolean.class, expression);
        dataset.setIncrementWhenExpression(c);
    }

    protected final void setDatasetDataPreSorted(boolean value) {
        dataset.setDataPreSorted(value);
    }

    protected final void setCrosstabDatasetRun(String datasetName) {
        if (!jasperDesign.getDatasetMap().containsKey(datasetName)) {
        //throw new ReportException("error.reportForm.setCrosstabDatasetRun.DatasetNameNotFound");
        }
        currentDatasetRun = new JRDesignDatasetRun();
        currentDatasetRun.setDatasetName(datasetName);
        dataset.setDatasetRun(currentDatasetRun);
//        if (useDBConnection()) {
//            setCrosstabDatasetRunConnectionExpression(
//                    JRDesignParameter.REPORT_CONNECTION);
//        } else {
//            setCrosstabDatasetRunDataSourceExpression(
//                    JRDesignParameter.REPORT_DATA_SOURCE);
//        }
    }

    protected final void setDatasetRunConnectionExpression(Class clazz, String expression) {
        JRDesignExpression e =
                ReportFactory.createExpression(clazz, expression);
        currentDatasetRun.setConnectionExpression(e);
    }

    protected final void setDatasetRunConnectionExpression(String expression) {
        setDatasetRunConnectionExpression(java.sql.Connection.class, expression);
    }

    protected final void setDatasetRunDataSourceExpression(Class clazz, String expression) {
        JRDesignExpression e =
                ReportFactory.createExpression(clazz, expression);
        currentDatasetRun.setDataSourceExpression(e);
    }

    protected final void setDatasetRunDataSourceExpression(String expression) {
        setDatasetRunDataSourceExpression(JRDataSource.class, expression);
    }

    protected final void addDatasetRunParameter(String name, Class clazz, String expression) throws JRException {
        JRDesignExpression e =
                ReportFactory.createExpression(clazz, expression);
        JRDesignDatasetParameter p = new JRDesignDatasetParameter();
        p.setName(name);
        p.setExpression(e);
        currentDatasetRun.addParameter(p);
    }

    protected final void addParameter(String name, Class clazz,
            String defaultValueExpression, String expression) throws JRException {
        JRDesignCrosstabParameter f = new JRDesignCrosstabParameter();
        f.setName(name);
        f.setValueClass(clazz);
        JRDesignExpression a =
                ReportFactory.createExpression(clazz, defaultValueExpression);
        f.setDefaultValueExpression(a);
        JRDesignExpression a2 =
                ReportFactory.createExpression(clazz, expression);
        f.setExpression(a2);
        crosstab.addParameter(f);
    }
}
