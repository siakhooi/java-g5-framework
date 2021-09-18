/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.F;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI1;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.SY.SY;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSFL0SY extends ListForm {

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
        super.addTextField("PayTyp", "BSSY.PayTyp");
        super.setFieldHelpMessage("BSSY.PayTyp.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSSY.Nme");
        super.setFieldHelpMessage("BSSY.Nme.help");
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

        super.setAddForm(BSFS0SY.class);
        super.setCopyForm(BSFS0SY.class);
        super.setEditForm(BSFS0SY.class);
        super.setViewForm(BSFS0SY.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSFL0SY";
            SY.select_All(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSFL0SY.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSFL0SY.error.description");
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
        String PayTyp = cmd.list.getString(selectedRow, "PayTyp");
        cmd.out.map.texts.put(MAP.BSSY.PAYTYP, PayTyp);
        cmd.out.stringValue = PayTyp;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String PayTyp = cmd.list.getString(selectedRow, "PayTyp");
        cmd.out.map.texts.put(MAP.BSSY.PAYTYP, PayTyp);
        UserFormInterface f = cmd.form.create(BSFP0SY.class);
        cmd.form.execute(f);
    }

    @Override
    public String getFormTitle() {
        return "BSFL0SY.title";
    }
}
