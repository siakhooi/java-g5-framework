/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.H;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.is.A.ISAI1;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;
import com.mysoftwarehouse.is.db.ITM.ITM;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISHL0ITM extends ListForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ISAI1.class;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Itm", "ISITM.Itm");
        super.setFieldHelpMessage("ISITM.Itm.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "ISITM.Nme");
        super.setFieldHelpMessage("ISITM.Nme.help");
        super.addTextField("Uom", "ISUOM.Uom");
        super.setFieldHelpMessage("ISUOM.Uom.help");
        super.addNumericField("Qty", "ISITM.Qty");
        super.setFieldHelpMessage("ISITM.Qty.help");
        super.setFieldOutputFormat("#0.0000");

        super.addNumericField("SsQty", "ISITM.SsQty");
        super.setFieldHelpMessage("ISITM.SsQty.help");
        super.setFieldOutputFormat("#0.0000");

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
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, true);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, true);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setEditForm(ISHE0ITM.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String Whs = GET.Whs(this);
            String rsName = "ISHL0ITM";
            ITM.select_All(this, rsName, Whs);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "ISHL0ITM.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "ISHL0ITM.error.description");
        }
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        String itm = cmd.list.getString(selectedRow, "Itm");
        cmd.out.map.texts.put(MAP.ISITM.ITM, itm);
        cmd.out.stringValue = itm;
    }

    @Override
    public String getFormTitle() {
        return "ISHL0ITM.title";
    }
}
