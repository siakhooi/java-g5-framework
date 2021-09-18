/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicobject;

import com.gqrsoft.g5.kernel.config.ImageIconResource;
import com.gqrsoft.g5.developer.publicobject.FunctionKeyEnum.FunctionKey;
import javax.swing.ImageIcon;

/**
 *
 * @author Ng Siak Hooi
 */
public class ModeEntryFormEnum {
    /* on mode change - event - change buttons configuration
     * on init/button press steps:
     * load default from ButtonAction
     * event - let application customize
     *  field verification?
     * confirmation?
     * save process
     * load process
     * switch mode/close form
     * event again
     */

    public enum Mode {

        ADD("ModeEntryFormEnum.Mode.Add"),
        EDIT("ModeEntryFormEnum.Mode.Edit"),
        VIEW("ModeEntryFormEnum.Mode.View"),
        SEARCH("ModeEntryFormEnum.Mode.Search");
        public String statusBarText;

        Mode(String statusBarText) {
            this.statusBarText = statusBarText;
        }
    }

    public enum ModeAction {
//if button action==null, not show button
        None(false, null, ModeSaveProcess.None, ModeLoadProcess.None, NextModeType.None, ButtonConfiguration.None),
        AddMode(false, null, ModeSaveProcess.None, ModeLoadProcess.LoadDefault, NextModeType.AddMode, ButtonConfiguration.Add),
        SearchMode(false, null, ModeSaveProcess.None, ModeLoadProcess.LoadDefault, NextModeType.SearchMode, ButtonConfiguration.Search),
        ViewMode(false, null, ModeSaveProcess.None, ModeLoadProcess.LoadData, NextModeType.ViewMode, ButtonConfiguration.View),
        CopyMode(false, null, ModeSaveProcess.None, ModeLoadProcess.LoadData, NextModeType.AddMode, ButtonConfiguration.Copy),
        EditMode(false, null, ModeSaveProcess.None, ModeLoadProcess.LoadData, NextModeType.EditMode, ButtonConfiguration.Edit),
        ResetAdd(false, "ModeEntryFormEnum.ModeAction.confirmation.ResetAdd", ModeSaveProcess.None, ModeLoadProcess.LoadDefault, NextModeType.AddMode, ButtonConfiguration.Add),
        ReloadCopy(false, "ModeEntryFormEnum.ModeAction.confirmation.ReloadCopy", ModeSaveProcess.None, ModeLoadProcess.LoadData, NextModeType.AddMode, ButtonConfiguration.Copy),
        CancelAddAndClose(false, "ModeEntryFormEnum.ModeAction.confirmation.CancelAddAndClose", ModeSaveProcess.None, ModeLoadProcess.None, NextModeType.CloseForm, ButtonConfiguration.None),
        CancelAddAndSearch(false, "ModeEntryFormEnum.ModeAction.confirmation.CancelAddAndSearch", ModeSaveProcess.None, ModeLoadProcess.LoadDefault, NextModeType.SearchMode, ButtonConfiguration.Search),
        SaveAddAndClose(true, null, ModeSaveProcess.SaveAdd, ModeLoadProcess.None, NextModeType.CloseForm, ButtonConfiguration.None),
        SaveAddAndEdit(true, null, ModeSaveProcess.SaveAdd, ModeLoadProcess.LoadData, NextModeType.EditMode, ButtonConfiguration.Edit),
        SaveAddAndView(true, null, ModeSaveProcess.SaveAdd, ModeLoadProcess.LoadData, NextModeType.ViewMode, ButtonConfiguration.View),
        SaveAddAndSearch(true, null, ModeSaveProcess.SaveAdd, ModeLoadProcess.LoadDefault, NextModeType.SearchMode, ButtonConfiguration.Search),
        SaveAddAndAdd(true, null, ModeSaveProcess.SaveAdd, ModeLoadProcess.LoadDefault, NextModeType.AddMode, ButtonConfiguration.Add),
        ReloadEdit(false, "ModeEntryFormEnum.ModeAction.confirmation.ReloadEdit", ModeSaveProcess.None, ModeLoadProcess.LoadData, NextModeType.EditMode, ButtonConfiguration.Edit),
        CancelEditAndClose(false, "ModeEntryFormEnum.ModeAction.confirmation.CancelEditAndClose", ModeSaveProcess.None, ModeLoadProcess.None, NextModeType.CloseForm, ButtonConfiguration.None),
        CancelEditAndView(false, "ModeEntryFormEnum.ModeAction.confirmation.CancelEditAndView", ModeSaveProcess.None, ModeLoadProcess.LoadData, NextModeType.ViewMode, ButtonConfiguration.View),
        CancelEditAndSearch(false, "ModeEntryFormEnum.ModeAction.confirmation.CancelEditAndSearch", ModeSaveProcess.None, ModeLoadProcess.LoadDefault, NextModeType.SearchMode, ButtonConfiguration.Search),
        CancelEditAndAdd(false, "ModeEntryFormEnum.ModeAction.confirmation.CancelEditAndAdd", ModeSaveProcess.None, ModeLoadProcess.LoadDefault, NextModeType.AddMode, ButtonConfiguration.Add),
        CancelEditAndCopy(false, "ModeEntryFormEnum.ModeAction.confirmation.CancelEditAndCopy", ModeSaveProcess.None, ModeLoadProcess.LoadData, NextModeType.AddMode, ButtonConfiguration.Copy),
        SaveEditAndClose(true, null, ModeSaveProcess.SaveEdit, ModeLoadProcess.None, NextModeType.CloseForm, ButtonConfiguration.None),
        SaveEditAndView(true, null, ModeSaveProcess.SaveEdit, ModeLoadProcess.LoadData, NextModeType.ViewMode, ButtonConfiguration.View),
        SaveEditAndEdit(true, null, ModeSaveProcess.SaveEdit, ModeLoadProcess.LoadData, NextModeType.EditMode, ButtonConfiguration.Edit),
        SaveEditAndSearch(true, null, ModeSaveProcess.SaveEdit, ModeLoadProcess.LoadDefault, NextModeType.SearchMode, ButtonConfiguration.Search),
        SaveEditAndAdd(true, null, ModeSaveProcess.SaveEdit, ModeLoadProcess.LoadDefault, NextModeType.AddMode, ButtonConfiguration.Add),
        SaveEditAndCopy(true, null, ModeSaveProcess.SaveEdit, ModeLoadProcess.LoadData, NextModeType.AddMode, ButtonConfiguration.Copy),
        SaveDeleteAndClose(false, "ModeEntryFormEnum.ModeAction.confirmation.SaveDeleteAndClose", ModeSaveProcess.SaveDelete, ModeLoadProcess.None, NextModeType.CloseForm, ButtonConfiguration.None),
        SaveDeleteAndSearch(false, "ModeEntryFormEnum.ModeAction.confirmation.SaveDeleteAndSearch", ModeSaveProcess.SaveDelete, ModeLoadProcess.LoadDefault, NextModeType.SearchMode, ButtonConfiguration.Search),
        SaveDeleteAndAdd(false, "ModeEntryFormEnum.ModeAction.confirmation.SaveDeleteAndAdd", ModeSaveProcess.SaveDelete, ModeLoadProcess.LoadDefault, NextModeType.AddMode, ButtonConfiguration.Add),
        //VerifyFields(true, null, ModeSaveProcess.None, ModeLoadProcess.None, NextModeType.None, ButtonConfiguration.None),
        CloseForm(false, null, ModeSaveProcess.None, ModeLoadProcess.None, NextModeType.CloseForm, ButtonConfiguration.None);
        //
        public boolean fieldVerification;
        public String confirmationText;
        public ModeSaveProcess saveProcess;
        public ModeLoadProcess loadProcess;
        public NextModeType nextModeType;
        public ButtonConfiguration buttonConfiguration;

