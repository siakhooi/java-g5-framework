/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.chart;

import com.gqrsoft.g5.experimental.reportdesign.g5abstract.AbstractPieDatasetChartDesign;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ChartDesignEnum;
import net.sf.jasperreports.charts.design.JRDesignPieDataset;
import net.sf.jasperreports.charts.design.JRDesignPiePlot;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class PieChartDesign extends AbstractPieDatasetChartDesign {

    protected JRDesignPiePlot piePlot;

    public PieChartDesign(JasperDesign jd) {
        super.chart = new JRDesignChart(
                jd, ChartDesignEnum.ChartType.PIE.chartType);
        super.dataset = (JRDesignChartDataset) chart.getDataset();
        super.pieDataset = (JRDesignPieDataset) chart.getDataset();
        super.jasperDesign = jd;
        super.plot = chart.getPlot();
        piePlot = (JRDesignPiePlot) super.plot;
    }

    protected final void setCircular(boolean isCircular) {
        piePlot.setCircular(isCircular);
    }
}
