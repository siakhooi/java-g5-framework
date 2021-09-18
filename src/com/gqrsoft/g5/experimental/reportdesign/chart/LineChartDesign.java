/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.chart;

import com.gqrsoft.g5.experimental.reportdesign.g5abstract.AbstractCategoryDatasetChartDesign;
import com.gqrsoft.g5.experimental.reportdesign.g5abstract.ReportFactory;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ChartDesignEnum;
import java.awt.Color;
import net.sf.jasperreports.charts.design.JRDesignCategoryDataset;
import net.sf.jasperreports.charts.design.JRDesignLinePlot;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class LineChartDesign extends AbstractCategoryDatasetChartDesign {

    protected JRDesignLinePlot linePlot;

    public LineChartDesign(JasperDesign jd) {
        super.chart = new JRDesignChart(
                jd, ChartDesignEnum.ChartType.LINE.chartType);
        super.dataset = (JRDesignChartDataset) chart.getDataset();
        super.categoryDataset = (JRDesignCategoryDataset) chart.getDataset();
        super.jasperDesign = jd;
        super.plot = chart.getPlot();
        linePlot = (JRDesignLinePlot) super.plot;
    }

    protected final void setShowLines(boolean value) {
        linePlot.setShowLines(value);
    }

    protected final void setShowShapes(boolean value) {
        linePlot.setShowShapes(value);
    }

//=== axis ===
    protected final void setCategoryAxisLabelColor(Color value) {
        linePlot.setCategoryAxisLabelColor(value);
    }

    protected final void setCategoryAxisLabelExpression(String value) {
        JRDesignExpression e =
                ReportFactory.createExpression(String.class, value);
        linePlot.setCategoryAxisLabelExpression(e);
    }

    protected final void setCategoryAxisLineColor(Color value) {
        linePlot.setCategoryAxisLineColor(value);
    }

    protected final void setCategoryAxisTickLabelColor(Color value) {
        linePlot.setCategoryAxisTickLabelColor(value);
    }

    protected final void setCategoryAxisTickLabelMask(String value) {
        linePlot.setCategoryAxisTickLabelMask(value);
    }

    protected final void setValueAxisLabelColor(Color value) {
        linePlot.setValueAxisLabelColor(value);
    }

    protected final void setValueAxisLabelExpression(String value) {
        JRDesignExpression e =
                ReportFactory.createExpression(String.class, value);
        linePlot.setValueAxisLabelExpression(e);
    }

    protected final void setValueAxisLineColor(Color value) {
        linePlot.setValueAxisLineColor(value);
    }

    protected final void setValueAxisTickLabelColor(Color value) {
        linePlot.setValueAxisTickLabelColor(value);
    }

    protected final void setValueAxisTickLabelMask(String value) {
        linePlot.setValueAxisTickLabelMask(value);
    }
}
