/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.F;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.is.A.ISAI1;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.TCD.TCD;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISFL0TCD extends ListForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ISAI1.class;
    }

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
        super.setActionEnable(ListActionType.DELETE, true);
        super.setActionEnable(ListActionType.RELOAD, false);
        super.setActionEnable(ListActionType.SELECT, false);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, true);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, true);
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
            String rsName = "ISFL0TCD";
            TCD.select_All(this, rsName, Whs);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "ISFL0TCD.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "ISFL0TCD.error.description");
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
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String tcd = cmd.list.getString(selectedRow, "Tcd");
        cmd.out.map.texts.put(MAP.ISTCD.TCD, tcd);
        UserFormInterface f = cmd.form.create(ISFP0TCD.class);
        cmd.form.execute(f);
    }

    @Override
    public String getFormTitle() {
        return "ISFL0TCD.title";
    }
}
