/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.form.EntryForm;
import com.gqrsoft.g5.developer.publicobject.EntryFieldWrongDataTypeException;
import com.gqrsoft.g5.developer.publicobject.FieldEnum.TextCaseType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;
import com.mysoftwarehouse.bs.db.CMP.CMPS;
import java.sql.SQLException;
import javax.swing.Icon;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBE0CMPS extends EntryForm {

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

        super.addTab("sign", "BSBE0CMPS.tab.sign");
        super.addTextField("QttSign1", "BSCMPS.QttSign1");
        super.setFieldHelpMessage("BSCMPS.QttSign1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("QttSign2", "BSCMPS.QttSign2");
        super.setFieldHelpMessage("BSCMPS.QttSign2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("InvSign1", "BSCMPS.InvSign1");
        super.setFieldHelpMessage("BSCMPS.InvSign1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("InvSign2", "BSCMPS.InvSign2");
        super.setFieldHelpMessage("BSCMPS.InvSign2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("RcpSign1", "BSCMPS.RcpSign1");
        super.setFieldHelpMessage("BSCMPS.RcpSign1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("RcpSign2", "BSCMPS.RcpSign2");
        super.setFieldHelpMessage("BSCMPS.RcpSign2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("PorSign1", "BSCMPS.PorSign1");
        super.setFieldHelpMessage("BSCMPS.PorSign1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("PorSign2", "BSCMPS.PorSign2");
        super.setFieldHelpMessage("BSCMPS.PorSign2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("PivSign1", "BSCMPS.PivSign1");
        super.setFieldHelpMessage("BSCMPS.PivSign1.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);

        super.addTextField("PivSign2", "BSCMPS.PivSign2");
        super.setFieldHelpMessage("BSCMPS.PivSign2.help");
        super.setFieldMaximumLength(60);
        super.setFieldTextColumn(60);
    }

    @Override
    protected void buildButtonsList() {
        String name = "", label = "";
        name = "OK";
        label = "BSBE0CMPS.button.ok";
        label = cmd.lang.getString(label);
        Icon icon = cmd.icon.getOKIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, true);
        name = "CLOSE";
        label = "BSBE0CMPS.button.close";
        label = cmd.lang.getString(label);
        icon = cmd.icon.getCloseIcon(cmd.icon.getDefaultHeight());
        super.buttons.addI18nButton(name, label, icon, false);
    }

    @Override
    public void buttonClick(String name) {
        if ("OK".equals(name)) {
            try {
                String Cmp = GET.Cmp(this);
                String QttSign1 = cmd.entry.getString("QttSign1");
                String QttSign2 = cmd.entry.getString("QttSign2");
                String InvSign1 = cmd.entry.getString("InvSign1");
                String InvSign2 = cmd.entry.getString("InvSign2");
                String RcpSign1 = cmd.entry.getString("RcpSign1");
                String RcpSign2 = cmd.entry.getString("RcpSign2");
                String PorSign1 = cmd.entry.getString("PorSign1");
                String PorSign2 = cmd.entry.getString("PorSign2");
                String PivSign1 = cmd.entry.getString("PivSign1");
                String PivSign2 = cmd.entry.getString("PivSign2");

                cmd.db.begin();
                CMPS.update(this, Cmp,
                        QttSign1, QttSign2, InvSign1, InvSign2,
                        RcpSign1, RcpSign2, PorSign1, PorSign2,
                        PivSign1, PivSign2);
                cmd.db.commit();

                cmd.global.texts.put(MAP.BSCMPS.QTTSIGN1, QttSign1);
                cmd.global.texts.put(MAP.BSCMPS.QTTSIGN2, QttSign2);
                cmd.global.texts.put(MAP.BSCMPS.INVSIGN1, InvSign1);
                cmd.global.texts.put(MAP.BSCMPS.INVSIGN2, InvSign2);
                cmd.global.texts.put(MAP.BSCMPS.RCPSIGN1, RcpSign1);
                cmd.global.texts.put(MAP.BSCMPS.RCPSIGN2, RcpSign2);
                cmd.global.texts.put(MAP.BSCMPS.PORSIGN1, PorSign1);
                cmd.global.texts.put(MAP.BSCMPS.PORSIGN2, PorSign2);
                cmd.global.texts.put(MAP.BSCMPS.PIVSIGN1, PivSign1);
                cmd.global.texts.put(MAP.BSCMPS.PIVSIGN2, PivSign2);

            } catch (SQLException ex) {
                try {
                    cmd.db.rollback();
                } catch (SQLException ex2) {
                }
                cmd.log.severe("BSBE0CMPS.error", ex);
            } catch (EntryFieldWrongDataTypeException ex) {
                cmd.log.sysSevere("BSBE0CMPS.error", ex);
            }
        }
        cmd.form.closeForm();
    }

    @Override
    public void initValue() {
        try {
            String rsName = "BSBE0CMPS";
            String cmp = GET.Cmp(this);
            cmd.entry.setValue("Cmp", cmp);
            if (CMPS.select(this, rsName, cmp)) {

                cmd.entry.setValue("QttSign1", cmd.db.getString(rsName, "QttSign1"));
                cmd.entry.setValue("QttSign2", cmd.db.getString(rsName, "QttSign2"));
                cmd.entry.setValue("InvSign1", cmd.db.getString(rsName, "InvSign1"));
                cmd.entry.setValue("InvSign2", cmd.db.getString(rsName, "InvSign2"));
                cmd.entry.setValue("RcpSign1", cmd.db.getString(rsName, "RcpSign1"));
                cmd.entry.setValue("RcpSign2", cmd.db.getString(rsName, "RcpSign2"));
                cmd.entry.setValue("PorSign1", cmd.db.getString(rsName, "PorSign1"));
                cmd.entry.setValue("PorSign2", cmd.db.getString(rsName, "PorSign2"));
                cmd.entry.setValue("PivSign1", cmd.db.getString(rsName, "PivSign1"));
                cmd.entry.setValue("PivSign2", cmd.db.getString(rsName, "PivSign2"));
            }
        } catch (EntryFieldWrongDataTypeException ex) {
            cmd.log.sysSevere("BSBE0CMPS.error", ex);
        } catch (SQLException ex) {
            cmd.log.severe("BSBE0CMPS.error", ex);
        }
    }

    @Override
    public String getFormTitle() {
        return "BSBE0CMPS.title";
    }

    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
