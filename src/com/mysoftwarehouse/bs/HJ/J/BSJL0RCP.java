/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.J;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI0;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.RCP.RCP;
import com.mysoftwarehouse.bs.db.RCP.RCPA;
import com.mysoftwarehouse.bs.db.RCP.RCPC;
import com.mysoftwarehouse.bs.db.RCP.RCPD;
import com.mysoftwarehouse.bs.db.RCP.RCPE;
import com.mysoftwarehouse.bs.db.RCP.RCPJ;
import com.mysoftwarehouse.bs.db.RCP.RCPR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSJL0RCP extends ListForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return BSAI0.class;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("RcpNo", "BSRCP.RcpNo");
        super.setFieldHelpMessage("BSRCP.RcpNo.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Cus", "BSRCP.Cus");
        super.setFieldHelpMessage("BSRCP.Cus.help");

        super.addCalendarField("RcpDte", "BSRCP.RcpDte");
        super.setFieldHelpMessage("BSRCP.RcpDte.help");
        super.setFieldFormat(true, false);
        super.setFieldListColumnWidth(100);

        super.addNumericField("TtlAmt", "BSRCP.TtlAmt");
        super.setFieldHelpMessage("BSRCP.TtlAmt.help");
        super.setFieldOutputFormat("#0.00");

        super.addTextField("Status", "BSRCP.Status");
        super.setFieldHelpMessage("BSRCP.Status.help");

        super.addTextField("PayTyp", "BSRCP.PayTyp");
        super.setFieldHelpMessage("BSRCP.PayTyp.help");
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

        super.setAddForm(BSJS0RCP.class);
        super.setEditForm(BSJS0RCP.class);
        super.setViewForm(BSJS0RCP.class);
        super.setCopyForm(BSJS0RCP.class);
        super.buttons.addButton("genFrmInv", "BSJL0RCP.genFrmInv",
                cmd.icon.getSearchIcon(cmd.icon.getDefaultHeight()), false);
    }

    @Override
    public void buttonClick(String name, int selectedRow, int[] selectedRows) {
        if ("genFrmInv".equals(name)) {
            BSJP0RCP f = new BSJP0RCP();
            cmd.form.execute(f);
            cmd.list.refreshList();
        } else {
            super.buttonClick(name, selectedRow, selectedRows);
        }
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSJL0RCP";
            RCP.select_AllActive(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSJL0RCP.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSJL0RCP.error.description");
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
        String RcpNo = cmd.list.getString(selectedRow, "RcpNo");
        cmd.out.map.texts.put(MAP.BSRCP.RCPNO, RcpNo);
        cmd.out.stringValue = RcpNo;
    }

    @Override
    public void executeDelete(int selectedRow, int[] arg1) throws Exception {
        String Cmp = GET.Cmp(this);
        String RcpNo = cmd.list.getString(selectedRow, "RcpNo");
        cmd.db.begin();
        RCP.delete(this, Cmp, RcpNo);
        RCPD.delete(this, Cmp, RcpNo);
        RCPE.delete(this, Cmp, RcpNo);
        RCPA.delete(this, Cmp, RcpNo);
        RCPC.delete(this, Cmp, RcpNo);
        RCPJ.delete(this, Cmp, RcpNo);
        RCPR.delete(this, Cmp, RcpNo);
        cmd.db.commit();
    }

    @Override
    public String getFormTitle() {
        return "BSJL0RCP.title";
    }
}
