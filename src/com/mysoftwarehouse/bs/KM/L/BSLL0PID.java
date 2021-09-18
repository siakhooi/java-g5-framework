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
import com.mysoftwarehouse.bs.db.PI.PID;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSLL0PID extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Text", "BSPID.Text");
        super.setFieldHelpMessage("BSPID.Text.help");
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
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, false);
        super.setActionEnable(ListActionType.RECORD_CHECK_ON_EDIT, false);

        super.setEditForm(BSLT0PID.class);
    }

    @Override
    public void executeReload(int selectedRow, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String Itm = cmd.in.map.texts.get(MAP.BSPI.ITM);
            String rsName = "BSLL0PID";
            PID.select_All(this, rsName, Cmp, Itm);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSLL0PID.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSLL0PID.error.description");
        }
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        String Itm = cmd.in.map.texts.get(MAP.BSPI.ITM);
        cmd.out.map.texts.put(MAP.BSPI.ITM, Itm);
        cmd.out.stringValue = Itm;
    }

    @Override
    public String getFormTitle() {
        return "BSLL0PID.title";
    }
}
