/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.F;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSFR0SY extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmPayTyp", "BSFR0SY.FrmPayTyp");
        super.setFieldHelpMessage("BSFR0SY.FrmPayTyp.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSFL1SY.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToPayTyp", "BSFR0SY.ToPayTyp");
        super.setFieldHelpMessage("BSFR0SY.ToPayTyp.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSFL1SY.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSFJ0SY.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromPayTyp = cmd.entry.getString("FrmPayTyp");
            String ToPayTyp = cmd.entry.getString("ToPayTyp");
            cmd.out.map.texts.put(MAP.BSSY.FROM, FromPayTyp);
            cmd.out.map.texts.put(MAP.BSSY.TO, ToPayTyp);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSFR0SY.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSFR0SY.title";
    }
}
