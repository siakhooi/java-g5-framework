/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.H;

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
public class BSHR3QTTD extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmCus", "BSHR3QTTD.FrmCus");
        super.setFieldHelpMessage("BSHR3QTTD.FrmCus.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToCus", "BSHR3QTTD.ToCus");
        super.setFieldHelpMessage("BSHR3QTTD.ToCus.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSHR3QTTD.Active");
        super.setFieldHelpMessage("BSHR3QTTD.Active.help");
        super.addBooleanField("Inactive", "BSHR3QTTD.Inactive");
        super.setFieldHelpMessage("BSHR3QTTD.Inactive.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSHJ3QTTD.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FrmCus = cmd.entry.getString("FrmCus");
            String toCus = cmd.entry.getString("ToCus");
            boolean active = cmd.entry.getBoolean("Active");
            boolean inactive = cmd.entry.getBoolean("Inactive");
            cmd.out.map.texts.put(MAP.BSCUS.FROM, FrmCus);
            cmd.out.map.texts.put(MAP.BSCUS.TO, toCus);
            cmd.out.map.booleans.put(MAP.BSCUS.ACTIVE, new Boolean(active));
            cmd.out.map.booleans.put(MAP.BSCUS.INACTIVE, new Boolean(inactive));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSHR3QTTD.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSHR3QTTD.title";
    }
}
