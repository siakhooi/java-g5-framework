/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.I;

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
import java.util.ArrayList;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISIL0ITM extends ListForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ISAI1.class;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.MULTIPLE_INTERVAL;
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

        super.addNumericField("StdCst", "ISITM.StdCst");
        super.setFieldHelpMessage("ISITM.StdCst.help");
        super.setFieldOutputFormat("#0.0000");

        super.addNumericField("WacCst", "ISITM.WacCst");
        super.setFieldHelpMessage("ISITM.WacCst.help");
        super.setFieldOutputFormat("#0.0000");

    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, false);
        super.setActionEnable(ListActionType.EDIT, true);
        super.setActionEnable(ListActionType.COPY, false);
        super.setActionEnable(ListActionType.VIEW, false);
        super.setActionEnable(ListActionType.DELETE, false);
        super.setActionEnable(ListActionType.RELOAD, false);
        super.setActionEnable(ListActionType.SELECT, false);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, true);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, true);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setEditForm(ISIE0ITM.class);

        super.buttons.addButton("WacToStd", "ISIL0ITM.button.WacToStd",
                cmd.icon.getCommandIcon(cmd.icon.getDefaultHeight()), false);
    }

    @Override
    public void buttonClick(String name, int selectedRow, int[] selectedRows) {
        if ("WacToStd".equals(name)) {
            if (cmd.list.getSelectedRows().length == 0) {
                cmd.common.showMessage(DialogMessageType.ERROR,
                        "ISIL0ITM.error.NoSelected.title", "ISIL0ITM.error.NoSelected.description");
                return;
            }
            ArrayList<String> itms = new ArrayList<String>();
            for (int i : cmd.list.getSelectedRows()) {
                String itm = cmd.list.getString(i, "Itm");
                System.out.println(itm);
                itms.add(itm);
            }
            cmd.out.objectValue = itms;
            //cmd.out.stringValues = (String[]) itms.toArray();
            ISIP0ITM f = new ISIP0ITM();
            cmd.form.execute(f);
            cmd.list.refreshList();
        } else {
            super.buttonClick(name, selectedRow, selectedRows);
        }
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Whs = GET.Whs(this);
            String rsName = "ISIL0ITM";
            ITM.select_All(this, rsName, Whs);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "ISIL0ITM.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "ISIL0ITM.error.description");
        }
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        String itm = cmd.list.getString(selectedRow, "Itm");
        cmd.out.map.texts.put(MAP.ISITM.ITM, itm);
        cmd.out.stringValue = itm;
    }

    @Override
    public String getFormTitle() {
        return "ISIL0ITM.title";
    }
}
