/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.chart;

import com.gqrsoft.g5.experimental.reportdesign.g5abstract.AbstractCategoryDatasetChartDesign;
import com.gqrsoft.g5.experimental.reportdesign.g5abstract.ReportFactory;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ChartDesignEnum;
import java.awt.Color;
import net.sf.jasperreports.charts.design.JRDesignBarPlot;
import net.sf.jasperreports.charts.design.JRDesignCategoryDataset;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class BarChartDesign extends AbstractCategoryDatasetChartDesign {

    protected JRDesignBarPlot barPlot;

    public BarChartDesign(JasperDesign jd, boolean stacked) {
        ChartDesignEnum.ChartType ct = stacked
                ? ChartDesignEnum.ChartType.STACKEDBAR
                : ChartDesignEnum.ChartType.BAR;
        super.chart = new JRDesignChart(jd, ct.chartType);
        super.dataset = (JRDesignChartDataset) chart.getDataset();
        super.categoryDataset = (JRDesignCategoryDataset) chart.getDataset();
        super.jasperDesign = jd;
        super.plot = chart.getPlot();
        barPlot = (JRDesignBarPlot) super.plot;
    }

    protected final void setShowLabels(boolean value) {
        barPlot.setShowLabels(value);
    }

    protected final void setShowTickLabels(boolean value) {
        barPlot.setShowTickLabels(value);
    }

    protected final void setShowTickMarks(boolean value) {
        barPlot.setShowTickMarks(value);
    }

//==== axis ===
    protected final void setCategoryAxisLabelColor(Color value) {
        barPlot.setCategoryAxisLabelColor(value);
    }

    protected final void setCategoryAxisLabelExpression(String value) {
        JRDesignExpression e =
                ReportFactory.createExpression(String.class, value);
        barPlot.setCategoryAxisLabelExpression(e);
    }

    protected final void setCategoryAxisLineColor(Color value) {
        barPlot.setCategoryAxisLineColor(value);
    }

    protected final void setCategoryAxisTickLabelColor(Color value) {
        barPlot.setCategoryAxisTickLabelColor(value);
    }

    protected final void setCategoryAxisTickLabelMask(String value) {
        barPlot.setCategoryAxisTickLabelMask(value);
    }

    protected final void setValueAxisLabelColor(Color value) {
        barPlot.setValueAxisLabelColor(value);
    }

    protected final void setValueAxisLabelExpression(String value) {
        JRDesignExpression e =
                ReportFactory.createExpression(String.class, value);
        barPlot.setValueAxisLabelExpression(e);
    }

    protected final void setValueAxisLineColor(Color value) {
        barPlot.setValueAxisLineColor(value);
    }

    protected final void setValueAxisTickLabelColor(Color value) {
        barPlot.setValueAxisTickLabelColor(value);
    }

    protected final void setValueAxisTickLabelMask(String value) {
        barPlot.setValueAxisTickLabelMask(value);
    }
}
