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
public class BSPR0PIV extends ReportEntryForm {

    @Override
    protected void buildFieldList() {

        super.addCalendarField("FrmDte", "BSPR0PIV.FrmDte");
        super.setFieldHelpMessage("BSPR0PIV.FrmDte.help");
        super.setFieldMandatory(false);
        super.setFieldFormat(true, false);

        super.addCalendarField("ToDte", "BSPR0PIV.ToDte");
        super.setFieldHelpMessage("BSPR0PIV.ToDte.help");
        super.setFieldMandatory(false);
        super.setFieldFormat(true, false);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSPJ0PIV.class;
    }

    @Override
    public void onOutExit() {
        try {
            Calendar fromDte = cmd.entry.getCalendar("FrmDte");
            Calendar toDte = cmd.entry.getCalendar("ToDte");
            cmd.out.map.calendars.put(MAP.BSPIV.FROMDATE, fromDte);
            cmd.out.map.calendars.put(MAP.BSPIV.TODATE, toDte);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSPR0PIV.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "BSPR0PIV.title";
    }

    @Override
    public void initValue() {
    }
}
