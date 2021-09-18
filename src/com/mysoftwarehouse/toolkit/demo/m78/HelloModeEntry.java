/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m78;

import com.gqrsoft.g5.developer.form.ModeEntryForm;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.AlignmentType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.toolkit.demo.m80.MemberList;
import java.awt.Color;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloModeEntry extends ModeEntryForm {
//    public ModeAction enteringAction = ModeAction.SearchMode;
//    public Mode currentMode;
//    //during actions
//    public boolean fieldVerification;
//    public String i18nConfirmationText;
//    public NextModeType nextModeType;
//    public ButtonConfiguration buttonConfiguration;
//    public ModeSaveProcess nextModeSaveProcess;
//    public ModeLoadProcess nextModeLoadProcess;
    @Override
    public void initModeAction() {
        cmd.debug.println("initModeAction()");
    }

    @Override
    public void finishModeAction() {
        cmd.debug.println("finishModeAction()");
    }

    @Override
    public void saveAdd() throws Exception {
        cmd.debug.println("saveAdd()");
    }

    @Override
    public void saveEdit() throws Exception {
        cmd.debug.println("saveEdit()");
    }

    @Override
    public void saveDelete() throws Exception {
        cmd.debug.println("saveDelete()");
    }

    @Override
    public void loadData() throws Exception {
        cmd.debug.println("loadData()");
    }

    @Override
    public void loadDefault() throws Exception {
//            if (!cmd.entry.isNull("age")) {
//                int i = cmd.entry.getInteger("age");
//                if (i == 1) {
//                    return false;
//                }
//            }
        cmd.debug.println("loadDefault()");
        cmd.entry.setValue("member", "A123");
        cmd.entry.setValue("age", 0);
        cmd.entry.setValue("favcolor", Color.PINK);

    }

    @Override
    protected void buildFieldList() {
        super.addTextField("member", "member.member.label");
        super.setFieldHelpMessage("member.member.help");
        super.setFieldTextColumn(20);
        super.setFieldMaximumLength(10);
        super.setFieldAllowCharacters("0123456789Aa");
        super.setFieldFormat("(A)(\\d{5})", "{0}-{1}");
        super.setFieldMandatory(true);
        super.setFieldTextAlignment(AlignmentType.CENTER);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        //super.setFieldEditable(false);
        super.setFieldSelectForm(MemberList.class);
        //super.setFieldViewForm(HelloImage.class);


        super.addTab("General", "General");
        super.addTextField("lastname", "member.lastname.label");
        super.setFieldHelpMessage("member.lastname.help");
        super.setFieldMandatory(true);

        super.addTextField("firstname", "member.firstname.label");
        super.setFieldHelpMessage("member.firstname.help");
        super.setFieldEditable(false);

        super.addTab("Status", "Status", 2);
        super.addBooleanField("pass", "member.pass.label");
        super.setFieldHelpMessage("member.pass.help");
        super.spanColumn(2);
        //
        super.addCalendarField("regdate", "member.regdate.label");
        super.setFieldHelpMessage("member.regdate.help");
        super.setFieldFormat(true, true);
        //
        super.addCalendarField("regdate1", "member.regdate.label");
        super.setFieldHelpMessage("member.regdate.help");
        //super.setFieldVisible(false);

        //super.addTab("Personal", "Personal");
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

        super.addImageField("photo", "member.photo.label");
        super.addDirectoryField("directory", "member.directory.label");
        super.addNewFileField("newfile", "member.newfile.label");
        super.addOpenFileField("openfile", "member.openfile.label");
    }

    @Override
    protected void buildButtonsList() {
    }

    @Override
    public String getFormTitle() {
        return "HelloModeEntry.title";
    }
}
