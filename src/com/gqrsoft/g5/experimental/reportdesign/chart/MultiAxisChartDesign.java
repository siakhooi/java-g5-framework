/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.chart;

import com.gqrsoft.g5.experimental.reportdesign.g5abstract.AbstractCategoryDatasetChartDesign;
import com.gqrsoft.g5.experimental.reportdesign.g5abstract.AbstractChartDesign;
import com.gqrsoft.g5.experimental.reportdesign.g5public.ChartDesignEnum;
import net.sf.jasperreports.charts.design.JRDesignChartAxis;
import net.sf.jasperreports.charts.design.JRDesignMultiAxisPlot;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class MultiAxisChartDesign extends AbstractChartDesign {

    protected JRDesignMultiAxisPlot multiAxisPlot;

    public MultiAxisChartDesign(JasperDesign jd) {
        super.chart = new JRDesignChart(
                jd, ChartDesignEnum.ChartType.MULTI_AXIS.chartType);
//        super.dataset = (JRDesignChartDataset) chart.getDataset();
//        super.categoryDataset = (JRDesignCategoryDataset) chart.getDataset();
        super.jasperDesign = jd;
        super.plot = chart.getPlot();
        multiAxisPlot = (JRDesignMultiAxisPlot) super.plot;
    }

    protected final void addChart(
            AbstractCategoryDatasetChartDesign childChart,
            ChartDesignEnum.ChartAxisPosition a) {
        JRDesignChartAxis axis = new JRDesignChartAxis(chart);
        axis.setPosition(a.chartAxisPosition);
        axis.setChart(childChart.chart);
        multiAxisPlot.addAxis(axis);
    }
}
