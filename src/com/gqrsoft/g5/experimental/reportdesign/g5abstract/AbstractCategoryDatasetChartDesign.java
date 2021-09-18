/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5abstract;

import net.sf.jasperreports.charts.design.JRDesignCategoryDataset;
import net.sf.jasperreports.charts.design.JRDesignCategorySeries;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractCategoryDatasetChartDesign extends AbstractChartDesign {

    protected JRDesignCategoryDataset categoryDataset;

    protected final void addSeries(
            Class seriesClass, String series,
            Class categoryClass, String category,
            Class labelClass, String label,
            Class valueClass, String value) {
        JRDesignExpression se =
                ReportFactory.createExpression(seriesClass, series);
        JRDesignExpression ca =
                ReportFactory.createExpression(categoryClass, category);
        JRDesignExpression la =
                ReportFactory.createExpression(labelClass, label);
        JRDesignExpression va =
                ReportFactory.createExpression(valueClass, value);

        JRDesignCategorySeries cs = new JRDesignCategorySeries();
        cs.setSeriesExpression(se);
        cs.setCategoryExpression(ca);
        cs.setLabelExpression(la);
        cs.setValueExpression(va);

        categoryDataset.addCategorySeries(cs);
    }

    protected final void addSeriesExpression(
            String series,
            String category,
            String label,
            String value) {
        addSeries(
                Comparable.class, series,
                Comparable.class, category,
                String.class, label,
                Number.class, value);
    }
}
