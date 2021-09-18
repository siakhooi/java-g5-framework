/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.chart;

import com.gqrsoft.g5.experimental.reportdesign.g5abstract.AbstractCategoryDatasetChartDesign;
import com.gqrsoft.g5.experimental.reportdesign.g5abstract.ReportFactory;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ChartDesignEnum;
import java.awt.Color;
import net.sf.jasperreports.charts.design.JRDesignAreaPlot;
import net.sf.jasperreports.charts.design.JRDesignCategoryDataset;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AreaChartDesign extends AbstractCategoryDatasetChartDesign {

    protected JRDesignAreaPlot areaPlot;

    public AreaChartDesign(JasperDesign jd, boolean stacked) {
        ChartDesignEnum.ChartType ct = stacked
                ? ChartDesignEnum.ChartType.STACKEDAREA
                : ChartDesignEnum.ChartType.AREA;
        super.chart = new JRDesignChart(jd, ct.chartType);
        super.dataset = (JRDesignChartDataset) chart.getDataset();
        super.categoryDataset = (JRDesignCategoryDataset) chart.getDataset();
        super.jasperDesign = jd;
        super.plot = chart.getPlot();
        areaPlot = (JRDesignAreaPlot) super.plot;
    }

    protected final void setCategoryAxisLabelColor(Color value) {
        areaPlot.setCategoryAxisLabelColor(value);
    }

    protected final void setCategoryAxisLabelExpression(String value) {
        JRDesignExpression e =
                ReportFactory.createExpression(String.class, value);
        areaPlot.setCategoryAxisLabelExpression(e);
    }

    protected final void setCategoryAxisLineColor(Color value) {
        areaPlot.setCategoryAxisLineColor(value);
    }

    protected final void setCategoryAxisTickLabelColor(Color value) {
        areaPlot.setCategoryAxisTickLabelColor(value);
    }

    protected final void setCategoryAxisTickLabelMask(String value) {
        areaPlot.setCategoryAxisTickLabelMask(value);
    }

    protected final void setValueAxisLabelColor(Color value) {
        areaPlot.setValueAxisLabelColor(value);
    }

    protected final void setValueAxisLabelExpression(String value) {
        JRDesignExpression e =
                ReportFactory.createExpression(String.class, value);
        areaPlot.setValueAxisLabelExpression(e);
    }

    protected final void setValueAxisLineColor(Color value) {
        areaPlot.setValueAxisLineColor(value);
    }

    protected final void setValueAxisTickLabelColor(Color value) {
        areaPlot.setValueAxisTickLabelColor(value);
    }

    protected final void setValueAxisTickLabelMask(String value) {
        areaPlot.setValueAxisTickLabelMask(value);
    }
}
