/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.I;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.CG.C.BSCL1CUS;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSIR3INV extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmCus", "BSIR3INV.FrmCus");
        super.setFieldHelpMessage("BSIR3INV.FrmCus.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToCus", "BSIR3INV.ToCus");
        super.setFieldHelpMessage("BSIR3INV.ToCus.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSIR3INV.Active");
        super.setFieldHelpMessage("BSIR3INV.Active.help");
        super.addBooleanField("Inactive", "BSIR3INV.Inactive");
        super.setFieldHelpMessage("BSIR3INV.Inactive.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSIJ3INV.class;
    }

    @Override
    public void onOutExit() {
        try {
            String fromCus = cmd.entry.getString("FrmCus");
            String toCus = cmd.entry.getString("ToCus");
            boolean active = cmd.entry.getBoolean("Active");
            boolean inactive = cmd.entry.getBoolean("Inactive");
            cmd.out.map.texts.put(MAP.BSCUS.FROM, fromCus);
            cmd.out.map.texts.put(MAP.BSCUS.TO, toCus);
            cmd.out.map.booleans.put(MAP.BSCUS.ACTIVE, new Boolean(active));
            cmd.out.map.booleans.put(MAP.BSCUS.INACTIVE, new Boolean(inactive));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSIR3INV.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSIR3INV.title";
    }
}
