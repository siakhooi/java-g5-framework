/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicobject;

import com.gqrsoft.g5.developer.publicobject.FunctionKeyEnum.FunctionKey;
import com.gqrsoft.g5.kernel.config.ImageIconResource;
import javax.swing.ImageIcon;

/**
 *
 * @author Ng Siak Hooi
 */
public class ReportEntryFormEnum {

    public enum ReportSystemButton {

        VIEWONSCREEN("view", "ReportEntryFormEnum.ReportSystemButton.viewonscreen.label",
        "ReportEntryFormEnum.ReportSystemButton.viewonscreen.tooltip",
        ImageIconResource.getReportViewOnScreenButtonImageIcon(),
        FunctionKey.REPORT_VIEWONSCREEN),
        //
        QUICKPRINT("quickprint", "ReportEntryFormEnum.ReportSystemButton.quickprint.label",
        "ReportEntryFormEnum.ReportSystemButton.quickprint.tooltip",
        ImageIconResource.getReportQuickPrintButtonImageIcon(),
        FunctionKey.REPORT_QUICKPRINT),
        //
        PRINT("print", "ReportEntryFormEnum.ReportSystemButton.print.label",
        "ReportEntryFormEnum.ReportSystemButton.print.tooltip",
        ImageIconResource.getReportPrintButtonImageIcon(),
        FunctionKey.REPORT_PRINT),
        //
        SAVEASPDF("saveaspdf", "ReportEntryFormEnum.ReportSystemButton.saveaspdf.label",
        "ReportEntryFormEnum.ReportSystemButton.saveaspdf.tooltip",
        ImageIconResource.getReportSaveAsPDFButtonImageIcon(),
        FunctionKey.REPORT_SAVEASPDF),
        //
        RESET("reset", "ReportEntryFormEnum.ReportSystemButton.reset.label",
        "ReportEntryFormEnum.ReportSystemButton.reset.tooltip",
        ImageIconResource.getReportResetButtonImageIcon(),
        FunctionKey.REPORT_RESET),
        //
        CLOSE("close", "ReportEntryFormEnum.ReportSystemButton.close.label",
        "ReportEntryFormEnum.ReportSystemButton.close.tooltip",
        ImageIconResource.getReportCloseButtonImageIcon(),
        FunctionKey.REPORT_CLOSE);
        //
        public String name;
        public String label;
        public String tooltip;
        public ImageIcon icon;
        public FunctionKeyEnum.FunctionKey functionKey;

        ReportSystemButton(String name, String label, String tooltip,
                ImageIcon icon, FunctionKeyEnum.FunctionKey functionKey) {
            this.name = name;
            this.label = label;
            this.tooltip = tooltip;
            this.icon = icon;
            this.functionKey = functionKey;
        }
    }
}
