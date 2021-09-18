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
import com.mysoftwarehouse.bs.db.QTT.QTTJ;
import com.mysoftwarehouse.bs.db.QTT.QTTJ_Amt;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSHL0QTTJ extends ListForm {

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
        super.addNumericField("Seq", "BSQTTJ.Seq");
        super.setFieldHelpMessage("BSQTTJ.Seq.help");
        super.setFieldOutputFormat("#0");

        super.addTextField("Adj", "BSQTTJ.Adj");
        super.setFieldHelpMessage("BSQTTJ.Adj.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSQTTJ.Nme");
        super.setFieldHelpMessage("BSQTTJ.Nme.help");

        super.addTextField("Typ", "BSQTTJ.Typ");
        super.setFieldHelpMessage("BSQTTJ.Typ.help");

        super.addNumericField("Prio", "BSQTTJ.Prio");
        super.setFieldHelpMessage("BSQTTJ.Prio.help");
        super.setFieldOutputFormat("#0");

        super.addNumericField("Amt", "BSQTTJ.Amt");
        super.setFieldHelpMessage("BSQTTJ.Amt.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("EffAmt", "BSQTTJ.EffAmt");
        super.setFieldHelpMessage("BSQTTJ.EffAmt.help");
        super.setFieldOutputFormat("#0.00");

//        super.addNumericField("OpenAmt", "BSQTTJ.OpenAmt");
//        super.setFieldHelpMessage("BSQTTJ.OpenAmt.help");
//        super.setFieldOutputFormat("#0.00");
//
        super.addNumericField("NewAmt", "BSQTTJ.NewAmt");
        super.setFieldHelpMessage("BSQTTJ.NewAmt.help");
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

        super.setAddForm(BSHS0QTTJ.class);
        super.setCopyForm(BSHS0QTTJ.class);
        super.setEditForm(BSHS0QTTJ.class);
        super.setViewForm(BSHS0QTTJ.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String QttNo = cmd.local.map.texts.get(MAP.BSQTT.QTTNO);
            String rsName = "BSHL0QTTJ";
            QTTJ.select_All(this, rsName, Cmp, QttNo);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSHL0QTTJ.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSHL0QTTJ.error.description");
        }
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        executeView(selectedRow, selectedRows);
    }

    @Override
    public void executeView(int selectedRow, int[] selectedRows) throws Exception {
        String QttNo = cmd.local.map.texts.get(MAP.BSQTT.QTTNO);
        int seq = ((Integer) cmd.list.getObject(selectedRow, "Seq")).intValue();
        cmd.out.intValue = seq;
        cmd.out.map.texts.put(MAP.BSQTT.QTTNO, QttNo);
    }

    @Override
    public void executeAdd(int arg0, int[] arg1) throws Exception {
        String QttNo = cmd.local.map.texts.get(MAP.BSQTT.QTTNO);
        cmd.out.map.texts.put(MAP.BSQTT.QTTNO, QttNo);
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String Cmp = GET.Cmp(this);
        String Qtt = cmd.local.map.texts.get(MAP.BSQTT.QTTNO);
        int seq = ((Integer) cmd.list.getObject(selectedRow, "Seq")).intValue();
        try {
            cmd.db.begin();
            QTTJ.delete(this, Cmp, Qtt, seq);
            QTTJ_Amt.calculate(this, Cmp, Qtt);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.log.severe("BSHL0QTTJ.error", ex);
            cmd.db.rollback();
        }
    }

    @Override
    public void onOutEnter() {
        cmd.form.broadcastSignal(QttSignal.REFRESH);
    }

    @Override
    public String getFormTitle() {
        return "BSHL0QTTJ.title";
    }
}
