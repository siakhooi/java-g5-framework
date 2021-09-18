/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.C;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.is.A.ISAI1;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.ITM.ITM;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISCL0ITM extends ListForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ISAI1.class;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Itm", "ISITM.Itm");
        super.setFieldHelpMessage("ISITM.Itm.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "ISITM.Nme");
        super.setFieldHelpMessage("ISITM.Nme.help");
        super.addTextField("Uom", "ISUOM.Uom");
        super.setFieldHelpMessage("ISUOM.Uom.help");
        super.addNumericField("Qty", "ISITM.Qty");
        super.setFieldHelpMessage("ISITM.Qty.help");
        super.setFieldOutputFormat("#0.0000");

        super.addNumericField("SsQty", "ISITM.SsQty");
        super.setFieldHelpMessage("ISITM.SsQty.help");
        super.setFieldOutputFormat("#0.0000");

        super.addNumericField("StdCst", "ISITM.StdCst");
        super.setFieldHelpMessage("ISITM.StdCst.help");
        super.setFieldOutputFormat("#0.0000");

        super.addNumericField("WacCst", "ISITM.WacCst");
        super.setFieldHelpMessage("ISITM.WacCst.help");
        super.setFieldOutputFormat("#0.0000");

        super.addTextField("Remark", "ISITM.Remark");
        super.setFieldHelpMessage("ISITM.Remark.help");
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

        super.setAddForm(ISCS0ITM.class);
        super.setCopyForm(ISCS0ITM.class);
        super.setEditForm(ISCS0ITM.class);
        super.setViewForm(ISCS0ITM.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Whs = GET.Whs(this);
            String rsName = "ISCL0ITM";
            ITM.select_All(this, rsName, Whs);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "ISCL0ITM.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "ISCL0ITM.error.description");
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
        String itm = cmd.list.getString(selectedRow, "Itm");
        cmd.out.map.texts.put(MAP.ISITM.ITM, itm);
        cmd.out.stringValue = itm;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String itm = cmd.list.getString(selectedRow, "Itm");
        cmd.out.map.texts.put(MAP.ISITM.ITM, itm);
        UserFormInterface f = cmd.form.create(ISCP0ITM.class);
        cmd.form.execute(f);
    }

    @Override
    public String getFormTitle() {
        return "ISCL0ITM.title";
    }
}
