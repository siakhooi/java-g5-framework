/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.list;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.kernel.framepanel.ListFormFramePanel;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.FormEnum.EnterType;
import com.gqrsoft.g5.kernel.control.FormControl;
import java.io.File;

/**
 *
 * @author Ng Siak Hooi
 */
public class ListCommands {

    private ListForm form;
    private ListFormFramePanel listPanel;
    protected FormControl formControl;
    int[] _keep_selected_rows;
    int _keep_vertical_value;

    private void keepSelection() {
        _keep_selected_rows = getSelectedRows();
        _keep_vertical_value = listPanel.tablePanel.jScrollPane.getVerticalScrollBar().getValue();
    }

    private void restoreSelection() {
        formControl.cmd.list.clearSelection();
        for (int i : _keep_selected_rows) {
            formControl.cmd.list.addRowSelection(i, i);
        }
        listPanel.tablePanel.jScrollPane.getVerticalScrollBar().setValue(_keep_vertical_value);
    }

    public void doClose() {
        formControl.cmd.form.closeForm();
    }

    public void init(FormControl formControl) {
        this.formControl = formControl;
        listPanel = formControl.listPanel;
        form = formControl.listForm;
    }

    private int getSelectedRow() {
        return listPanel.table.getSelectedRow();
    }

    private int[] getSelectedRows() {
        return listPanel.table.getSelectedRows();
    }

    public void doAdd() throws Exception {
        if (!form.model.add) {
            return;
        }
        keepSelection();
        form.executeAdd(getSelectedRow(), getSelectedRows());
        Class<? extends UserFormInterface> ufClass =
                form.model.AddFormInterface;
        if (ufClass != null) {
            UserFormInterface uf =
                    formControl.cmd.form.create(ufClass);
            formControl.cmd.out.formEnterType = EnterType.LISTADD;
            formControl.cmd.form.execute(uf);
        }
        reload();
        restoreSelection();
    }

    public void doCopy() throws Exception {
        if (!form.model.copy) {
            return;
        }
        if (form.model.recordCheckOnCopy && getSelectedRows().length == 0) {
            String title = "ListPanel.CopyButton.error.title";
            String msg = "ListPanel.CopyButton.error.NoRecordSelected";
            title = formControl.cmd.lang.getSystemString(title);
            msg = formControl.cmd.lang.getSystemString(msg);
            formControl.cmd.common.showI18nMessage(
                    DialogMessageType.ERROR, title, msg);
            return;
        }
        keepSelection();
        form.executeCopy(getSelectedRow(), getSelectedRows());
        Class<? extends UserFormInterface> ufClass =
                form.model.CopyFormInterface;
        if (ufClass != null) {
            UserFormInterface uf =
                    formControl.cmd.form.create(ufClass);
            formControl.cmd.out.formEnterType = EnterType.LISTCOPY;
            formControl.cmd.form.execute(uf);
        }
        reload();
        restoreSelection();
    }

    public void doDelete() throws Exception {
        if (!form.model.delete) {
            return;
        }
        if (form.model.recordCheckOnDelete && getSelectedRows().length == 0) {
            String title = "ListPanel.DeleteButton.error.title";
            String msg = "ListPanel.DeleteButton.error.NoRecordSelected";
            title = formControl.cmd.lang.getSystemString(title);
            msg = formControl.cmd.lang.getSystemString(msg);
            formControl.cmd.common.showI18nMessage(
                    DialogMessageType.ERROR, title, msg);
            return;
        }
        int selectedRow = getSelectedRow();
        int[] selectedRows = getSelectedRows();
        form.verifyDelete(selectedRow, selectedRows);

        String i = formControl.cmd.data.int2String(selectedRows.length);
        String title = "ListPanel.ConfirmDelete.title";
        String msg = "ListPanel.ConfirmDelete.message.{0}Records";
        title = formControl.cmd.lang.getSystemString(title);
        msg = formControl.cmd.lang.getSystemString(msg, i);
        boolean b = formControl.cmd.common.showI18nConfirmation(
                DialogMessageType.WARNING, title, msg);
        if (!b) {
            return;
        }
        keepSelection();
        form.executeDelete(selectedRow, selectedRows);
        Class<? extends UserFormInterface> ufClass =
                form.model.DeleteFormInterface;
        if (ufClass != null) {
            UserFormInterface uf =
                    formControl.cmd.form.create(ufClass);
            formControl.cmd.out.formEnterType = EnterType.LISTDELETE;
            formControl.cmd.form.execute(uf);
        }
        reload();
        restoreSelection();
    }

