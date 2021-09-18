/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.I;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.INV.INVE;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSIL0INVE extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    public void receiveSignal(int signal) {
        if (signal == InvSignal.REFRESH) {
            cmd.list.refreshList();
        }
    }

    @Override
    protected void buildKeyList() {
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Text", "BSINVE.Text");
        super.setFieldHelpMessage("BSINVE.Text.help");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, false);
        super.setActionEnable(ListActionType.EDIT, true);
        super.setActionEnable(ListActionType.COPY, false);
        super.setActionEnable(ListActionType.VIEW, false);
        super.setActionEnable(ListActionType.DELETE, false);
        super.setActionEnable(ListActionType.RELOAD, true);
        super.setActionEnable(ListActionType.SELECT, false);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, false);
        super.setActionEnable(ListActionType.RECORD_CHECK_ON_EDIT, false);

        super.setEditForm(BSIT0INVE.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String InvNo = cmd.local.map.texts.get(MAP.BSINV.INVNO);
            String Itm = cmd.local.map.texts.get(MAP.BSSI.ITM);

            String rsName = "BSIL0INVE";
            INVE.selectAll(this, rsName, Cmp, InvNo, Itm);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSIL0INVE.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSIL0INVE.error.description");
        }
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        String Inv = cmd.local.map.texts.get(MAP.BSINV.INVNO);
        cmd.out.map.texts.put(MAP.BSINV.INVNO, Inv);
        String Itm = cmd.local.map.texts.get(MAP.BSSI.ITM);
        cmd.out.map.texts.put(MAP.BSSI.ITM, Itm);
    }

    @Override
    public String getFormTitle() {
        return "BSIL0INVE.title";
    }
}
