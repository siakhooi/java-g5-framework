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
import com.mysoftwarehouse.bs.db.INV.INVJ;
import com.mysoftwarehouse.bs.db.INV.INVJ_Amt;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSIL0INVJ extends ListForm {

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
        super.addNumericField("Seq", "BSINVJ.Seq");
        super.setFieldHelpMessage("BSINVJ.Seq.help");
        super.setFieldOutputFormat("#0");

        super.addTextField("Adj", "BSINVJ.Adj");
        super.setFieldHelpMessage("BSINVJ.Adj.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSINVJ.Nme");
        super.setFieldHelpMessage("BSINVJ.Nme.help");

        super.addTextField("Typ", "BSINVJ.Typ");
        super.setFieldHelpMessage("BSINVJ.Typ.help");

        super.addNumericField("Prio", "BSINVJ.Prio");
        super.setFieldHelpMessage("BSINVJ.Prio.help");
        super.setFieldOutputFormat("#0");

        super.addNumericField("Amt", "BSINVJ.Amt");
        super.setFieldHelpMessage("BSINVJ.Amt.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("EffAmt", "BSINVJ.EffAmt");
        super.setFieldHelpMessage("BSINVJ.EffAmt.help");
        super.setFieldOutputFormat("#0.00");

//        super.addNumericField("OpenAmt", "BSINVJ.OpenAmt");
//        super.setFieldHelpMessage("BSINVJ.OpenAmt.help");
//        super.setFieldOutputFormat("#0.00");
//
        super.addNumericField("NewAmt", "BSINVJ.NewAmt");
        super.setFieldHelpMessage("BSINVJ.NewAmt.help");
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

        super.setAddForm(BSIS0INVJ.class);
        super.setCopyForm(BSIS0INVJ.class);
        super.setEditForm(BSIS0INVJ.class);
        super.setViewForm(BSIS0INVJ.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String InvNo = cmd.local.map.texts.get(MAP.BSINV.INVNO);
            String rsName = "BSIL0INVJ";
            INVJ.select_All(this, rsName, Cmp, InvNo);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSIL0INVJ.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSIL0INVJ.error.description");
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
    public void executeAdd(int arg0, int[] arg1) throws Exception {
        String InvNo = cmd.local.map.texts.get(MAP.BSINV.INVNO);
        cmd.out.map.texts.put(MAP.BSINV.INVNO, InvNo);
    }

    @Override
    public void executeView(int selectedRow, int[] selectedRows) throws Exception {
        String InvNo = cmd.in.map.texts.get(MAP.BSINV.INVNO);
        int seq = ((Integer) cmd.list.getObject(selectedRow, "Seq")).intValue();
        cmd.out.intValue = seq;
        cmd.out.map.texts.put(MAP.BSINV.INVNO, InvNo);
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String Cmp = GET.Cmp(this);
        String InvNo = cmd.local.map.texts.get(MAP.BSINV.INVNO);
        int seq = ((Integer) cmd.list.getObject(selectedRow, "Seq")).intValue();
        try {
            cmd.db.begin();
            INVJ.delete(this, Cmp, InvNo, seq);
            INVJ_Amt.calculate(this, Cmp, InvNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.log.severe("BSIL0INVJ.error", ex);
            cmd.db.rollback();
        }
    }

    @Override
    public void onOutEnter() {
        cmd.form.broadcastSignal(InvSignal.REFRESH);
    }

    @Override
    public String getFormTitle() {
        return "BSIL0INVJ.title";
    }
}
