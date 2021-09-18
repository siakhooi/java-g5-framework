/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.C;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSCR2CUS extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmCus", "BSCR2CUS.FrmCus");
        super.setFieldHelpMessage("BSCR2CUS.FrmCus.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToCus", "BSCR2CUS.ToCus");
        super.setFieldHelpMessage("BSCR2CUS.ToCus.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSCR2CUS.Active");
        super.setFieldHelpMessage("BSCR2CUS.Active.help");
        super.addBooleanField("Inactive", "BSCR2CUS.Inactive");
        super.setFieldHelpMessage("BSCR2CUS.Inactive.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSCJ2CUS.class;
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
            cmd.log.sysSevere("BSCR2CUS.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSCR2CUS.title";
    }
}
