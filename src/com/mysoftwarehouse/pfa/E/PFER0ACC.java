/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.E;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.pfa.C.PFCL0ACC;
import com.mysoftwarehouse.pfa.db.ACC.ACC;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFER0ACC extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmAcc", "PFER0ACC.FrmAcc");
        super.setFieldHelpMessage("PFER0ACC.FrmAcc.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(PFCL0ACC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTextField("ToAcc", "PFER0ACC.ToAcc");
        super.setFieldHelpMessage("PFER0ACC.ToAcc.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(PFCL0ACC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return PFEJ0ACC.class;
    }

    @Override
    public void onOutExit() {
        try {
            String fromAcc = cmd.entry.getString("FrmAcc");
            String toAcc = cmd.entry.getString("ToAcc");
            cmd.out.map.texts.put(ACC.FROMACC, fromAcc);
            cmd.out.map.texts.put(ACC.TOACC, toAcc);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFER0ACC.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "PFER0ACC.title";
    }
}
