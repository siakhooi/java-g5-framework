/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.CFG.CFGI;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBE0CFGI extends EntryForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return BSBI0CFGI.class;
    }

    @Override
    protected void buildFieldList() {
        super.addTextField("Cmp", "BSCMP.Cmp");
        super.setFieldHelpMessage("BSCMP.Cmp.help");
        super.setFieldMandatory(true);
        super.setFieldEditable(false);
        super.setFieldMaximumLength(10);
        super.setFieldSelectForm(BSBL1CMP.class);
        super.setFieldTextCaseType(TextCaseType.UPPER);
        super.setFieldTextColumn(10);

        super.addTab("letterhead", "BSBE0CFGI.tab.letterhead");
        super.addImageField("LetterHead", "BSCFGI.LetterHead");
        super.setFieldHelpMessage("BSCFGI.LetterHead.help");

    }

    @Override
    protected void buildButtonsList() {
        String name = "", label = "";
        name = "OK";
        label = "BSBE0CFGI.button.ok";
        label = cmd.lang.getString(label);
        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, true);
        name = "CLEAR";
        label = "BSBE0CFGI.button.clear";
        label = cmd.lang.getString(label);
        icon = cmd.icon.getDeleteIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, false);
        name = "CLOSE";
        label = "BSBE0CFGI.button.close";
        label = cmd.lang.getString(label);
        icon = cmd.icon.getCloseIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, false);
    }

    @Override
    public void buttonClick(String name) {
        if ("CLEAR".equals(name)) {
            try {
                String cmp = GET.Cmp(this);
                cmd.db.begin();
                CFGI.update(this, cmp, null);
                cmd.db.commit();
                cmd.local.imageValue = null;
                cmd.form.broadcastSignal(CfgiSignal.REFRESH, true);
            } catch (SQLException ex) {
                cmd.log.severe("BSBE0CFGI.error", ex);
            }
        }
        if ("OK".equals(name)) {
            try {
                String cmp = GET.Cmp(this);
                String LetterHead = cmd.entry.getString("LetterHead");

                if (!cmd.data.isNull(LetterHead)) {
                    cmd.db.begin();
                    InputStream is = new FileInputStream(LetterHead);
                    byte[] img = cmd.data.inputStream2ByteArray(is);
                    CFGI.update(this, cmp, img);
                    cmd.db.commit();
                    initValue();
                }
            } catch (IOException ex) {
                cmd.log.severe("BSBE0CFGI.error", ex);
            } catch (SQLException ex) {
                try {
                    cmd.db.rollback();
                } catch (SQLException ex2) {
                }
                cmd.log.severe("BSBE0CFGI.error", ex);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSBE0CFGI.error", ex);
            }
        }
        if ("CLOSE".equals(name)) {
            cmd.form.closeForm();
        }
    }

    @Override
    public void initValue() {
        try {
            String cmp = GET.Cmp(this);
            cmd.entry.setValue("Cmp", cmp);
            InputStream is = CFGI.getLetterHead(this, cmp);
            if (is != null) {
                cmd.local.imageValue = ImageIO.read(is);
                cmd.form.broadcastSignal(CfgiSignal.REFRESH, true);
                cmd.frame.pack();
            }
        } catch (IOException ex) {
            cmd.log.severe("BSBE0CFGI.error", ex);
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.severe("BSBE0CFGI.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "BSBE0CFGI.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
