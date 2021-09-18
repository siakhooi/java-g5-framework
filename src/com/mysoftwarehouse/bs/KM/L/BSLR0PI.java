/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.L;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSLR0PI extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmItm", "BSLR0PI.FrmItm");
        super.setFieldHelpMessage("BSLR0PI.FrmItm.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSLL1PI.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToItm", "BSLR0PI.ToItm");
        super.setFieldHelpMessage("BSLR0PI.ToItm.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSLL1PI.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSLR0PI.Active");
        super.setFieldHelpMessage("BSLR0PI.Active.help");
        super.addBooleanField("Inactive", "BSLR0PI.Inactive");
        super.setFieldHelpMessage("BSLR0PI.Inactive.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSLJ0PI.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromItm = cmd.entry.getString("FrmItm");
            String ToItm = cmd.entry.getString("ToItm");
            boolean active = cmd.entry.getBoolean("Active");
            boolean inactive = cmd.entry.getBoolean("Inactive");
            cmd.out.map.texts.put(MAP.BSPI.FROM, FromItm);
            cmd.out.map.texts.put(MAP.BSPI.TO, ToItm);
            cmd.out.map.booleans.put(MAP.BSPI.ACTIVE, new Boolean(active));
            cmd.out.map.booleans.put(MAP.BSPI.INACTIVE, new Boolean(inactive));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSLR0PI.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSLR0PI.title";
    }
}
