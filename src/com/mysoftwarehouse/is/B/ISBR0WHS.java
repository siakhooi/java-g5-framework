/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.B;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.data.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISBR0WHS extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmWhs", "ISBR0WHS.FrmWhs");
        super.setFieldHelpMessage("ISBR0WHS.FrmWhs.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISBL1WHS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToWhs", "ISBR0WHS.ToWhs");
        super.setFieldHelpMessage("ISBR0WHS.ToWhs.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISBL1WHS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return ISBJ0WHS.class;
    }

    @Override
    public void onOutExit() {
        try {
            String fromWhs = cmd.entry.getString("FrmWhs");
            String toWhs = cmd.entry.getString("ToWhs");
            cmd.out.map.texts.put(MAP.ISWHS.FROM, fromWhs);
            cmd.out.map.texts.put(MAP.ISWHS.TO, toWhs);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("ISBR0WHS.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "ISBR0WHS.title";
    }
}
