/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.N;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.mysoftwarehouse.bs.conf.MAP;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSNR2POR extends ReportEntryForm {

    @Override
    protected void buildFieldList() {

        super.addCalendarField("FrmDte", "BSNR2POR.FrmDte");
        super.setFieldHelpMessage("BSNR2POR.FrmDte.help");
        super.setFieldMandatory(false);
        super.setFieldFormat(true, false);

        super.addCalendarField("ToDte", "BSNR2POR.ToDte");
        super.setFieldHelpMessage("BSNR2POR.ToDte.help");
        super.setFieldMandatory(false);
        super.setFieldFormat(true, false);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSNJ2POR.class;
    }

    @Override
    public void onOutExit() {
        try {
            Calendar fromDte = cmd.entry.getCalendar("FrmDte");
            Calendar toDte = cmd.entry.getCalendar("ToDte");
            cmd.out.map.calendars.put(MAP.BSPOR.FROMDATE, fromDte);
            cmd.out.map.calendars.put(MAP.BSPOR.TODATE, toDte);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSNR2POR.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "BSNR2POR.title";
    }

    @Override
    public void initValue() {
    }
}
