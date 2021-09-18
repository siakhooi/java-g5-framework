/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5public;

import net.sf.jasperreports.charts.JRChartAxis;
import net.sf.jasperreports.engine.JRChart;

/**
 *
 * @author Ng Siak Hooi
 */
public class ChartDesignEnum {

    public enum ChartType {

        AREA(JRChart.CHART_TYPE_AREA),
        BAR3D(JRChart.CHART_TYPE_BAR3D),
        BAR(JRChart.CHART_TYPE_BAR),
        //(JRChart.CHART_TYPE_BUBBLE),
        //(JRChart.CHART_TYPE_CANDLESTICK),
        //(JRChart.CHART_TYPE_HIGHLOW),
        LINE(JRChart.CHART_TYPE_LINE),
        PIE3D(JRChart.CHART_TYPE_PIE3D),
        PIE(JRChart.CHART_TYPE_PIE),
        //(JRChart.CHART_TYPE_SCATTER),
        STACKEDBAR3D(JRChart.CHART_TYPE_STACKEDBAR3D),
        STACKEDBAR(JRChart.CHART_TYPE_STACKEDBAR),
        //(JRChart.CHART_TYPE_XYAREA),
        //(JRChart.CHART_TYPE_XYBAR),
        //(JRChart.CHART_TYPE_XYLINE),
        //(JRChart.CHART_TYPE_TIMESERIES),
        //(JRChart.CHART_TYPE_METER),
        //(JRChart.CHART_TYPE_THERMOMETER),
        MULTI_AXIS(JRChart.CHART_TYPE_MULTI_AXIS),
        STACKEDAREA(JRChart.CHART_TYPE_STACKEDAREA);
        public byte chartType = JRChart.CHART_TYPE_PIE;

        ChartType(byte chartType) {
            this.chartType = chartType;
        }
    }

    public enum ChartElementPosition {

        TOP(JRChart.EDGE_TOP),
        BOTTOM(JRChart.EDGE_BOTTOM),
        LEFT(JRChart.EDGE_LEFT),
        RIGHT(JRChart.EDGE_RIGHT);
        public byte chartElementPosition = JRChart.EDGE_RIGHT;

        ChartElementPosition(byte chartElementPosition) {
            this.chartElementPosition = chartElementPosition;
        }
    }

    public enum ChartOrientation {

        VERTICAL(org.jfree.chart.plot.PlotOrientation.VERTICAL),
        HORIZONTAL(org.jfree.chart.plot.PlotOrientation.HORIZONTAL);
        public org.jfree.chart.plot.PlotOrientation chartOrientation =
                org.jfree.chart.plot.PlotOrientation.VERTICAL;

        ChartOrientation(org.jfree.chart.plot.PlotOrientation chartOrientation) {
            this.chartOrientation = chartOrientation;
        }
    }

    public enum ChartAxisPosition {

        LEFT_OR_TOP(JRChartAxis.POSITION_LEFT_OR_TOP),
        RIGHT_OR_BOTTOM(JRChartAxis.POSITION_RIGHT_OR_BOTTOM);
        public byte chartAxisPosition = JRChartAxis.POSITION_LEFT_OR_TOP;

        ChartAxisPosition(byte chartAxisPosition) {
            this.chartAxisPosition = chartAxisPosition;
        }
    }
}
