/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.G;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.pfa.C.PFCL0ACC;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFGE0ACC extends EntryForm {

    @Override
    protected void buildFieldList() {
        super.addTextField("FrmAcc", "PFGE0ACC.FrmAcc");
        super.setFieldHelpMessage("PFGE0ACC.FrmAcc.help");
        super.setFieldEditable(false);
        super.setFieldMaximumLength(20);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);

        super.addTextField("ToAcc", "PFGE0ACC.ToAcc");
        super.setFieldHelpMessage("PFGE0ACC.ToAcc.help");
        super.setFieldMandatory(true);
        super.setFieldMaximumLength(20);
        super.setFieldSelectForm(PFCL0ACC.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(20);
    }

    @Override
    protected void buildButtonsList() {
        String name = "", label = "";
        name = "OK";
        label = "PFGE0ACC.button.ok";
        label = cmd.lang.getString(label);
        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, true);
        name = "CLOSE";
        label = "PFGE0ACC.button.close";
        label = cmd.lang.getString(label);
        icon = cmd.icon.getCloseIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, false);
    }

    @Override
    public void buttonClick(String name) {
        if ("OK".equals(name)) {
            try {
                String FrmAcc = cmd.entry.getString("FrmAcc");
                String ToAcc = cmd.entry.getString("ToAcc");
                cmd.out.map.texts.put(ACC.FROMACC, FrmAcc);
                cmd.out.map.texts.put(ACC.TOACC, ToAcc);
                UserFormInterface f = cmd.form.create(PFGP0ACC.class);
                cmd.form.execute(f);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("PFGE0ACC.error", ex);
            }
        }
        cmd.form.closeForm();
    }

    @Override
    public void initValue() {
        try {
            cmd.entry.setValue("FrmAcc", 
                    cmd.in.map.texts.get(ACC.FROMACC));
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFGE0ACC.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "PFGE0ACC.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
