/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.D;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.SI.SID;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSDL0SID extends ListForm {

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
    //super.addNumericField("Seq", "BSSID.Seq");
    //super.setFieldHelpMessage("BSSID.Seq.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Text", "BSSID.Text");
        super.setFieldHelpMessage("BSSID.Text.help");
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

        super.setEditForm(BSDT0SID.class);
    }

    @Override
    public void executeReload(int selectedRow, int[] arg1) {
        try {
            String Cmp = GET.Cmp(this);
            String Itm = cmd.in.map.texts.get(MAP.BSSI.ITM);
            String rsName = "BSDL0SID";
            SID.select_All(this, rsName, Cmp, Itm);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSDL0SID.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title,
                    "BSDL0SID.error.description");
        }
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        String Itm = cmd.in.map.texts.get(MAP.BSSI.ITM);
        cmd.out.map.texts.put(MAP.BSSI.ITM, Itm);
        cmd.out.stringValue = Itm;
    }

    @Override
    public String getFormTitle() {
        return "BSDL0SID.title";
    }
}
