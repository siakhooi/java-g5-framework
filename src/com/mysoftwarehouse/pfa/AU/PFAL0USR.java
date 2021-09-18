/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.AU;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.FormEnum.FrameState;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.pfa.M.PFMI0USR;
import com.mysoftwarehouse.pfa.N.PFNH0USR;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFAL0USR extends ListForm {

    @Override
    public void eventAfterVisible() {
        cmd.frame.setState(FrameState.MAXIMIZED_BOTH);
    }

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return PFMI0USR.class;
		
    }

    @Override
    public Class<? extends UserFormInterface> getTopForm() {
        return PFNH0USR.class;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Id", "PFUSR.Id");
        super.setFieldHelpMessage("PFUSR.Id.help");
    //super.setFieldListColumnWidth(400);        
    }

    @Override
    protected void buildDataList() {
        super.addTextField("FstNme", "PFUSR.FstNme");
        super.setFieldHelpMessage("PFUSR.FstNme.help");
        super.addTextField("LstNme", "PFUSR.LstNme");
        super.setFieldHelpMessage("PFUSR.LstNme.help");
        super.addTextField("Gender", "PFUSR.Gender");
        super.setFieldHelpMessage("PFUSR.Gender.help");
        super.addTextField("Career", "PFUSR.Career");
        super.setFieldHelpMessage("PFUSR.Career.help");
        super.addTextField("City", "PFUSR.City");
        super.setFieldHelpMessage("PFUSR.City.help");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, true);
        super.setActionEnable(ListActionType.EDIT, true);
        super.setActionEnable(ListActionType.COPY, true);
        super.setActionEnable(ListActionType.VIEW, true);
        super.setActionEnable(ListActionType.DELETE, true);
        super.setActionEnable(ListActionType.RELOAD, false);
        super.setActionEnable(ListActionType.SELECT, true);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setAddForm(PFAS0USR.class);
        super.setCopyForm(PFAS0USR.class);
        super.setEditForm(PFAS0USR.class);
        super.setViewForm(PFAS0USR.class);
        super.setDeleteForm(PFAP1USR.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String rsName = "PFAL0USR";
            USR.select_All(this, rsName);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "PFAL0USR.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title,
                    "PFAL0USR.error.description");
        }
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
        String id = cmd.list.getString(selectedRow, "Id");
        cmd.out.map.texts.put(USR.PFUSR_ID, id);
        cmd.out.stringValue = id;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String id = cmd.list.getString(selectedRow, "Id");
        cmd.out.map.texts.put(USR.PFUSR_ID, id);
//        UserFormInterface f = cmd.form.create(PFAP1USR.class);
//        cmd.form.execute(f);
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        String id = cmd.list.getString(selectedRow, "Id");
        cmd.global.texts.put(USR.PFUSR_ID, id);
        cmd.out.map.texts.put(USR.PFUSR_ID, id);
        UserFormInterface f = cmd.form.create(PFAP0USR.class);
        cmd.form.executeInNewThread(f);
    }

    @Override
    public String getFormTitle() {
        return "PFAL0USR.title";
    }
}
