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
import com.mysoftwarehouse.bs.db.RCP.RCPD;
import com.mysoftwarehouse.bs.db.RCP.RCPE;
import com.mysoftwarehouse.bs.db.RCP.RCPJ_Amt;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSJL0RCPD extends ListForm {

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
        super.addTextField("Itm", "BSRCPD.Itm");
        super.setFieldHelpMessage("BSRCPD.Itm.help");
    }

    @Override
    protected void buildDataList() {
        super.addNumericField("Price", "BSRCPD.Price");
        super.setFieldHelpMessage("BSRCPD.Price.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("Qty", "BSRCPD.Qty");
        super.setFieldHelpMessage("BSRCPD.Qty.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("TtlAmt", "BSRCPD.TtlAmt");
        super.setFieldHelpMessage("BSRCPD.TtlAmt.help");
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

        super.setAddForm(BSJS0RCPD.class);
        super.setCopyForm(BSJS0RCPD.class);
        super.setEditForm(BSJS0RCPD.class);
        super.setViewForm(BSJS0RCPD.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String RcpNo = cmd.local.map.texts.get(MAP.BSRCP.RCPNO);
            String rsName = "BSJL0RCPD";
            RCPD.select_All(this, rsName, Cmp, RcpNo);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSJL0RCPD.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSJL0RCPD.error.description");
        }
    }

    @Override
    public void executeAdd(int selectedRow, int[] selectedRows) throws Exception {
        String RcpNo = cmd.local.map.texts.get(MAP.BSRCP.RCPNO);
        cmd.out.map.texts.put(MAP.BSRCP.RCPNO, RcpNo);
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
        String RcpNo = cmd.local.map.texts.get(MAP.BSRCP.RCPNO);
        String Itm = cmd.list.getString(selectedRow, "Itm");
        cmd.out.map.texts.put(MAP.BSRCP.RCPNO, RcpNo);
        cmd.out.map.texts.put(MAP.BSSI.ITM, Itm);
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws SQLException {
        String Cmp = GET.Cmp(this);
        String RcpNo = cmd.local.map.texts.get(MAP.BSRCP.RCPNO);
        String Itm = cmd.list.getString(selectedRow, "Itm");
        cmd.db.begin();
        RCPD.delete(this, Cmp, RcpNo, Itm);
        RCPE.delete(this, Cmp, RcpNo, Itm);
        RCPJ_Amt.calculate(this, Cmp, RcpNo);
        cmd.db.commit();
        cmd.form.broadcastSignal(RcpSignal.REFRESH);
    }

    @Override
    public String getFormTitle() {
        return "BSJL0RCPD.title";
    }
}