        ModeAction(boolean fieldVerification,
                String confirmationText,
                ModeSaveProcess saveProcess,
                ModeLoadProcess loadProcess,
                NextModeType nextModeType,
                ButtonConfiguration buttonConfiguration) {
            this.fieldVerification = fieldVerification;
            this.confirmationText = confirmationText;
            this.saveProcess = saveProcess;
            this.loadProcess = loadProcess;
            this.nextModeType = nextModeType;
            this.buttonConfiguration = buttonConfiguration;
        }
    }

    public enum NextModeType {

        None, AddMode, EditMode, ViewMode, SearchMode, CloseForm;
    }

    public enum ModeSaveProcess {

        None,
        SaveAdd, SaveDelete, SaveEdit;
    }

    public enum ModeLoadProcess {

        None,
        LoadDefault, LoadData;
    }

    public enum ButtonConfiguration {

        None,
        Add,
        Copy,
        Search,
        Edit,
        View;

        public ButtonConfiguration2 x() {
            switch (this) {
                case Add:
                    return ButtonConfiguration2.Add;
                case Copy:
                    return ButtonConfiguration2.Copy;
                case Edit:
                    return ButtonConfiguration2.Edit;
                case Search:
                    return ButtonConfiguration2.Search;
                case View:
                    return ButtonConfiguration2.View;
            }
            return ButtonConfiguration2.None;
        }
    }

