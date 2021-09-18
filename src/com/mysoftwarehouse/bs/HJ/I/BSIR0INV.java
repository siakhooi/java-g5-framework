/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.I;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.InvEnum;
import com.mysoftwarehouse.bs.db.INV.INV;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSIR0INV extends ReportEntryForm {

    @Override
    public boolean verify(String fieldName) {
        String Cmp = GET.Cmp(this);
        if ("InvNo".equals(fieldName)) {
            try {
                return INV.hasInv(this, Cmp, cmd.entry.getString(fieldName));
            } catch (SQLException ex) {
                cmd.log.severe("BSIR0INV.error", ex);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSIR0INV.error", ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("InvNo", "BSIR0INV.InvNo");
        super.setFieldHelpMessage("BSIR0INV.InvNo.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(BSIL1INV.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addRadioboxField("Format", "BSIR0INV.Format");
        super.setFieldHelpMessage("BSIR0INV.Format.help");
        for (InvEnum.Format i : InvEnum.Format.values()) {
            super.addOption(i.typ, i.name);
        }
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSIJ0INV.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromInv = cmd.entry.getString("InvNo");
            String Format = cmd.entry.getString("Format");
            cmd.out.map.texts.put(MAP.BSINV.FROM, FromInv);
            cmd.out.map.texts.put(MAP.BSCFG.INVFORMAT, Format);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSIR0INV.error", ex);
        }
    }

    @Override
    public void initValue() {
        try {
            cmd.entry.setValue("Format", cmd.global.texts.get(MAP.BSCFG.INVFORMAT));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSIR0INV.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "BSIR0INV.title";
    }
}
