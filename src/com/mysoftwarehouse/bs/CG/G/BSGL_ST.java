/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.G;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.data.GET;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class BSGL_ST extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Trm", "BSST.Trm");
        super.setFieldHelpMessage("BSST.Trm.help");
    //super.setFieldListColumnWidth(400);        
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSST.Nme");
        super.setFieldHelpMessage("BSST.Nme.help");

        super.addBooleanField("ForQtt", "BSST.ForQtt");
        super.setFieldHelpMessage("BSST.ForQtt.help");

        super.addBooleanField("ForInv", "BSST.ForInv");
        super.setFieldHelpMessage("BSST.ForInv.help");

        super.addBooleanField("ForRcp", "BSST.ForRcp");
        super.setFieldHelpMessage("BSST.ForRcp.help");

        super.addTextField("Status", "BSST.Status");
        super.setFieldHelpMessage("BSST.Status.help");
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
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, true);

//        super.setAddForm(BSGS0ST.class);
//        super.setCopyForm(BSGS0ST.class);
//        super.setEditForm(BSGS0ST.class);
//        super.setViewForm(BSGS0ST.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSGL_ST";
            query(rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSGL_ST.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSGL_ST.error.description");
        }
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        String Trm = cmd.list.getString(selectedRow, "Trm");
        cmd.in.stringValue = Trm;
    }

    protected abstract void query(String rsName, String cmp) throws SQLException;
}
