/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicobject;

import com.gqrsoft.g5.kernel.config.ImageIconResource;
import com.gqrsoft.g5.developer.publicobject.FunctionKeyEnum.FunctionKey;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Ng Siak Hooi
 */
public class ListFormEnum {

    public enum ListActionType {

        ADD,
        EDIT,
        COPY,
        VIEW,
        DELETE,
        RELOAD,
        SELECT,
        SAVE_ALL_TO_CSV,
        SAVE_SELECTED_TO_CSV,
        RECORD_CHECK_ON_DELETE,
        RECORD_CHECK_ON_COPY,
        RECORD_CHECK_ON_VIEW,
        RECORD_CHECK_ON_EDIT,
        CLOSE;
    }

    public enum SelectionType {

        SINGLE(ListSelectionModel.SINGLE_SELECTION),
        SINGLE_INTERVAL(ListSelectionModel.SINGLE_INTERVAL_SELECTION),
        MULTIPLE_INTERVAL(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        public int type;

        SelectionType(int type) {
            this.type = type;
        }
    }

    public enum DataType {

        VECTOR,
        RESULTSET,
        ARRAY;
    }

    public enum ColumnAutoResizeMode {

        OFF(JTable.AUTO_RESIZE_OFF),
        NEXT_COLUMN(JTable.AUTO_RESIZE_NEXT_COLUMN),
        SUBSEQUENT_COLUMNS(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS),
        LAST_COLUMN(JTable.AUTO_RESIZE_LAST_COLUMN),
        ALL_COLUMNS(JTable.AUTO_RESIZE_ALL_COLUMNS);
        public int autoResizeMode;

        ColumnAutoResizeMode(int autoResizeMode) {
            this.autoResizeMode = autoResizeMode;
        }
    }

    public enum ListSystemButton {

        Add("add", "ListFormEnum.ListSystemButton.add.label",
        "ListFormEnum.ListSystemButton.add.tooltip",
        ImageIconResource.getListAddButtonImageIcon(),
        FunctionKey.LIST_ADD),
        //
        Copy("copy", "ListFormEnum.ListSystemButton.copy.label",
        "ListFormEnum.ListSystemButton.copy.tooltip",
        ImageIconResource.getListCopyButtonImageIcon(),
        FunctionKey.LIST_COPY),
        //
        Edit("edit", "ListFormEnum.ListSystemButton.edit.label",
        "ListFormEnum.ListSystemButton.edit.tooltip",
        ImageIconResource.getListEditButtonImageIcon(),
        FunctionKey.LIST_EDIT),
        //
        Delete("delete", "ListFormEnum.ListSystemButton.delete.label",
        "ListFormEnum.ListSystemButton.delete.tooltip",
        ImageIconResource.getListDeleteButtonImageIcon(),
        FunctionKey.LIST_DELETE),
        //
        View("view", "ListFormEnum.ListSystemButton.view.label",
        "ListFormEnum.ListSystemButton.view.tooltip",
        ImageIconResource.getListViewButtonImageIcon(),
        FunctionKey.LIST_VIEW),
        //
        Select("select", "ListFormEnum.ListSystemButton.select.label",
        "ListFormEnum.ListSystemButton.select.tooltip",
        ImageIconResource.getListSelectButtonImageIcon(),
        FunctionKey.LIST_SELECT),
        //
        Close("close", "ListFormEnum.ListSystemButton.close.label",
        "ListFormEnum.ListSystemButton.close.tooltip",
        ImageIconResource.getListCloseButtonImageIcon(),
        FunctionKey.LIST_CLOSE),
        //
        //edit/view
        Reload("reload", "ListFormEnum.ListSystemButton.reload.label",
        "ListFormEnum.ListSystemButton.reload.tooltip",
        ImageIconResource.getListReloadButtonImageIcon(),
        FunctionKey.LIST_RELOAD),
        //
        //search/add
        SaveAllToCsv("savealltocsv", "ListFormEnum.ListSystemButton.savealltocsv.label",
        "ListFormEnum.ListSystemButton.savealltocsv.tooltip",
        ImageIconResource.getListSaveAllToCSVButtonImageIcon(),
        FunctionKey.LIST_SAVEALLTOCSV),
        //
        SaveSelectedToCsv("saveselectedtocsv", "ListFormEnum.ListSystemButton.saveselectedtocsv.label",
        "ListFormEnum.ListSystemButton.saveselectedtocsv.tooltip",
        ImageIconResource.getListSaveSelectedToCSVButtonImageIcon(),
        FunctionKey.LIST_SAVESELECTEDTOCSV);
        public String name;
        public String label;
        public String tooltip;
        public ImageIcon icon;
        public FunctionKeyEnum.FunctionKey functionKey;

        ListSystemButton(String name, String label, String tooltip,
                ImageIcon icon, FunctionKeyEnum.FunctionKey functionKey) {
            this.name = name;
            this.label = label;
            this.tooltip = tooltip;
            this.icon = icon;
            this.functionKey = functionKey;
        }
    }
}
