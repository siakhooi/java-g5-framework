/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBR0CMP extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmCmp", "BSBR0CMP.FrmCmp");
        super.setFieldHelpMessage("BSBR0CMP.FrmCmp.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSBL1CMP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToCmp", "BSBR0CMP.ToCmp");
        super.setFieldHelpMessage("BSBR0CMP.ToCmp.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSBL1CMP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSBJ0CMP.class;
    }

    @Override
    public void onOutExit() {
        try {
            String fromCmp = cmd.entry.getString("FrmCmp");
            String toCmp = cmd.entry.getString("ToCmp");
            cmd.out.map.texts.put(MAP.BSCMP.FROM, fromCmp);
            cmd.out.map.texts.put(MAP.BSCMP.TO, toCmp);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSBR0CMP.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSBR0CMP.title";
    }
}
