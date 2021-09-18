/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.D;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.is.A.ISAI1;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.UOM.UOM;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISDL0UOM extends ListForm {

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
        super.addTextField("Uom", "ISUOM.Uom");
        super.setFieldHelpMessage("ISUOM.Uom.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "ISUOM.Nme");
        super.setFieldHelpMessage("ISUOM.Nme.help");
        super.addTextField("Remark", "ISUOM.Remark");
        super.setFieldHelpMessage("ISUOM.Remark.help");
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

        super.setAddForm(ISDS0UOM.class);
        super.setCopyForm(ISDS0UOM.class);
        super.setEditForm(ISDS0UOM.class);
        super.setViewForm(ISDS0UOM.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Whs = GET.Whs(this);
            String rsName = "ISDL0UOM";
            UOM.select_All(this, rsName, Whs);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "ISDL0UOM.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "ISDL0UOM.error.description");
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
        String uom = cmd.list.getString(selectedRow, "Uom");
        cmd.out.map.texts.put(MAP.ISUOM.UOM, uom);
        cmd.out.stringValue = uom;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String uom = cmd.list.getString(selectedRow, "Uom");
        cmd.out.map.texts.put(MAP.ISUOM.UOM, uom);
        UserFormInterface f = cmd.form.create(ISDP0UOM.class);
        cmd.form.execute(f);
    }

    @Override
    public String getFormTitle() {
        return "ISDL0UOM.title";
    }
}
