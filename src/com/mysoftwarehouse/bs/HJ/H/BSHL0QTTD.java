/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.H;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.QTT.QTTD;
import com.mysoftwarehouse.bs.db.QTT.QTTE;
import com.mysoftwarehouse.bs.db.QTT.QTTJ_Amt;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSHL0QTTD extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    public void receiveSignal(int signal) {
        if (signal == QttSignal.REFRESH) {
            cmd.list.refreshList();
        }
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Itm", "BSQTTD.Itm");
        super.setFieldHelpMessage("BSQTTD.Itm.help");
    }

    @Override
    protected void buildDataList() {
        super.addNumericField("Price", "BSQTTD.Price");
        super.setFieldHelpMessage("BSQTTD.Price.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("Qty", "BSQTTD.Qty");
        super.setFieldHelpMessage("BSQTTD.Qty.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("TtlAmt", "BSQTTD.TtlAmt");
        super.setFieldHelpMessage("BSQTTD.TtlAmt.help");
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

        super.setAddForm(BSHS0QTTD.class);
        super.setCopyForm(BSHS0QTTD.class);
        super.setEditForm(BSHS0QTTD.class);
        super.setViewForm(BSHS0QTTD.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String QttNo = cmd.local.map.texts.get(MAP.BSQTT.QTTNO);
            String rsName = "BSHL0QTTD";
            QTTD.select_All(this, rsName, Cmp, QttNo);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSHL0QTTD.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSHL0QTTD.error.description");
        }
    }

    @Override
    public void executeAdd(int selectedRow, int[] selectedRows) throws Exception {
        String Qtt = cmd.local.map.texts.get(MAP.BSQTT.QTTNO);
        cmd.out.map.texts.put(MAP.BSQTT.QTTNO, Qtt);
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
        String QttNo = cmd.local.map.texts.get(MAP.BSQTT.QTTNO);
        String Itm = cmd.list.getString(selectedRow, "Itm");
        cmd.out.map.texts.put(MAP.BSQTT.QTTNO, QttNo);
        cmd.out.map.texts.put(MAP.BSSI.ITM, Itm);
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String Cmp = GET.Cmp(this);
        String QttNo = cmd.local.map.texts.get(MAP.BSQTT.QTTNO);
        String Itm = cmd.list.getString(selectedRow, "Itm");
        cmd.db.begin();
        QTTD.delete(this, Cmp, QttNo, Itm);
        QTTE.delete(this, Cmp, QttNo, Itm);
        QTTJ_Amt.calculate(this, Cmp, QttNo);
        cmd.db.commit();
        cmd.form.broadcastSignal(QttSignal.REFRESH);
    }

    @Override
    public String getFormTitle() {
        return "BSHL0QTTD.title";
    }
}
