/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.J;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.RcpEnum;
import com.mysoftwarehouse.bs.db.RCP.RCP;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSJR0RCP extends ReportEntryForm {

    @Override
    public boolean verify(String fieldName) {
        String Cmp = GET.Cmp(this);
        if ("RcpNo".equals(fieldName)) {
            try {
                return RCP.hasRcp(this, Cmp, cmd.entry.getString(fieldName));
            } catch (SQLException ex) {
                cmd.log.severe("BSJR0RCP.error", ex);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSJR0RCP.error", ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("RcpNo", "BSJR0RCP.RcpNo");
        super.setFieldHelpMessage("BSJR0RCP.RcpNo.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(BSJL1RCP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addRadioboxField("Format", "BSJR0RCP.Format");
        super.setFieldHelpMessage("BSJR0RCP.Format.help");
        for (RcpEnum.Format i : RcpEnum.Format.values()) {
            super.addOption(i.typ, i.name);
        }
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSJJ0RCP.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromRcp = cmd.entry.getString("RcpNo");
            String Format = cmd.entry.getString("Format");
            cmd.out.map.texts.put(MAP.BSRCP.FROM, FromRcp);
            cmd.out.map.texts.put(MAP.BSCFG.RCPFORMAT, Format);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSJR0RCP.error", ex);
        }
    }

    @Override
    public void initValue() {
        try {
            cmd.entry.setValue("Format", cmd.global.texts.get(MAP.BSCFG.RCPFORMAT));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSJR0RCP.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "BSJR0RCP.title";
    }
}
