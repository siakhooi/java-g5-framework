/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.K;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI1;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.SUP.SUP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSKL0SUP extends ListForm {

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
        super.addTextField("Sup", "BSSUP.Sup");
        super.setFieldHelpMessage("BSSUP.Sup.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSSUP.Nme");
        super.setFieldHelpMessage("BSSUP.Nme.help");
        super.addTextField("Typ", "BSSUP.Typ");
        super.setFieldHelpMessage("BSSUP.Typ.help");
        super.addTextField("RegNo", "BSSUP.RegNo");
        super.setFieldHelpMessage("BSSUP.RegNo.help");
        super.addTextField("Status", "BSSUP.Status");
        super.setFieldHelpMessage("BSSUP.Status.help");
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

        super.setAddForm(BSKS0SUP.class);
        super.setCopyForm(BSKS0SUP.class);
        super.setEditForm(BSKS0SUP.class);
        super.setViewForm(BSKS0SUP.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSKL0SUP";
            SUP.select_All(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSKL0SUP.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSKL0SUP.error.description");
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
        String sup = cmd.list.getString(selectedRow, "Sup");
        cmd.out.map.texts.put(MAP.BSSUP.SUP, sup);
        cmd.out.stringValue = sup;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String sup = cmd.list.getString(selectedRow, "Sup");
        cmd.out.map.texts.put(MAP.BSSUP.SUP, sup);
        UserFormInterface f = cmd.form.create(BSKP0SUP.class);
        cmd.form.execute(f);
    }

    @Override
    public String getFormTitle() {
        return "BSKL0SUP.title";
    }
}
