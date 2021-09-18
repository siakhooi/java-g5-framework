/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.G;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.C.ISCL1ITM;
import com.mysoftwarehouse.is.data.MAP;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISGR1TXN extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmItm", "ISGR1TXN.FrmItm");
        super.setFieldHelpMessage("ISGR1TXN.FrmItm.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISCL1ITM.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToItm", "ISGR1TXN.ToItm");
        super.setFieldHelpMessage("ISGR1TXN.ToItm.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISCL1ITM.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addCalendarField("FrmDte", "ISGR1TXN.FrmDte");
        super.setFieldHelpMessage("ISGR1TXN.FrmDte.help");
        super.setFieldMandatory(false);
        super.setFieldFormat(true, false);

        super.addCalendarField("ToDte", "ISGR1TXN.ToDte");
        super.setFieldHelpMessage("ISGR1TXN.ToDte.help");
        super.setFieldMandatory(false);
        super.setFieldFormat(true, false);

    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return ISGJ1TXN.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromItm = cmd.entry.getString("FrmItm");
            String ToItm = cmd.entry.getString("ToItm");
            Calendar fromDte = cmd.entry.getCalendar("FrmDte");
            Calendar toDte = cmd.entry.getCalendar("ToDte");
            cmd.out.map.texts.put(MAP.ISTXN.FROM, FromItm);
            cmd.out.map.texts.put(MAP.ISTXN.TO, ToItm);
            cmd.out.map.calendars.put(MAP.ISTXN.FROMDATE, fromDte);
            cmd.out.map.calendars.put(MAP.ISTXN.TODATE, toDte);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("ISGR1TXN.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "ISGR1TXN.title";
    }
}
