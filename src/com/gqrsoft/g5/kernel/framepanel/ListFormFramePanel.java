/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel;

import com.gqrsoft.g5.kernel.framepanel.button.ButtonSystemEventInterface;
import com.gqrsoft.g5.kernel.framepanel.list.ListCommands;
import com.gqrsoft.g5.kernel.framepanel.list.gui.ListTablePanel;
import com.gqrsoft.g5.kernel.framepanel.list.gui.column.ColumnManager;
import com.gqrsoft.g5.kernel.framepanel.list.gui.datamodel.AbstractListDataManager;
import com.gqrsoft.g5.kernel.framepanel.list.gui.datamodel.ArrayDataManager;
import com.gqrsoft.g5.kernel.framepanel.list.gui.datamodel.ResultSetDataManager;
import com.gqrsoft.g5.kernel.framepanel.list.gui.table.DefaultReadOnlyTable;
import com.gqrsoft.g5.kernel.framepanel.list.gui.table.DefaultReadOnlyTableModel;
import com.gqrsoft.g5.developer_protected.tools.ButtonsBuilder;
import com.gqrsoft.g5.developer.publicobject.FormEnum;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.config.ImageIconResource;
import com.gqrsoft.g5.kernel.core.util.IMAGE;
import com.gqrsoft.g5.kernel.framepanel.button.ButtonToolBarGenerator;
import com.gqrsoft.g5.kernel.framepanel.list.gui.datamodel.VectorDataManager;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ListFormFramePanel extends AbstractFramePanel implements ButtonSystemEventInterface {

    private JPanel buttonPanel = new JPanel();
    public ListTablePanel tablePanel = new ListTablePanel();
    public ColumnManager columnManager;
    public AbstractListDataManager dataManager;
    public DefaultReadOnlyTableModel tableModel;
    public DefaultReadOnlyTable table;
    public ListCommands commands;
    public ButtonsBuilder systemButtons;

    public void executeDoubleClick(int row, int col) {
        executeAction(getFormControl().listForm.getDoubleClickAction());
    }

    @Override
    public void initPanel0() {
        if (getFormControl().in.formEnterType == FormEnum.EnterType.FIELDSELECT) {
            getFormControl().listForm.model.select = true;
        } else {
            getFormControl().listForm.model.select = false;
        }
    }

    @Override
    public void initPanel() {
        systemButtons = new ButtonsBuilder(getFormControl().userForm);
    }

    @Override
    public void onInExit() {
        getFormControl().listForm.resultset = null;
        getFormControl().listForm.vector = null;
        getFormControl().listForm.array = null;
    }

    @Override
    public void onInEnter() {
        commands = new ListCommands();
        commands.init(getFormControl());

        columnManager = new ColumnManager();
        columnManager.init(getFormControl());

        dataManager = createDataManager();
        dataManager.init(getFormControl());

        tableModel = new DefaultReadOnlyTableModel();
        tableModel.init(getFormControl());

        table = new DefaultReadOnlyTable();
        table.init(getFormControl());
        table.setModel(tableModel);
        table.setAutoResizeMode(
                getFormControl().listForm.getColumnAutoResizeMode().autoResizeMode);

        buildSystemButtonsList();

//        ButtonPanelGenerator bpg = new ButtonPanelGenerator();
        ButtonToolBarGenerator bpg = new ButtonToolBarGenerator();
        buttonPanel = bpg.generate(
                getFormControl(),
                getFormControl().listForm.buttons,
                getFormControl().listForm,
                systemButtons, this);

        tablePanel.init(getFormControl());
        getPanel().setLayout(new BorderLayout());
        if (getFormControl().listForm.showButtons()) {
            getPanel().add(buttonPanel, BorderLayout.PAGE_START);
        }
        getPanel().add(tablePanel, BorderLayout.CENTER);

        //prepareListData
        executeAction(ListActionType.RELOAD);
    }

    private void buildSystemButtonsList() {
        insertSystemButton(ListFormEnum.ListSystemButton.Add,
                getFormControl().listForm.model.add);
        systemButtons.addSeparator();

        insertSystemButton(ListFormEnum.ListSystemButton.Copy,
                getFormControl().listForm.model.copy);
        insertSystemButton(ListFormEnum.ListSystemButton.View,
                getFormControl().listForm.model.view);
        insertSystemButton(ListFormEnum.ListSystemButton.Edit,
                getFormControl().listForm.model.edit);
        systemButtons.addSeparator();

        insertSystemButton(ListFormEnum.ListSystemButton.Delete,
                getFormControl().listForm.model.delete);
        systemButtons.addSeparator();

        insertSystemButton(ListFormEnum.ListSystemButton.Reload,
                getFormControl().listForm.model.reload);
        insertSystemButton(ListFormEnum.ListSystemButton.Select,
                getFormControl().listForm.model.select);
        systemButtons.addSeparator();

        insertSystemButton(ListFormEnum.ListSystemButton.Close,
                getFormControl().listForm.model.close);
        systemButtons.addSeparator();

        insertSystemButton(ListFormEnum.ListSystemButton.SaveAllToCsv,
                getFormControl().listForm.model.saveAllCSV);
        insertSystemButton(ListFormEnum.ListSystemButton.SaveSelectedToCsv,
                getFormControl().listForm.model.saveSelectedCSV);
    }

    private void insertSystemButton(ListFormEnum.ListSystemButton b, boolean enabled) {
        String label = getFormControl().cmd.lang.getSystemString(b.label);
        String tooltip = getFormControl().cmd.lang.getSystemString(b.tooltip);
        ImageIcon icon = IMAGE.resize(b.icon,
                EngineConfiguration.List.DEFAULT_ICON_HEIGHT);
        ImageIcon disabledIcon = IMAGE.resize(ImageIconResource.getBlankIcon(),
                EngineConfiguration.List.DEFAULT_ICON_HEIGHT);
        systemButtons.addI18nButton(b.name, label, icon, false);
        systemButtons.setI18nToolTipText(tooltip);
        systemButtons.setFunctionKey(b.functionKey);
        systemButtons.setFocusable(false);
        systemButtons.setEnabled(b.name, enabled);
    //systemButtons.setDisabledIcon(disabledIcon);
    }

    @Override
    public void onOutExit() {
    }

    @Override
    public void onOutEnter() {
    //executeAction(ListActionType.RELOAD);
    }

    @Override
    public void systemButtonClick(String name) {
        if (ListFormEnum.ListSystemButton.Add.name.equals(name)) {
            executeAction(ListActionType.ADD);
        } else if (ListFormEnum.ListSystemButton.Copy.name.equals(name)) {
            executeAction(ListActionType.COPY);
        } else if (ListFormEnum.ListSystemButton.Edit.name.equals(name)) {
            executeAction(ListActionType.EDIT);
        } else if (ListFormEnum.ListSystemButton.Delete.name.equals(name)) {
            executeAction(ListActionType.DELETE);
        } else if (ListFormEnum.ListSystemButton.Reload.name.equals(name)) {
            executeAction(ListActionType.RELOAD);
        } else if (ListFormEnum.ListSystemButton.SaveAllToCsv.name.equals(name)) {
            executeAction(ListActionType.SAVE_ALL_TO_CSV);
        } else if (ListFormEnum.ListSystemButton.SaveSelectedToCsv.name.equals(name)) {
            executeAction(ListActionType.SAVE_SELECTED_TO_CSV);
        } else if (ListFormEnum.ListSystemButton.Close.name.equals(name)) {
            executeAction(ListActionType.CLOSE);
        } else if (ListFormEnum.ListSystemButton.Select.name.equals(name)) {
            executeAction(ListActionType.SELECT);
        } else if (ListFormEnum.ListSystemButton.View.name.equals(name)) {
            executeAction(ListActionType.VIEW);
        }
    }

    private void error(String errTitle, Exception ex) {
        getFormControl().cmd.log.warning(errTitle, ex);
        String i18nTitle = getFormControl().cmd.lang.getSystemString(errTitle);
        getFormControl().cmd.common.showI18nMessage(
                FormEnum.DialogMessageType.ERROR,
                i18nTitle, ex.getLocalizedMessage());
    }

    public void executeAction(ListActionType actionType) {
        switch (actionType) {
            case ADD:
                try {
                    commands.doAdd();
                } catch (Exception ex) {
                    error("List.Add.Error", ex);
                }
                break;
            case COPY:
                try {
                    commands.doCopy();
                } catch (Exception ex) {
                    error("List.Copy.Error", ex);
                }
                break;
            case EDIT:
                try {
                    commands.doEdit();
                } catch (Exception ex) {
                    error("List.Edit.Error", ex);
                }
                break;
            case DELETE:
                try {
                    commands.doDelete();
                } catch (Exception ex) {
                    error("List.Delete.Error", ex);
                }
                break;
            case RELOAD:
                commands.doReload1();
                break;
            case SAVE_ALL_TO_CSV:
                try {
                    commands.doSaveAllToCSV();
                } catch (Exception ex) {
                    error("List.SaveAll.Error", ex);
                }
                break;
            case SAVE_SELECTED_TO_CSV:
                try {
                    commands.doSaveSelectedToCSV();
                } catch (Exception ex) {
                    error("List.Save.Error", ex);
                }
                break;
            case SELECT:
                try {
                    commands.doSelect();
                } catch (Exception ex) {
                    error("List.Select.Error", ex);
                }
                break;
            case VIEW:
                try {
                    commands.doView();
                } catch (Exception ex) {
                    error("List.View.Error", ex);
                }
                break;
            case CLOSE:
                commands.doClose();
                break;
        }
    }

    @Override
    public void onEscapeKeyPressed() {
        executeAction(ListActionType.CLOSE);
    }

    private AbstractListDataManager createDataManager() {
        switch (getFormControl().listForm.getDataType()) {
            case ARRAY:
                return new ArrayDataManager();
            case RESULTSET:
                return new ResultSetDataManager();
            case VECTOR:
                return new VectorDataManager();
        }
        return null;
    }
}
