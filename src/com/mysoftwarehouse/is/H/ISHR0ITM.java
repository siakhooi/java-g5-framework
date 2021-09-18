/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.H;

import com.gqrsoft.g5.developer.form.ReportEntryForm;
import com.gqrsoft.g5.developer.form.ReportExecutionForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.is.C.ISCL1ITM;
import com.mysoftwarehouse.is.data.MAP;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISHR0ITM extends ReportEntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmItm", "ISHR0ITM.FrmItm");
        super.setFieldHelpMessage("ISHR0ITM.FrmItm.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISCL1ITM.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTextField("ToItm", "ISHR0ITM.ToItm");
        super.setFieldHelpMessage("ISHR0ITM.ToItm.help");
        super.setFieldMandatory(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(ISCL1ITM.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);
    }

    @Override
    public Class<? extends ReportExecutionForm> getReportForm() {
        return ISHJ0ITM.class;
    }

    @Override
    public void onOutExit() {
        try {
            String FromItm = cmd.entry.getString("FrmItm");
            String ToItm = cmd.entry.getString("ToItm");
            cmd.out.map.texts.put(MAP.ISITM.FROM, FromItm);
            cmd.out.map.texts.put(MAP.ISITM.TO, ToItm);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("ISHR0ITM.error", ex);
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "ISHR0ITM.title";
    }
}
