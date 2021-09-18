/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.C;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.E.ISEL1LOC;
import com.mysoftwarehouse.is.data.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISCR1ITMB extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmLoc", "ISCR1ITMB.FrmLoc");
        super.setFieldHelpMessage("ISCR1ITMB.FrmLoc.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISEL1LOC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToLoc", "ISCR1ITMB.ToLoc");
        super.setFieldHelpMessage("ISCR1ITMB.ToLoc.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISEL1LOC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return ISCJ1ITMB.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromLoc = cmd.entry.getString("FrmLoc");
            String ToLoc = cmd.entry.getString("ToLoc");
            cmd.out.map.texts.put(MAP.ISLOC.FROM, FromLoc);
            cmd.out.map.texts.put(MAP.ISLOC.TO, ToLoc);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("ISCR1ITMB.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "ISCR1ITMB.title";
    }
}
