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
public class PFFR0ACCM extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addCalendarField("Period", "PFFR0ACCM.Period");
        super.setFieldHelpMessage("PFFR0ACCM.Period.help");
        super.setFieldInputFormat("yyyyMM");
        super.setFieldOutputFormat("MMM yyyy");
        super.setFieldMandatory(true);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return PFFJ0ACCM.class;
    }

    @Override
    public void onOutExit() {
        try {
            Calendar period = cmd.entry.getCalendar("Period");
            cmd.out.map.calendars.put(ACC.PERIOD, period);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFFR0ACCM.error", ex);
        }
    }

    @Override
    public void initValue() {
        try {
            cmd.entry.setValue("Period", cmd.cal.getNow());
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFFR0ACCM.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "PFFR0ACCM.title";
    }
}
