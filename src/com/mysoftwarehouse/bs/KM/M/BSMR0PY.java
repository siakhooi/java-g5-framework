/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.M;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSMR0PY extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmPayTyp", "BSMR0PY.FrmPayTyp");
        super.setFieldHelpMessage("BSMR0PY.FrmPayTyp.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSML1PY.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToPayTyp", "BSMR0PY.ToPayTyp");
        super.setFieldHelpMessage("BSMR0PY.ToPayTyp.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSML1PY.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSMJ0PY.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromPayTyp = cmd.entry.getString("FrmPayTyp");
            String ToPayTyp = cmd.entry.getString("ToPayTyp");
            cmd.out.map.texts.put(MAP.BSPY.FROM, FromPayTyp);
            cmd.out.map.texts.put(MAP.BSPY.TO, ToPayTyp);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSMR0PY.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSMR0PY.title";
    }
}
