/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m80;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.AlignmentType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeSystemButton;
import java.awt.Color;
import java.io.File;
import java.math.BigDecimal;

/**
 *
 * @author Ng Siak Hooi
 */
public class Member extends ModeEntryForm {

    @Override
    public void initModeAction() {
    }

    @Override
    public void finishModeAction() {
        if (currentMode == currentMode.ADD) {
            cmd.mode.setAction(ModeSystemButton.Back, ModeAction.CloseForm);
        }
    }

    @Override
    public void saveAdd() throws Exception {
        String psName = "ps2";
        String sql = "insert into member (" +
                "member, " +
                "firstname, " +
                "lastname, " +
                "pass, " +
                "regdate, " +
                "favcolor, " +
                "pwd, " +
                "age, " +
                "amount, " +
                "sex, " +
                "membertype) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        cmd.db.begin();
        cmd.db.setStatement(psName, sql);
        int m = 1;
        cmd.db.setParameter(psName, m++, cmd.entry.getString("member"));
        cmd.db.setParameter(psName, m++, cmd.entry.getString("firstname"));
        cmd.db.setParameter(psName, m++, cmd.entry.getString("lastname"));
        cmd.db.setParameter(psName, m++, cmd.data.boolean2String(cmd.entry.getBoolean("pass")));
        cmd.db.setParameter(psName, m++, cmd.entry.getCalendar("regdate"));
        cmd.db.setParameter(psName, m++, cmd.data.Color2long(cmd.entry.getColor("favcolor")));
        cmd.db.setParameter(psName, m++, cmd.entry.getString("pwd"));
        cmd.db.setParameter(psName, m++, cmd.entry.getInteger("age"));
        cmd.db.setParameter(psName, m++, cmd.entry.getBigDecimal("amount"));
        cmd.db.setParameter(psName, m++, cmd.entry.getString("sex"));
        cmd.db.setParameter(psName, m++, cmd.entry.getString("membertype"));
        int i = cmd.db.execUpdate(psName);
        cmd.db.commit();
    }

    @Override
    public void saveEdit() throws Exception {
        String psName = "ps2";
        String sql = "update member set " +
                "firstname=?, " +
                "lastname=?, " +
                "pass=?, " +
                "regdate=?, " +
                "favcolor=?, " +
                "pwd=?, " +
                "age=?, " +
                "amount=?, " +
                "sex=?, " +
                "membertype=? " +
                "where member=?";
        cmd.db.begin();
        cmd.db.setStatement(psName, sql);
        int m = 1;
        cmd.db.setParameter(psName, m++, cmd.entry.getString("firstname"));
        cmd.db.setParameter(psName, m++, cmd.entry.getString("lastname"));
        cmd.db.setParameter(psName, m++, cmd.data.boolean2String(cmd.entry.getBoolean("pass")));
        cmd.db.setParameter(psName, m++, cmd.entry.getCalendar("regdate"));
        cmd.db.setParameter(psName, m++, cmd.data.Color2long(cmd.entry.getColor("favcolor")));
        cmd.db.setParameter(psName, m++, cmd.entry.getString("pwd"));
        cmd.db.setParameter(psName, m++, cmd.entry.getInteger("age"));
        cmd.db.setParameter(psName, m++, cmd.entry.getBigDecimal("amount"));
        cmd.db.setParameter(psName, m++, cmd.entry.getString("sex"));
        cmd.db.setParameter(psName, m++, cmd.entry.getString("membertype"));
        cmd.db.setParameter(psName, m++, cmd.entry.getString("member"));
        int i = cmd.db.execUpdate(psName);
        cmd.db.commit();
    }

    @Override
    public void saveDelete() throws Exception {
        String psName = "ps1";
        String sql = "delete from member where member=?";
        cmd.db.begin();

        cmd.db.setStatement(psName, sql);
        String m = cmd.entry.getString("member");
        cmd.db.setParameter(psName, 1, m);
        cmd.db.execUpdate(psName);
        cmd.db.commit();
    }

