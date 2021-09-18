/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.FormEnum.FrameState;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.bs.A.BSAI0;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.db.CMP.CMP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBL0CMP extends ListForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return BSAI0.class;
    }

    @Override
    public Class<? extends UserFormInterface> getTopForm() {
        return BSBH0CMP.class;
    }

    @Override
    public void eventAfterVisible() {
        cmd.frame.setState(FrameState.MAXIMIZED_BOTH);
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    protected void buildKeyList() {
        super.addTextField("Cmp", "BSCMP.Cmp");
        super.setFieldHelpMessage("BSCMP.Cmp.help");
    //super.setFieldListColumnWidth(400);        
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "BSCMP.Nme");
        super.setFieldHelpMessage("BSCMP.Nme.help");
        super.addTextField("RegNo", "BSCMP.RegNo");
        super.setFieldHelpMessage("BSCMP.RegNo.help");
        super.addTextField("WebSte", "BSCMP.WebSte");
        super.setFieldHelpMessage("BSCMP.WebSte.help");
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

        super.setAddForm(BSBS0CMP.class);
        super.setCopyForm(BSBS0CMP.class);
        super.setEditForm(BSBS0CMP.class);
        super.setViewForm(BSBS0CMP.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String rsName = "BSBL0CMP";
            CMP.select_All(this, rsName);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "BSBL0CMP.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "BSBL0CMP.error.description");
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
        String Cmp = cmd.list.getString(selectedRow, "Cmp");
        cmd.out.map.texts.put(MAP.BSCMP.CMP, Cmp);
        cmd.out.stringValue = Cmp;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String id = cmd.list.getString(selectedRow, "Cmp");
        cmd.out.map.texts.put(MAP.BSCMP.CMP, id);
        UserFormInterface f = cmd.form.create(BSBP2CMP.class);
        cmd.form.execute(f);
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        String Cmp = cmd.list.getString(selectedRow, "Cmp");
        String Nme = cmd.list.getString(selectedRow, "Nme");
        cmd.global.texts.put(MAP.BSCMP.CMP, Cmp);
        cmd.global.texts.put(MAP.BSCMP.NAME, Nme);
        cmd.out.map.texts.put(MAP.BSCMP.CMP, Cmp);
        UserFormInterface f = cmd.form.create(BSBP0CMP.class);
        cmd.form.executeInNewThread(f);
    }

    @Override
    public String getFormTitle() {
        return "BSBL0CMP.title";
    }
}
