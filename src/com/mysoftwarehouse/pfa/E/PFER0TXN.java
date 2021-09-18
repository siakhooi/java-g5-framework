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
public class PFER0TXN extends ReportEntryForm {

    @Override
    protected void buildFieldList() {

        super.addTextField("FrmAcc", "PFER0TXN.FromAcc");
        super.setFieldHelpMessage("PFER0TXN.FrmAcc.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(PFCL0ACC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTextField("ToAcc", "PFER0TXN.ToAcc");
        super.setFieldHelpMessage("PFER0TXN.ToAcc.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(PFCL0ACC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addCalendarField("FrmDte", "PFER0TXN.FrmDte");
        super.setFieldHelpMessage("PFER0TXN.FrmDte.help");
        super.setFieldMandatory(false);
        super.setFieldFormat(true, true);

        super.addCalendarField("ToDte", "PFER0TXN.ToDte");
        super.setFieldHelpMessage("PFER0TXN.ToDte.help");
        super.setFieldMandatory(false);
        super.setFieldFormat(true, true);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return PFEJ0TXN.class;
    }

    @Override
    public void onOutExit() {
        try {
            String fromAcc = cmd.entry.getString("FrmAcc");
            String toAcc = cmd.entry.getString("ToAcc");
            Calendar fromDte = cmd.entry.getCalendar("FrmDte");
            Calendar toDte = cmd.entry.getCalendar("ToDte");
            cmd.out.map.texts.put(ACC.FROMACC, fromAcc);
            cmd.out.map.texts.put(ACC.TOACC, toAcc);
            cmd.out.map.calendars.put(ACC.FROMDATE, fromDte);
            cmd.out.map.calendars.put(ACC.TODATE, toDte);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFER0TXN.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "PFER0TXN.title";
    }
}
