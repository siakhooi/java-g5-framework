/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.B;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.FormEnum.FrameState;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.is.A.ISAI0;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.db.WHS.WHS;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISBL0WHS extends ListForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ISAI0.class;
    }

    @Override
    public Class<? extends UserFormInterface> getTopForm() {
        return ISBH0WHS.class;
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
        super.addTextField("Whs", "ISWHS.Whs");
        super.setFieldHelpMessage("ISWHS.Whs.help");
    //super.setFieldListColumnWidth(400);        
    }

    @Override
    protected void buildDataList() {
        super.addTextField("Nme", "ISWHS.Nme");
        super.setFieldHelpMessage("ISWHS.Nme.help");
        super.addTextField("CstTyp", "ISWHS.CstTyp");
        super.setFieldHelpMessage("ISWHS.CstTyp.help");

        super.addTextField("Remark", "ISWHS.Remark");
        super.setFieldHelpMessage("ISWHS.Remark.help");
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

        super.setAddForm(ISBS0WHS.class);
        super.setCopyForm(ISBS0WHS.class);
        super.setEditForm(ISBS0WHS.class);
        super.setViewForm(ISBS0WHS.class);
    }

    @Override
    public void executeReload(int arg0, int[] arg1) {
        try {
            String rsName = "ISBL0WHS";
            WHS.select_All(this, rsName);
            useSQL(rsName);
        } catch (SQLException ex) {
            String title = "ISBL0WHS.error";
            cmd.log.severe(title, ex);
            cmd.common.showMessage(DialogMessageType.ERROR,
                    title, "ISBL0WHS.error.description");
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
        String Cmp = cmd.list.getString(selectedRow, "Whs");
        cmd.out.map.texts.put(MAP.ISWHS.WHS, Cmp);
        cmd.out.stringValue = Cmp;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        String id = cmd.list.getString(selectedRow, "Whs");
        cmd.out.map.texts.put(MAP.ISWHS.WHS, id);
        UserFormInterface f = cmd.form.create(ISBP2WHS.class);
        cmd.form.execute(f);
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        String Cmp = cmd.list.getString(selectedRow, "Whs");
        String Nme = cmd.list.getString(selectedRow, "Nme");
        cmd.global.texts.put(MAP.ISWHS.WHS, Cmp);
        cmd.global.texts.put(MAP.ISWHS.NAME, Nme);
        cmd.out.map.texts.put(MAP.ISWHS.WHS, Cmp);
        UserFormInterface f = cmd.form.create(ISBP0WHS.class);
        cmd.form.executeInNewThread(f);
    }

    @Override
    public String getFormTitle() {
        return "ISBL0WHS.title";
    }
}
