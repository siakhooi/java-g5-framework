/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.C;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI1;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.CUS.CUS;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSCL0CUS extends ListForm {

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
        super.addTextField("Cus", "BSCUS.Cus");
        super.setFieldHelpMessage("BSCUS.Cus.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSCUS.Nme");
        super.setFieldHelpMessage("BSCUS.Nme.help");
        super.addTextField("Typ", "BSCUS.Typ");
        super.setFieldHelpMessage("BSCUS.Typ.help");
        super.addTextField("RegNo", "BSCUS.RegNo");
        super.setFieldHelpMessage("BSCUS.RegNo.help");
        super.addTextField("Status", "BSCUS.Status");
        super.setFieldHelpMessage("BSCUS.Status.help");
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

        super.setAddForm(BSCS0CUS.class);
        super.setCopyForm(BSCS0CUS.class);
        super.setEditForm(BSCS0CUS.class);
        super.setViewForm(BSCS0CUS.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSCL0CUS";
            CUS.select_All(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSCL0CUS.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSCL0CUS.error.description");
        }
    }

    @Override
    public void executeCopy(int selectedRow, int[] selectedRows) throws Exception {
        executeView(selectedRow, selectedRows);
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows)  throws Exception{
        executeView(selectedRow, selectedRows);
    }

    @Override
    public void executeView(int selectedRow, int[] selectedRows) throws Exception {
        String cus = cmd.list.getString(selectedRow, "Cus");
        cmd.out.map.texts.put(MAP.BSCUS.CUS, cus);
        cmd.out.stringValue = cus;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows)  throws Exception{
        String cus = cmd.list.getString(selectedRow, "Cus");
        cmd.out.map.texts.put(MAP.BSCUS.CUS, cus);
        UserFormInterface f = cmd.form.create(BSCP0CUS.class);
        cmd.form.execute(f);
    }

    @Override
    public String getFormTitle() {
        return "BSCL0CUS.title";
    }
}
