/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.L;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PI.PI;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSLL1PI extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Itm", "BSPI.Itm");
        super.setFieldHelpMessage("BSPI.Itm.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSPI.Nme");
        super.setFieldHelpMessage("BSPI.Nme.help");

        super.addTextField("Status", "BSPI.Status");
        super.setFieldHelpMessage("BSPI.Status.help");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, true);
        super.setActionEnable(ListActionType.EDIT, true);
        super.setActionEnable(ListActionType.COPY, true);
        super.setActionEnable(ListActionType.VIEW, true);
        super.setActionEnable(ListActionType.DELETE, false);
        super.setActionEnable(ListActionType.RELOAD, false);
        super.setActionEnable(ListActionType.SELECT, true);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setAddForm(BSLS0PI.class);
        super.setCopyForm(BSLS0PI.class);
        super.setEditForm(BSLS0PI.class);
        super.setViewForm(BSLS0PI.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            //String Sup = cmd.in.map.texts.get(MAP.BSSUP.SUP);
            String rsName = "BSLL1PI";
            PI.select_AllActive(this, rsName, Cmp); //, Sup);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSLL1PI.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSLL1PI.error.description");
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
        cmd.out.map.texts.put(MAP.BSPI.ITM, itm);
        cmd.out.stringValue = itm;
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        String itm = cmd.list.getString(selectedRow, "Itm");
        cmd.in.stringValue = itm;
    }

    @Override
    public String getFormTitle() {
        return "BSLL1PI.title";
    }
}
