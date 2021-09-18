/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.C;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.ITM.ITMB;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISCL0ITMB extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Loc", "ISLOC.Loc");
        super.setFieldHelpMessage("ISLOC.Loc.help");
        super.addTextField("Nme", "ISLOC.Nme");
        super.setFieldHelpMessage("ISLOC.Nme.help");
    }

    @Override
    protected void buildDataList() {
        super.addNumericField("Qty", "ISITMB.Qty");
        super.setFieldHelpMessage("ISITMB.Qty.help");
        super.setFieldFormat("", "#.0000");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, false);
        super.setActionEnable(ListActionType.EDIT, false);
        super.setActionEnable(ListActionType.COPY, false);
        super.setActionEnable(ListActionType.VIEW, false);
        super.setActionEnable(ListActionType.DELETE, false);
        super.setActionEnable(ListActionType.RELOAD, false);
        super.setActionEnable(ListActionType.SELECT, false);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, false);
        super.setActionEnable(ListActionType.RECORD_CHECK_ON_EDIT, false);
    }

    @Override
    public void executeReload(int selectedRow, int[] arg1) {
        try {
            String Whs = GET.Whs(this);
            String Itm = cmd.in.map.texts.get(MAP.ISITM.ITM);
            String rsName = "ISCL0ITMB";
            ITMB.select_AllbyItm(this, rsName, Whs, Itm);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "ISCL0ITMB.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title,
                    "ISCL0ITMB.error.description");
        }
    }

    @Override
    public String getFormTitle() {
        return "ISCL0ITMB.title";
    }
}
