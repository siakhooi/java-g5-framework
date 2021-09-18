/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.E;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSER0SA extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmAdj", "BSER0SA.FrmAdj");
        super.setFieldHelpMessage("BSER0SA.FrmAdj.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSEL2SA.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToAdj", "BSER0SA.ToAdj");
        super.setFieldHelpMessage("BSER0SA.ToAdj.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSEL2SA.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSER0SA.Active");
        super.setFieldHelpMessage("BSER0SA.Active.help");
        
        super.addBooleanField("Inactive", "BSER0SA.Inactive");
        super.setFieldHelpMessage("BSER0SA.Inactive.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSEJ0SA.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromAdj = cmd.entry.getString("FrmAdj");
            String ToAdj = cmd.entry.getString("ToAdj");
            boolean active = cmd.entry.getBoolean("Active");
            boolean inactive = cmd.entry.getBoolean("Inactive");
            cmd.out.map.texts.put(MAP.BSSA.FROM, FromAdj);
            cmd.out.map.texts.put(MAP.BSSA.TO, ToAdj);
            cmd.out.map.booleans.put(MAP.BSSA.ACTIVE, new Boolean(active));
            cmd.out.map.booleans.put(MAP.BSSA.INACTIVE, new Boolean(inactive));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSER0SA.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSER0SA.title";
    }
}
