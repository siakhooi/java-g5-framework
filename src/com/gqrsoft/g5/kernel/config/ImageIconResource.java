/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.config;

import com.gqrsoft.g5.kernel.core.util.IMAGE;
import javax.swing.ImageIcon;

/**
 *
 * @author Ng Siak Hooi
 */
public class ImageIconResource {

    private static final String PATHBASE = "/com/gqrsoft/g5/resources/images/";

    public static ImageIcon getEngineLogo() {
        return IMAGE.createImageIcon(PATHBASE + "icon.jpg");
    }

    public static ImageIcon getBlankIcon() {
        return IMAGE.createImageIcon(PATHBASE + "blank.png");
    }
    // === context menu
    public static ImageIcon getContextMenuLookupIcon() {
        return IMAGE.createImageIcon(PATHBASE + "contextmenu/lookup.png");
    }

    public static ImageIcon getContextMenuHelpIcon() {
        return IMAGE.createImageIcon(PATHBASE + "contextmenu/help.png");
    }

    public static ImageIcon getContextMenuCutIcon() {
        return IMAGE.createImageIcon(PATHBASE + "contextmenu/cut.png");
    }

    public static ImageIcon getContextMenuCopyIcon() {
        return IMAGE.createImageIcon(PATHBASE + "contextmenu/copy.png");
    }

    public static ImageIcon getContextMenuPasteIcon() {
        return IMAGE.createImageIcon(PATHBASE + "contextmenu/paste.png");
    }

    public static ImageIcon getContextMenuDeleteIcon() {
        return IMAGE.createImageIcon(PATHBASE + "contextmenu/delete.png");
    }

    public static ImageIcon getContextMenuSelectAllIcon() {
        return IMAGE.createImageIcon(PATHBASE + "contextmenu/selectall.png");
    }

    public static ImageIcon getContextMenuClearAllIcon() {
        return IMAGE.createImageIcon(PATHBASE + "contextmenu/clearall.png");
    }

    //=== start of mode icons    
    public static ImageIcon getModeSearchButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "mode/search.png");
    }

    public static ImageIcon getModeAddButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "mode/add.png");
    }

    public static ImageIcon getModeCopyButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "mode/copy.png");
    }

    public static ImageIcon getModeEditButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "mode/edit.png");
    }

    public static ImageIcon getModeDeleteButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "mode/delete.png");
    }

    public static ImageIcon getModeViewButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "mode/view.png");
    }

    public static ImageIcon getModeOKButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "mode/ok.png");
    }

    public static ImageIcon getModeCancelButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "mode/cancel.png");
    }

    public static ImageIcon getModeCloseButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "mode/close.png");
    }

    public static ImageIcon getModeReloadButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "mode/reload.png");
    }

    public static ImageIcon getModeResetButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "mode/reset.png");
    }

    public static ImageIcon getModeBackButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "mode/back.png");
    }

    //=== start of report icons    
    public static ImageIcon getReportViewOnScreenButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "report/screen.png");
    }

    public static ImageIcon getReportQuickPrintButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "report/quickprint.png");
    }

    public static ImageIcon getReportPrintButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "report/print.png");
    }

    public static ImageIcon getReportSaveAsPDFButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "report/pdf.png");
    }
//    public static ImageIcon getReportSaveAsPDFButtonImageIcon() {
//        ImageIcon ic1 = getReportSaveButtonImageIcon();
//        ImageIcon ic2 = getReportPDFButtonImageIcon();
//        return IMAGE.resize(
//                IMAGE.mergeImageIcons(ic1, ic2),
//                EngineConfiguration.Entry.DEFAULT_ICON_HEIGHT);
//    }
//    private static ImageIcon getReportSaveButtonImageIcon() {
//        return IMAGE.createImageIcon(PATHBASE + "report/export.png");
//    }
//
//    private static ImageIcon getReportPDFButtonImageIcon() {
//        return IMAGE.createImageIcon(PATHBASE + "report/pdf.png");
//    }
    public static ImageIcon getReportCloseButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "report/close.png");
    }

    public static ImageIcon getReportResetButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "report/reset.png");
    }

//=== start of list icons    
    public static ImageIcon getListAddButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "list/add.png");
    }

    public static ImageIcon getListEditButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "list/edit.png");
    }

    public static ImageIcon getListViewButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "list/view.png");
    }

    public static ImageIcon getListDeleteButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "list/delete.png");
    }

    public static ImageIcon getListCopyButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "list/copy.png");
    }

    public static ImageIcon getListSaveAllToCSVButtonImageIcon() {
        return getListCSVButtonImageIcon();
//        ImageIcon ic1 = getListExportAllButtonImageIcon();
//        ImageIcon ic2 = getListCSVButtonImageIcon();
//        return IMAGE.mergeImageIcons(ic1, ic2);
    }

    public static ImageIcon getListSaveSelectedToCSVButtonImageIcon() {
        return getListCSVButtonImageIcon();
//        ImageIcon ic1 = getListExportButtonImageIcon();
//        ImageIcon ic2 = getListCSVButtonImageIcon();
//        return IMAGE.mergeImageIcons(ic1, ic2);
    }

    public static ImageIcon getListReloadButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "list/reload.png");
    }

    public static ImageIcon getListSelectButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "list/select.png");
    }
    /*
     *  not directly use in button
     */

    public static ImageIcon getListExportButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "list/export.png");
    }

    /*
     *  not directly use in button
     */
    public static ImageIcon getListExportAllButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "list/saveall.png");
    }
    /*
     *  not directly use in button
     */

    public static ImageIcon getListCSVButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "list/csv.png");
    }

    public static ImageIcon getListCloseButtonImageIcon() {
        return IMAGE.createImageIcon(PATHBASE + "list/close.png");
    }


//=== start of entry field icons
    public static ImageIcon getEntryFieldLookupButton() {
        return IMAGE.createImageIcon(PATHBASE + "entryfield/lookup.png");
    }

//=== start of process icons
    public static ImageIcon getProcessFormInformationIcon() {
        return IMAGE.createImageIcon(PATHBASE + "process/process.png");
    }
//=== start of menu icons
    public static ImageIcon getMenuApplicationIcon() {
        return IMAGE.createImageIcon(PATHBASE + "menu/app.png");
    }

    public static ImageIcon getMenuRootIcon() {
        return IMAGE.createImageIcon(PATHBASE + "menu/root.png");
    }

    public static ImageIcon getMenuCommandIcon() {
        return IMAGE.createImageIcon(PATHBASE + "menu/command.png");
    }

    public static ImageIcon getMenuRoleOpenIcon() {
        return IMAGE.createImageIcon(PATHBASE + "menu/roleopen.png");
    }

    public static ImageIcon getMenuRoleCloseIcon() {
        return IMAGE.createImageIcon(PATHBASE + "menu/roleclose.png");
    }

    public static ImageIcon getMenuCategoryOpenIcon() {
        return IMAGE.createImageIcon(PATHBASE + "menu/catopen.png");
    }

    public static ImageIcon getMenuCategoryCloseIcon() {
        return IMAGE.createImageIcon(PATHBASE + "menu/catclose.png");
    }
}
