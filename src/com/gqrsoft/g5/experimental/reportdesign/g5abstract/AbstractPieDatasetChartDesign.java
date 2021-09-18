/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5abstract;

import net.sf.jasperreports.charts.design.JRDesignPieDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractPieDatasetChartDesign extends AbstractChartDesign {

    protected JRDesignPieDataset pieDataset;

    protected final void setExpression(String key, String label, String value) {
        setKeyExpression(key);
        setLabelExpression(label);
        setValueExpression(value);
    }

    protected final void setExpression(
            Class keyClazz, String key,
            Class labelClazz, String label,
            Class valueClazz, String value) {
        setKeyExpression(keyClazz, key);
        setLabelExpression(labelClazz, label);
        setValueExpression(valueClazz, value);
    }

    protected final void setKeyExpression(String expression) {
        setKeyExpression(Comparable.class, expression);
    }

    protected final void setLabelExpression(String expression) {
        setLabelExpression(String.class, expression);
    }

    protected final void setValueExpression(String expression) {
        setValueExpression(Number.class, expression);
    }

    protected final void setKeyExpression(Class clazz, String expression) {
        JRDesignExpression e1 =
                ReportFactory.createExpression(clazz, expression);
        pieDataset.setKeyExpression(e1);
    }

    protected final void setLabelExpression(Class clazz, String expression) {
        JRDesignExpression e1 =
                ReportFactory.createExpression(clazz, expression);
        pieDataset.setLabelExpression(e1);
    }

    protected final void setValueExpression(Class clazz, String expression) {
        JRDesignExpression e1 =
                ReportFactory.createExpression(clazz, expression);
        pieDataset.setValueExpression(e1);
    }
}
