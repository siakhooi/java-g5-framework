/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.chart;

import com.gqrsoft.g5.experimental.reportdesign.g5abstract.AbstractPieDatasetChartDesign;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ChartDesignEnum;
import net.sf.jasperreports.charts.design.JRDesignPie3DPlot;
import net.sf.jasperreports.charts.design.JRDesignPieDataset;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class Pie3DChartDesign extends AbstractPieDatasetChartDesign {

    protected JRDesignPie3DPlot pie3DPlot;

    public Pie3DChartDesign(JasperDesign jd) {
        super.chart = new JRDesignChart(
                jd, ChartDesignEnum.ChartType.PIE3D.chartType);
        super.dataset = (JRDesignChartDataset) chart.getDataset();
        super.pieDataset = (JRDesignPieDataset) chart.getDataset();
        super.jasperDesign = jd;
        super.plot = chart.getPlot();
        pie3DPlot = (JRDesignPie3DPlot) super.plot;
    }

    protected final void setCircular(boolean isCircular) {
        pie3DPlot.setCircular(isCircular);
    }

    protected final void setDepth(double depthFactor) {
        pie3DPlot.setDepthFactor(depthFactor);
    }
}
