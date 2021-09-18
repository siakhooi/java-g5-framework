/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.E;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.pfa.C.PFCL0ACC;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFER1ACC extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FromAcc", "PFER1ACC.FromAcc");
        super.setFieldHelpMessage("PFER1ACC.FromAcc.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(PFCL0ACC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTextField("ToAcc", "PFER1ACC.ToAcc");
        super.setFieldHelpMessage("PFER1ACC.ToAcc.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(PFCL0ACC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addRadioboxfield("RptTyp", "PFER1ACC.RptTyp");
        super.setFieldHelpMessage("PFER1ACC.RptTyp.help");
        super.addOption("Y", "PFER1ACC.RptTyp.Y");
        super.addOption("M", "PFER1ACC.RptTyp.M");

        super.addTab("Yearly", "PFER1ACC.tab.Yearly");
        super.addCalendarField("Year", "PFER1ACC.Year");
        super.setFieldHelpMessage("PFER1ACC.Year.help");
        super.setFieldInputFormat("yyyy");
        super.setFieldOutputFormat("yyyy");
        super.setFieldMandatory(true);

        super.addTab("Monthly", "PFER1ACC.tab.Monthly");
        super.addCalendarField("MonthYear", "PFER1ACC.Month");
        super.setFieldHelpMessage("PFER1ACC.Month.help");
        super.setFieldInputFormat("yyyyMM");
        super.setFieldOutputFormat("MMM yyyy");
        super.setFieldMandatory(true);
    }

    @Override
    public boolean verify(String fieldName) {
        try {
            if ("RptTyp".equals(fieldName)) {
                String v = cmd.entry.getString("RptTyp");
                if ("Y".equals(v)) {
                    cmd.entry.setTabEnabled("Yearly", true);
                    cmd.entry.setTabEnabled("Monthly", false);
                    cmd.entry.showTab("Yearly");
                } else {
                    cmd.entry.setTabEnabled("Yearly", false);
                    cmd.entry.setTabEnabled("Monthly", true);
                    cmd.entry.showTab("Monthly");
                }
            }
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFER1ACC.error", ex);
        }
        return true;
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return PFEJ1ACC.class;
    }

    @Override
    public void onOutExit() {
        try {
            String fromAcc = cmd.entry.getString("FromAcc");
            String toAcc = cmd.entry.getString("ToAcc");
            String rptTyp = cmd.entry.getString("RptTyp");
            Calendar year = cmd.entry.getCalendar("Year");
            Calendar month = cmd.entry.getCalendar("MonthYear");
            cmd.out.map.texts.put(ACC.FROMACC, fromAcc);
            cmd.out.map.texts.put(ACC.TOACC, toAcc);
            cmd.out.map.texts.put(ACC.RPTTYP, rptTyp);
            cmd.out.map.calendars.put(ACC.RPTYEAR, year);
            cmd.out.map.calendars.put(ACC.RPTYEARMONTH, month);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFER1ACC.error", ex);
        }
    }

    @Override
    public void initValue() {
        try {
            cmd.entry.setValue("RptTyp", "Y");
            cmd.entry.setValue("Year", cmd.cal.getNow());
            cmd.entry.setValue("MonthYear", cmd.cal.getNow());
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFER1ACC.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "PFER1ACC.title";
    }
}
