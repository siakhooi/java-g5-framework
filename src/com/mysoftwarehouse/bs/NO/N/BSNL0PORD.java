/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.N;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.POR.PORD;
import com.mysoftwarehouse.bs.db.POR.PORE;
import com.mysoftwarehouse.bs.db.POR.POR_Amt;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSNL0PORD extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    public void receiveSignal(int signal) {
        if (signal == PorSignal.REFRESH) {
            cmd.list.refreshList();
        }
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Itm", "BSPORD.Itm");
        super.setFieldHelpMessage("BSPORD.Itm.help");
    }

    @Override
    protected void buildDataList() {
        super.addNumericField("Price", "BSPORD.Price");
        super.setFieldHelpMessage("BSPORD.Price.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("Qty", "BSPORD.Qty");
        super.setFieldHelpMessage("BSPORD.Qty.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("TtlAmt", "BSPORD.TtlAmt");
        super.setFieldHelpMessage("BSPORD.TtlAmt.help");
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

        super.setAddForm(BSNS0PORD.class);
        super.setCopyForm(BSNS0PORD.class);
        super.setEditForm(BSNS0PORD.class);
        super.setViewForm(BSNS0PORD.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String PorNo = cmd.local.map.texts.get(MAP.BSPOR.PORNO);
            String rsName = "BSNL0PORD";
            PORD.select_All(this, rsName, Cmp, PorNo);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSNL0PORD.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSNL0PORD.error.description");
        }
    }

    @Override
    public void executeAdd(int selectedRow, int[] selectedRows) throws Exception {
        String PorNo = cmd.local.map.texts.get(MAP.BSPOR.PORNO);
        cmd.out.map.texts.put(MAP.BSPOR.PORNO, PorNo);
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
        String PorNo = cmd.local.map.texts.get(MAP.BSPOR.PORNO);
        String Itm = cmd.list.getString(selectedRow, "Itm");
        cmd.out.map.texts.put(MAP.BSPOR.PORNO, PorNo);
        cmd.out.map.texts.put(MAP.BSSI.ITM, Itm);
        String Sup = cmd.local.map.texts.get(MAP.BSSUP.SUP);
        cmd.out.map.texts.put(MAP.BSSUP.SUP, Sup);
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String Cmp = GET.Cmp(this);
        String PorNo = cmd.local.map.texts.get(MAP.BSPOR.PORNO);
        String Itm = cmd.list.getString(selectedRow, "Itm");
        cmd.db.begin();
        PORD.delete(this, Cmp, PorNo, Itm);
        PORE.delete(this, Cmp, PorNo, Itm);
        POR_Amt.calculate(this, Cmp, PorNo);
        cmd.db.commit();
        cmd.form.broadcastSignal(PorSignal.REFRESH);
    }

    @Override
    public String getFormTitle() {
        return "BSNL0PORD.title";
    }
}
