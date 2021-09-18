/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.O;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.KM.K.BSKL1SUP;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSOR3PIVD extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmSup", "BSOR3PIVD.FrmSup");
        super.setFieldHelpMessage("BSOR3PIVD.FrmSup.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSKL1SUP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToSup", "BSOR3PIVD.ToSup");
        super.setFieldHelpMessage("BSOR3PIVD.ToSup.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSKL1SUP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSOR3PIVD.Active");
        super.setFieldHelpMessage("BSOR3PIVD.Active.help");
        super.addBooleanField("Inactive", "BSOR3PIVD.Inactive");
        super.setFieldHelpMessage("BSOR3PIVD.Inactive.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSOJ3PIVD.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FrmSup = cmd.entry.getString("FrmSup");
            String ToSup = cmd.entry.getString("ToSup");
            boolean active = cmd.entry.getBoolean("Active");
            boolean inactive = cmd.entry.getBoolean("Inactive");
            cmd.out.map.texts.put(MAP.BSSUP.FROM, FrmSup);
            cmd.out.map.texts.put(MAP.BSSUP.TO, ToSup);
            cmd.out.map.booleans.put(MAP.BSSUP.ACTIVE, new Boolean(active));
            cmd.out.map.booleans.put(MAP.BSSUP.INACTIVE, new Boolean(inactive));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSOR3PIVD.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSOR3PIVD.title";
    }
}
