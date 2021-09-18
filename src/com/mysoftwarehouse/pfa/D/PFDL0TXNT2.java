/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.D;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.mysoftwarehouse.pfa.db.TXNT2.TXNT2;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFDL0TXNT2 extends ListForm {

    @Override
    protected void buildKeyList() {
        super.addNumericField("Seq", "PFTXNT2.Seq");
        super.setFieldHelpMessage("PFTXNT2.Seq.help");
        super.setFieldOutputFormat("#0");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("TxnId", "PFTXNT2.TxnId");
        super.setFieldHelpMessage("PFTXNT2.TxnId.help");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, true);
        super.setActionEnable(ListActionType.EDIT, false);
        super.setActionEnable(ListActionType.COPY, false);
        super.setActionEnable(ListActionType.VIEW, false);
        super.setActionEnable(ListActionType.DELETE, true);
        super.setActionEnable(ListActionType.RELOAD, true);
        super.setActionEnable(ListActionType.SELECT, false);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setAddForm(PFDE0TXNT2.class);

        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        String name, label;
        name = "PFDL0TXNT2.Process";
        label = "PFDL0TXNT2.button.Process";
        label = cmd.lang.getString(label);
        super.buttons.addI18nButton(name, label, icon, false);
    }

    @Override
    public void buttonClick(String name, int selectedRow, int[] selectedRows) {
        if ("PFDL0TXNT2.Process".equals(name)) {
            int r = cmd.list.getRowCount();
            if (r == 0) {
                String title = "PFDL0TXNT2.NoRecordMessage.title";
                String message = "PFDL0TXNT2.NoRecordMessage.description";
                cmd.common.showMessage(DialogMessageType.ERROR,
                        title, message);
                return;
            }
            UserFormInterface f = cmd.form.create(PFDP1TXNT2.class);
            cmd.form.execute(f);
            cmd.list.refreshList();
        }
    }

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        String rsName = "PFDL0TXNT2";
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        try {
            TXNT2.select_All(this, rsName, id);
            super.useSQL(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFDL0TXNT2.error", ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    "PFDL0TXNT2.error",
                    "PFDL0TXNT2.error.description");
        }
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        for (int i = 0; i < selectedRows.length; i++) {
            int r = selectedRows[i];
            Integer seq = (Integer) cmd.list.getObject(r, "Seq");
            String id = cmd.global.texts.get(USR.PFUSR_ID);
            try {
                cmd.db.begin();
                TXNT2.delete(this, id, seq.intValue());
                cmd.db.commit();
            } catch (SQLException ex) {
                cmd.db.rollback();
                throw ex;
            }
        }
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
    }

    @Override
    public String getFormTitle() {
        return "PFDL0TXNT2.title";
    }
}
