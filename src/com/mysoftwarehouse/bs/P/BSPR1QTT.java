/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.P;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.mysoftwarehouse.bs.conf.MAP;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSPR1QTT extends ReportEntryForm {

    @Override
    protected void buildFieldList() {

        super.addCalendarField("FrmDte", "BSPR1QTT.FrmDte");
        super.setFieldHelpMessage("BSPR1QTT.FrmDte.help");
        super.setFieldMandatory(false);
        super.setFieldFormat(true, false);

        super.addCalendarField("ToDte", "BSPR1QTT.ToDte");
        super.setFieldHelpMessage("BSPR1QTT.ToDte.help");
        super.setFieldMandatory(false);
        super.setFieldFormat(true, false);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSPJ1QTT.class;
    }

    @Override
    public void onOutExit() {
        try {
            Calendar fromDte = cmd.entry.getCalendar("FrmDte");
            Calendar toDte = cmd.entry.getCalendar("ToDte");
            cmd.out.map.calendars.put(MAP.BSQTT.FROMDATE, fromDte);
            cmd.out.map.calendars.put(MAP.BSQTT.TODATE, toDte);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSPR1QTT.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "BSPR1QTT.title";
    }

    @Override
    public void initValue() {
    }
}