    public enum ButtonConfiguration2 {

        None(null, null, null, null, null, null,
        null, null, null, null, null, null, ModeAction.CloseForm),
        //
        Add(ModeAction.CancelAddAndSearch, null, null,
        null, null, ModeAction.SaveAddAndClose,
        ModeAction.CancelAddAndClose, null, ModeAction.ResetAdd,
        null, null, ModeAction.CancelAddAndClose, ModeAction.CancelAddAndClose),
        //
        Copy(ModeAction.CancelAddAndSearch, null, null,
        null, null, ModeAction.SaveAddAndClose,
        ModeAction.CancelAddAndClose, ModeAction.ReloadCopy, null,
        null, null, ModeAction.CloseForm, ModeAction.CancelAddAndClose),
        //
        Search(null, ModeAction.AddMode, null,
        null, null, ModeAction.ViewMode,
        null, null, ModeAction.SearchMode,
        null, null, ModeAction.CloseForm, ModeAction.CloseForm),
        //
        Edit(ModeAction.SearchMode, ModeAction.AddMode, null,
        null, null, ModeAction.SaveEditAndClose,
        ModeAction.CancelEditAndClose, ModeAction.ReloadEdit, null,
        null, null, ModeAction.CloseForm, ModeAction.CancelEditAndClose),
        //
        View(ModeAction.SearchMode, ModeAction.AddMode, ModeAction.CopyMode,
        ModeAction.EditMode, null, null,
        null, ModeAction.ViewMode, null,
        ModeAction.SaveDeleteAndClose, null, ModeAction.CloseForm, ModeAction.CloseForm);
        //
        public ModeAction search;
        public ModeAction add;
        public ModeAction copy;
        public ModeAction edit;
        public ModeAction view;
        public ModeAction ok;
        //
        public ModeAction cancel;
        public ModeAction reload;
        public ModeAction reset;
        public ModeAction delete;
        public ModeAction back;
        public ModeAction close;
        // 
        public ModeAction escapeKey;

        ButtonConfiguration2(ModeAction search, ModeAction add, ModeAction copy,
                ModeAction edit, ModeAction view, ModeAction ok, ModeAction cancel,
                ModeAction reload, ModeAction reset, ModeAction delete, ModeAction back,
                ModeAction close, ModeAction escapeKey) {
            this.search = search;
            this.add = add;
            this.copy = copy;
            this.edit = edit;
            this.view = view;
            this.ok = ok;
            this.cancel = cancel;
            this.reload = reload;
            this.reset = reset;
            this.delete = delete;
            this.back = back;
            this.close = close;
            this.escapeKey = escapeKey;
        }
    }

