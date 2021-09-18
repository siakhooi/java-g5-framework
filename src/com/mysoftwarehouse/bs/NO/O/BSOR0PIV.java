/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.O;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PivEnum;
import com.mysoftwarehouse.bs.db.PIV.PIV;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSOR0PIV extends ReportEntryForm {

    @Override
    public boolean verify(String fieldName) {
        String Cmp = GET.Cmp(this);
        if ("PivNo".equals(fieldName)) {
            try {
                return PIV.hasPiv(this, Cmp, cmd.entry.getString(fieldName));
            } catch (SQLException ex) {
                cmd.log.severe("BSOR0PIV.error", ex);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSOR0PIV.error", ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("PivNo", "BSOR0PIV.PivNo");
        super.setFieldHelpMessage("BSOR0PIV.PivNo.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(BSOL1PIV.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addRadioboxField("Format", "BSOR0PIV.Format");
        super.setFieldHelpMessage("BSOR0PIV.Format.help");
        for (PivEnum.Format i : PivEnum.Format.values()) {
            super.addOption(i.typ, i.name);
        }
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSOJ0PIV.class;
    }

    @Override
    public void onOutExit() {
        try {
            String PivNo = cmd.entry.getString("PivNo");
            String Format = cmd.entry.getString("Format");
            cmd.out.map.texts.put(MAP.BSPIV.FROM, PivNo);
            cmd.out.map.texts.put(MAP.BSCFG.PIVFORMAT, Format);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSOR0PIV.error", ex);
        }
    }

    @Override
    public void initValue() {
        try {
            cmd.entry.setValue("Format", cmd.global.texts.get(MAP.BSCFG.PIVFORMAT));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSOR0PIV.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "BSOR0PIV.title";
    }
}
