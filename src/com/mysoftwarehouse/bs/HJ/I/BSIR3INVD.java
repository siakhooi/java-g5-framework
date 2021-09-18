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
public class BSIR3INVD extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmCus", "BSIR3INVD.FrmCus");
        super.setFieldHelpMessage("BSIR3INVD.FrmCus.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToCus", "BSIR3INVD.ToCus");
        super.setFieldHelpMessage("BSIR3INVD.ToCus.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSCL1CUS.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSIR3INVD.Active");
        super.setFieldHelpMessage("BSIR3INVD.Active.help");
        super.addBooleanField("Inactive", "BSIR3INVD.Inactive");
        super.setFieldHelpMessage("BSIR3INVD.Inactive.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSIJ3INVD.class;
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
            cmd.log.sysSevere("BSIR3INVD.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSIR3INVD.title";
    }
}
