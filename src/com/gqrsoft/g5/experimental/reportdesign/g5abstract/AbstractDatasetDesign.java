/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5abstract;

import com.gqrsoft.g5.experimental.reportdesign.g5public.ReportDesignEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.design.JRDesignVariable;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class AbstractDatasetDesign {

    private JRDesignGroup currentGroup;
    private JRDesignVariable currentVariable;
    public JRDesignDataset dataset;

    public abstract void defineReportQuery();

    public abstract void defineVariables();

    public abstract void defineParameters();

    public abstract void defineFields();

    public abstract void defineSortFields();

    public abstract void defineGroups();

    public void init(boolean isMain) {
        dataset = new JRDesignDataset(isMain);
        defineReportQuery();
        defineVariables();
        defineParameters();
        defineFields();
        defineSortFields();
        defineGroups();
    }

    /**
     * 
     * @param expression evaluates to Boolean value, when false, the record will be skipped.
     */
    protected final void setFilterExpression(String expression) {
        JRDesignExpression a =
                ReportFactory.createExpression(Boolean.class, expression);
        dataset.setFilterExpression(a);
    }

    protected final void setResourceBundle(String baseName) {
        dataset.setResourceBundle(baseName);
    }

    protected final void setQuery(String sql) {
        JRDesignQuery q = new JRDesignQuery();
        q.setText(sql);
        dataset.setQuery(q);
    }

    protected final void addField(String name, Class clazz) throws JRException {
        JRDesignField f = new JRDesignField();
        f.setName(name);
        f.setValueClass(clazz);
        dataset.addField(f);
    }

    protected final void addField(String name, Class clazz, String description) throws JRException {
        JRDesignField f = new JRDesignField();
        f.setName(name);
        f.setValueClass(clazz);
        f.setDescription(description);
        dataset.addField(f);
    }

    protected final void addSortField(String name, ReportDesignEnum.SortOrder sortOrder) throws JRException {
        JRDesignSortField f = new JRDesignSortField();
        f.setName(name);
        f.setOrder(sortOrder.value);
        dataset.addSortField(f);
    }

    protected final void addParameter(String name, Class clazz, String defaultValueExpression) throws JRException {
        JRDesignParameter f = new JRDesignParameter();
        f.setName(name);
        f.setValueClass(clazz);
        JRDesignExpression a =
                ReportFactory.createExpression(clazz, defaultValueExpression);
        f.setDefaultValueExpression(a);
        dataset.addParameter(f);
    }

    protected final void addVariable(String name, Class clazz,
            String initValueExpression, String expression) throws JRException {
        JRDesignVariable f = new JRDesignVariable();
        f.setName(name);
        f.setValueClass(clazz);

        JRDesignExpression a =
                ReportFactory.createExpression(clazz, initValueExpression);
        f.setInitialValueExpression(a);

        a = ReportFactory.createExpression(clazz, expression);
        f.setExpression(a);
        dataset.addVariable(f);
        currentVariable = f;
    }

    protected final void setVariableCalculationType(
            ReportDesignEnum.CalculationType c) {
        currentVariable.setCalculation(c.value);
    }

    protected final void setVariableResetType(ReportDesignEnum.ResetType r) {
        currentVariable.setResetType(r.value);
    }

    protected final void setVariableResetGroupName(String groupName) {
        JRDesignGroup g = (JRDesignGroup) dataset.getGroupsMap().get(groupName);
        currentVariable.setResetGroup(g);
    }

    protected final void setVariableIncrementType(ReportDesignEnum.ResetType r) {
        currentVariable.setIncrementType(r.value);
    }

    protected final void setVariableIncrementGroupName(String groupName) {
        JRDesignGroup g = (JRDesignGroup) dataset.getGroupsMap().get(groupName);
        currentVariable.setIncrementGroup(g);
    }

    protected final void addGroup(
            String groupName, Class expressClass, String expression) throws JRException {
        JRDesignGroup f = new JRDesignGroup();
        f.setName(groupName);
        JRDesignExpression e =
                ReportFactory.createExpression(expressClass, expression);
        f.setExpression(e);
        dataset.addGroup(f);
        currentGroup = f;
    }

    protected final void setGroupReprintHeaderOnEachPage(boolean value) {
        currentGroup.setReprintHeaderOnEachPage(value);
    }

    protected final void setGroupResetPageNumber(boolean value) {
        currentGroup.setResetPageNumber(value);
    }

    protected final void setGroupStartInNewColumn(boolean value) {
        currentGroup.setStartNewColumn(value);
    }

    protected final void setGroupStartInNewPage(boolean value) {
        currentGroup.setStartNewPage(value);
    }

    protected final void setGroupMinHeightToStartNewPage(int h) {
        currentGroup.setMinHeightToStartNewPage(h);
    }

    protected final void setGroupCountVariable(String varName) {
        JRDesignVariable v = (JRDesignVariable) dataset.getVariablesMap().get(varName);
        currentGroup.setCountVariable(v);
    }
}
