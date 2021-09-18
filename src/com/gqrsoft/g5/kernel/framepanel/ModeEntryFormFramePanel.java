/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel;

import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;
import com.gqrsoft.g5.developer_protected.tools.ButtonConfig;
import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.config.ImageIconResource;
import com.gqrsoft.g5.kernel.core.util.IMAGE;
import com.gqrsoft.g5.kernel.framepanel.entry.entryField.entryfield.AbstractEntryField;
import com.gqrsoft.g5.kernel.framepanel.entry.entryForm.EntryFieldGroup;
import com.gqrsoft.g5.kernel.framepanel.entry.modeEntryForm.ModeEntryFormButtonLoader;
import javax.swing.ImageIcon;

/**
 *
 * @author Ng Siak Hooi
 */
public class ModeEntryFormFramePanel extends EntryFormFramePanel {

    private ModeAction currentEscapeKeyAction;

    private void showField(boolean key, boolean data) {
        EntryFieldGroup b = getFormControl().modeEntryForm.keyEntryFieldGroup;
        b.generatedPanel.setVisible(key);
        this.dataFieldTabbedPane.setVisible(data);

    }

    private void enableField(boolean key, boolean data) {
        EntryFieldGroup b = getFormControl().modeEntryForm.keyEntryFieldGroup;
        for (AbstractEntryField aef : b.allEntryFieldsByIndex) {
            aef.display.setEditable(key);
            aef.refreshAll();
        }

        for (EntryFieldGroup efg : getFormControl().modeEntryForm.allTab.values()) {
            for (AbstractEntryField aef : efg.allEntryFieldsByIndex) {
                aef.display.setEditable(data);
                aef.refreshAll();
            }
        }
    }

    @Override
    public void onEscapeKeyPressed() {
        executeModeAction(currentEscapeKeyAction);
    }

    /* on mode change - event - change buttons configuration
     * on init/button press steps:
     *- load default from ButtonAction
     *- event - let application customize
     *  field verification?
     * confirmation?
     * save process
     * load process
     * switch mode/close form
     * event again
     */
    public void executeModeAction(ModeEntryFormEnum.ModeAction modeAction) {
        loadModeAction(modeAction);
        getFormControl().modeEntryForm.initModeAction();
        if (!checkFieldVerification()) {
            return;
        }
        if (!checkConfirmation()) {
            return;
        }
        try {
            executeSaveProcess();
        } catch (Exception e) {
            String msg = "ModeEntryPanel.error.saveData.{0}";
            getFormControl().cmd.log.severe(msg, e);
            msg = getFormControl().cmd.lang.getSystemString(msg,
                    e.getLocalizedMessage());
            statusBar.setStatus(msg);
            return;
        }
        try {
            executeLoadProcess();
        } catch (Exception e) {
            String msg = "ModeEntryPanel.error.loadData.{0}";
            getFormControl().cmd.log.severe(msg, e);
            msg = getFormControl().cmd.lang.getSystemString(msg,
                    e.getLocalizedMessage());
            statusBar.setStatus(msg);
            return;
        }
        switchMode();
        getFormControl().modeEntryForm.finishModeAction();
        statusBar.setStatus("");
    }

