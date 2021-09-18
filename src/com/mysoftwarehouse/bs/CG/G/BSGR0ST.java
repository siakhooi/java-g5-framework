/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.CG.G;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSGR0ST extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmTrm", "BSGR0ST.FrmTrm");
        super.setFieldHelpMessage("BSGR0ST.FrmTrm.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSGL4ST.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToTrm", "BSGR0ST.ToTrm");
        super.setFieldHelpMessage("BSGR0ST.ToTrm.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSGL4ST.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addBooleanField("Active", "BSGR0ST.Active");
        super.setFieldHelpMessage("BSGR0ST.Active.help");
        super.addBooleanField("Inactive", "BSGR0ST.Inactive");
        super.setFieldHelpMessage("BSGR0ST.Inactive.help");
        super.addBooleanField("ForQtt", "BSGR0ST.ForQtt");
        super.setFieldHelpMessage("BSGR0ST.ForQtt.help");
        super.addBooleanField("ForInv", "BSGR0ST.ForInv");
        super.setFieldHelpMessage("BSGR0ST.ForInv.help");
        super.addBooleanField("ForRcp", "BSGR0ST.ForRcp");
        super.setFieldHelpMessage("BSGR0ST.ForRcp.help");
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSGJ0ST.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FrmTrm = cmd.entry.getString("FrmTrm");
            String ToTrm = cmd.entry.getString("ToTrm");
            boolean active = cmd.entry.getBoolean("Active");
            boolean inactive = cmd.entry.getBoolean("Inactive");
            boolean ForQtt= cmd.entry.getBoolean("ForQtt");
            boolean ForInv= cmd.entry.getBoolean("ForInv");
            boolean ForRcp= cmd.entry.getBoolean("ForRcp");
            cmd.out.map.texts.put(MAP.BSST.FROM, FrmTrm);
            cmd.out.map.texts.put(MAP.BSST.TO, ToTrm);
            cmd.out.map.booleans.put(MAP.BSST.ACTIVE, new Boolean(active));
            cmd.out.map.booleans.put(MAP.BSST.INACTIVE, new Boolean(inactive));
            cmd.out.map.booleans.put(MAP.BSST.FORQTT, new Boolean(ForQtt));
            cmd.out.map.booleans.put(MAP.BSST.FORINV, new Boolean(ForInv));
            cmd.out.map.booleans.put(MAP.BSST.FORRCP, new Boolean(ForRcp));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSGR0ST.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "BSGR0ST.title";
    }
}
