/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.I;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.INV.INVD;
import com.mysoftwarehouse.bs.db.INV.INVE;
import com.mysoftwarehouse.bs.db.INV.INVJ_Amt;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSIL0INVD extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    public void receiveSignal(int signal) {
        if (signal == InvSignal.REFRESH) {
            cmd.list.refreshList();
        }
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Itm", "BSINVD.Itm");
        super.setFieldHelpMessage("BSINVD.Itm.help");
    }

    @Override
    protected void buildDataList() {
        super.addNumericField("Price", "BSINVD.Price");
        super.setFieldHelpMessage("BSINVD.Price.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("Qty", "BSINVD.Qty");
        super.setFieldHelpMessage("BSINVD.Qty.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("TtlAmt", "BSINVD.TtlAmt");
        super.setFieldHelpMessage("BSINVD.TtlAmt.help");
        super.setFieldOutputFormat("#0.00");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, true);
        super.setActionEnable(ListActionType.EDIT, true);
        super.setActionEnable(ListActionType.COPY, false);
        super.setActionEnable(ListActionType.VIEW, true);
        super.setActionEnable(ListActionType.DELETE, true);
        super.setActionEnable(ListActionType.RELOAD, true);
        super.setActionEnable(ListActionType.SELECT, false);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, false);

        super.setAddForm(BSIS0INVD.class);
        super.setCopyForm(BSIS0INVD.class);
        super.setEditForm(BSIS0INVD.class);
        super.setViewForm(BSIS0INVD.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String InvNo = cmd.local.map.texts.get(MAP.BSINV.INVNO);
            String rsName = "BSIL0INVD";
            INVD.select_All(this, rsName, Cmp, InvNo);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSIL0INVD.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSIL0INVD.error.description");
        }
    }

    @Override
    public void executeAdd(int selectedRow, int[] selectedRows) throws Exception {
        String InvNo = cmd.local.map.texts.get(MAP.BSINV.INVNO);
        cmd.out.map.texts.put(MAP.BSINV.INVNO, InvNo);
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
        String InvNo = cmd.local.map.texts.get(MAP.BSINV.INVNO);
        String Itm = cmd.list.getString(selectedRow, "Itm");
        cmd.out.map.texts.put(MAP.BSINV.INVNO, InvNo);
        cmd.out.map.texts.put(MAP.BSSI.ITM, Itm);
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws SQLException {
        String Cmp = GET.Cmp(this);
        String InvNo = cmd.local.map.texts.get(MAP.BSINV.INVNO);
        String Itm = cmd.list.getString(selectedRow, "Itm");
        cmd.db.begin();
        INVD.delete(this, Cmp, InvNo, Itm);
        INVE.delete(this, Cmp, InvNo, Itm);
        INVJ_Amt.calculate(this, Cmp, InvNo);
        cmd.db.commit();
        cmd.form.broadcastSignal(InvSignal.REFRESH);
    }

    @Override
    public String getFormTitle() {
        return "BSIL0INVD.title";
    }
}
