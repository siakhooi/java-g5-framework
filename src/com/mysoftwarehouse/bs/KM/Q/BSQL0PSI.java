/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.Q;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI1;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PSI.PSI;
import com.mysoftwarehouse.bs.db.PSI.PSID;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSQL0PSI extends ListForm {

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
        super.addTextField("SpcInst", "BSPSI.SpcInst");
        super.setFieldHelpMessage("BSPSI.SpcInst.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSPSI.Nme");
        super.setFieldHelpMessage("BSPSI.Nme.help");

        super.addTextField("Status", "BSPSI.Status");
        super.setFieldHelpMessage("BSPSI.Status.help");
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
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setAddForm(BSQS0PSI.class);
        super.setCopyForm(BSQS0PSI.class);
        super.setEditForm(BSQS0PSI.class);
        super.setViewForm(BSQS0PSI.class);
    }

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSQS0PSI";
            PSI.select_All(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSQL0PSI.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSQL0PSI.error.description");
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
        String SpcInst = cmd.list.getString(selectedRow, "SpcInst");
        cmd.out.map.texts.put(MAP.BSPSI.PSI, SpcInst);
        cmd.out.stringValue = SpcInst;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        try {
            String Cmp = GET.Cmp(this);
            String SpcInst = cmd.list.getString(selectedRow, "SpcInst");
            cmd.db.begin();
            PSI.delete(this, Cmp, SpcInst);
            PSID.delete(this, Cmp, SpcInst);
            cmd.db.commit();
        } catch (SQLException ex) {
            String title = "BSQL0PSI.error.delete";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSQL0PSI.error.delete.description");
        }
    }

    @Override
    public String getFormTitle() {
        return "BSQL0PSI.title";
    }
}
