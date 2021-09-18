/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.M;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI1;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PY.PY;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSML0PY extends ListForm {

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
        super.addTextField("PayTyp", "BSPY.PayTyp");
        super.setFieldHelpMessage("BSPY.PayTyp.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSPY.Nme");
        super.setFieldHelpMessage("BSPY.Nme.help");
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

        super.setAddForm(BSMS0PY.class);
        super.setCopyForm(BSMS0PY.class);
        super.setEditForm(BSMS0PY.class);
        super.setViewForm(BSMS0PY.class);
    }

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSML0PY";
            PY.select_All(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSML0PY.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSML0PY.error.description");
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
        cmd.out.map.texts.put(MAP.BSPY.PAYTYP, PayTyp);
        cmd.out.stringValue = PayTyp;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String PayTyp = cmd.list.getString(selectedRow, "PayTyp");
        cmd.out.map.texts.put(MAP.BSPY.PAYTYP, PayTyp);
        UserFormInterface f = cmd.form.create(BSMP0PY.class);
        cmd.form.execute(f);
    }

    @Override
    public String getFormTitle() {
        return "BSML0PY.title";
    }
}
