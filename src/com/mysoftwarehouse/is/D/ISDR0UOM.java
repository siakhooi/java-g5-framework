/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.D;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.data.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISDR0UOM extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmUom", "ISDR0UOM.FrmUom");
        super.setFieldHelpMessage("ISDR0UOM.FrmUom.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISDL1UOM.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToUom", "ISDR0UOM.ToUom");
        super.setFieldHelpMessage("ISDR0UOM.ToUom.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISDL1UOM.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return ISDJ0UOM.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromUom = cmd.entry.getString("FrmUom");
            String ToUom = cmd.entry.getString("ToUom");
            cmd.out.map.texts.put(MAP.ISUOM.FROM, FromUom);
            cmd.out.map.texts.put(MAP.ISUOM.TO, ToUom);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("ISDR0UOM.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "ISDR0UOM.title";
    }
}