    private void switchMode() {
        ModeEntryFormEnum.NextModeType nextModeType =
                getFormControl().modeEntryForm.nextModeType;
        switch (nextModeType) {
            case None:
                return;
            case CloseForm:
                getFormControl().cmd.form.closeForm();
                return;
            case AddMode:
                enableField(true, true);
                showField(true, true);
                getFormControl().modeEntryForm.currentMode = ModeEntryFormEnum.Mode.ADD;
                statusBar.setMode(getFormControl().modeEntryForm.currentMode);
                break;
            case EditMode:
                enableField(false, true);
                showField(true, true);
                getFormControl().modeEntryForm.currentMode = ModeEntryFormEnum.Mode.EDIT;
                statusBar.setMode(getFormControl().modeEntryForm.currentMode);
                break;
            case SearchMode:
                enableField(true, false);
                showField(true, false);
                getFormControl().modeEntryForm.currentMode = ModeEntryFormEnum.Mode.SEARCH;
                statusBar.setMode(getFormControl().modeEntryForm.currentMode);
                break;
            case ViewMode:
                enableField(false, false);
                showField(true, true);
                getFormControl().modeEntryForm.currentMode = ModeEntryFormEnum.Mode.VIEW;
                statusBar.setMode(getFormControl().modeEntryForm.currentMode);
                break;
        }
        ModeEntryFormButtonLoader.load(systemButtons,
                getFormControl().modeEntryForm.buttonConfig);
//        ModeEntryFormButtonLoader.load(systemButtons,
//                getFormControl().modeEntryForm.buttonConfiguration);
//        currentEscapeKeyAction =
//                getFormControl().modeEntryForm.buttonConfiguration.x().escapeKey;
        currentEscapeKeyAction =
                getFormControl().modeEntryForm.buttonConfig.escapeKey;
    }

    private boolean checkFieldVerification() {
//        if (!getFormControl().modeEntryForm.fieldVerification) {
//            return true;
//        }
        boolean allTrue = true;
        if (getFormControl().modeEntryForm.currentMode == null) {
            return true;
        }
        switch (getFormControl().modeEntryForm.currentMode) {
            case ADD:
                allTrue = verifyAllFields(true, true);
                break;
            case EDIT:
                allTrue = verifyAllFields(false, true);
                break;
            case SEARCH:
                allTrue = verifyAllFields(true, false);
                break;
            case VIEW:
                allTrue = verifyAllFields(false, false);
                break;
        }
        if (!getFormControl().modeEntryForm.fieldVerification) {
            return true;
        }
        if (!allTrue) {
            String msg = "ModeEntryPanel.error.checkFieldVerification";
            msg = getFormControl().cmd.lang.getSystemString(msg);
            statusBar.setStatus(msg);
        }
        return allTrue;
    }

    private void executeSaveProcess() throws Exception {
        ModeEntryFormEnum.ModeSaveProcess saveProcess =
                getFormControl().modeEntryForm.nextModeSaveProcess;
        switch (saveProcess) {
            case None:
                break;
            case SaveAdd:
                getFormControl().modeEntryForm.saveAdd();
                break;
            case SaveDelete:
                getFormControl().modeEntryForm.saveDelete();
                break;
            case SaveEdit:
                getFormControl().modeEntryForm.saveEdit();
                break;
        }
    }

    private void executeLoadProcess() throws Exception {
        ModeEntryFormEnum.ModeLoadProcess loadProcess =
                getFormControl().modeEntryForm.nextModeLoadProcess;
        switch (loadProcess) {
            case None:
                break;
            case LoadData:
                getFormControl().modeEntryForm.loadData();
                break;
            case LoadDefault:
                super.resetValue();
                getFormControl().modeEntryForm.loadDefault();
                break;
        }
    }

    private boolean checkConfirmation() {
        String msg = getFormControl().modeEntryForm.i18nConfirmationText;
        if (!getFormControl().cmd.data.isNull(msg)) {
            String title = "ModeEntryPanel.confirmation.title";
            title = getFormControl().cmd.lang.getSystemString(title);
            boolean ok = getFormControl().cmd.common.showI18nConfirmation(
                    DialogMessageType.QUESTION, title, msg);
            return ok;
        }
        return true;
    }

