/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.J;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.RCP.RCPJ;
import com.mysoftwarehouse.bs.db.RCP.RCPJ_Amt;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSJL0RCPJ extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    public void receiveSignal(int signal) {
        if (signal == RcpSignal.REFRESH) {
            cmd.list.refreshList();
        }
    }

    @Override
    protected void buildKeyList() {
        super.addNumericField("Seq", "BSRCPJ.Seq");
        super.setFieldHelpMessage("BSRCPJ.Seq.help");
        super.setFieldOutputFormat("#0");

        super.addTextField("Adj", "BSRCPJ.Adj");
        super.setFieldHelpMessage("BSRCPJ.Adj.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSRCPJ.Nme");
        super.setFieldHelpMessage("BSRCPJ.Nme.help");

        super.addTextField("Typ", "BSRCPJ.Typ");
        super.setFieldHelpMessage("BSRCPJ.Typ.help");

        super.addNumericField("Prio", "BSRCPJ.Prio");
        super.setFieldHelpMessage("BSRCPJ.Prio.help");
        super.setFieldOutputFormat("#0");

        super.addNumericField("Amt", "BSRCPJ.Amt");
        super.setFieldHelpMessage("BSRCPJ.Amt.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("EffAmt", "BSRCPJ.EffAmt");
        super.setFieldHelpMessage("BSRCPJ.EffAmt.help");
        super.setFieldOutputFormat("#0.00");

//        super.addNumericField("OpenAmt", "BSRCPJ.OpenAmt");
//        super.setFieldHelpMessage("BSRCPJ.OpenAmt.help");
//        super.setFieldOutputFormat("#0.00");

        super.addNumericField("NewAmt", "BSRCPJ.NewAmt");
        super.setFieldHelpMessage("BSRCPJ.NewAmt.help");
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

        super.setAddForm(BSJS0RCPJ.class);
        super.setCopyForm(BSJS0RCPJ.class);
        super.setEditForm(BSJS0RCPJ.class);
        super.setViewForm(BSJS0RCPJ.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String RcpNo = cmd.local.map.texts.get(MAP.BSRCP.RCPNO);
            String rsName = "BSJL0RCPJ";
            RCPJ.select_All(this, rsName, Cmp, RcpNo);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSJL0RCPJ.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSJL0RCPJ.error.description");
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
        String RcpNo = cmd.local.map.texts.get(MAP.BSRCP.RCPNO);
        cmd.out.map.texts.put(MAP.BSRCP.RCPNO, RcpNo);
    }

    @Override
    public void executeView(int selectedRow, int[] selectedRows) throws Exception {
        String RcpNo = cmd.local.map.texts.get(MAP.BSRCP.RCPNO);
        int seq = ((Integer) cmd.list.getObject(selectedRow, "Seq")).intValue();
        cmd.out.intValue = seq;
        cmd.out.map.texts.put(MAP.BSRCP.RCPNO, RcpNo);
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String Cmp = GET.Cmp(this);
        String RcpNo = cmd.local.map.texts.get(MAP.BSRCP.RCPNO);
        int seq = ((Integer) cmd.list.getObject(selectedRow, "Seq")).intValue();
        try {
            cmd.db.begin();
            RCPJ.delete(this, Cmp, RcpNo, seq);
            RCPJ_Amt.calculate(this, Cmp, RcpNo);
            cmd.db.commit();
        } catch (SQLException ex) {
            cmd.log.severe("BSJL0RCPJ.error", ex);
            cmd.db.rollback();
        }
    }

    @Override
    public void onOutEnter() {
        cmd.form.broadcastSignal(RcpSignal.REFRESH);
    }

    @Override
    public String getFormTitle() {
        return "BSJL0RCPJ.title";
    }
}
