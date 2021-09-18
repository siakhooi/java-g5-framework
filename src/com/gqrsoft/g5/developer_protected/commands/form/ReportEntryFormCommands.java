/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.form;

import com.gqrsoft.g5.developer.publicobject.ReportEntryFormEnum;
import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;

/**
 *
 * @author Ng Siak Hooi
 */
public class ReportEntryFormCommands extends AbstractCommandComponent {

    public void executeViewOnScreen() {
        if (getFormControl().reportEntryForm != null) {
            getFormControl().reportEntryPanel.systemButtonClick(
                    ReportEntryFormEnum.ReportSystemButton.VIEWONSCREEN.name);
        }
    }

    public void executeQuickPrint() {
        if (getFormControl().reportEntryForm != null) {
            getFormControl().reportEntryPanel.systemButtonClick(
                    ReportEntryFormEnum.ReportSystemButton.QUICKPRINT.name);
        }
    }

    public void executePrint() {
        if (getFormControl().reportEntryForm != null) {
            getFormControl().reportEntryPanel.systemButtonClick(
                    ReportEntryFormEnum.ReportSystemButton.PRINT.name);
        }
    }

    public void executeSaveAsPdf() {
        if (getFormControl().reportEntryForm != null) {
            getFormControl().reportEntryPanel.systemButtonClick(
                    ReportEntryFormEnum.ReportSystemButton.SAVEASPDF.name);
        }
    }

    public void executeReset() {
        if (getFormControl().reportEntryForm != null) {
            getFormControl().reportEntryPanel.systemButtonClick(
                    ReportEntryFormEnum.ReportSystemButton.RESET.name);
        }
    }

    public void executeClose() {
        if (getFormControl().reportEntryForm != null) {
            getFormControl().reportEntryPanel.systemButtonClick(
                    ReportEntryFormEnum.ReportSystemButton.CLOSE.name);
        }
    }
}
