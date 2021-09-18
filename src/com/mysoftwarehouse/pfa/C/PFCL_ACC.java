/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.C;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.FormEnum.EnterType;
import com.gqrsoft.g5.developer.publicobject.FormEnum.FrameState;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.pfa.B.MenuSignal;
import com.mysoftwarehouse.pfa.B.PFBM0;
import com.mysoftwarehouse.pfa.D.PFDL0TXN;
import com.mysoftwarehouse.pfa.D.PFDL1TXN;
import com.mysoftwarehouse.pfa.D.PFDL2TXN;
import com.mysoftwarehouse.pfa.D.PFDS0TXN;
import com.mysoftwarehouse.pfa.G.PFGP1ACC;
import com.mysoftwarehouse.pfa.G.PFGS0ACC;
import com.mysoftwarehouse.pfa.M.PFMI0ACC;
import com.mysoftwarehouse.pfa.N.PFNH0ACC;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.db.CFG.CFG;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class PFCL_ACC extends ListForm {

    @Override
    public void eventAfterVisible() {
        cmd.frame.setState(FrameState.MAXIMIZED_BOTH);
    }

    @Override
    public void receiveSignal(int signal) {
        if (signal == MenuSignal.SENDSELECTED) {
            String acc = cmd.list.getString(
                    cmd.list.getSelectedRow(), "Acc");
            cmd.local.map.texts.put(ACC.FROMACC, acc);
        } else if (signal == MenuSignal.TERMINATE) {
            cmd.form.closeForm();
        } else if (signal == MenuSignal.REFRESH) {
            cmd.list.refreshList();
        }
    }

    @Override
    public Class<? extends UserFormInterface> getTopForm() {
        if (cmd.global.booleans.get(CFG.PFCFG_SHOWHELP)) {
            return PFNH0ACC.class;
        } else {
            return null;
        }
    }

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return PFMI0ACC.class;
    }

    @Override
    public Class<? extends MenuForm> getMenuForm() {
        return PFBM0.class;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Acc", "PFACC.Acc");
        super.setFieldHelpMessage("PFACC.Acc.help");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "PFACC.Nme");
        super.setFieldHelpMessage("PFACC.Nme.help");

        super.addTextField("AccTyp", "PFACC.AccTyp");
        super.setFieldHelpMessage("PFACC.AccTyp.help");
        super.setFieldListColumnWidth(30);

        super.addTextField("Status", "PFACC.Status");
        super.setFieldHelpMessage("PFACC.Status.help");
        super.setFieldListColumnWidth(30);
        
        super.addTextField("ShowInMain", "PFACC.ShowInMain");
        super.setFieldHelpMessage("PFACC.ShowInMain.help");
        super.setFieldListColumnWidth(30);
        
        super.addNumericField("TxnCnt", "PFCL_ACC.TxnCnt");
        super.setFieldHelpMessage("PFCL_ACC.TxnCnt.help");
        super.setFieldOutputFormat("#0");

        super.addNumericField("TtlDbt", "PFCL_ACC.TtlDbt");
        super.setFieldHelpMessage("PFCL_ACC.TtlDbt.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("TtlCrt", "PFCL_ACC.TtlCrt");
        super.setFieldHelpMessage("PFCL_ACC.TtlCrt.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("TtlAmt", "PFCL_ACC.TtlAmt");
        super.setFieldHelpMessage("PFCL_ACC.TtlAmt.help");
        super.setFieldOutputFormat("#0.00");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, true);
        super.setActionEnable(ListActionType.EDIT, true);
        super.setActionEnable(ListActionType.COPY, true);
        super.setActionEnable(ListActionType.VIEW, true);
        super.setActionEnable(ListActionType.DELETE, true);
        super.setActionEnable(ListActionType.RELOAD, true);
        //super.setActionEnable(ListActionType.SELECT, true);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, true);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, true);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setAddForm(PFGS0ACC.class);
        super.setCopyForm(PFGS0ACC.class);
        super.setEditForm(PFGS0ACC.class);
        super.setViewForm(PFGS0ACC.class);
        super.setDeleteForm(PFGP1ACC.class);

        String name, tooltip, label;
        Icon icon = cmd.icon.getViewOnScreenIcon(
                cmd.icon.getDefaultHeight());
        label = "";

        name = "PFCL_ACC.PFDL0TXN";
        tooltip = "PFCL_ACC.PFDL0TXN";
        label = "PFCL_ACC.PFDL0TXN.label";
        label = cmd.lang.getString(label);
        super.buttons.addI18nButton(name, label, icon, false);
        super.buttons.setToolTipText(tooltip);

        name = "PFCL_ACC.PFDL1TXN";
        tooltip = "PFCL_ACC.PFDL1TXN";
        label = "PFCL_ACC.PFDL1TXN.label";
        label = cmd.lang.getString(label);
        super.buttons.addI18nButton(name, label, icon, false);
        super.buttons.setToolTipText(tooltip);

        name = "PFCL_ACC.PFDL2TXN";
        tooltip = "PFCL_ACC.PFDL2TXN";
        label = "PFCL_ACC.PFDL2TXN.label";
        label = cmd.lang.getString(label);
        super.buttons.addI18nButton(name, label, icon, false);
        super.buttons.setToolTipText(tooltip);

        super.buttons.addSeparator();

        name = "PFCL_ACC.PFDS0TXN";
        tooltip = "PFCL_ACC.PFDS0TXN";
        label = "PFCL_ACC.PFDS0TXN.label";
        label = cmd.lang.getString(label);
        super.buttons.addI18nButton(name, label, icon, false);
        super.buttons.setToolTipText(tooltip);
    }

    @Override
    public void buttonClick(String name, int selectedRow, int[] selectedRows) {
        if ("PFCL_ACC.PFDS0TXN".equals(name)) {
            if (selectedRow >= 0) {
                String acc = cmd.list.getString(selectedRow, "Acc");
                cmd.out.map.texts.put(ACC.PFACC_ACC, acc);
            }
            cmd.out.formEnterType = EnterType.LISTADD;
            UserFormInterface f = cmd.form.create(PFDS0TXN.class);
            cmd.form.execute(f);
            cmd.list.refreshList();
            return;
        }
        if (selectedRow < 0) {
            String title = "PFCL_ACC.NoAccountSelected.title";
            String message = "PFCL_ACC.NoAccountSelected.message";
            cmd.common.showMessage(DialogMessageType.ERROR, title, message);
            return;
        }
        String acc = cmd.list.getString(selectedRow, "Acc");
        cmd.out.map.texts.put(ACC.PFACC_ACC, acc);
        String accTyp = cmd.list.getString(selectedRow, "AccTyp");
        cmd.out.map.texts.put(ACC.PFACC_ACCTYP, accTyp);
        if ("PFCL_ACC.PFDL0TXN".equals(name)) {
            UserFormInterface f = cmd.form.create(PFDL0TXN.class);
            cmd.form.execute(f);
        } else if ("PFCL_ACC.PFDL1TXN".equals(name)) {
            UserFormInterface f = cmd.form.create(PFDL1TXN.class);
            cmd.form.execute(f);
        } else if ("PFCL_ACC.PFDL2TXN".equals(name)) {
            UserFormInterface f = cmd.form.create(PFDL2TXN.class);
            cmd.form.execute(f);
        }
        cmd.list.refreshList();
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String acc = cmd.list.getString(selectedRow, "Acc");
        cmd.out.map.texts.put(ACC.PFACC_ACC, acc);
//        UserFormInterface f = cmd.form.create(PFGP1ACC.class);
//        cmd.form.execute(f);
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        String acc = cmd.list.getString(selectedRow, "Acc");
        cmd.in.map.texts.put(ACC.PFACC_ACC, acc);
        String accName = cmd.list.getString(selectedRow, "Nme");
        cmd.in.map.texts.put(ACC.PFACC_ACCNAME, accName);
        cmd.in.stringValue = acc;
    }

    @Override
    public void executeCopy(int selectedRow, int[] selectedRows) throws Exception {
        executeView(selectedRow, selectedRows);
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        executeView(selectedRow, selectedRows);
    }

    @Override
    public void executeView(int selectedRow, int[] selectedRows) throws Exception {
        String acc = cmd.list.getString(selectedRow, "Acc");
        cmd.out.map.texts.put(ACC.PFACC_ACC, acc);
        cmd.out.stringValue = acc;
    }
}