    public void doEdit() throws Exception {
        if (!form.model.edit) {
            return;
        }
        if (form.model.recordCheckOnEdit && getSelectedRows().length == 0) {
            String title = "ListPanel.EditButton.error.title";
            String msg = "ListPanel.EditButton.error.NoRecordSelected";
            title = formControl.cmd.lang.getSystemString(title);
            msg = formControl.cmd.lang.getSystemString(msg);
            formControl.cmd.common.showI18nMessage(
                    DialogMessageType.ERROR, title, msg);
            return;
        }
        keepSelection();
        form.executeEdit(getSelectedRow(), getSelectedRows());
        Class<? extends UserFormInterface> ufClass =
                form.model.EditFormInterface;
        if (ufClass != null) {
            UserFormInterface uf =
                    formControl.cmd.form.create(ufClass);
            formControl.cmd.out.formEnterType = EnterType.LISTEDIT;
            formControl.cmd.form.execute(uf);
        }
        reload();
        restoreSelection();
    }

    public void doView() throws Exception {
        if (!form.model.view) {
            return;
        }
        if (form.model.recordCheckOnView && getSelectedRows().length == 0) {
            String title = "ListPanel.ViewButton.error.title";
            String msg = "ListPanel.ViewButton.error.NoRecordSelected";
            title = formControl.cmd.lang.getSystemString(title);
            msg = formControl.cmd.lang.getSystemString(msg);
            formControl.cmd.common.showI18nMessage(
                    DialogMessageType.ERROR, title, msg);
            return;
        }
        keepSelection();
        form.executeView(getSelectedRow(), getSelectedRows());
        Class<? extends UserFormInterface> ufClass =
                form.model.ViewFormInterface;
        if (ufClass != null) {
            UserFormInterface uf = formControl.cmd.form.create(
                    ufClass);
            formControl.cmd.out.formEnterType = EnterType.LISTVIEW;
            formControl.cmd.form.execute(uf);
        }
        reload();
        restoreSelection();
    }

    public void doReload1() {
        keepSelection();
        reload();
        restoreSelection();
    }

    private void reload() {
        form.executeReload(getSelectedRow(), getSelectedRows());
        listPanel.tableModel.reload();
        form.afterReload();
    }

    public void doSelect() throws Exception {
        if (!form.model.select) {
            return;
        }
//        int[] selectedRows = listPanel.table.getSelectedRows();

        if (getSelectedRows().length == 0) {
            String title = "ListPanel.SelectButton.error.title";
            String msg = "ListPanel.SelectButton.error.NoRecordSelected";
            title = formControl.cmd.lang.getSystemString(title);
            msg = formControl.cmd.lang.getSystemString(msg);
            formControl.cmd.common.showI18nMessage(
                    DialogMessageType.ERROR, title, msg);
            return;
        }
//        h.useSingle();
//        //move to this row
//        option().panel().listTableInputArea.getTableModel().getValueAt(selected, 0);
//        option().form().inFieldSelectReturn();
//        for(int i=0; i<selecteds.length; i++){
//            h.newRecord();
//            int j=selecteds[i];
//            option().panel().listTableInputArea.getTableModel().getValueAt(j, 0);
//            option().form().inFieldSelectReturn();
//        }
        form.executeSelect(getSelectedRow(), getSelectedRows());
        formControl.cmd.form.closeForm();
    }

    public void doSaveAllToCSV() throws Exception {
        if (!form.model.saveAllCSV) {
            return;
        }
        int selectedRow = getSelectedRow();
        int[] selectedRows = getSelectedRows();
        form.verifySaveAllToCSV(selectedRow, selectedRows);

        File i = formControl.cmd.common.chooseSaveCSVFile(null);
        if (i == null) {
            return;
        }
        formControl.cmd.out.i18nTitle = null;
        form.executeSaveAllToCSV(selectedRow, selectedRows);
        formControl.cmd.common.saveTableModelToCSVFile(
                listPanel.tableModel, i);
    }

    public void doSaveSelectedToCSV() throws Exception {
        if (!form.model.saveSelectedCSV) {
            return;
        }
        int selectedRow = getSelectedRow();
        int[] selectedRows = getSelectedRows();
        form.verifySaveSelectedToCSV(selectedRow, selectedRows);

        File i = formControl.cmd.common.chooseSaveCSVFile(null);
        if (i == null) {
            return;
        }
        formControl.cmd.out.i18nTitle = null;
        form.executeSaveSelectedToCSV(selectedRow, selectedRows);
        formControl.cmd.common.saveTableModelToCSVFile(
                listPanel.tableModel, i, selectedRows);
    }
}
