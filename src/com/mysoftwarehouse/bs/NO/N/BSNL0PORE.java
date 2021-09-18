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
import com.mysoftwarehouse.bs.db.POR.PORE;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSNL0PORE extends ListForm {

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
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Text", "BSPORE.Text");
        super.setFieldHelpMessage("BSPORE.Text.help");
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

        super.setEditForm(BSNT0PORE.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String PorNo = cmd.local.map.texts.get(MAP.BSPOR.PORNO);
            String Itm = cmd.local.map.texts.get(MAP.BSSI.ITM);

            String rsName = "BSNL0PORE";
            PORE.selectAll(this, rsName, Cmp, PorNo, Itm);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSNL0PORE.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSNL0PORE.error.description");
        }
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        String Por = cmd.local.map.texts.get(MAP.BSPOR.PORNO);
        cmd.out.map.texts.put(MAP.BSPOR.PORNO, Por);
        String Itm = cmd.local.map.texts.get(MAP.BSSI.ITM);
        cmd.out.map.texts.put(MAP.BSSI.ITM, Itm);
    }

    @Override
    public String getFormTitle() {
        return "BSNL0PORE.title";
    }
}