    public enum ModeSystemButton {

        Search("search", "ModeEntryFormEnum.ModeSystemButton.search.label",
        "ModeEntryFormEnum.ModeSystemButton.search.tooltip",
        ImageIconResource.getModeSearchButtonImageIcon(),
        FunctionKey.MODE_SEARCH),
        //
        Add("add", "ModeEntryFormEnum.ModeSystemButton.add.label",
        "ModeEntryFormEnum.ModeSystemButton.add.tooltip",
        ImageIconResource.getModeAddButtonImageIcon(),
        FunctionKey.MODE_ADD),
        //
        Copy("copy", "ModeEntryFormEnum.ModeSystemButton.copy.label",
        "ModeEntryFormEnum.ModeSystemButton.copy.tooltip",
        ImageIconResource.getModeCopyButtonImageIcon(),
        FunctionKey.MODE_COPY),
        //
        Edit("edit", "ModeEntryFormEnum.ModeSystemButton.edit.label",
        "ModeEntryFormEnum.ModeSystemButton.edit.tooltip",
        ImageIconResource.getModeEditButtonImageIcon(),
        FunctionKey.MODE_EDIT),
        //
        Delete("delete", "ModeEntryFormEnum.ModeSystemButton.delete.label",
        "ModeEntryFormEnum.ModeSystemButton.delete.tooltip",
        ImageIconResource.getModeDeleteButtonImageIcon(),
        FunctionKey.MODE_DELETE),
        //
        View("view", "ModeEntryFormEnum.ModeSystemButton.view.label",
        "ModeEntryFormEnum.ModeSystemButton.view.tooltip",
        ImageIconResource.getModeViewButtonImageIcon(),
        FunctionKey.MODE_VIEW),
        //
        OK("ok", "ModeEntryFormEnum.ModeSystemButton.ok.label",
        "ModeEntryFormEnum.ModeSystemButton.ok.tooltip",
        ImageIconResource.getModeOKButtonImageIcon(),
        FunctionKey.MODE_OK),
        //
        Cancel("cancel", "ModeEntryFormEnum.ModeSystemButton.cancel.label",
        "ModeEntryFormEnum.ModeSystemButton.cancel.tooltip",
        ImageIconResource.getModeCancelButtonImageIcon(),
        FunctionKey.MODE_CANCEL),
        //
        Close("close", "ModeEntryFormEnum.ModeSystemButton.close.label",
        "ModeEntryFormEnum.ModeSystemButton.close.tooltip",
        ImageIconResource.getModeCloseButtonImageIcon(),
        FunctionKey.MODE_CLOSE),
        //
        //edit/view
        Reload("reload", "ModeEntryFormEnum.ModeSystemButton.reload.label",
        "ModeEntryFormEnum.ModeSystemButton.reload.tooltip",
        ImageIconResource.getModeReloadButtonImageIcon(),
        FunctionKey.MODE_RELOAD),
        //
        //search/add
        Reset("reset", "ModeEntryFormEnum.ModeSystemButton.reset.label",
        "ModeEntryFormEnum.ModeSystemButton.reset.tooltip",
        ImageIconResource.getModeResetButtonImageIcon(),
        FunctionKey.MODE_RESET),
        //
        Back("back", "ModeEntryFormEnum.ModeSystemButton.back.label",
        "ModeEntryFormEnum.ModeSystemButton.back.tooltip",
        ImageIconResource.getModeBackButtonImageIcon(),
        FunctionKey.MODE_BACK); //
        public String name;
        public String label;
        public String tooltip;
        public ImageIcon icon;
        public FunctionKeyEnum.FunctionKey functionKey;

        ModeSystemButton(String name, String label, String tooltip,
                ImageIcon icon, FunctionKeyEnum.FunctionKey functionKey) {
            this.name = name;
            this.label = label;
            this.tooltip = tooltip;
            this.icon = icon;
            this.functionKey = functionKey;
        }
    }
}
