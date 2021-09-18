/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.D;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI1;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.SI.SI;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSDL0SI extends ListForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return BSAI1.class;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Itm", "BSSI.Itm");
        super.setFieldHelpMessage("BSSI.Itm.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSSI.Nme");
        super.setFieldHelpMessage("BSSI.Nme.help");
        super.addNumericField("Price", "BSSI.Price");
        super.setFieldHelpMessage("BSSI.Price.help");
        super.setFieldOutputFormat("#0.00");

        super.addTextField("Status", "BSSI.Status");
        super.setFieldHelpMessage("BSSI.Status.help");
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

        super.setAddForm(BSDS0SI.class);
        super.setCopyForm(BSDS0SI.class);
        super.setEditForm(BSDS0SI.class);
        super.setViewForm(BSDS0SI.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSDL0SI";
            SI.select_All(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSDL0SI.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSDL0SI.error.description");
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
        String itm = cmd.list.getString(selectedRow, "Itm");
        cmd.out.map.texts.put(MAP.BSSI.ITM, itm);
        cmd.out.stringValue = itm;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String itm = cmd.list.getString(selectedRow, "Itm");
        cmd.out.map.texts.put(MAP.BSSI.ITM, itm);
        UserFormInterface f = cmd.form.create(BSDP0SI.class);
        cmd.form.execute(f);
    }

    @Override
    public String getFormTitle() {
        return "BSDL0SI.title";
    }
}
