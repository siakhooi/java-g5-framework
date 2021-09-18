/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.G;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.mysoftwarehouse.pfa.db.ACCT.ACCT;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFGL0ACCT extends ListForm {

    @Override
    protected void buildKeyList() {
        super.addNumericField("Seq", "PFACCT.Seq");
        super.setFieldHelpMessage("PFACCT.Seq.help");
        super.setFieldOutputFormat("#0");
        super.setFieldListColumnWidth(0);
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Acc", "PFACCT.Acc");
        super.setFieldHelpMessage("PFACCT.Acc.help");

        super.addTextField("Nme", "PFACCT.Nme");
        super.setFieldHelpMessage("PFACCT.Nme.help");

        super.addTextField("AccTyp", "PFACCT.AccTyp");
        super.setFieldHelpMessage("PFACCT.AccTyp.help");

        super.addTextField("BalAmt", "PFACCT.BalAmt");
        super.setFieldHelpMessage("PFACCT.BalAmt.help");

        super.addTextField("BalDte", "PFACCT.BalDte");
        super.setFieldHelpMessage("PFACCT.BalDte.help");

        super.addTextField("Remark", "PFACCT.Remark");
        super.setFieldHelpMessage("PFACCT.Remark.help");
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

        super.setAddForm(PFGE0ACCT.class);

        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        String name, label;
        name = "PFGL0ACCT.Process";
        label = "PFGL0ACCT.button.Process";
        label = cmd.lang.getString(label);
        super.buttons.addI18nButton(name, label, icon, false);
    }

    @Override
    public void buttonClick(String name, int selectedRow, int[] selectedRows) {
        if ("PFGL0ACCT.Process".equals(name)) {
            int r = cmd.list.getRowCount();
            if (r == 0) {
                String title = "PFGL0ACCT.NoRecordMessage.title";
                String message = "PFGL0ACCT.NoRecordMessage.description";
                cmd.common.showMessage(DialogMessageType.ERROR,
                        title, message);
                return;
            }
            UserFormInterface f = cmd.form.create(PFGP1ACCT.class);
            cmd.form.execute(f);
            cmd.list.refreshList();
        }
    }

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        String rsName = "PFGL0ACCT";
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        try {
            ACCT.select_All(this, rsName, id);
            super.useSQL(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFGL0ACCT.error", ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    "PFGL0ACCT.error",
                    "PFGL0ACCT.error.description");
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
                ACCT.delete(this, id, seq.intValue());
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
        return "PFGL0ACCT.title";
    }
}
