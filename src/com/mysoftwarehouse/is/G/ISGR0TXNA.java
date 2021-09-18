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
public class ISGR0TXNA extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmItm", "ISGR0TXNA.FrmItm");
        super.setFieldHelpMessage("ISGR0TXNA.FrmItm.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISCL1ITM.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToItm", "ISGR0TXNA.ToItm");
        super.setFieldHelpMessage("ISGR0TXNA.ToItm.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISCL1ITM.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addCalendarField("FrmDte", "ISGR0TXNA.FrmDte");
        super.setFieldHelpMessage("ISGR0TXNA.FrmDte.help");
        super.setFieldMandatory(false);
        super.setFieldFormat(true, false);

        super.addCalendarField("ToDte", "ISGR0TXNA.ToDte");
        super.setFieldHelpMessage("ISGR0TXNA.ToDte.help");
        super.setFieldMandatory(false);
        super.setFieldFormat(true, false);

    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return ISGJ0TXNA.class;
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
            cmd.log.sysSevere("ISGR0TXNA.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "ISGR0TXNA.title";
    }
}
