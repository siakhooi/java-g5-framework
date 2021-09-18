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
import com.mysoftwarehouse.bs.db.PI.PIP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSLL1PIP extends ListForm {

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
        super.addNumericField("Price", "BSPIP.Price");
        super.setFieldHelpMessage("BSPIP.Price.help");
        super.setFieldOutputFormat("#0.00");

        super.addTextField("Status", "BSPIP.Status");
        super.setFieldHelpMessage("BSPIP.Status.help");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, false);
        super.setActionEnable(ListActionType.EDIT, false);
        super.setActionEnable(ListActionType.COPY, false);
        super.setActionEnable(ListActionType.VIEW, false);
        super.setActionEnable(ListActionType.DELETE, false);
        super.setActionEnable(ListActionType.RELOAD, false);
        super.setActionEnable(ListActionType.SELECT, true);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, true);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, true);
        super.setActionEnable(ListActionType.CLOSE, true);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String Sup = cmd.in.map.texts.get(MAP.BSSUP.SUP);
            String rsName = "BSLL1PIP";
            PIP.selectAllBySupplier(this, rsName, Cmp, Sup);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSLL1PIP.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSLL1PIP.error.description");
        }
    }

    @Override
    public void executeSelect(int selectedRow, int[] arg1) throws Exception {
        String Itm = cmd.list.getString(selectedRow, "Itm");
        cmd.in.stringValue = Itm;

    }

    @Override
    public String getFormTitle() {
        return "BSLL1PIP.title";
    }
}
