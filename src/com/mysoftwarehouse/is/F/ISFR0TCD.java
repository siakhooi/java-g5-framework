/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.F;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.data.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISFR0TCD extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmTcd", "ISFR0TCD.FrmTcd");
        super.setFieldHelpMessage("ISFR0TCD.FrmTcd.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISFL1TCD.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToTcd", "ISFR0TCD.ToTcd");
        super.setFieldHelpMessage("ISFR0TCD.ToTcd.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISFL1TCD.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return ISFJ0TCD.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromTcd = cmd.entry.getString("FrmTcd");
            String ToTcd = cmd.entry.getString("ToTcd");
            cmd.out.map.texts.put(MAP.ISTCD.FROM, FromTcd);
            cmd.out.map.texts.put(MAP.ISTCD.TO, ToTcd);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("ISFR0TCD.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "ISFR0TCD.title";
    }
}
