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
public class PFFR0ACC extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addRadioboxfield("RptTyp", "PFFR0ACC.RptTyp");
        super.setFieldHelpMessage("PFFR0ACC.RptTyp.help");
        super.addOption("Y", "PFFR0ACC.RptTyp.Y");
        super.addOption("M", "PFFR0ACC.RptTyp.M");

        super.addTab("Yearly", "PFFR0ACC.tab.Yearly");
        super.addCalendarField("Year", "PFFR0ACC.Year");
        super.setFieldHelpMessage("PFFR0ACC.Year.help");
        super.setFieldInputFormat("yyyy");
        super.setFieldOutputFormat("yyyy");
        super.setFieldMandatory(true);

        super.addTab("Monthly", "PFFR0ACC.tab.Monthly");
        super.addCalendarField("MonthYear", "PFFR0ACC.Month");
        super.setFieldHelpMessage("PFFR0ACC.Month.help");
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
            cmd.log.sysSevere("PFFR0ACC.error", ex);
        }
        return true;
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return PFFJ0ACC.class;
    }

    @Override
    public void onOutExit() {
        try {
            String rptTyp = cmd.entry.getString("RptTyp");
            Calendar year = cmd.entry.getCalendar("Year");
            Calendar month = cmd.entry.getCalendar("MonthYear");
            cmd.out.map.texts.put(ACC.RPTTYP, rptTyp);
            cmd.out.map.calendars.put(ACC.RPTYEAR, year);
            cmd.out.map.calendars.put(ACC.RPTYEARMONTH, month);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFFR0ACC.error", ex);
        }
    }

    @Override
    public void initValue() {
        try {
            cmd.entry.setValue("RptTyp", "Y");
            cmd.entry.setValue("Year", cmd.cal.getNow());
            cmd.entry.setValue("MonthYear", cmd.cal.getNow());
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFFR0ACC.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "PFFR0ACC.title";
    }
}
