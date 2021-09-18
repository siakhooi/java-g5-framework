/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.D;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.mysoftwarehouse.pfa.N.PFNH0TXNT;
import com.mysoftwarehouse.pfa.db.CFG.CFG;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFDE0TXNT extends EntryForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        if (cmd.global.booleans.get(CFG.PFCFG_SHOWHELP)) {
            return PFNH0TXNT.class;
        } else {
            return null;
        }
    }

    @Override
    protected void buildFieldList() {
        super.addOpenFileField("OpenImportFile", "PFDE0TXNT.OpenImportFile");
        super.setFieldHelpMessage("PFDE0TXNT.OpenImportFile.help");
        super.setFieldMandatory(true);

        super.addBooleanField("PurgeTempFirst", "PFDE0TXNT.PurgeTempFirst");
        super.setFieldHelpMessage("PFDE0TXNT.PurgeTempFirst.help");
    }

    @Override
    protected void buildButtonsList() {
        String name = "", label = "";
        name = "OK";
        label = "PFDE0TXNT.button.ok";
        label = cmd.lang.getString(label);
        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, true);
        name = "CLOSE";
        label = "PFDE0TXNT.button.close";
        label = cmd.lang.getString(label);
        icon = cmd.icon.getCloseIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, false);
    }

    @Override
    public void buttonClick(String name) {
        if ("OK".equals(name)) {
            try {
                String file = cmd.entry.getString("OpenImportFile");
                boolean purgeTemp = cmd.entry.getBoolean("PurgeTempFirst");
                cmd.out.stringValue = file;
                cmd.out.booleanValue = purgeTemp;
                UserFormInterface f = cmd.form.create(PFDP0TXNT.class);
                cmd.form.execute(f);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("PFDE0TXNT.error", ex);
            }
        }
        cmd.form.closeForm();
    }

    @Override
    public void initValue() {
        try {
            cmd.entry.setValue("PurgeTempFirst", true);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("PFDE0TXNT.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "PFDE0TXNT.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
