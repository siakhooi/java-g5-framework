/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.D;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.mysoftwarehouse.pfa.db.TXNT.TXNT;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFDL0TXNT extends ListForm {

    @Override
    protected void buildKeyList() {
        super.addNumericField("Seq", "PFTXNT.Seq");
        super.setFieldHelpMessage("PFTXNT.Seq.help");
        super.setFieldOutputFormat("#0");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("TxnDte", "PFTXNT.TxnDte");
        super.setFieldHelpMessage("PFTXNT.TxnDte.help");

        super.addTextField("FrmAcc", "PFTXNT.FrmAcc");
        super.setFieldHelpMessage("PFTXNT.FrmAcc.help");

        super.addTextField("ToAcc", "PFTXNT.ToAcc");
        super.setFieldHelpMessage("PFTXNT.ToAcc.help");

        super.addTextField("RefNo", "PFTXNT.RefNo");
        super.setFieldHelpMessage("PFTXNT.RefNo.help");

        super.addTextField("TxnTyp", "PFTXNT.TxnTyp");
        super.setFieldHelpMessage("PFTXNT.TxnTyp.help");

        super.addTextField("UsrAmt", "PFTXNT.UsrAmt");
        super.setFieldHelpMessage("PFTXNT.UsrAmt.help");

        super.addTextField("Dsc", "PFTXNT.Dsc");
        super.setFieldHelpMessage("PFTXNT.Dsc.help");

        super.addTextField("Remark", "PFTXNT.Remark");
        super.setFieldHelpMessage("PFTXNT.Remark.help");
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

        super.setAddForm(PFDE0TXNT.class);

        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        String name, label;
        name = "PFDL0TXNT.Process";
        label = "PFDL0TXNT.button.Process";
        label = cmd.lang.getString(label);
        super.buttons.addI18nButton(name, label, icon, false);
    }

    @Override
    public void buttonClick(String name, int selectedRow, int[] selectedRows) {
        if ("PFDL0TXNT.Process".equals(name)) {
            int r = cmd.list.getRowCount();
            if (r == 0) {
                String title = "PFDL0TXNT.NoRecordMessage.title";
                String message = "PFDL0TXNT.NoRecordMessage.description";
                cmd.common.showMessage(DialogMessageType.ERROR,
                        title, message);
                return;
            }
            UserFormInterface f = cmd.form.create(PFDP1TXNT.class);
            cmd.form.execute(f);
            cmd.list.refreshList();
        }
    }

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        String rsName = "PFDL0TXNT";
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        try {
            TXNT.select_All(this, rsName, id);
            super.useSQL(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFDL0TXNT.error", ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    "PFDL0TXNT.error",
                    "PFDL0TXNT.error.description");
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
                TXNT.delete(this, id, seq.intValue());
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
        return "PFDL0TXNT.title";
    }
}
