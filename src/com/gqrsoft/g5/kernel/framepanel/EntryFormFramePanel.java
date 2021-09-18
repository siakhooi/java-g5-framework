/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel;

import com.gqrsoft.g5.developer_protected.tools.ButtonsBuilder;
import com.gqrsoft.g5.kernel.framepanel.button.ButtonPanelLayoutFactory;
import com.gqrsoft.g5.kernel.framepanel.button.ButtonSystemEventInterface;
import com.gqrsoft.g5.kernel.framepanel.button.ButtonToolBarGenerator;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.EntryFieldEnum.FieldAction;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryForm.EntryFieldGroup;
import com.gqrsoft.g5.kernel.framepanel.entry.entryForm.EntryFieldsPanelGenerator;
import com.gqrsoft.g5.kernel.framepanel.entry.entryForm.EntryFormPanelLayoutFactory;
import com.gqrsoft.g5.kernel.framepanel.entry.entryForm.EntryFormStatusBar;
import com.gqrsoft.g5.kernel.framepanel.entry.entryForm.EntryFormValueResetter;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Ng Siak Hooi
 */
public class EntryFormFramePanel extends AbstractFramePanel implements ButtonSystemEventInterface {

    public JTabbedPane dataFieldTabbedPane = new JTabbedPane();
    public ButtonsBuilder systemButtons;
    public EntryFormStatusBar statusBar;

    protected void buildSystemButtonsList() {
    }

    @Override
    public void systemButtonClick(String name) {
    }

    protected JPanel getEntryPanel() {
        return getPanel();
    }

    @Override
    public void initPanel() {
    }

    @Override
    public void initPanel30() {
        statusBar = new EntryFormStatusBar(getFormControl());
        systemButtons = new ButtonsBuilder(getFormControl().userForm);
        systemButtons.clearAll();
        buildSystemButtonsList();

        EntryFieldsPanelGenerator.execute(
                getFormControl(),
                getFormControl().entryForm.keyEntryFieldGroup,
                statusBar, getFormControl().entryForm);
        for (EntryFieldGroup efg : getFormControl().entryForm.allTab.values()) {
            switch (efg.type) {
                case FIELD:
                    EntryFieldsPanelGenerator.execute(
                            getFormControl(),
                            efg, statusBar, getFormControl().entryForm);
                    break;
                case FORM:
                    EntryFieldsPanelGenerator.executeEmbedded(
                            getFormControl(), efg);
                    break;
            }
        }

        JPanel entryFieldsPanel = new JPanel();
        JPanel entryFormPanel = new JPanel();

        EntryFormPanelLayoutFactory.execute(
                entryFieldsPanel,
                dataFieldTabbedPane,
                getFormControl().entryForm);

        entryFormPanel.setLayout(new BorderLayout());
        entryFormPanel.add(entryFieldsPanel, BorderLayout.CENTER);

//        ButtonPanelGenerator bpg = new ButtonPanelGenerator();
        ButtonToolBarGenerator bpg = new ButtonToolBarGenerator();
        JPanel buttonPanel = bpg.generate(
                getFormControl(),
                getFormControl().entryForm.buttons,
                getFormControl().entryForm,
                systemButtons, this);
        ButtonPanelLayoutFactory.execute(entryFormPanel, buttonPanel,
                getFormControl().entryForm.buttons);

        getEntryPanel().setLayout(new BorderLayout());
        getEntryPanel().add(entryFormPanel, BorderLayout.CENTER);
        if (getFormControl().entryForm.showStatusBar()) {
            getEntryPanel().add(statusBar, BorderLayout.PAGE_END);
        }
        resetValue();
    //getEntryPanel().setOpaque(true);
    //getEntryPanel().setBackground(DefaultStyle.getEntryFormBackgroundColor());
    }

    protected void resetValue() {
        EntryFormValueResetter r = new EntryFormValueResetter();
        r.execute(getFormControl());
//        EntryFormAllEntryFieldsInitializer.init(getFormControl());
//        getFormControl().entryForm.initValue();
//        EntryFormAllEntryFieldsRefresher.init(getFormControl().entryForm);
    }

    @Override
    public void onEscapeKeyPressed() {
        getFormControl().entryForm.onEscapeKeyPressed();
    }

    @Override
    public void onInEnter() {
    }

    @Override
    public void onInExit() {
    }

    @Override
    public void onOutExit() {
//        getFormControl().entryForm.onFieldOutExit(getFormControl().out.fieldName);
    //already implemented in each *EntryField
    }

    @Override
    public void onOutEnter() {
//        getFormControl().entryForm.onFieldOutEnter(getFormControl().out.fieldName);
    //already implemented in each *EntryField
    }

    protected boolean verifyAllFields(boolean key, boolean data) {
        boolean allTrue = true;
        if (key) {
            EntryFieldGroup b = getFormControl().entryForm.keyEntryFieldGroup;
            for (AbstractEntryField aef : b.allEntryFieldsByIndex) {
                if (aef.dataComponent.isFocusOwner()) {
                    aef.executeAction(FieldAction.VERIFYVALUE);
                    aef.refreshAll();
                }
                if (!aef.display.valid) {
                    allTrue = false;
                }
            }
        }
        if (data) {
            for (EntryFieldGroup efg : getFormControl().entryForm.allTab.values()) {
                if (efg.generatedPanel.isEnabled()) {
                    for (AbstractEntryField aef : efg.allEntryFieldsByIndex) {
                        if (aef.dataComponent.isFocusOwner()) {
                            aef.executeAction(FieldAction.VERIFYVALUE);
                            aef.refreshAll();
                        }
                        if (!aef.display.valid) {
                            allTrue = false;
                        }
                    }
                }
            }
        }
        return allTrue;
    }
}
