/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.NO.N;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.PorEnum;
import com.mysoftwarehouse.bs.db.POR.POR;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSNR0POR extends ReportEntryForm {

    @Override
    public boolean verify(String fieldName) {
        String Cmp = GET.Cmp(this);
        if ("PorNo".equals(fieldName)) {
            try {
                return POR.hasPor(this, Cmp, cmd.entry.getString(fieldName));
            } catch (SQLException ex) {
                cmd.log.severe("BSNR0POR.error", ex);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSNR0POR.error", ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("PorNo", "BSNR0POR.PorNo");
        super.setFieldHelpMessage("BSNR0POR.PorNo.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(BSNL1POR.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addRadioboxField("Format", "BSNR0POR.Format");
        super.setFieldHelpMessage("BSNR0POR.Format.help");
        for (PorEnum.Format i : PorEnum.Format.values()) {
            super.addOption(i.typ, i.name);
        }
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSNJ0POR.class;
    }

    @Override
    public void onOutExit() {
        try {
            String PorNo = cmd.entry.getString("PorNo");
            String Format = cmd.entry.getString("Format");
            cmd.out.map.texts.put(MAP.BSPOR.FROM, PorNo);
            cmd.out.map.texts.put(MAP.BSCFG.PORFORMAT, Format);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSNR0POR.error", ex);
        }
    }

    @Override
    public void initValue() {
        try {
            cmd.entry.setValue("Format", cmd.global.texts.get(MAP.BSCFG.PORFORMAT));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSNR0POR.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "BSNR0POR.title";
    }
}
