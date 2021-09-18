/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.E;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.data.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISER0LOC extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmLoc", "ISER0LOC.FrmLoc");
        super.setFieldHelpMessage("ISER0LOC.FrmLoc.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISEL1LOC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToLoc", "ISER0LOC.ToLoc");
        super.setFieldHelpMessage("ISER0LOC.ToLoc.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISEL1LOC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return ISEJ0LOC.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromLoc = cmd.entry.getString("FrmLoc");
            String ToLoc = cmd.entry.getString("ToLoc");
            cmd.out.map.texts.put(MAP.ISLOC.FROM, FromLoc);
            cmd.out.map.texts.put(MAP.ISLOC.TO, ToLoc);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("ISER0LOC.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "ISER0LOC.title";
    }
}
