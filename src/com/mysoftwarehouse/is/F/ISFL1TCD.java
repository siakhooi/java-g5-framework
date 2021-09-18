/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.F;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.TCD.TCD;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISFL1TCD extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Tcd", "ISTCD.Tcd");
        super.setFieldHelpMessage("ISTCD.Tcd.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "ISTCD.Nme");
        super.setFieldHelpMessage("ISTCD.Nme.help");
        super.addTextField("Typ", "ISTCD.Typ");
        super.setFieldHelpMessage("ISTCD.Typ.help");
        super.addTextField("Remark", "ISTCD.Remark");
        super.setFieldHelpMessage("ISTCD.Remark.help");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, true);
        super.setActionEnable(ListActionType.EDIT, true);
        super.setActionEnable(ListActionType.COPY, true);
        super.setActionEnable(ListActionType.VIEW, true);
        super.setActionEnable(ListActionType.DELETE, false);
        super.setActionEnable(ListActionType.RELOAD, false);
        super.setActionEnable(ListActionType.SELECT, true);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setAddForm(ISFS0TCD.class);
        super.setCopyForm(ISFS0TCD.class);
        super.setEditForm(ISFS0TCD.class);
        super.setViewForm(ISFS0TCD.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Whs = GET.Whs(this);
            String rsName = "ISFL1TCD";
            TCD.select_All(this, rsName, Whs);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "ISFL1TCD.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title,
                    "ISFL1TCD.error.description");
        }
    }

    @Override
    public void executeCopy(int selectedRow, int[] selectedRows) throws Exception {
        executeView(selectedRow, selectedRows);
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        executeView(selectedRow, selectedRows);
    }

    @Override
    public void executeView(int selectedRow, int[] selectedRows) throws Exception {
        String tcd = cmd.list.getString(selectedRow, "Tcd");
        cmd.out.map.texts.put(MAP.ISTCD.TCD, tcd);
        cmd.out.stringValue = tcd;
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        String tcd = cmd.list.getString(selectedRow, "Tcd");
        cmd.in.stringValue = tcd;
    }

    @Override
    public String getFormTitle() {
        return "ISFL1TCD.title";
    }
}