    @Override
    public void loadData() throws Exception {
        String psName = "ps1";
        String rsName = "rs1";
        String sql = "select * from member where member=?";
        cmd.db.setStatement(psName, sql);
        String m = cmd.entry.getString("member");
        cmd.debug.println("member: " + m);
        cmd.db.setParameter(psName, 1, m);
        cmd.db.execQuery(psName, rsName);
        boolean v = cmd.db.next(rsName);
        if (!v) {
            super.throwNoRecordError();
        }

        cmd.entry.setValue("age", cmd.db.getInteger(rsName, "age"));
        cmd.entry.setValue("lastname", cmd.db.getString(rsName, "lastname"));
        cmd.entry.setValue("firstname", cmd.db.getString(rsName, "firstname"));
        cmd.entry.setValue("pass", cmd.data.isBoolean(cmd.db.getString(rsName, "pass")));
        cmd.entry.setValue("regdate",
                cmd.db.getDate(rsName, "regdate"));
        BigDecimal d = cmd.db.getBigDecimal(rsName, "favcolor");
        Color c = cmd.data.long2Color(d.longValue());
        cmd.entry.setValue("favcolor", c);
        cmd.entry.setValue("pwd", cmd.db.getString(rsName, "pwd"));
        cmd.entry.setValue("amount", cmd.db.getBigDecimal(rsName, "amount"));
        cmd.entry.setValue("sex", cmd.db.getString(rsName, "sex"));
        cmd.entry.setValue("membertype", cmd.db.getString(rsName, "membertype"));
    }

    @Override
    public void loadDefault() throws Exception {
        cmd.entry.setValue("age", 13);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("member", "member.member.label");
        super.setFieldHelpMessage("member.member.help");
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(10);
        //super.setFieldAllowCharacters("0123456789Aa");
        //super.setFieldFormat("(A)(\\d{5})", "{0}-{1}");
        super.setFieldMandatory(true);
        super.setFieldTextAlignment(AlignmentType.CENTER);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldSelectForm(MemberSelectionListing.class);
        //super.setFieldViewForm(HelloImage.class);


        super.addTab("General", "General");
        super.addTextField("lastname", "member.lastname.label");
        super.setFieldHelpMessage("member.lastname.help");
        super.setFieldTextColumn(20);
        super.setFieldMandatory(true);

        super.addTextField("firstname", "member.firstname.label");
        super.setFieldHelpMessage("member.firstname.help");
        super.setFieldTextColumn(20);
        //super.setFieldEditable(false);

        super.addTab("Status", "Status", 2);
        super.addBooleanField("pass", "member.pass.label");
        super.setFieldHelpMessage("member.pass.help");
        super.spanColumn(2);
        //
        super.addCalendarField("regdate", "member.regdate.label");
        super.setFieldHelpMessage("member.regdate.help");
        super.setFieldTextColumn(20);
        //
        super.addCalendarField("regdate1", "member.regdate.label");
        super.setFieldHelpMessage("member.regdate.help");
        super.setFieldFormat(true, true);

        super.addTab("Personal", "Personal");
        super.addColorField("favcolor", "member.favcolor.label");
        super.setFieldHelpMessage("member.favcolor.help");

        super.addPasswordField("pwd", "member.pwd.label");
        super.setFieldHelpMessage("member.pwd.help");
        super.addNumericField("age", "member.age.label");
        super.setFieldHelpMessage("member.age.help");

        super.addTab("Account", "Accounts");
        super.addNumericField("amount", "member.amount.label");
        super.setFieldHelpMessage("member.amount.help");
        super.setFieldOutputFormat("#0.00000");
        super.setFieldTextColumn(20);

        super.addRadioboxField("sex", "member.sex.label");
        super.setFieldHelpMessage("member.sex.help");
        super.addOption("M", "Male");
        super.addOption("F", "Female");
        super.addOption("G", "Gay");

        super.addComboField("membertype", "member.membertype.label");
        super.setFieldHelpMessage("member.membertype.help");
        super.addOption("A", "Type A");
        super.addOption("B", "Type B");
        super.addOption("C", "Type C");
        super.addOption("D", "Type D");
        super.addOption("E", "Type E");
        super.addOption("F", "Type F");
    }

    @Override
    public void buttonClick(String name) {
        cmd.log.severe("buttonClick: " + name);
        //cmd.mode.executeAction(ModeAction.CloseForm);
        try {
            File f = cmd.common.chooseSaveCSVFile(null);
            if (f == null) {
                return;
            }
            String psName = "ps8";
            String rsName = "rs8";
            String sql = "select * from member where sex=?";
            cmd.db.setStatement(psName, sql);
            cmd.db.setParameter(psName, 1, "M");
            cmd.db.execQuery(psName, rsName);
            boolean v = cmd.db.next(rsName);
            if (v) {
                cmd.common.saveResultSetToCSVFile(
                        cmd.db.getResultSet(rsName), f);
            }
        } catch (Exception ex) {

        }
    }

    @Override
    public void onFieldOutExit(String fieldName) {
        cmd.debug.println("onFieldOutExit: " + fieldName);
    }

    @Override
    public void onFieldOutEnter(String fieldName) {
        cmd.debug.println("onFieldOutEnter: " + fieldName);
    }

    @Override
    protected void buildButtonsList() {
        super.buttons.addI18nButton("test", "test2");
    }

    @Override
    public String getFormTitle() {
        return "Member.title";
    }

}
