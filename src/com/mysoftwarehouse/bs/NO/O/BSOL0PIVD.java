/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.O;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PIV.PIVD;
import com.mysoftwarehouse.bs.db.PIV.PIVE;
import com.mysoftwarehouse.bs.db.PIV.PIV_Amt;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSOL0PIVD extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    public void receiveSignal(int signal) {
        if (signal == PivSignal.REFRESH) {
            cmd.list.refreshList();
        }
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Itm", "BSPIVD.Itm");
        super.setFieldHelpMessage("BSPIVD.Itm.help");
    }

    @Override
    protected void buildDataList() {
        super.addNumericField("Price", "BSPIVD.Price");
        super.setFieldHelpMessage("BSPIVD.Price.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("Qty", "BSPIVD.Qty");
        super.setFieldHelpMessage("BSPIVD.Qty.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("TtlAmt", "BSPIVD.TtlAmt");
        super.setFieldHelpMessage("BSPIVD.TtlAmt.help");
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

        super.setAddForm(BSOS0PIVD.class);
        super.setCopyForm(BSOS0PIVD.class);
        super.setEditForm(BSOS0PIVD.class);
        super.setViewForm(BSOS0PIVD.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String PivNo = cmd.local.map.texts.get(MAP.BSPIV.PIVNO);
            String rsName = "BSOL0PIVD";
            PIVD.select_All(this, rsName, Cmp, PivNo);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSOL0PIVD.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSOL0PIVD.error.description");
        }
    }

    @Override
    public void executeAdd(int selectedRow, int[] selectedRows) throws Exception {
        String PivNo = cmd.local.map.texts.get(MAP.BSPIV.PIVNO);
        cmd.out.map.texts.put(MAP.BSPIV.PIVNO, PivNo);
        String Sup = cmd.local.map.texts.get(MAP.BSSUP.SUP);
        cmd.out.map.texts.put(MAP.BSSUP.SUP, Sup);
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
        String PivNo = cmd.local.map.texts.get(MAP.BSPIV.PIVNO);
        String Itm = cmd.list.getString(selectedRow, "Itm");
        cmd.out.map.texts.put(MAP.BSPIV.PIVNO, PivNo);
        cmd.out.map.texts.put(MAP.BSSI.ITM, Itm);
        String Sup = cmd.local.map.texts.get(MAP.BSSUP.SUP);
        cmd.out.map.texts.put(MAP.BSSUP.SUP, Sup);
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String Cmp = GET.Cmp(this);
        String PivNo = cmd.local.map.texts.get(MAP.BSPIV.PIVNO);
        String Itm = cmd.list.getString(selectedRow, "Itm");
        cmd.db.begin();
        PIVD.delete(this, Cmp, PivNo, Itm);
        PIVE.delete(this, Cmp, PivNo, Itm);
        PIV_Amt.calculate(this, Cmp, PivNo);
        cmd.db.commit();
        cmd.form.broadcastSignal(PivSignal.REFRESH);
    }

    @Override
    public String getFormTitle() {
        return "BSOL0PIVD.title";
    }
}
