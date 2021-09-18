/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.N;

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
public class BSNR3POR extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmSup", "BSNR3POR.FrmSup");
        super.setFieldHelpMessage("BSNR3POR.FrmSup.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSKL1SUP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToSup", "BSNR3POR.ToSup");
        super.setFieldHelpMessage("BSNR3POR.ToSup.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSKL1SUP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSNR3POR.Active");
        super.setFieldHelpMessage("BSNR3POR.Active.help");
        super.addBooleanField("Inactive", "BSNR3POR.Inactive");
        super.setFieldHelpMessage("BSNR3POR.Inactive.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSNJ3POR.class;
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
            cmd.log.sysSevere("BSNR3POR.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSNR3POR.title";
    }
}
