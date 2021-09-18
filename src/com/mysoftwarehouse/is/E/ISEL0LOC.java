/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.E;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.is.A.ISAI1;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.LOC.LOC;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISEL0LOC extends ListForm {

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
        super.addTextField("Loc", "ISLOC.Loc");
        super.setFieldHelpMessage("ISLOC.Loc.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "ISLOC.Nme");
        super.setFieldHelpMessage("ISLOC.Nme.help");
        super.addTextField("Remark", "ISLOC.Remark");
        super.setFieldHelpMessage("ISLOC.Remark.help");
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

        super.setAddForm(ISES0LOC.class);
        super.setCopyForm(ISES0LOC.class);
        super.setEditForm(ISES0LOC.class);
        super.setViewForm(ISES0LOC.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Whs = GET.Whs(this);
            String rsName = "ISEL0LOC";
            LOC.select_All(this, rsName, Whs);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "ISEL0LOC.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "ISEL0LOC.error.description");
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
        String loc = cmd.list.getString(selectedRow, "Loc");
        cmd.out.map.texts.put(MAP.ISLOC.LOC, loc);
        cmd.out.stringValue = loc;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String loc = cmd.list.getString(selectedRow, "Loc");
        cmd.out.map.texts.put(MAP.ISLOC.LOC, loc);
        UserFormInterface f = cmd.form.create(ISEP0LOC.class);
        cmd.form.execute(f);
    }

    @Override
    public String getFormTitle() {
        return "ISEL0LOC.title";
    }
}
