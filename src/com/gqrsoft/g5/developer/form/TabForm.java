/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.form;

import com.gqrsoft.g5.developer.publicobject.TabFormEnum;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class TabForm extends BlankForm {

    public abstract void buildTabForm();
    protected JTabbedPane tabbedPane;

    @Override
    public final void buildBlankForm(JPanel parent) {
        tabbedPane = new JTabbedPane();
        parent.setLayout(new BorderLayout());
        parent.add(tabbedPane, BorderLayout.CENTER);
        buildTabForm();
    }

    protected final void addI18nTab(String i18nTab, UserFormInterface form,
            Icon icon, String tooltips) {
        JPanel i = cmd.form.createEmbedded(form);
        tabbedPane.addTab(i18nTab, icon, i, tooltips);
    }

    protected final void addTab(String tab, UserFormInterface form,
            Icon icon, String tooltips) {
        addI18nTab(cmd.lang.getString(tab), form, icon, tooltips);
    }

    protected final void setTabLayout(TabFormEnum.TabLayoutPolicy v) {
        tabbedPane.setTabLayoutPolicy(v.tabLayoutPolicy);
    }

    protected final void setIcon(int index, Icon icon) {
        tabbedPane.setIconAt(index, icon);
    }

    protected final void setTooltips(int index, String tooltips) {
        tabbedPane.setToolTipTextAt(index, tooltips);
    }

    protected final void setEnabled(int index, boolean enabled) {
        tabbedPane.setEnabledAt(index, enabled);
    }

    protected final void setBackgroundAt(int index, Color background) {
        tabbedPane.setBackgroundAt(index, background);
    }

    protected final void setForegroundAt(int index, Color foreground) {
        tabbedPane.setForegroundAt(index, foreground);
    }

    protected final void setSelectedIndex(int index) {
        tabbedPane.setSelectedIndex(index);
    }
}
