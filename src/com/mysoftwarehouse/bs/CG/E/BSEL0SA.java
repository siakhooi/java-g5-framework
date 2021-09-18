/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.E;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI1;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.SA.SA;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSEL0SA extends ListForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return BSAI1.class;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Adj", "BSSA.Adj");
        super.setFieldHelpMessage("BSSA.Adj.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSSA.Nme");
        super.setFieldHelpMessage("BSSA.Nme.help");

        super.addTextField("Typ", "BSSA.Typ");
        super.setFieldHelpMessage("BSSA.Typ.help");
        super.setFieldListColumnWidth(20);

        super.addNumericField("Prio", "BSSA.Prio");
        super.setFieldHelpMessage("BSSA.Prio.help");
        super.setFieldOutputFormat("#0");
        super.setFieldListColumnWidth(20);

        super.addNumericField("Amt", "BSSA.Amt");
        super.setFieldHelpMessage("BSSA.Amt.help");
        super.setFieldOutputFormat("#0.00");

        super.addBooleanField("Dflt", "BSSA.Dflt");
        super.setFieldHelpMessage("BSSA.Dflt.help");
        super.setFieldListColumnWidth(20);

        super.addTextField("Status", "BSSA.Status");
        super.setFieldHelpMessage("BSSA.Status.help");
        super.setFieldListColumnWidth(20);
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

        super.setAddForm(BSES0SA.class);
        super.setCopyForm(BSES0SA.class);
        super.setEditForm(BSES0SA.class);
        super.setViewForm(BSES0SA.class);
    }

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSEL0SA";
            SA.select_All(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSEL0SA.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSEL0SA.error.description");
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
        String Adj = cmd.list.getString(selectedRow, "Adj");
        cmd.out.map.texts.put(MAP.BSSA.ADJ, Adj);
        cmd.out.stringValue = Adj;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        try {
            String Cmp = GET.Cmp(this);
            String Adj = cmd.list.getString(selectedRow, "Adj");
            cmd.db.begin();
            SA.delete(this, Cmp, Adj);
            cmd.db.commit();
        } catch (SQLException ex) {
            String title = "BSEL0SA.error.delete";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSEL0SA.error.delete.description");
        }
    }

    @Override
    public String getFormTitle() {
        return "BSEL0SA.title";
    }
}
