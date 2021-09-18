/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.KM.K;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSKR2SUP extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmSup", "BSKR2SUP.FrmSup");
        super.setFieldHelpMessage("BSKR2SUP.FrmSup.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSKL1SUP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToSup", "BSKR2SUP.ToSup");
        super.setFieldHelpMessage("BSKR2SUP.ToSup.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSKL1SUP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSKR2SUP.Active");
        super.setFieldHelpMessage("BSKR2SUP.Active.help");
        super.addBooleanField("Inactive", "BSKR2SUP.Inactive");
        super.setFieldHelpMessage("BSKR2SUP.Inactive.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSKJ2SUP.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromSup = cmd.entry.getString("FrmSup");
            String ToSup = cmd.entry.getString("ToSup");
            boolean active = cmd.entry.getBoolean("Active");
            boolean inactive = cmd.entry.getBoolean("Inactive");
            cmd.out.map.texts.put(MAP.BSSUP.FROM, FromSup);
            cmd.out.map.texts.put(MAP.BSSUP.TO, ToSup);
            cmd.out.map.booleans.put(MAP.BSSUP.ACTIVE, new Boolean(active));
            cmd.out.map.booleans.put(MAP.BSSUP.INACTIVE, new Boolean(inactive));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSKR2SUP.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSKR2SUP.title";
    }
}