    private void loadModeAction(ModeEntryFormEnum.ModeAction ma) {
        //CONSOLE.println("loadModeAction()1:" + ma);
        //CONSOLE.println("loadModeAction()2:" + ma.name());
        getFormControl().modeEntryForm.fieldVerification =
                ma.fieldVerification;
        getFormControl().modeEntryForm.i18nConfirmationText =
                getFormControl().cmd.lang.getSystemString(
                ma.confirmationText);
        getFormControl().modeEntryForm.nextModeSaveProcess =
                ma.saveProcess;
        getFormControl().modeEntryForm.nextModeLoadProcess =
                ma.loadProcess;
        getFormControl().modeEntryForm.nextModeType =
                ma.nextModeType;
        getFormControl().modeEntryForm.buttonConfig = new ButtonConfig();
        getFormControl().modeEntryForm.buttonConfig.load(ma.buttonConfiguration);
//        getFormControl().modeEntryForm.buttonConfiguration =
//                ma.buttonConfiguration;
    }

    @Override
    public void onInEnter() {
    }

    @Override
    public void eventAfterVisible() {
        ModeEntryFormEnum.ModeAction ma = getFormControl().modeEntryForm.enteringAction;
        switch (getFormControl().cmd.in.formEnterType) {
            case FIELDSELECT:
                ma = ModeAction.SearchMode;
                break;
            case FIELDVIEW:
                ma = ModeAction.ViewMode;
                break;
            case LISTADD:
                ma = ModeAction.AddMode;
                break;
            case LISTDELETE:
                ma = ModeAction.ViewMode;
                break;
            case LISTCOPY:
                ma = ModeAction.CopyMode;
                break;
            case LISTEDIT:
                ma = ModeAction.EditMode;
                break;
            case LISTVIEW:
                ma = ModeAction.ViewMode;
                break;
            default:
                break;
        }
        executeModeAction(ma);
    }

    @Override
    public final void systemButtonClick(String name) {
        ModeEntryFormEnum.ModeAction ma =
                super.systemButtons.allButtonsByName.get(name).getModeAction();
        if (ma == null) {
            return;
        }
        executeModeAction(ma);
    }

    @Override
    protected final void buildSystemButtonsList() {
        insertSystemButton(ModeEntryFormEnum.ModeSystemButton.Search);
        insertSystemButton(ModeEntryFormEnum.ModeSystemButton.Add);
        insertSystemButton(ModeEntryFormEnum.ModeSystemButton.Copy);
        insertSystemButton(ModeEntryFormEnum.ModeSystemButton.View);
        insertSystemButton(ModeEntryFormEnum.ModeSystemButton.Edit);
        systemButtons.addSeparator();
        insertSystemButton(ModeEntryFormEnum.ModeSystemButton.OK);
        insertSystemButton(ModeEntryFormEnum.ModeSystemButton.Cancel);
        systemButtons.addSeparator();
        insertSystemButton(ModeEntryFormEnum.ModeSystemButton.Reload);
        insertSystemButton(ModeEntryFormEnum.ModeSystemButton.Reset);
        systemButtons.addSeparator();
        insertSystemButton(ModeEntryFormEnum.ModeSystemButton.Delete);
        systemButtons.addSeparator();
        insertSystemButton(ModeEntryFormEnum.ModeSystemButton.Back);
        insertSystemButton(ModeEntryFormEnum.ModeSystemButton.Close);
    }

    private void insertSystemButton(ModeEntryFormEnum.ModeSystemButton b) {
        String label = getFormControl().cmd.lang.getSystemString(b.label);
        String tooltip = getFormControl().cmd.lang.getSystemString(b.tooltip);
        ImageIcon icon = IMAGE.resize(b.icon,
                EngineConfiguration.Entry.DEFAULT_ICON_HEIGHT);
        ImageIcon disabledIcon = IMAGE.resize(ImageIconResource.getBlankIcon(),
                EngineConfiguration.List.DEFAULT_ICON_HEIGHT);

        systemButtons.addI18nButton(b.name, label, icon, false);
        systemButtons.setI18nToolTipText(tooltip);
        systemButtons.setFunctionKey(b.functionKey);
        systemButtons.setFocusable(false);
        //systemButtons.setDisabledIcon(disabledIcon);
    }
}
