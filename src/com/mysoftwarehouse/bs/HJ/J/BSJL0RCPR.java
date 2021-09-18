/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.J;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.RCP.RCPR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSJL0RCPR extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    public void receiveSignal(int signal) {
        if (signal == RcpSignal.REFRESH) {
            cmd.list.refreshList();
        }
    }

    @Override
    protected void buildKeyList() {
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Text", "BSRCPR.Text");
        super.setFieldHelpMessage("BSRCPR.Text.help");
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

        super.setEditForm(BSJT0RCPR.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String RcpNo = cmd.local.map.texts.get(MAP.BSRCP.RCPNO);
            String rsName = "BSJL0RCPR";
            RCPR.select_All(this, rsName, Cmp, RcpNo);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSJL0RCPR.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSJL0RCPR.error.description");
        }
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        String RcpNo = cmd.local.map.texts.get(MAP.BSRCP.RCPNO);
        cmd.out.map.texts.put(MAP.BSRCP.RCPNO, RcpNo);
    }

    @Override
    public String getFormTitle() {
        return "BSJL0RCPR.title";
    }
}
