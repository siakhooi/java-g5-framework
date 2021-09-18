/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.chart;

import com.gqrsoft.g5.experimental.reportdesign.g5abstract.AbstractCategoryDatasetChartDesign;
import com.gqrsoft.g5.experimental.reportdesign.g5abstract.ReportFactory;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ChartDesignEnum;
import java.awt.Color;
import net.sf.jasperreports.charts.design.JRDesignBar3DPlot;
import net.sf.jasperreports.charts.design.JRDesignCategoryDataset;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class Bar3DChartDesign extends AbstractCategoryDatasetChartDesign {

    protected JRDesignBar3DPlot bar3DPlot;

    public Bar3DChartDesign(JasperDesign jd, boolean stacked) {
        ChartDesignEnum.ChartType ct = stacked
                ? ChartDesignEnum.ChartType.STACKEDBAR3D
                : ChartDesignEnum.ChartType.BAR3D;
        super.chart = new JRDesignChart(jd, ct.chartType);
        super.dataset = (JRDesignChartDataset) chart.getDataset();
        super.categoryDataset = (JRDesignCategoryDataset) chart.getDataset();
        super.jasperDesign = jd;
        super.plot = chart.getPlot();
        bar3DPlot = (JRDesignBar3DPlot) super.plot;
    }

    protected final void setShowLabels(boolean value) {
        bar3DPlot.setShowLabels(value);
    }

    protected final void setChartBar3DXOffset(double value) {
        bar3DPlot.setXOffset(value);
    }

    protected final void setChartBar3DYOffset(double value) {
        bar3DPlot.setYOffset(value);
    }

//==== axis ===
    protected final void setCategoryAxisLabelColor(Color value) {
        bar3DPlot.setCategoryAxisLabelColor(value);
    }

    protected final void setCategoryAxisLabelExpression(String value) {
        JRDesignExpression e =
                ReportFactory.createExpression(String.class, value);
        bar3DPlot.setCategoryAxisLabelExpression(e);
    }

    protected final void setCategoryAxisLineColor(Color value) {
        bar3DPlot.setCategoryAxisLineColor(value);
    }

    protected final void setCategoryAxisTickLabelColor(Color value) {
        bar3DPlot.setCategoryAxisTickLabelColor(value);
    }

    protected final void setCategoryAxisTickLabelMask(String value) {
        bar3DPlot.setCategoryAxisTickLabelMask(value);
    }

    protected final void setValueAxisLabelColor(Color value) {
        bar3DPlot.setValueAxisLabelColor(value);
    }

    protected final void setValueAxisLabelExpression(String value) {
        JRDesignExpression e =
                ReportFactory.createExpression(String.class, value);
        bar3DPlot.setValueAxisLabelExpression(e);
    }

    protected final void setValueAxisLineColor(Color value) {
        bar3DPlot.setValueAxisLineColor(value);
    }

    protected final void setValueAxisTickLabelColor(Color value) {
        bar3DPlot.setValueAxisTickLabelColor(value);
    }

    protected final void setValueAxisTickLabelMask(String value) {
        bar3DPlot.setValueAxisTickLabelMask(value);
    }
}
