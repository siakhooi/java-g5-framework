/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.ut.C;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import java.io.File;
import javax.swing.Icon;

/**
 * //Empty Directory Remover
 * @author Ng Siak Hooi
 */
public class UTCE0 extends EntryForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return UTCH0.class;
    }

    @Override
    protected void buildFieldList() {
        super.addDirectoryField("Dir", "UTCE0.Dir");
        super.setFieldHelpMessage("UTCE0.Dir.help");
        super.setFieldMandatory(true);
        super.setFieldTextColumn(30);
    }

    @Override
    protected void buildButtonsList() {
        String name = "", label = "";
        name = "OK";
        label = "UTCE0.button.ok";
        label = cmd.lang.getString(label);
        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, true);
        name = "CLOSE";
        label = "UTCE0.button.close";
        label = cmd.lang.getString(label);
        icon = cmd.icon.getCloseIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, false);
    }

    @Override
    public void verifyField(String fieldName) throws Exception {
        if (fieldName.equals("Dir")) {
            String s = cmd.entry.getString("Dir");
            File f = new File(s);
            if (!f.exists()) {
                super.throwVerifyError("UTCE0.error.PathNotExist");
            }
            if (!f.isDirectory()) {
                super.throwVerifyError("UTCE0.error.PathNotDirectory");
            }
        }
        super.verifyField(fieldName);
    }

    @Override
    public void buttonClick(String name) {
        if ("OK".equals(name)) {
            try {
                String s = cmd.entry.getString("Dir");
                cmd.out.stringValue = s;
                UserFormInterface f = cmd.form.create(UTCP0.class);
                cmd.form.execute(f);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("UTCE0.error", ex);
            }
        } else {
            cmd.form.closeForm();
        }
    }

    @Override
    public void initValue() {
    }

    @Override
    public String getFormTitle() {
        return "UTCE0.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
