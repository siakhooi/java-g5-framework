/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m75;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.AlignmentType;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.EntryFormEnum.StatusType;
import com.mysoftwarehouse.toolkit.demo.m20.image.HelloImage;
import com.mysoftwarehouse.toolkit.demo.m20.blank.HelloWorld;
import com.mysoftwarehouse.toolkit.demo.m20.image.HelloLogo;
import com.mysoftwarehouse.toolkit.demo.m70.HelloListWithArray;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloEntryWithEmbed extends EntryForm {

    @Override
    public String getFormTitle() {
        return "HelloEntry.title";
    }

    public void onEscapeKeyPressed() {
        cmd.debug.println("calling close form");
        cmd.form.closeForm();
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
        super.setFieldSelectForm(HelloWorld.class);
        super.setFieldViewForm(HelloImage.class);


        super.addTab("General", "General");
        super.addTextField("lastname", "member.lastname.label");
        super.setFieldHelpMessage("member.lastname.help");
        super.setFieldMandatory(true);

        super.addTextField("firstname", "member.firstname.label");
        super.setFieldHelpMessage("member.firstname.help");
        super.setFieldEditable(false);

        super.addTab("Logo", "Logo", HelloLogo.class);
        super.addTab("ArrayList", "List With Array", HelloListWithArray.class);

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
        super.buttons.addI18nButton("OK", "OKOK");
        super.buttons.setFocusable(false);
        super.buttons.addI18nButton("Cancel", "Cancel");
        super.buttons.setFocusable(false);
        super.buttons.addI18nButton("ShowAccount", "Show Account");
        super.buttons.setFocusable(false);
    }

    @Override
    public void buttonClick(String name) {
        if ("OK".equals(name)) {
            try {
                cmd.entry.setValue("member", "HHHHH");
                cmd.entry.setValue("regdate", cmd.cal.getNow());

                cmd.entry.setEditable("favcolor", false);
                cmd.entry.setMandatory("amount", true);
                cmd.entry.setVisible("photo", false);
                cmd.entry.setVisible("pwd", false);
                cmd.entry.setTabEnabled("Status", false);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.debug.error("buttonClick", ex);
            }
        } else if ("ShowAccount".equals(name)) {
            cmd.entry.setTabEnabled("Status", false);
            cmd.entry.showTab("Account");
        }
        cmd.debug.println("you click: " + name);
        cmd.entry.setI18nStatus(StatusType.INFO, name);
    }

    @Override
    public void onFocusGain(String fieldName) {
        cmd.debug.println("focusGain: " + fieldName);
    }

    @Override
    public void onFocusLost(String fieldName) {
        cmd.debug.println("focusLost: " + fieldName);
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
    public void verifyField(String fieldName) throws Exception {
        cmd.debug.println("verify: " + fieldName);
        if (fieldName.equals("age")) {
            if (!cmd.entry.isNull("age")) {
                int i = cmd.entry.getInteger("age");
                cmd.debug.println("age: " + i);
                if (i > 18 || i <= 0) {
                    super.throwI18nVerifyError("Invalid Age Range (1-18)!");
                }
            }
        } else if ("regdate".equals(fieldName)) {
            if (!cmd.entry.isNull("regdate")) {
                Calendar i = cmd.entry.getCalendar("regdate");
                cmd.debug.println(
                        cmd.lang.formatCalendar(i, "MM dd, yyyy"));
            }
        } else if ("amount".equals(fieldName)) {
            if (!cmd.entry.isNull("amount")) {
                BigDecimal b = cmd.entry.getBigDecimal("amount");
                cmd.debug.println(b.toPlainString());
            }
        } else if ("pass".equals(fieldName)) {
            if (!cmd.entry.isNull("pass")) {
                boolean i = cmd.entry.getBoolean(fieldName);
                cmd.debug.println("true/false: " + i);
            }
        } else if ("favcolor".equals(fieldName)) {
            if (!cmd.entry.isNull("favcolor")) {
                Color i = cmd.entry.getColor(fieldName);
                cmd.debug.println(cmd.data.Color2Hex(i));
            }
        }
        super.verifyField(fieldName);
    }

//    @Override
//    public boolean verify(String fieldName) {
//        cmd.debug.println("verify: " + fieldName);
//        try {
//            if (fieldName.equals("age")) {
//                if (!cmd.entry.isNull("age")) {
//                    int i = cmd.entry.getInteger("age");
//                    cmd.debug.println("age: " + i);
//                    if (i > 18 || i <= 0) {
//                        super.setI18nVerifyErrorMessage("Invalid Age Range (1-18)!");
//                        return false;
//                    }
//
//                }
//            } else if ("regdate".equals(fieldName)) {
//                if (!cmd.entry.isNull("regdate")) {
//                    Calendar i = cmd.entry.getCalendar("regdate");
//                    cmd.debug.println(
//                            cmd.lang.formatCalendar(i, "MM dd, yyyy"));
//                }
//            } else if ("amount".equals(fieldName)) {
//                if (!cmd.entry.isNull("amount")) {
//                    BigDecimal b = cmd.entry.getBigDecimal("amount");
//                    cmd.debug.println(b.toPlainString());
//                }
//            } else if ("pass".equals(fieldName)) {
//                if (!cmd.entry.isNull("pass")) {
//                    boolean i = cmd.entry.getBoolean(fieldName);
//                    cmd.debug.println("true/false: " + i);
//                }
//            } else if ("favcolor".equals(fieldName)) {
//                if (!cmd.entry.isNull("favcolor")) {
//                    Color i = cmd.entry.getColor(fieldName);
//                    cmd.debug.println(cmd.data.Color2Hex(i));
//                }
//            }
//        } catch (EntryFieldWrongDataTypeException ex) {
//        }
//        return super.verify(fieldName);
//    }
    @Override
    public void initValue() {
        try {
            cmd.entry.setValue("member", "MM123");
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.debug.error("initValue", ex);
        }
    }
}
