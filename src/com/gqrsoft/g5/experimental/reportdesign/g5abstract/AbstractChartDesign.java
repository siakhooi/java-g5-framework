/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5abstract;

import com.gqrsoft.g5.experimental.reportdesign.g5public.ChartDesignEnum;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ElementDesignEnum;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ReportDesignEnum;
import java.awt.Color;
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetParameter;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractChartDesign {

    public JRDesignChart chart;
    protected JasperDesign jasperDesign;
    protected JRChartPlot plot;
    protected JRDesignChartDataset dataset;
    private JRDesignDatasetRun currentDatasetRun;

    protected abstract void init();

    protected final void setEvaluationGroup(String groupName) {
        JRDesignGroup g = (JRDesignGroup) jasperDesign.getGroupsMap().get(groupName);
        chart.setEvaluationGroup(g);
    }

    protected final void setEvaluationTime(ElementDesignEnum.EvaluationTime t) {
        chart.setEvaluationTime(t.value);
    }

    protected final void setShowLegend(boolean value) {
        chart.setShowLegend(value);
    }

    protected final void setLegendColor(Color value) {
        chart.setLegendColor(value);
    }

    protected final void setLegendBackgroundColor(Color value) {
        chart.setLegendBackgroundColor(value);
    }

    protected final void setLegendPosition(ChartDesignEnum.ChartElementPosition t) {
        chart.setLegendPosition(t.chartElementPosition);
    }

    protected final void setTitleColor(Color value) {
        chart.setTitleColor(value);
    }

    /**
     * 
     * @param expression evaluates to String
     */
    protected final void setTitleExpression(String expression) {
        JRDesignExpression t =
                ReportFactory.createExpression(String.class, expression);
        chart.setTitleExpression(t);
    }

    protected final void setTitlePosition(
            ChartDesignEnum.ChartElementPosition t) {
        chart.setTitlePosition(t.chartElementPosition);
    }

    protected final void setSubtitleColor(Color value) {
        chart.setSubtitleColor(value);
    }

    /**
     * 
     * @param expression evaluates to String
     */
    protected final void setSubtitleExpression(String expression) {
        JRDesignExpression t =
                ReportFactory.createExpression(String.class, expression);
        chart.setSubtitleExpression(t);
    }

    protected final void setBackColor(Color value) {
        plot.setBackcolor(value);
    }

    protected final void setBackgroundAlpha(float alpha) {
        plot.setBackgroundAlpha(alpha);
    }

    protected final void setForegroundAlpha(float alpha) {
        plot.setForegroundAlpha(alpha);
    }

    protected final void setLabelRotation(double rotation) {
        plot.setLabelRotation(rotation);
    }

    protected final void setOrientation(
            ChartDesignEnum.ChartOrientation orientation) {
        plot.setOrientation(orientation.chartOrientation);
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

    protected final void setDatasetRun(String datasetName) {
        if (!jasperDesign.getDatasetMap().containsKey(datasetName)) {
        //throw new ReportException("error.reportForm.setChartDatasetRun.DatasetNameNotFound");
        }
        currentDatasetRun = new JRDesignDatasetRun();
        currentDatasetRun.setDatasetName(datasetName);
        dataset.setDatasetRun(currentDatasetRun);
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
}
