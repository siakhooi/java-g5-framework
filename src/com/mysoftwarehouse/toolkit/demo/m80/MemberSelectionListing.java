/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m80;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class MemberSelectionListing extends ListForm {

    @Override
    protected void buildKeyList() {
        super.addTextField("member", "member.member.label");
        super.setFieldHelpMessage("member.member.help");
        super.setFieldListColumnWidth(400);
    }

    @Override
    protected void buildDataList() {
        super.addTextField("lastname", "member.lastname.label");
        super.setFieldHelpMessage("member.lastname.help");
        super.addTextField("firstname", "member.firstname.label");
        super.setFieldHelpMessage("member.firstname.help");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, false);
        super.setActionEnable(ListActionType.EDIT, false);
        super.setActionEnable(ListActionType.COPY, false);
        super.setActionEnable(ListActionType.VIEW, false);
        super.setActionEnable(ListActionType.DELETE, false);
        super.setActionEnable(ListActionType.RELOAD, true);
        super.setActionEnable(ListActionType.SELECT, true);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, false);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, false);
        super.setActionEnable(ListActionType.CLOSE, true);

//        super.setAddForm(ViewHtmlForm.class);
//        super.setCopyForm(ViewHtmlForm.class);
//        super.setEditForm(ViewHtmlForm.class);
//        super.setViewForm(ViewHtmlForm.class);

    }

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        try {
            String sql = "select * from member order by member";
            cmd.db.setStatement("ps", sql);
            cmd.db.execQuery("ps", "rs");
            super.useSQL("rs");
        } catch (SQLException ex) {
            cmd.common.showI18nMessage(DialogMessageType.ERROR,
                    "Error", "Unable to retrieve data");
        }

    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        cmd.in.stringValue = cmd.list.getString(selectedRow, "member");
    }

    @Override
    public String getFormTitle() {
        return "MemberSelectionListing.title";
    }
}
