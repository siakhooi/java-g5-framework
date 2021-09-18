/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.I;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI0;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.INV.INV;
import com.mysoftwarehouse.bs.db.INV.INVA;
import com.mysoftwarehouse.bs.db.INV.INVC;
import com.mysoftwarehouse.bs.db.INV.INVD;
import com.mysoftwarehouse.bs.db.INV.INVE;
import com.mysoftwarehouse.bs.db.INV.INVJ;
import com.mysoftwarehouse.bs.db.INV.INVR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSIL0INV extends ListForm {

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
        super.addTextField("InvNo", "BSINV.InvNo");
        super.setFieldHelpMessage("BSINV.InvNo.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Cus", "BSINV.Cus");
        super.setFieldHelpMessage("BSINV.Cus.help");

        super.addCalendarField("InvDte", "BSINV.InvDte");
        super.setFieldHelpMessage("BSINV.InvDte.help");
        super.setFieldFormat(true, false);
        super.setFieldListColumnWidth(100);

        super.addCalendarField("DueDte", "BSINV.DueDte");
        super.setFieldHelpMessage("BSINV.DueDte.help");
        super.setFieldFormat(true, false);
        super.setFieldListColumnWidth(100);

        super.addNumericField("TtlAmt", "BSINV.TtlAmt");
        super.setFieldHelpMessage("BSINV.TtlAmt.help");
        super.setFieldOutputFormat("#0.00");

        super.addTextField("Status", "BSINV.Status");
        super.setFieldHelpMessage("BSINV.Status.help");
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

        super.setAddForm(BSIS0INV.class);
        super.setEditForm(BSIS0INV.class);
        super.setViewForm(BSIS0INV.class);
        super.setCopyForm(BSIS0INV.class);

        super.buttons.addButton("genFrmQtt", "BSIL0INV.genFrmQtt",
                cmd.icon.getSearchIcon(cmd.icon.getDefaultHeight()), false);
    }

    @Override
    public void buttonClick(String name, int selectedRow, int[] selectedRows) {
        if ("genFrmQtt".equals(name)) {
            BSIP0INV f = new BSIP0INV();
            cmd.form.execute(f);
            cmd.list.refreshList();
        } else {
            super.buttonClick(name, selectedRow, selectedRows);
        }
    }

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSIL0INV";
            INV.select_AllActive(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSIL0INV.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSIL0INV.error.description");
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
        String InvNo = cmd.list.getString(selectedRow, "InvNo");
        cmd.out.map.texts.put(MAP.BSINV.INVNO, InvNo);
        cmd.out.stringValue = InvNo;
    }

    @Override
    public void executeDelete(int selectedRow, int[] arg1) throws Exception {
        String Cmp = GET.Cmp(this);
        String InvNo = cmd.list.getString(selectedRow, "InvNo");
        cmd.db.begin();
        INV.delete(this, Cmp, InvNo);
        INVD.delete(this, Cmp, InvNo);
        INVE.delete(this, Cmp, InvNo);
        INVA.delete(this, Cmp, InvNo);
        INVC.delete(this, Cmp, InvNo);
        INVJ.delete(this, Cmp, InvNo);
        INVR.delete(this, Cmp, InvNo);
        cmd.db.commit();
    }

    @Override
    public String getFormTitle() {
        return "BSIL0INV.title";
    }
}
