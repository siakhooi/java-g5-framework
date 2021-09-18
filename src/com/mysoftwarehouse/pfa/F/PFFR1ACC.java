/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.F;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFFR1ACC extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addRadioboxfield("RptAccTyp", "PFFR1ACC.RptAccTyp");
        super.setFieldHelpMessage("PFFR1ACC.RptAccTyp.help");
        super.addOption("A", "AccTyp.A");
        super.addOption("L", "AccTyp.L");
        super.addOption("I", "AccTyp.I");
        super.addOption("E", "AccTyp.E");

        super.addRadioboxfield("RptTyp", "PFFR1ACC.RptTyp");
        super.setFieldHelpMessage("PFFR1ACC.RptTyp.help");
        super.addOption("Y", "PFFR1ACC.RptTyp.Y");
        super.addOption("M", "PFFR1ACC.RptTyp.M");

        super.addTab("Yearly", "PFFR1ACC.tab.Yearly");
        super.addCalendarField("Year", "PFFR1ACC.Year");
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
//            } else if ("Year".equals(fieldName)) {
//                Calendar c = cmd.entry.getCalendar(fieldName);
//                cmd.entry.setValue("MonthYear", c);
//            } else if ("MonthYear".equals(fieldName)) {
//                Calendar c = cmd.entry.getCalendar(fieldName);
//                cmd.entry.setValue("Year", c);
            }
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFFR1ACC.error", ex);
        }
        return true;
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return PFFJ1ACC.class;
    }

    @Override
    public void onOutExit() {
        try {
            String rptAccTyp = cmd.entry.getString("RptAccTyp");
            String rptTyp = cmd.entry.getString("RptTyp");
            Calendar year = cmd.entry.getCalendar("Year");
            Calendar month = cmd.entry.getCalendar("MonthYear");
            cmd.out.map.texts.put(ACC.RPTTYP, rptTyp);
            cmd.out.map.texts.put(ACC.RPTACCTYP, rptAccTyp);
            cmd.out.map.calendars.put(ACC.RPTYEAR, year);
            cmd.out.map.calendars.put(ACC.RPTYEARMONTH, month);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFFR1ACC.error", ex);
        }
    }

    @Override
    public void initValue() {
        try {
            cmd.entry.setValue("RptAccTyp", "A");
            cmd.entry.setValue("RptTyp", "Y");
            cmd.entry.setValue("Year", cmd.cal.getNow());
            cmd.entry.setValue("MonthYear", cmd.cal.getNow());
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFFR1ACC.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "PFFR1ACC.title";
    }
}
