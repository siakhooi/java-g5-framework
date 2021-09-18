/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.HJ.H;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.data.QttEnum;
import com.mysoftwarehouse.bs.db.QTT.QTT;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSHR0QTT extends ReportEntryForm {

    @Override
    public boolean verify(String fieldName) {
        String Cmp = GET.Cmp(this);
        if ("QttNo".equals(fieldName)) {
            try {
                return QTT.hasQtt(this, Cmp, cmd.entry.getString(fieldName));
            } catch (SQLException ex) {
                cmd.log.severe("BSHR0QTT.error", ex);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSHR0QTT.error", ex);
            }
        }
        return super.verify(fieldName);
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("QttNo", "BSHR0QTT.QttNo");
        super.setFieldHelpMessage("BSHR0QTT.QttNo.help");
        super.setFieldSelectForm(BSHL1QTT.class);
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(20);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addRadioboxField("Format", "BSHR0QTT.Format");
        super.setFieldHelpMessage("BSHR0QTT.Format.help");
        for (QttEnum.Format i : QttEnum.Format.values()) {
            super.addOption(i.typ, i.name);
        }
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return BSHJ0QTT.class;
    }

    @Override
    public void onOutExit() {
        try {
            String QttNo = cmd.entry.getString("QttNo");
            String Format = cmd.entry.getString("Format");
            cmd.out.map.texts.put(MAP.BSQTT.FROM, QttNo);
            cmd.out.map.texts.put(MAP.BSCFG.QTTFORMAT, Format);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSHR0QTT.error", ex);
        }
    }

    @Override
    public void initValue() {
        try {
            cmd.entry.setValue("Format", 
                    cmd.global.texts.get(MAP.BSCFG.QTTFORMAT));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSHR0QTT.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "BSHR0QTT.title";
    }
}
