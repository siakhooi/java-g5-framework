/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.C;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.pfa.G.PFGS0ACC;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.db.ACC.ACC_CL0ACC;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFCL0ACC extends ListForm {

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
        super.addTextField("AccTyp", "PFACC.AccTyp");
        super.setFieldHelpMessage("PFACC.AccTyp.help");

        super.addTextField("Nme", "PFACC.Nme");
        super.setFieldHelpMessage("PFACC.Nme.help");

        super.addTextField("Remark", "PFACC.Remark");
        super.setFieldHelpMessage("PFACC.Remark.help");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, true);
        super.setActionEnable(ListActionType.EDIT, true);
        super.setActionEnable(ListActionType.COPY, true);
        super.setActionEnable(ListActionType.VIEW, true);
        super.setActionEnable(ListActionType.DELETE, false);
        super.setActionEnable(ListActionType.RELOAD, true);
        super.setActionEnable(ListActionType.SELECT, true);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setAddForm(PFGS0ACC.class);
        super.setCopyForm(PFGS0ACC.class);
        super.setEditForm(PFGS0ACC.class);
        super.setViewForm(PFGS0ACC.class);
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        String acc = cmd.list.getString(selectedRow, "Acc");
        cmd.in.map.texts.put(ACC.PFACC_ACC, acc);
        String accName = cmd.list.getString(selectedRow, "Nme");
        cmd.in.map.texts.put(ACC.PFACC_ACCNAME, accName);
        String accTyp = cmd.list.getString(selectedRow, "AccTyp");
        cmd.in.map.texts.put(ACC.PFACC_ACCTYP, accTyp);
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

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        String rsName = "PFCL0ACC";
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        try {
            ACC_CL0ACC.select(this, rsName, id);
            super.useSQL(rsName);
        } catch (SQLException ex) {
            cmd.log.severe("PFCL0ACC.error", ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    "PFCL0ACC.error",
                    "PFCL0ACC.error.description");
        }
    }

    @Override
    public String getFormTitle() {
        return "PFCL0ACC.title";
    }
}
