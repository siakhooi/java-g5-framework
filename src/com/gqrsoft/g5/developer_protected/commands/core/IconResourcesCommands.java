/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.core;

import com.gqrsoft.g5.kernel.config.EngineConfiguration;
import com.gqrsoft.g5.kernel.config.ImageIconResource;
import com.gqrsoft.g5.kernel.core.util.IMAGE;
import javax.swing.ImageIcon;

/**
 *
 * @author Ng Siak Hooi
 */
public class IconResourcesCommands {

    public int getDefaultHeight() {
        return EngineConfiguration.Entry.DEFAULT_ICON_HEIGHT;
    }

    public int getDefaultMenuIconHeight() {
        return EngineConfiguration.Entry.DEFAULT_CONTEXT_MENU_IMAGE_HEIGHT;
    }

    public ImageIcon getLogo() {
        return ImageIconResource.getEngineLogo();
    }

    public ImageIcon getBlankIcon(int h) {
        return IMAGE.resize(ImageIconResource.getBlankIcon(), h);
    }

    public ImageIcon getContextMenuLookupIcon(int h) {
        return IMAGE.resize(ImageIconResource.getContextMenuLookupIcon(), h);
    }

    public ImageIcon getContextMenuHelpIcon(int h) {
        return IMAGE.resize(ImageIconResource.getContextMenuHelpIcon(), h);
    }

    public ImageIcon getContextMenuCutIcon(int h) {
        return IMAGE.resize(ImageIconResource.getContextMenuCutIcon(), h);
    }

    public ImageIcon getContextMenuCopyIcon(int h) {
        return IMAGE.resize(ImageIconResource.getContextMenuCopyIcon(), h);
    }

    public ImageIcon getContextMenuPasteIcon(int h) {
        return IMAGE.resize(ImageIconResource.getContextMenuPasteIcon(), h);
    }

    public ImageIcon getContextMenuDeleteIcon(int h) {
        return IMAGE.resize(ImageIconResource.getContextMenuDeleteIcon(), h);
    }

    public ImageIcon getContextMenuSelectAllIcon(int h) {
        return IMAGE.resize(ImageIconResource.getContextMenuSelectAllIcon(), h);
    }

    public ImageIcon getContextMenuClearAllIcon(int h) {
        return IMAGE.resize(ImageIconResource.getContextMenuClearAllIcon(), h);
    }

    //=== start of mode icons    
    public ImageIcon getSearchIcon(int h) {
        return IMAGE.resize(ImageIconResource.getModeSearchButtonImageIcon(), h);
    }

    public ImageIcon getAddIcon(int h) {
        return IMAGE.resize(ImageIconResource.getModeSearchButtonImageIcon(), h);
    }

    public ImageIcon getCopyIcon(int h) {
        return IMAGE.resize(ImageIconResource.getModeCopyButtonImageIcon(), h);
    }

    public ImageIcon getEditIcon(int h) {
        return IMAGE.resize(ImageIconResource.getModeEditButtonImageIcon(), h);
    }

    public ImageIcon getDeleteIcon(int h) {
        return IMAGE.resize(ImageIconResource.getModeDeleteButtonImageIcon(), h);
    }

    public ImageIcon getViewIcon(int h) {
        return IMAGE.resize(ImageIconResource.getModeViewButtonImageIcon(), h);
    }

    public ImageIcon getOKIcon(int h) {
        return IMAGE.resize(ImageIconResource.getModeOKButtonImageIcon(), h);
    }

    public ImageIcon getCancelIcon(int h) {
        return IMAGE.resize(ImageIconResource.getModeCancelButtonImageIcon(), h);
    }

    public ImageIcon getCloseIcon(int h) {
        return IMAGE.resize(ImageIconResource.getModeCloseButtonImageIcon(), h);
    }

    public ImageIcon getReloadIcon(int h) {
        return IMAGE.resize(ImageIconResource.getModeReloadButtonImageIcon(), h);
    }

    public ImageIcon getResetIcon(int h) {
        return IMAGE.resize(ImageIconResource.getModeResetButtonImageIcon(), h);
    }

    public ImageIcon getBackIcon(int h) {
        return IMAGE.resize(ImageIconResource.getModeBackButtonImageIcon(), h);
    }

    //=== start of report icons    
    public ImageIcon getViewOnScreenIcon(int h) {
        return IMAGE.resize(ImageIconResource.getReportViewOnScreenButtonImageIcon(), h);
    }

    public ImageIcon getQuickPrintIcon(int h) {
        return IMAGE.resize(ImageIconResource.getReportQuickPrintButtonImageIcon(), h);
    }

    public ImageIcon getPrintIcon(int h) {
        return IMAGE.resize(ImageIconResource.getReportPrintButtonImageIcon(), h);
    }

    public ImageIcon getPDFIcon(int h) {
        return IMAGE.resize(ImageIconResource.getReportSaveAsPDFButtonImageIcon(), h);
    }

    public ImageIcon getSelectIcon(int h) {
        return IMAGE.resize(ImageIconResource.getListSelectButtonImageIcon(), h);
    }

    public ImageIcon getExportAllIcon(int h) {
        return IMAGE.resize(ImageIconResource.getListExportAllButtonImageIcon(), h);
    }

    public ImageIcon getExportIcon(int h) {
        return IMAGE.resize(ImageIconResource.getListExportButtonImageIcon(), h);
    }

    public ImageIcon getCSVIcon(int h) {
        return IMAGE.resize(ImageIconResource.getListCSVButtonImageIcon(), h);
    }

    public ImageIcon getApplicationIcon(int h) {
        return IMAGE.resize(ImageIconResource.getMenuApplicationIcon(), h);
    }

    public ImageIcon getCommandIcon(int h) {
        return IMAGE.resize(ImageIconResource.getMenuCommandIcon(), h);
    }
}
