/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel;

import com.gqrsoft.g5.developer.publicobject.ReportEntryFormEnum;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.EnterType;
import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.config.ImageIconResource;
import com.gqrsoft.g5.kernel.core.util.IMAGE;
import javax.swing.ImageIcon;

/**
 *
 * @author Ng Siak Hooi
 */
public class ReportEntryFormFramePanel extends EntryFormFramePanel {

    @Override
    public void onEscapeKeyPressed() {
        systemButtonClick(ReportEntryFormEnum.ReportSystemButton.CLOSE.name);
    }

    @Override
    public final void systemButtonClick(String name) {
        if (name.equals(ReportEntryFormEnum.ReportSystemButton.CLOSE.name)) {
            if (!getFormControl().reportEntryForm.hasCloseButton) {
                return;
            }
            getFormControl().cmd.form.closeForm();
            return;
        }
        if (name.equals(ReportEntryFormEnum.ReportSystemButton.RESET.name)) {
            if (!getFormControl().reportEntryForm.hasResetButton) {
                return;
            }
            super.resetValue();
            //getFormControl().reportEntryForm.initValue();
            return;
        }
        if (name.equals(ReportEntryFormEnum.ReportSystemButton.VIEWONSCREEN.name)) {
            executeForm(getFormControl().reportEntryForm.hasViewButton, EnterType.REPORTVIEW);
            return;
        } else if (name.equals(ReportEntryFormEnum.ReportSystemButton.PRINT.name)) {
            executeForm(getFormControl().reportEntryForm.hasPrintDialogButton, EnterType.REPORTPRINT);
            return;
        } else if (name.equals(ReportEntryFormEnum.ReportSystemButton.QUICKPRINT.name)) {
            executeForm(getFormControl().reportEntryForm.hasPrintDirectButton, EnterType.REPORTQUICKPRINT);
            return;
        } else if (name.equals(ReportEntryFormEnum.ReportSystemButton.SAVEASPDF.name)) {
            executeForm(getFormControl().reportEntryForm.hasSaveAsPDFButton, EnterType.REPORTSAVEASPDF);
            return;
        }
    }

    private void executeForm(boolean hasButton, EnterType t) {
        if (!hasButton) {
            return;
        }
        if (getFormControl().reportEntryForm.getReportForm() == null) {
            return;
        }
        if (!super.verifyAllFields(true, true)) {
            return;
        }

        getFormControl().cmd.out.formEnterType = t;
        ReportExecutionForm form =
                (ReportExecutionForm) getFormControl().cmd.form.create(
                getFormControl().reportEntryForm.getReportForm());
        getFormControl().cmd.form.executeInNewThread(form);
    }

    @Override
    protected final void buildSystemButtonsList() {
        if (getFormControl().reportEntryForm.hasViewButton) {
            insertSystemButton(ReportEntryFormEnum.ReportSystemButton.VIEWONSCREEN);
        }
        if (getFormControl().reportEntryForm.hasPrintDirectButton) {
            insertSystemButton(ReportEntryFormEnum.ReportSystemButton.QUICKPRINT);
        }
        if (getFormControl().reportEntryForm.hasPrintDialogButton) {
            insertSystemButton(ReportEntryFormEnum.ReportSystemButton.PRINT);
        }
        if (getFormControl().reportEntryForm.hasSaveAsPDFButton) {
            insertSystemButton(ReportEntryFormEnum.ReportSystemButton.SAVEASPDF);
        }
        systemButtons.addSeparator();
        if (getFormControl().reportEntryForm.hasResetButton) {
            insertSystemButton(ReportEntryFormEnum.ReportSystemButton.RESET);
        }
        systemButtons.addSeparator();
        if (getFormControl().reportEntryForm.hasCloseButton) {
            insertSystemButton(ReportEntryFormEnum.ReportSystemButton.CLOSE);
        }
    }

    private void insertSystemButton(ReportEntryFormEnum.ReportSystemButton b) {
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
        systemButtons.setDisabledIcon(disabledIcon);
    }
}
