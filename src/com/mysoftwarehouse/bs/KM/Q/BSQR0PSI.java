/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license SpcInsts.
 */
package com.mysoftwarehouse.bs.KM.Q;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSQR0PSI extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmSpcInst", "BSQR0PSI.FrmSpcInst");
        super.setFieldHelpMessage("BSQR0PSI.FrmSpcInst.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSQL1PSI.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToSpcInst", "BSQR0PSI.ToSpcInst");
        super.setFieldHelpMessage("BSQR0PSI.ToSpcInst.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSQL1PSI.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSQR0PSI.Active");
        super.setFieldHelpMessage("BSQR0PSI.Active.help");
        super.addBooleanField("Inactive", "BSQR0PSI.Inactive");
        super.setFieldHelpMessage("BSQR0PSI.Inactive.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSQJ0PSI.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FrmSpcInst = cmd.entry.getString("FrmSpcInst");
            String ToSpcInst = cmd.entry.getString("ToSpcInst");
            boolean active = cmd.entry.getBoolean("Active");
            boolean inactive = cmd.entry.getBoolean("Inactive");
            cmd.out.map.texts.put(MAP.BSPSI.FROM, FrmSpcInst);
            cmd.out.map.texts.put(MAP.BSPSI.TO, ToSpcInst);
            cmd.out.map.booleans.put(MAP.BSPSI.ACTIVE, new Boolean(active));
            cmd.out.map.booleans.put(MAP.BSPSI.INACTIVE, new Boolean(inactive));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSQR0PSI.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSQR0PSI.title";
    }
}
