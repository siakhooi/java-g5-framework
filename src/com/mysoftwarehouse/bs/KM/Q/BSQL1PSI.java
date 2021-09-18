/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.Q;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.PSI.PSI;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSQL1PSI extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("SpcInst", "BSPSI.SpcInst");
        super.setFieldHelpMessage("BSPSI.SpcInst.help");
    //super.setFieldListColumnWidth(400);        
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSPSI.Nme");
        super.setFieldHelpMessage("BSPSI.Nme.help");

        super.addTextField("Status", "BSPSI.Status");
        super.setFieldHelpMessage("BSPSI.Status.help");
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
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String rsName = "BSQL1PSI";
            PSI.select_All(this, rsName, Cmp);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSQL1PSI.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSQL1PSI.error.description");
        }
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        String SpcInst = cmd.list.getString(selectedRow, "SpcInst");
        cmd.in.stringValue = SpcInst;
    }
    @Override
    public String getFormTitle() {
        return "BSQL1PSI.title";
    }

}
