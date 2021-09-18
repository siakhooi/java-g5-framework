/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
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
public class BSQR0PSID extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmSpcInst", "BSQR0PSID.FrmSpcInst");
        super.setFieldHelpMessage("BSQR0PSID.FrmSpcInst.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSQL1PSI.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToSpcInst", "BSQR0PSID.ToSpcInst");
        super.setFieldHelpMessage("BSQR0PSID.ToSpcInst.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSQL1PSI.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSQR0PSID.Active");
        super.setFieldHelpMessage("BSQR0PSID.Active.help");
        super.addBooleanField("Inactive", "BSQR0PSID.Inactive");
        super.setFieldHelpMessage("BSQR0PSID.Inactive.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSQJ0PSID.class;
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
            cmd.log.sysSevere("BSQR0PSID.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSQR0PSID.title";
    }
}
