/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.L;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PI.PIP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSLL0PIP extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Sup", "BSSUP.Sup");
        super.setFieldHelpMessage("BSSUP.Sup.help");
    }

    @Override
    protected void buildDataList() {
        super.addNumericField("Price", "BSPIP.Price");
        super.setFieldHelpMessage("BSPIP.Price.help");
        super.setFieldOutputFormat("#0.00");

        super.addTextField("Status", "BSPIP.Status");
        super.setFieldHelpMessage("BSPIP.Status.help");
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
        super.setActionEnable(ListActionType.CLOSE, false);

        super.setAddForm(BSLS0PIP.class);
        super.setCopyForm(BSLS0PIP.class);
        super.setEditForm(BSLS0PIP.class);
        super.setViewForm(BSLS0PIP.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String Itm = cmd.in.map.texts.get(MAP.BSPI.ITM);
            String rsName = "BSLL0PIP";
            PIP.select_AllActive(this, rsName, Cmp, Itm);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSLL0PIP.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSLL0PIP.error.description");
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
    public void executeAdd(int selectedRow, int[] selectedRows) throws Exception {
        String Itm = cmd.in.map.texts.get(MAP.BSPI.ITM);
        cmd.out.map.texts.put(MAP.BSPI.ITM, Itm);
    }

    @Override
    public void executeView(int selectedRow, int[] selectedRows) throws Exception {
        String Itm = cmd.in.map.texts.get(MAP.BSPI.ITM);
        cmd.out.map.texts.put(MAP.BSPI.ITM, Itm);
        String sup = cmd.list.getString(selectedRow, "Sup");
        cmd.out.map.texts.put(MAP.BSSUP.SUP, sup);
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String Cmp = GET.Cmp(this);
        String Itm = cmd.in.map.texts.get(MAP.BSPI.ITM);
        String sup = cmd.list.getString(selectedRow, "Sup");
        cmd.db.begin();
        PIP.delete(this, Cmp, Itm, sup);
        cmd.db.commit();
    }

    @Override
    public String getFormTitle() {
        return "BSLL0PIP.title";
    }
}
