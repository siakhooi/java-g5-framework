/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m80;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.DataType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class MemberList extends ListForm {

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

        super.addBooleanField("pass", "member.pass.label");
        super.setFieldHelpMessage("member.pass.help");
        super.addCalendarField("regdate", "member.regdate.label");
        super.setFieldHelpMessage("member.regdate.help");
        super.setFieldFormat(true, true);

        super.addCalendarField("regdate1", "member.regdate.label");
        super.setFieldHelpMessage("member.regdate.help");

        super.addColorField("favcolor", "member.favcolor.label");
        super.setFieldHelpMessage("member.favcolor.help");

        super.addPasswordField("pwd", "member.pwd.label");
        super.setFieldHelpMessage("member.pwd.help");
        super.addNumericField("age", "member.age.label");
        super.setFieldHelpMessage("member.age.help");

        super.addNumericField("amount", "member.amount.label");
        super.setFieldHelpMessage("member.amount.help");
        super.setFieldOutputFormat("#0.00000");

    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, true);
        super.setActionEnable(ListActionType.EDIT, true);
        super.setActionEnable(ListActionType.COPY, true);
        super.setActionEnable(ListActionType.VIEW, true);
        super.setActionEnable(ListActionType.DELETE, true);
        super.setActionEnable(ListActionType.RELOAD, true);
        super.setActionEnable(ListActionType.SELECT, true);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, true);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, true);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setAddForm(MemberMaintenance.class);
        super.setCopyForm(MemberMaintenance.class);
        super.setEditForm(MemberMaintenance.class);
        super.setViewForm(MemberMaintenance.class);
        super.buttons.addI18nButton("Test", "test");
    }

    @Override
    public void buttonClick(String name, int selectedRow, int[] selectedRows) {
        cmd.debug.println(name + ": " + selectedRows.length);
        for (int i : selectedRows) {
            cmd.debug.println(i + ":" + cmd.list.getObject(i, "member"));
        }
        cmd.list.refreshList();
    }

    @Override
    public void afterReload() {
        cmd.list.selectLastRow();
        cmd.list.addRowSelection(2, 5);
        cmd.list.addRowSelection(12, 22);
    }

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        try {
            String sql = "select *, regdate as regdate1 from member order by member";
            cmd.db.setStatement("ps", sql);
            cmd.db.execQuery("ps", "rs");
            super.useSQL("rs");
        } catch (SQLException ex) {
            cmd.common.showI18nMessage(DialogMessageType.ERROR,
                    "Error", "Unable to retrieve data");
        }
    }

    @Override
    public void executeAdd(int selectedRow, int[] selectedRows) throws Exception {
        cmd.debug.println("add: " + selectedRows.length);

//        cmd.out.message = "<HTML><BODY><H1>WELCOMe</H1></BODY></HTML>";
//        cmd.out.i18nTitle = "welcome ...";
    }

    @Override
    public void executeCopy(int selectedRow, int[] selectedRows) throws Exception {
        cmd.debug.println("copy: " + selectedRows.length);
        cmd.out.stringValue = cmd.list.getString(selectedRow, "member");
        cmd.out.map.texts.put("member",
                cmd.list.getString(selectedRow, "member"));
//        cmd.out.message = "<HTML><BODY><H1>COPY</H1></BODY></HTML>";
//        cmd.out.i18nTitle = "copying ...";
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        cmd.debug.println("edit: " + selectedRows.length);
        cmd.out.stringValue = cmd.list.getString(selectedRow, "member");
        cmd.out.map.texts.put("member",
                cmd.list.getString(selectedRow, "member"));

//        cmd.out.message = "<HTML><BODY><H1>EDIT</H1></BODY></HTML>";
//        cmd.out.i18nTitle = "editing ...";
    }

    @Override
    public void executeView(int selectedRow, int[] selectedRows) throws Exception {
        cmd.debug.println("view: " + selectedRows.length);
        cmd.out.stringValue = cmd.list.getString(selectedRow, "member");
        cmd.out.map.texts.put("member",
                cmd.list.getString(selectedRow, "member"));
//        cmd.out.message = "<HTML><BODY><H1>VIEW</H1></BODY></HTML>";
//        cmd.out.i18nTitle = "viewing ...";
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        cmd.debug.println("delete: " + selectedRows.length);
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
        cmd.debug.println("selected: " + selectedRows.length);
    }

    @Override
    public void executeSaveAllToCSV(int selectedRow, int[] selectedRows) throws Exception {
        cmd.debug.println("save all: " + selectedRows.length);
    }

    @Override
    public void executeSaveSelectedToCSV(int selectedRow, int[] selectedRows) throws Exception {
        cmd.debug.println("save selected: " + selectedRows.length);
    }

    @Override
    public String getFormTitle() {
        return "HelloList.title";
    }

    @Override
    public DataType getDataType() {
        return DataType.RESULTSET;
    }
}
