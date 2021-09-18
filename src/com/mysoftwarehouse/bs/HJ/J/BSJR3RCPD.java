/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.J;

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
public class BSJR3RCPD extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmCus", "BSJR3RCPD.FrmCus");
        super.setFieldHelpMessage("BSJR3RCPD.FrmCus.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToCus", "BSJR3RCPD.ToCus");
        super.setFieldHelpMessage("BSJR3RCPD.ToCus.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSJR3RCPD.Active");
        super.setFieldHelpMessage("BSJR3RCPD.Active.help");
        super.addBooleanField("Inactive", "BSJR3RCPD.Inactive");
        super.setFieldHelpMessage("BSJR3RCPD.Inactive.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSJJ3RCPD.class;
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
            cmd.log.sysSevere("BSJR3RCPD.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSJR3RCPD.title";
    }
}
